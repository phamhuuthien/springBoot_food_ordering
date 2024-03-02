package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping
    public ResponseEntity<?> deleteRedis(@RequestParam String name){
        ResponseData responseData = new ResponseData();
        if(redisTemplate.delete(name)){
            responseData.setData(true);
        }else{
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
