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
public class OrderProductsKey implements Serializable {

    @Column(name = "product_id", updatable = false, nullable = false)
    private UUID productId;

    @Column(name = "order_id", updatable = false, nullable = false)
    private UUID orderId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductsKey that = (OrderProductsKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
