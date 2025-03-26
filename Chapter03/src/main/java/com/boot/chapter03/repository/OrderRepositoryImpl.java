package com.boot.chapter03.repository;

import com.boot.chapter03.entity.ItemEntity;
import com.boot.chapter03.entity.OrderEntity;
import com.boot.chapter03.model.NewOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExt{

    @PersistenceContext private final EntityManager em;

    private final ItemRepository itemRepo;
    private final CartRepository cRepo;
    private final OrderItemRepository oiRepo;

    public OrderRepositoryImpl(
            EntityManager em, ItemRepository itemRepo, CartRepository cRepo, OrderItemRepository oiRepo
    ){
        this.em = em;
        this.itemRepo = itemRepo;
        this.cRepo = cRepo;
        this.oiRepo = oiRepo;
    }

    @Override
    public Optional<OrderEntity> insert(NewOrder m){
        Iterable<ItemEntity> dbItems = itemRepo.findByCustomerId(m.getCustomerId());
        return Optional.empty();
    }

}
