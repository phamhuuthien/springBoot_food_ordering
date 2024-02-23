package com.IT.osahaneat.payload.Request;

import lombok.Data;

import java.util.Date;
@Data
public class RestaurantUpdateRequest {
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private String image;
    private boolean isFreeship;
    private String address;
    private Date openDate;
    private int[] cateId;

}
