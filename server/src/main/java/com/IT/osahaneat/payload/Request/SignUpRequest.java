package com.IT.osahaneat.payload.Request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String fullname;
    private String email;
    private String password;
    private String address;
    private int roleId;
}
