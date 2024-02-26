package com.IT.osahaneat.payload.Request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String password;
    private String newPassword;
}
