package com.IT.osahaneat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int id;
    private String userName ;
    private String fullName ;
    private String address;
    private Date createDate ;
}
