package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="user_name")
    private String userName ;
    @Column(name="password")
    private String password ;
    @Column(name="full_name")
    private String fullName ;
    @Column(name="address")
    private String address ;
    @Column(name="create_date")
    private Date createDate ;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles role;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<RatingFood> listRatingFoods;

    @OneToMany(mappedBy="user")
    private Set<RatingRestaurant> ratingRestaurants;

    @OneToMany(mappedBy="user")
    private Set<Orders> listOrders;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createDate=" + createDate +
                ", role=" + role +
                ", listRatingFoods=" + listRatingFoods +
                ", ratingRestaurants=" + ratingRestaurants +
                ", listOrders=" + listOrders +
                '}';
    }
}
