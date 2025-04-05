package com.boot.chapter03.service;

import com.boot.chapter03.entity.ItemEntity;
import com.boot.chapter03.entity.ProductEntity;
import com.boot.chapter03.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class ItemServiceImpl implements ItemService{
    @Override
    public ItemEntity toEntity(Item m) {
        ItemEntity e = new ItemEntity();
        e.setProduct(new ProductEntity().setId(UUID.fromString(m.getId())))
                .setPrice(m.getUnitPrice())
                .setQuantity(m.getQuantity());
        return e;
    }

    @Override
    public List<ItemEntity> toEntityList(List<Item> items) {
        if(Objects.isNull(items)){
            return List.of();
        }
        return items.stream().map(this::toEntity).collect(toList());
    }

    @Override
    public Item toModel(ItemEntity e) {
        Item m = new Item();
        m.id(e.getProduct().getId().toString()).unitPrice(e.getPrice()).quantity(e.getQuantity());
        return m;
    }

    @Override
    public List<Item> toModelList(List<ItemEntity> items) {
        if (Objects.isNull(items)) {
            return List.of();
        } else {
            return items.stream().map(this::toModel).collect(toList());
        }
    }
}
