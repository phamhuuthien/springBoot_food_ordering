package com.IT.osahaneat.dto;


import lombok.Data;

@Data
public class FoodDTO {
    private int id;
    private String title ;
    private String image ;
    private String timeShip ;
    private Boolean isFreeship ;
    private Double price ;
    private String cateName;
}
