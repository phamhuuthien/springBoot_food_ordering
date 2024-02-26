package com.IT.osahaneat.controller;

import com.IT.osahaneat.entity.Restaurant;
import com.IT.osahaneat.payload.Request.RestaurantUpdateRequest;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.FileServiceImp;
import com.IT.osahaneat.services.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantServiceImp restaurantServiceImp;
    @CrossOrigin
    @GetMapping("/home")
    public ResponseEntity<?> getHomePageRestaurants(){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getHomePageRestaurants());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/home/{id}")
    public ResponseEntity<?> getDetailRestaurants(@PathVariable int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getDetailRestaurant(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllRestaurants(){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getAllRestaurants());
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> createRestaurant(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam String description,
            @RequestParam Boolean is_freeship,
            @RequestParam String address,
            @RequestParam String open_date
    ){
        ResponseData responseData = new ResponseData();
        Restaurant data = restaurantServiceImp.InsertRestaurant(
                file,title,subtitle,description,is_freeship,address,open_date
        );
        responseData.setIsSuccess(true);
        responseData.setData(data);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/admin")
   public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.updateRestaurant(restaurantUpdateRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.*}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename) {
        Resource file = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
