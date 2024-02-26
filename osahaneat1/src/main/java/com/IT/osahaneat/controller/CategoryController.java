package com.IT.osahaneat.controller;

import com.IT.osahaneat.entity.Category;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getAllCategory());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getCategoryHomePage(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestParam String cateName){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.updateCategory(id,cateName));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> createCategory(@RequestParam String nameCate){
        ResponseData responseData = new ResponseData();
        Category data = categoryServiceImp.createCategory(nameCate);
        responseData.setIsSuccess(true);
        responseData.setStatus(200);
        responseData.setData(data);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }


//    @GetMapping("/clearCache")
//    @CacheEvict(value = "getCategoryHomePage", allEntries = true)
//    public String clearCache(String name){
//        return "clear cache success" + name;
//    }
}
