package com.IT.osahaneat.controller;

import com.IT.osahaneat.payload.Request.SignUpRequest;
import com.IT.osahaneat.payload.ResponseData;
import com.IT.osahaneat.services.imp.LoginServiceImp;
import com.IT.osahaneat.utils.JwtUtilsHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceimp ;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn( @RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        if(loginServiceimp.checkLogin(username, password)){
            responseData.setStatus(200);
            responseData.setIsSuccess(true);
            responseData.setDescription("success");
            responseData.setData(jwtUtilsHelper.generateToken(username));
        }else{
            responseData.setStatus(404);
            responseData.setIsSuccess(false);
            responseData.setDescription("error");
            responseData.setData("");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        if(loginServiceimp.addUser(signUpRequest)){
            responseData.setStatus(200);
            responseData.setDescription("success");
            responseData.setData(true);
        }else{
            responseData.setStatus(404);
            responseData.setDescription("error");
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
