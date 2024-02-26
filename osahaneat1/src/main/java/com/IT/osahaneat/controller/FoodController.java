package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.Request.FoodRequest;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodServiceImp foodServiceImp;

    @GetMapping()
    public ResponseEntity<?> getAllFood(){
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.getAllFood());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> createFood(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String time_ship,
            @RequestParam boolean is_freeship,
            @RequestParam double price,
            @RequestParam int cate_id
    ){
        ResponseData responseData = new ResponseData();
        Boolean data = foodServiceImp.InsertFood(file,  title,  time_ship,  is_freeship,  price,  cate_id);
        responseData.setData(data);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.deleteFood(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable int id,@RequestBody FoodRequest foodRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.updateFood(id,foodRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
