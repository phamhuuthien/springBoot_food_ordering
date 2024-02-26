package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.RatingFoodServiceImp;
import com.IT.osahaneat.services.imp.RatingRestaurantServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingFood")
public class RatingFoodController {
    @Autowired
    private RatingFoodServiceImp ratingFoodServiceImp;

    @PostMapping()
    public ResponseEntity<?> createRatingRestaurant(HttpServletRequest request, @RequestParam int idFood, @RequestParam String content, @RequestParam double ratePoint){
        ResponseData responseData = new ResponseData();
        responseData.setData(ratingFoodServiceImp.createRatingFood(request,idFood,content,ratePoint));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
