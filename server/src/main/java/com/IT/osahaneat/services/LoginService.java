package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.UserRepository;
import com.IT.osahaneat.entity.Roles;
import com.IT.osahaneat.entity.Users;
import com.IT.osahaneat.payload.Request.SignUpRequest;
import com.IT.osahaneat.services.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Boolean checkLogin(String username, String password) {
        Users users = userRepository.findByUserName(username);
        return passwordEncoder.matches(password,users.getPassword());
    }

    @Override
    public Boolean addUser(SignUpRequest signUpRequest) {
        Users user = new Users();
        Roles role = new Roles();
        if(userRepository.findByUserName(signUpRequest.getEmail())==null){
            role.setId(signUpRequest.getRoleId());
            user.setFullName(signUpRequest.getFullname());
            user.setUserName(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setAddress(signUpRequest.getAddress());
            user.setRole(role);
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }
}
