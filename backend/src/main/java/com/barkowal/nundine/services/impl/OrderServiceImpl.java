package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.dtos.order.CreateOrderRequest;
import com.barkowal.nundine.domain.dtos.product.UpdateProductStockRequest;
import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductStock;
import com.barkowal.nundine.domain.entities.User;
import com.barkowal.nundine.exceptions.CreateOrderException;
import com.barkowal.nundine.exceptions.ProductNotFoundException;
import com.barkowal.nundine.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final AccountBalanceService accountBalanceService;

    @Transactional
    public void createOrder(UUID buyerId, CreateOrderRequest createOrderRequest){
        Inventory sellerInventory = inventoryService.getInventoryByInventoryId(createOrderRequest.inventoryId());
        User seller = sellerInventory.getOwner();

        List<UUID> productIds = createOrderRequest.productIds();
        List<Integer> quantities = createOrderRequest.quantities();
        Integer cost = 0;

        if(productIds.size() != quantities.size()){
            throw new CreateOrderException("Invalid request, productIds must be equal to quantities.");
        }

        for (int i = 0; i < productIds.size(); i++) {
            ProductStock stock = this.productService.getProductStock(productIds.get(i), sellerInventory.getId());

            if(stock == null){
                throw new CreateOrderException("Stock of the product is null.");
            }

            if(quantities.get(i) > stock.getQuantity()){
                throw new CreateOrderException("Invalid request, requested quantity exceeds available stock.");
            }

            Product product = this.productService.getProductById(productIds.get(i));
            if(product == null){
                throw new ProductNotFoundException(String.format("Product with ID '%s' does not exist", productIds.get(i)));
            }
            cost += product.getCurrentPrice() * quantities.get(i);
        }

        Long buyerBalance = this.accountBalanceService.getAccountBalance(buyerId).getBalance();
        if(cost > buyerBalance){
            throw new CreateOrderException("Invalid request, cost exceeds buyers balance.");
        }
        this.accountBalanceService.setAccountBalance(buyerId, buyerBalance - cost);

        Long sellerBalance = this.accountBalanceService.getAccountBalance(seller.getId()).getBalance();
        this.accountBalanceService.setAccountBalance(seller.getId(), sellerBalance + cost);

        for (int i = 0; i < productIds.size(); i++) {

            ProductStock sellerStock = this.productService.getProductStock(productIds.get(i), sellerInventory.getId());
            UpdateProductStockRequest req = new UpdateProductStockRequest(sellerStock.getQuantity() - quantities.get(i));
            this.productService.updateProductStock(productIds.get(i), sellerInventory.getId(), req);

            Inventory buyerInventory = this.inventoryService.getInventory(buyerId);
            ProductStock buyerStock = this.productService.getProductStock(productIds.get(i), buyerInventory.getId());
            Integer buyersProductQuantity = buyerStock == null? 0 : buyerStock.getQuantity();
            // no need to create new productStock, because updateProductStock creates a new one if it's null
            req = new UpdateProductStockRequest(buyersProductQuantity + quantities.get(i));
            this.productService.updateProductStock(productIds.get(i), buyerInventory.getId(), req);

        }
    }

}
