package com.IT.osahaneat.services;

import com.IT.osahaneat.Keys.KeyOrderItem;
import com.IT.osahaneat.Responsitory.FoodRepository;
import com.IT.osahaneat.Responsitory.OrderItemRepository;
import com.IT.osahaneat.Responsitory.OrderRepository;
import com.IT.osahaneat.Responsitory.PromoRepository;
import com.IT.osahaneat.entity.*;
import com.IT.osahaneat.payload.Request.OrderDetailRequest;
import com.IT.osahaneat.payload.Request.OrderRequest;
import com.IT.osahaneat.security.CustomJwtFilter;
import com.IT.osahaneat.services.imp.OrderServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CustomJwtFilter customJwtFilter;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    PromoRepository promoRepository;

    @Override
    public Boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest) {
        double total = 0;
        try {
            Users user = customJwtFilter.getUserFromToken(request);
            user.setId(user.getId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());

            Promo promo = promoRepository.findByresId(orderRequest.getResId());

            Orders order = new Orders();
            order.setUser(user);
            order.setRestaurant(restaurant);
            order.setStatus("Pending");

            long millis=System.currentTimeMillis();
            Date date = new Date(millis);
            order.setCreateDate(date);

            orderRepository.save(order);

            List<OrderItem> orderItems = new ArrayList<>();
            for(OrderDetailRequest orderDetailRequest : orderRequest.getOrderDetailRequests()){

                Food food = foodRepository.findById(orderDetailRequest.getFoodId()).get();


                KeyOrderItem keyOrderItem = new KeyOrderItem(order.getId(),food.getId());
                OrderItem orderItem = new OrderItem();
                orderItem.setKeyOrderItem(keyOrderItem);
                orderItem.setQuantity(orderDetailRequest.getQuantity());

                total+= food.getPrice()*orderDetailRequest.getQuantity();

                orderItems.add(orderItem);
            }
            orderItemRepository.saveAll(orderItems);

            Orders orderUpdate = orderRepository.findById(order.getId()).get();

            if(promo!=null){
                total = (double) Math.round(total - (total*promo.getPercent())/100 * 10) / 10;
            }

            orderUpdate.setTotal(total);
            orderRepository.save(orderUpdate);

            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean confirmOrder(int id, String status) {
        Orders order = orderRepository.findById(id).get();
        if(order!=null){
            order.setStatus(status);
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
