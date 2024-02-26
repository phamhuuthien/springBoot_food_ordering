package com.IT.osahaneat.payload.Request;

import lombok.Data;

import java.sql.Date;

@Data
public class PromoRequest {
    private int resId;
    private Date startDate;
    private Date endDate;
    private double percent;
}
