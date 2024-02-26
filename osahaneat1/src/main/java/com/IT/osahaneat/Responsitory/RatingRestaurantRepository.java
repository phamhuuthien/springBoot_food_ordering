package com.IT.osahaneat.Responsitory;

import com.IT.osahaneat.entity.RatingRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRestaurantRepository extends JpaRepository<RatingRestaurant,Integer> {
}
