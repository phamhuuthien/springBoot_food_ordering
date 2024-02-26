package com.IT.osahaneat.Responsitory;

import com.IT.osahaneat.entity.RatingFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingFoodRepository extends JpaRepository<RatingFood,Integer> {

    @Query(value = "select * from rating_food where food_id = :foodId", nativeQuery = true)
    RatingFood findByFoodId(int foodId);

}
