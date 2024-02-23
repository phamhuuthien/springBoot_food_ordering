package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title ;
    @Column(name="image")
    private String image ;
    @Column(name="time_ship")
    private String timeShip ;
    @Column(name="is_freeship")
    private Boolean isFreeship ;
    @Column(name="price")
    private Double price ;

    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category ;

    @OneToMany(mappedBy = "food")
    private Set<RatingFood> listRatingFood;

    @OneToMany(mappedBy = "food")
    private List<OrderItem> listOrderItem;
}
