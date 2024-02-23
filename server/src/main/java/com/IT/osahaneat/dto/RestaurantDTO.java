package com.IT.osahaneat.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RestaurantDTO {
    private int id;
    private String title;
    private String subtitle;
    private String image;
    private double rating;
    private boolean isFreeShip;
    private Date openDate;
    private String address;
    List<CategoryDTO> category;
//     trong categoryDTO đã có name với list menu
}
