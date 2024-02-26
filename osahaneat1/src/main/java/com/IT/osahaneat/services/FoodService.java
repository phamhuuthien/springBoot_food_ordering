package com.IT.osahaneat.services;

import com.IT.osahaneat.Keys.KeyOrderItem;
import com.IT.osahaneat.Responsitory.*;
import com.IT.osahaneat.dto.FoodDTO;
import com.IT.osahaneat.entity.Category;
import com.IT.osahaneat.entity.Food;
import com.IT.osahaneat.entity.OrderItem;
import com.IT.osahaneat.entity.Orders;
import com.IT.osahaneat.payload.Request.FoodRequest;
import com.IT.osahaneat.services.imp.FileServiceImp;
import com.IT.osahaneat.services.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService implements FoodServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RatingFoodRepository ratingFoodRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Cacheable("getAllFood")
    @Override
    public List<FoodDTO> getAllFood() {
        List<Food> listFoods = foodRepository.findAll();
        List<FoodDTO> listFoodDTOs = new ArrayList<>();
        for(Food food: listFoods){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(food.getId());
            foodDTO.setTitle(food.getTitle());
            foodDTO.setImage(food.getImage());
            foodDTO.setTimeShip(food.getTimeShip());
            foodDTO.setIsFreeship(food.getIsFreeship());
            foodDTO.setPrice(food.getPrice());
            foodDTO.setCateName(food.getCategory().getNameCate());
            listFoodDTOs.add(foodDTO);
        }
        return listFoodDTOs;
    }

    @Override
    public boolean updateFood(int id, FoodRequest foodRequest) {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if(foodOptional.isPresent()){
            Food food = foodOptional.get();
            Optional.ofNullable(foodRequest.getTitle()).ifPresent(food::setTitle);
            Optional.ofNullable(foodRequest.getImage()).ifPresent(food::setImage);
            Optional.ofNullable(foodRequest.getTimeShip()).ifPresent(food::setTimeShip);
            Optional.ofNullable(foodRequest.getPrice()).ifPresent(food::setPrice);
            Optional.ofNullable(foodRequest.getIsFreeship()).ifPresent(food::setIsFreeship);
            Category category = categoryRepository.findCategoryByNameCate(foodRequest.getCateName());
            if(category!=null){
                food.setCategory(category);
            }
            foodRepository.save(food);
            return true;
        }
        return false;
    }

    @Override
    public boolean InsertFood(MultipartFile file, String title, String time_ship, boolean is_freeship, double price, int cate_id) {
        Boolean isInsertSuccess = false;
        try {
            Boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Food food = new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(time_ship);
                food.setIsFreeship(is_freeship);
                food.setPrice(price);

                Category category = new Category();
                category.setId(cate_id);

                food.setCategory(category);
                foodRepository.save(food);

                isInsertSuccess = true;
            }
        }catch (Exception e) {
            System.out.println("insert restaurant error " + e.getMessage());
        }
        return isInsertSuccess;
    }

    @Override
    public boolean deleteFood(int id) {
        if(foodRepository.existsById(id)){

            OrderItem orderItem = orderItemRepository.findOrderItemByFoodId(id);

            Orders order = orderRepository.findById(orderItem.getOrder().getId()).get();
            if(order.getStatus().equals("CANCELLED")){
                List<OrderItem> listOrderItem = orderItemRepository.findOrderItemsByOrderId(order.getId());

                orderItemRepository.deleteAll(listOrderItem);

                orderRepository.delete(order);

                ratingFoodRepository.delete(ratingFoodRepository.findByFoodId(id));

                foodRepository.deleteById(id);

                return true;
            }
        }
        return false;
    }
}
