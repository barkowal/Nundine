package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductPriceHistory;

public interface ProductPriceHistoryService {
    ProductPriceHistory addNewProductPriceRecord(Product product);
    void deleteAllProductRecords(Product product);
}
