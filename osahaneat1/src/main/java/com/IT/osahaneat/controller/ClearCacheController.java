package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.ClearCacheServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clearCache")
public class ClearCacheController {

    @Autowired
    ClearCacheServiceImp clearCacheServiceImp;
    @GetMapping("")
    public ResponseEntity<?> clearCache(@RequestParam String nameCache){
        ResponseData responseData = new ResponseData();
        Boolean clearCache = clearCacheServiceImp.clearCache(nameCache);
        responseData.setIsSuccess(clearCache);
        responseData.setDescription("delete cache successfully");
        responseData.setData(clearCache);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
