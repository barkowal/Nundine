package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.dtos.order.CreateOrderRequest;
import com.barkowal.nundine.domain.dtos.product.UpdateProductStockRequest;
import com.barkowal.nundine.domain.entities.*;
import com.barkowal.nundine.exceptions.CreateOrderException;
import com.barkowal.nundine.exceptions.ProductNotFoundException;
import com.barkowal.nundine.repositories.OrderProductsRepository;
import com.barkowal.nundine.repositories.OrderRepository;
import com.barkowal.nundine.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final UserService userService;
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final AccountBalanceService accountBalanceService;

    @Transactional
    public void createOrder(UUID buyerId, CreateOrderRequest createOrderRequest){
        Inventory sellerInventory = inventoryService.getInventoryByInventoryId(createOrderRequest.inventoryId());
        User seller = sellerInventory.getOwner();
        User buyer = userService.getUser(buyerId);

        List<UUID> productIds = createOrderRequest.productIds();
        List<Integer> quantities = createOrderRequest.quantities();
        Long cost = 0L;

        if(seller.getId().equals(buyerId)){
            throw new CreateOrderException("Invalid request, Buyer and Seller cannot be the same user.");
        }

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
            cost += (long) product.getCurrentPrice() * quantities.get(i);
        }

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setBuyer(buyer);
        order.setSeller(seller);
        order.setTotalAmount(cost);
        orderRepository.save(order);

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

            OrderProductsKey key = new OrderProductsKey();
            key.setOrderId(order.getId());
            key.setProductId(productIds.get(i));

            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setId(key);
            orderProducts.setProductId(sellerStock.getProductId());
            orderProducts.setOrderId(order);
            orderProducts.setQuantity(quantities.get(i));

            orderProductsRepository.save(orderProducts);

        }
    }

}
