package com.IT.osahaneat.services;

import com.IT.osahaneat.Keys.KeyMenuRestaurant;
import com.IT.osahaneat.Responsitory.MenuRestaurantRepository;
import com.IT.osahaneat.Responsitory.RestaurantRepository;
import com.IT.osahaneat.dto.CategoryDTO;
import com.IT.osahaneat.dto.MenuDTO;
import com.IT.osahaneat.dto.RestaurantDTO;
import com.IT.osahaneat.entity.*;
import com.IT.osahaneat.payload.Request.RestaurantUpdateRequest;
import com.IT.osahaneat.services.imp.FileServiceImp;
import com.IT.osahaneat.services.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public Restaurant InsertRestaurant(MultipartFile file, String title, String subtitle, String description, Boolean is_freeship, String address, String open_date) {
        Restaurant restaurant = new Restaurant();
        try {
            Boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setDescription(description);
                restaurant.setIsFreeship(is_freeship);
                restaurant.setAddress(address);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date openDate = simpleDateFormat.parse(open_date);
                restaurant.setOpenDate(openDate);

                restaurantRepository.save(restaurant);
            }
        }catch (Exception e) {
            System.out.println("insert restaurant error " + e.getMessage());
        }

        return restaurant;
    }

    @Cacheable("getHomePageRestaurant")
    @Override
    public List<RestaurantDTO> getHomePageRestaurants() {
        List<RestaurantDTO> listRestaurantsDTO = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
//        List<Restaurant> list= listData.getContent();
        for(Restaurant data : listData){
             RestaurantDTO restaurantDTO = new RestaurantDTO();
             restaurantDTO.setId(data.getId());
             restaurantDTO.setTitle(data.getTitle());
             restaurantDTO.setSubtitle(data.getSubtitle());
             restaurantDTO.setImage(data.getImage());
             restaurantDTO.setFreeShip(data.getIsFreeship());
             restaurantDTO.setAddress(data.getAddress());
             restaurantDTO.setOpenDate(data.getOpenDate());
             restaurantDTO.setRating(calculateRating(data.getListRatingRestaurants()));
             List<CategoryDTO> listCategoryDto = new ArrayList<>();
             for(MenuRestaurant menuRestaurant :data.getListMenuRestaurant()){
                 CategoryDTO categoryDTO = new CategoryDTO();
                 categoryDTO.setNameCate(menuRestaurant.getCategory().getNameCate());
                 listCategoryDto.add(categoryDTO);
             }
             restaurantDTO.setCategory(listCategoryDto);
             listRestaurantsDTO.add(restaurantDTO);
        }

        return listRestaurantsDTO;
    }

    @Cacheable("getAllRestaurant")
    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> listRestaurants = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);

        for(Restaurant data : listData){
            Restaurant restaurant = new Restaurant();
            restaurant.setId(data.getId());
            restaurant.setTitle(data.getTitle());
            restaurant.setSubtitle(data.getSubtitle());
            restaurant.setImage(data.getImage());
            restaurant.setAddress(data.getAddress());
            restaurant.setDescription(data.getDescription());
            restaurant.setIsFreeship(data.getIsFreeship());
            restaurant.setOpenDate(data.getOpenDate());
//            restaurant.setListMenuRestaurant(data.getListMenuRestaurant());
//            List<MenuRestaurant> listMenuRestaurants = new ArrayList<>();
//            MenuRestaurant menuRestaurantItem = new MenuRestaurant();
//            for(MenuRestaurant menuRestaurant : data.getListMenuRestaurant()){
//                    Category category = menuRestaurant.getCategory();
//                    category.setNameCate(menuRestaurant.getCategory().getNameCate());
//                    List<Food> listFood = new ArrayList<>();
//                    for(Food food : menuRestaurant.getCategory().getListFood()){
//                        Food foodItem = new Food();
//                        foodItem.setId(food.getId());
//                        foodItem.setImage(food.getImage());
//                        foodItem.setTitle(food.getTitle());
//                        foodItem.setIsFreeship(food.getIsFreeship());
//                        listFood.add(foodItem);
//                    }
//                    category.setListFood(listFood);
//                    menuRestaurantItem.setCategory(category);
//            }
//            listMenuRestaurants.add(menuRestaurantItem);
//            restaurant.setListMenuRestaurant(listMenuRestaurants);

//            restaurant.setListPromo(data.getListPromo());
//            restaurant.setListRatingRestaurants(data.getListRatingRestaurants());
//            restaurant.setListOrders(data.getListOrders());
            listRestaurants.add(restaurant);
        }
        return listRestaurants;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
