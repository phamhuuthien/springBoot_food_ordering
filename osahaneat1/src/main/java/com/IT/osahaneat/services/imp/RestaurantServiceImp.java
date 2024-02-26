package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.dto.RestaurantDTO;
import com.IT.osahaneat.entity.Restaurant;
import com.IT.osahaneat.payload.Request.RestaurantUpdateRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    Restaurant InsertRestaurant(
             MultipartFile file,
             String title,
             String subtitle,
             String description,
             Boolean is_freeship,
             String address,
             String open_date
    );
    List<RestaurantDTO> getHomePageRestaurants();

    List<Restaurant> getAllRestaurants();
    RestaurantDTO getDetailRestaurant(int id);

    Boolean updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest);
}
