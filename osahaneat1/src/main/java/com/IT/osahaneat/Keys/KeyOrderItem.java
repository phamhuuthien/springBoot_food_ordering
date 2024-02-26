package com.IT.osahaneat.Keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KeyOrderItem implements Serializable {
    @Column(name="order_id")
    private int orderId ;
    @Column(name="food_id")
    private int foodId ;
}
