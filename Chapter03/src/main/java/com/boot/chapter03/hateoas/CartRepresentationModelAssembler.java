package com.boot.chapter03.hateoas;

import com.boot.chapter03.entity.CartEntity;
import com.boot.chapter03.model.Cart;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class CartRepresentationModelAssembler extends RepresentationModelAssemblerSupport<CartEntity, Cart> {
    private final ItemService itemService;
}
