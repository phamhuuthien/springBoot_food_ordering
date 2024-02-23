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
public class KeyMenuRestaurant implements Serializable {
    @Column(name = "cate_id")
    private  int cateId ;
    @Column(name = "res_id")
    private  int resId ;
}
