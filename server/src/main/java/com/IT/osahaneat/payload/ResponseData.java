package com.IT.osahaneat.payload;

import lombok.Data;

@Data
public class ResponseData {
    private int status;
    private Boolean isSuccess = true ;
    private String description;
    private Object data;
}
