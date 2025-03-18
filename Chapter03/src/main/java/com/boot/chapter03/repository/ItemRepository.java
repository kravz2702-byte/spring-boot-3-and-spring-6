package com.boot.chapter03.repository;

import com.boot.chapter03.entity.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {
    @Query(value = """
    SELECT i.* 
    FROM ecomm."user" u
    JOIN ecomm.cart c ON u.id = c.user_id
    JOIN ecomm.cart_item ci ON c.id = ci.cart_id 
    JOIN ecomm.item i ON ci.item_id = i.id
    WHERE u.id = :customerId""",
            nativeQuery = true)
    Iterable<ItemEntity> findByCustomerId(String customerId);

    @Modifying
    @Query(
            value = "delete from ecomm.cart_item where item_id in (:ids) and cart_id = :cartId",
            nativeQuery = true
    )
    void deleteCartItemJoinById(List<UUID> ids, UUID cartId);
}
