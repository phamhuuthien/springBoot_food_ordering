package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="total")
    private double total;
    @Column(name="status")
    private String status;

    @Column(name="create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name="res_id")
    private Restaurant restaurant;



    @OneToMany(mappedBy = "order")
    private List<OrderItem> listOrderItem;
}
