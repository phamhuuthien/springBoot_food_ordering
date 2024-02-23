package com.IT.osahaneat.Responsitory;

import com.IT.osahaneat.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo,Integer> {
    @Query(value = "SELECT * FROM Promo WHERE res_id = :resId", nativeQuery = true)
    Promo findByresId(int resId);
}
