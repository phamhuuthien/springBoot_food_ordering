package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name_cate")
    private String nameCate ;
    @Column(name="create_date")
    private Date createDate ;

    @OneToMany(mappedBy="category")
    private List<Food> listFood;

    @OneToMany(mappedBy="category")
    private List<MenuRestaurant> listMenuRestaurant;
}
