package com.barkowal.nundine.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts {
    @EmbeddedId
    private OrderProductsKey id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order orderId;
}
