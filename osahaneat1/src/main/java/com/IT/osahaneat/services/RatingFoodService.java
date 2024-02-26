package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.RatingFoodRepository;
import com.IT.osahaneat.entity.*;
import com.IT.osahaneat.security.CustomJwtFilter;
import com.IT.osahaneat.services.imp.RatingFoodServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class RatingFoodService implements RatingFoodServiceImp {
    @Autowired
    RatingFoodRepository ratingFoodRepository;

    @Autowired
    CustomJwtFilter customJwtFilter;
    @Override
    public Boolean createRatingFood(HttpServletRequest request, int idFood, String content, double ratePoint) {
        Users user = customJwtFilter.getUserFromToken(request);
        Food food = new Food();
        food.setId(idFood);
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        if (user != null) {
            RatingFood ratingFood = new RatingFood(user,food,content,ratePoint,date);
            ratingFoodRepository.save(ratingFood);
            return true;
        }
        return false;
    }
}
