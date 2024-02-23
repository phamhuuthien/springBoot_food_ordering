package com.IT.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class PromoDTO {
    private int resId;
    private Date startDate;
    private Date endDate;
    private double percent;
}
