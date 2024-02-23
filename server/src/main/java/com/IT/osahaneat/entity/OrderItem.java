package com.IT.osahaneat.entity;

import com.IT.osahaneat.Keys.KeyOrderItem;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "order_item")
public class OrderItem {

    @EmbeddedId
    private KeyOrderItem keyOrderItem;


    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id",insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name="food_id",insertable = false, updatable = false)
    private Food food;

}
