package com.IT.osahaneat.payload.Request;

import lombok.Data;

@Data
public class OrderRequest {
    private int resId;
    private OrderDetailRequest[] orderDetailRequests;
}
