package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.payload.Request.SignUpRequest;

public interface LoginServiceImp {
    Boolean checkLogin(String username, String password);
    Boolean addUser(SignUpRequest signUpRequest);
}
