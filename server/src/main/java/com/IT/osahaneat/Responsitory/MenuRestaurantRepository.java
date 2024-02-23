package com.IT.osahaneat.Responsitory;

import com.IT.osahaneat.entity.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRestaurantRepository extends JpaRepository<MenuRestaurant,Integer> {
    @Query(value = "select * from menu_restaurant where res_id = :resId", nativeQuery = true)
    List<MenuRestaurant> findByResId(int resId);
}
