package com.IT.osahaneat.Responsitory;

import com.IT.osahaneat.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Integer> {
}
