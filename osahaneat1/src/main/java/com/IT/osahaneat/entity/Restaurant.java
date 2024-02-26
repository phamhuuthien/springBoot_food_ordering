package com.IT.osahaneat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id ;
    @Column(name="title")
    private String title ;
    @Column(name="subtitle")

    private String subtitle ;
    @Column(name="description")

    private String description ;
    @Column(name="image")

    private String image ;
    @Column(name="is_freeship")

    private Boolean isFreeship ;
    @Column(name="address")
    private String address ;
    @Column(name="open_date")
    private Date openDate ;


    @OneToMany(mappedBy = "restaurant")
    private List<RatingRestaurant> listRatingRestaurants;

    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> listOrders;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuRestaurant> listMenuRestaurant;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> listPromo;
}
