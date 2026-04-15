package com.barkowal.nundine.domain.specifications;

import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class ProductSpecs {
    public static Specification<Product> hasUserId(UUID userId) {
        return (root, query, cb) -> {
            if(userId == null) return null;

            Join<Product, User> userJoin = root.join("supplier", JoinType.INNER);
            return cb.equal(userJoin.get("id"), userId);
        };
    }
}
