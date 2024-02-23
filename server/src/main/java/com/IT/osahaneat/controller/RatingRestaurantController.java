package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.RatingRestaurantServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratingRestaurant")
public class RatingRestaurantController {

    @Autowired
    private RatingRestaurantServiceImp ratingRestaurantServiceImp;

    @PostMapping()
    public ResponseEntity<?> createRatingRestaurant(HttpServletRequest request, @RequestParam int idRes,@RequestParam String content, @RequestParam double ratePoint){
        ResponseData responseData = new ResponseData();
        responseData.setData(ratingRestaurantServiceImp.createRatingRes(request,idRes,content,ratePoint));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
