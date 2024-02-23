package com.IT.osahaneat.entity;

import com.IT.osahaneat.Keys.KeyMenuRestaurant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name="menu_restaurant")
public class MenuRestaurant {
    @EmbeddedId
    private KeyMenuRestaurant keyMenuRestaurant;

    @ManyToOne
    @JoinColumn(name="cate_id",insertable=false, updatable=false)
    private Category category;


    @ManyToOne
    @JoinColumn(name="res_id",insertable=false, updatable=false)
    private Restaurant restaurant;
    @Column(name="create_date")
    private Date createDate ;
}
