package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.dto.UserDTO;
import com.IT.osahaneat.entity.Users;
import com.IT.osahaneat.payload.Request.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUsers();
    UserDTO getCurrentUsers(HttpServletRequest request);

    boolean resetPassword(HttpServletRequest request, ResetPasswordRequest resetPasswordRequest);

    UserDTO updateProfile(HttpServletRequest request, UserDTO userDto);

    boolean deleteUserById(int uId);
}
