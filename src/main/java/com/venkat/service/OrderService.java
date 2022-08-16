package com.venkat.service;

import com.venkat.model.Order;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {

    public Order createOrder(String prodName, Long amount, String parentOrderId){
        Order order = new Order();
        order.setId(parentOrderId == null ? UUID.randomUUID().toString(): parentOrderId);
        order.setCreationDate(LocalDateTime.now());
        order.setAmount(amount);
        order.setProductName(prodName);

        return order;
    }
}
