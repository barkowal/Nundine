package com.barkowal.nundine.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class ProductStockKey implements Serializable {
    @Column(name = "product_id", updatable = false, nullable = false)
    private UUID productId;

    @Column(name = "inventory_id", updatable = false, nullable = false)
    private UUID inventoryId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductStockKey that = (ProductStockKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(inventoryId, that.inventoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, inventoryId);
    }
}
