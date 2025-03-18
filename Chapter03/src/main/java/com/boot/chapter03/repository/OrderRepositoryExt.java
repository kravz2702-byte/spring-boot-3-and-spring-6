package com.boot.chapter03.repository;

import com.boot.chapter03.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(newOrder m)
}
