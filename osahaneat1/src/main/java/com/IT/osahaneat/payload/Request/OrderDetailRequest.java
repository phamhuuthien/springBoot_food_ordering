package com.IT.osahaneat.payload.Request;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private int foodId;
    private int quantity;

}
