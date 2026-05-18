package com.barkowal.nundine.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

// This table should record prices of a product at any given time
// New record should appear when price of a product changes
// No record means that the product's price hasn't changed
@Entity
@Table(name = "products_price_history")
@SQLDelete(sql = "UPDATE products_price_history SET deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceHistory {

    @Id
    @Column(name = "id", updatable = false, nullable=false)
    private UUID id;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "from_date", updatable = false, nullable = false)
    private LocalDateTime fromDate;

    @CreatedDate
    @Column(name = "to_date", updatable = false, nullable = false)
    private LocalDateTime toDate;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