//       nhận 1 id 1 restaurant co nhiều menuRestaurant . 1 menuRestaurant nhiều id cate nhưng có 1 res . 1 cate thì nhiều food
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if(restaurant.isPresent()){
            List<CategoryDTO> listCategoryDTOS = new ArrayList<>();

//             trong optional muốn lấy ra getter thì chúng ra chấm get trước
//             khi chấm get thì nó hủy kiểu optional sau đó chuyển sáng entity
            Restaurant data = restaurant.get();
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setRating(calculateRating(data.getListRatingRestaurants()));
            restaurantDTO.setFreeShip(data.getIsFreeship());
            restaurantDTO.setOpenDate(data.getOpenDate());
            restaurantDTO.setAddress(data.getAddress());

            for(MenuRestaurant menuRestaurant : data.getListMenuRestaurant()){
                CategoryDTO categoryDTO = new CategoryDTO();
                List<MenuDTO> listMenuDTOs = new ArrayList<>();
                categoryDTO.setNameCate(menuRestaurant.getCategory().getNameCate());

                for(Food food : menuRestaurant.getCategory().getListFood()){
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setImage(food.getImage());
                    menuDTO.setFreeship(food.getIsFreeship());
                    listMenuDTOs.add(menuDTO);
                }
                categoryDTO.setMenu(listMenuDTOs);
                listCategoryDTOS.add(categoryDTO);
            }
            restaurantDTO.setCategory(listCategoryDTOS);
        }
        return restaurantDTO;
    }

    @Override
    public Boolean updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest) {
        Optional<Restaurant> restaurantOptional =  restaurantRepository.findById(restaurantUpdateRequest.getId());
        if(restaurantOptional.isPresent()){
            Restaurant restaurant = restaurantOptional.get();
            Optional.ofNullable(restaurantUpdateRequest.getTitle()).ifPresent(restaurant::setTitle);
            Optional.ofNullable(restaurantUpdateRequest.getSubtitle()).ifPresent(restaurant::setSubtitle);
            Optional.ofNullable(restaurantUpdateRequest.getDescription()).ifPresent(restaurant::setDescription);

            Optional.ofNullable(restaurantUpdateRequest.getImage()).ifPresent(restaurant::setImage);

            Boolean isFreeship = restaurantUpdateRequest.isFreeship();
            if(isFreeship|| !isFreeship){
                restaurant.setIsFreeship(isFreeship);
            }

            Optional.ofNullable(restaurantUpdateRequest.getAddress()).ifPresent(restaurant::setAddress);
            Optional.ofNullable(restaurantUpdateRequest.getOpenDate()).ifPresent(restaurant::setOpenDate);


            restaurantRepository.save(restaurant);

            if(restaurantUpdateRequest.getCateId()!=null){
                List<MenuRestaurant> listMenuRestaurants = menuRestaurantRepository.findByResId(restaurantUpdateRequest.getId());
                if(listMenuRestaurants!=null){
                    menuRestaurantRepository.deleteAll(listMenuRestaurants);
                }
                for(int idCate : restaurantUpdateRequest.getCateId()){
                    MenuRestaurant menuRestaurant = new MenuRestaurant();
                    KeyMenuRestaurant keyMenuRestaurant = new KeyMenuRestaurant(idCate,restaurantUpdateRequest.getId());
                    menuRestaurant.setKeyMenuRestaurant(keyMenuRestaurant);
                    menuRestaurantRepository.save(menuRestaurant);
                }
            }
            return true;
        }
        return false;
    }

    private double calculateRating(List<RatingRestaurant> listRating){
        double totalPoint = 0;
        for(RatingRestaurant data : listRating){
            totalPoint += data.getRatePoint();
        }
        return totalPoint / listRating.size();
    }
}
