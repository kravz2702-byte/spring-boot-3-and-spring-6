package com.boot.chapter03.repository;

import com.boot.chapter03.entity.OrderEntity;
import com.boot.chapter03.model.NewOrder;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(NewOrder m);
}
