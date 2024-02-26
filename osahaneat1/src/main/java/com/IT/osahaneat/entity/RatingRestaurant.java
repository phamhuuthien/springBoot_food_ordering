package com.IT.osahaneat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@Entity(name="rating_restaurant")
public class RatingRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "res_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name="content")
    private String content ;

    @Column(name="rate_point")
    private double ratePoint ;

    @Column(name="date_created")
    private Date dateCreated ;

    @Override
    public String toString() {
        return "RatingRestaurant{" +
                "id=" + id +
                ", user=" + user +
                // Exclude the reference to the parent (restaurant) to avoid cyclic reference
                // ", restaurant=" + restaurant +
                ", content='" + content + '\'' +
                ", ratePoint=" + ratePoint +
                '}';
    }

    public RatingRestaurant(Users user, Restaurant restaurant, String content, double ratePoint,Date dateCreated) {
        this.user = user;
        this.restaurant = restaurant;
        this.content = content;
        this.ratePoint = ratePoint;
        this.dateCreated= dateCreated;
    }
}
