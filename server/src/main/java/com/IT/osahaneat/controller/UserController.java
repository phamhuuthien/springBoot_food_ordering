package com.IT.osahaneat.controller;

import com.IT.osahaneat.dto.UserDTO;
import com.IT.osahaneat.payload.Request.ResetPasswordRequest;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("admin")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;
    @GetMapping("/user/getCurrent")
    public ResponseEntity<?> getCurrent(HttpServletRequest request){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getCurrentUsers(request));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/user/resetPassword")
    public ResponseEntity<?> resetPassword(HttpServletRequest request, @RequestBody ResetPasswordRequest resetPasswordRequest){
        ResponseData responseData = new ResponseData();
        if(userServiceImp.resetPassword(request,resetPasswordRequest)){
            responseData.setData("update successfully");
        }else {
            responseData.setData("update failed");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/user/updateProfile")
    public ResponseEntity<?> updateProfile (HttpServletRequest request,@RequestBody UserDTO userdto){
        ResponseData responseData = new ResponseData();
        UserDTO data = userServiceImp.updateProfile(request,userdto);
        responseData.setIsSuccess(true);
        responseData.setStatus(200);
        responseData.setData(data);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/admin/getAllUser")
    public ResponseEntity<?> getAllUser(){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getAllUsers());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        if(userServiceImp.deleteUserById(id)){
            responseData.setIsSuccess(true);
            responseData.setStatus(200);
            responseData.setData(true);
            return new ResponseEntity<>(responseData,HttpStatus.OK);
        }else{
            responseData.setIsSuccess(false);
            responseData.setStatus(400);
            responseData.setData(false);
            return new ResponseEntity<>(responseData,HttpStatus.BAD_REQUEST);
        }

    }
}
