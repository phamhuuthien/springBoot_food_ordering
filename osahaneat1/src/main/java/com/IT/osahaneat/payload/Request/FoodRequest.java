package com.IT.osahaneat.payload.Request;

import lombok.Data;

@Data
public class FoodRequest {
    private String title ;
    private String image ;
    private String timeShip ;
    private Boolean isFreeship ;
    private Double price ;
    private String cateName;
}
