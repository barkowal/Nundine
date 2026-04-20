package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductPriceHistory;
import com.barkowal.nundine.repositories.ProductPriceHistoryRepository;
import com.barkowal.nundine.services.ProductPriceHistoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductPriceHistoryServiceImpl implements ProductPriceHistoryService {
    private final ProductPriceHistoryRepository priceHistoryRepository;

    @Override
    @Transactional
    public ProductPriceHistory addNewProductPriceRecord(Product product) {
        ProductPriceHistory lastPriceHistory = priceHistoryRepository.findFirstByProductIdOrderByToDateDesc(product.getId())
                .orElse(null);

        LocalDateTime fromDate = product.getCreatedAt();

        // If there were previous price changes, fromDate should be toDate from the most recent history
        if(lastPriceHistory != null){
            fromDate = lastPriceHistory.getToDate();
        }

        ProductPriceHistory newPriceHistory = new ProductPriceHistory(
                UUID.randomUUID(),
                product.getCurrentPrice(),
                fromDate,
                LocalDateTime.now(),
                product
        );

        return priceHistoryRepository.save(newPriceHistory);
    }
}
