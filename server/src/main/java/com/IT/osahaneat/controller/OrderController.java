package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.Request.OrderRequest;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.OrderService;
import com.IT.osahaneat.services.imp.OrderServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderServiceImp;
    @PostMapping
    public ResponseEntity<?> insertorder(HttpServletRequest request, @RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.insertOrder(request,orderRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<?> confirmOrder(@PathVariable int id,@RequestParam String status){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.confirmOrder(id,status));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
