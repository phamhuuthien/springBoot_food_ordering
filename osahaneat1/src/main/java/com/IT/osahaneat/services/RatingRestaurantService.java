package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.RatingRestaurantRepository;
import com.IT.osahaneat.entity.RatingRestaurant;
import com.IT.osahaneat.entity.Restaurant;
import com.IT.osahaneat.entity.Users;
import com.IT.osahaneat.security.CustomJwtFilter;
import com.IT.osahaneat.services.imp.RatingRestaurantServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class RatingRestaurantService implements RatingRestaurantServiceImp {
    @Autowired
    RatingRestaurantRepository ratingRestaurantRepository;

    @Autowired
    CustomJwtFilter customJwtFilter;
    @Override
    public Boolean createRatingRes(HttpServletRequest request, int idRes, String content, double ratePoint) {

        Users user = customJwtFilter.getUserFromToken(request);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(idRes);
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        if (user != null) {
            RatingRestaurant ratingRestaurant = new RatingRestaurant(user,restaurant,content,ratePoint,date);
            ratingRestaurantRepository.save(ratingRestaurant);
            return true;
        }
        return false;
    }
}
