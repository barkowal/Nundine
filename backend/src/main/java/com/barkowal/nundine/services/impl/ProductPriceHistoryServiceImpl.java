package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductPriceHistory;
import com.barkowal.nundine.repositories.ProductPriceHistoryRepository;
import com.barkowal.nundine.services.ProductPriceHistoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
                null,
                product
        );

        return priceHistoryRepository.save(newPriceHistory);
    }

    @Override
    public void deleteAllProductRecords(Product product) {
        List<ProductPriceHistory> productHistory = this.priceHistoryRepository.findAllByProductId(product.getId());
        productHistory.forEach(this.priceHistoryRepository::delete);
    }


}
