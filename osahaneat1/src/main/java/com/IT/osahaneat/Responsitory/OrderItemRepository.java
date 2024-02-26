package com.IT.osahaneat.Responsitory;


import com.IT.osahaneat.Keys.KeyOrderItem;
import com.IT.osahaneat.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
    @Query(value = "select * from order_item where food_id = :foodId", nativeQuery = true)
    OrderItem findOrderItemByFoodId(int foodId);

    @Query(value = "select * from order_item where order_id = :orderId", nativeQuery = true)
    List<OrderItem> findOrderItemsByOrderId(int orderId);
}
