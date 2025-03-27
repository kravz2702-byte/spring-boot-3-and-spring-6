package com.boot.chapter03.service;

import com.boot.chapter03.entity.ItemEntity;
import com.boot.chapter03.model.Item;

import java.util.List;

public interface ItemService {
    ItemEntity toEntity(Item m);

    List<ItemEntity> toEntityList(List<Item> items);

    Item toModel(ItemEntity e);

    List<Item> toModelList(List<ItemEntity> items);
}
