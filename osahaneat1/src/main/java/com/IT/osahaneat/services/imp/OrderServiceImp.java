package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.payload.Request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface OrderServiceImp {
    Boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest);

    Boolean confirmOrder(int id,String status);
}
