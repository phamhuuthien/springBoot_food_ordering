package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@Entity(name="rating_food")
public class RatingFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;

    @Column(name="content")
    private String content ;

    @Column(name="rate_point")
    private double ratePoint ;

    @Column(name="date_created")
    private Date dateCreated ;
    public RatingFood(Users user, Food food, String content, double ratePoint,Date dateCreated) {
        this.user = user;
        this.food = food;
        this.content = content;
        this.ratePoint = ratePoint;
        this.dateCreated = dateCreated;
    }
}
