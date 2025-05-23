package com.boot.chapter03.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;

    @Column(name = "UNIT_PRICE")
    private Double price;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<CartEntity> cart;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

    public UUID getId() {
        return id;
    }

    public ItemEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public ItemEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ItemEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return quantity == that.quantity && product.equals(that.product) && Objects
                .equals(price, that.price);
    }
}
