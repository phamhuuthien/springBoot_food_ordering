package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.Request.PromoRequest;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.PromoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promo")
public class PromoController {
    @Autowired
    PromoServiceImp promoServiceImp;

    @GetMapping("/home")
    public ResponseEntity<?> getAllPromo(){
        ResponseData responseData = new ResponseData();
        responseData.setData(promoServiceImp.getAll());
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createPromo(@RequestBody PromoRequest promoRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(promoServiceImp.createPromo(promoRequest));
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> autoDeletePromoExpires(){
        ResponseData responseData = new ResponseData();
        responseData.setData(promoServiceImp.autoDeletePromoExpires());
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
}
