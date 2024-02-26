package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.UserRepository;
import com.IT.osahaneat.dto.UserDTO;
import com.IT.osahaneat.entity.Users;
import com.IT.osahaneat.payload.Request.ResetPasswordRequest;
import com.IT.osahaneat.security.CustomJwtFilter;
import com.IT.osahaneat.services.imp.UserServiceImp;
import com.IT.osahaneat.utils.JwtUtilsHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @Autowired
    CustomJwtFilter customJwtFilter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> listUsers =userRepository.findAll();
        List<UserDTO> listUsersDto = new ArrayList<>();
        for(Users user : listUsers){
            UserDTO userDto = new UserDTO();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUserName());
            userDto.setFullName(user.getFullName());
            userDto.setAddress(user.getAddress());
            userDto.setCreateDate(user.getCreateDate());
            listUsersDto.add(userDto);
        }
        return listUsersDto;
    }

    @Override
    public UserDTO getCurrentUsers(HttpServletRequest request) {
        Users user = customJwtFilter.getUserFromToken(request);
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setFullName(user.getFullName());
        userDto.setAddress(user.getAddress());
        userDto.setCreateDate(user.getCreateDate());
        return userDto;
    }

    @Override
    public boolean resetPassword(HttpServletRequest request, ResetPasswordRequest resetPasswordRequest) {
        Users user = customJwtFilter.getUserFromToken(request);
        if(passwordEncoder.matches(resetPasswordRequest.getPassword(),user.getPassword())){
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserDTO updateProfile(HttpServletRequest request, UserDTO userDtoParam) {
        Users user = customJwtFilter.getUserFromToken(request);
        if(userDtoParam.getFullName().length()!=0){
            user.setFullName(userDtoParam.getFullName());
        }
        if (userDtoParam.getAddress().length()!=0){
            user.setAddress(userDtoParam.getAddress());
        }
        userRepository.save(user);
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setFullName(user.getFullName());
        userDto.setAddress(user.getAddress());
        return userDto;
    }

    @Override
    public boolean deleteUserById(int uId) {
        if (userRepository.existsById(uId)) {
            userRepository.deleteById(uId);
            return true;
        } else {
            // Nếu không, ném ra một ngoại lệ hoặc xử lý theo cách khác tùy thuộc vào yêu cầu của bạn
            throw new IllegalArgumentException("User not found with id: " + uId);
        }
    }
}
