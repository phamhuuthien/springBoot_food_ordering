package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="res_id")
    private Restaurant restaurant;

    @Column(name="percent")
    private double percent ;
    @Column(name="start_date")
    private Date startDate ;
    @Column(name="end_date")
    private Date endDate ;
}
