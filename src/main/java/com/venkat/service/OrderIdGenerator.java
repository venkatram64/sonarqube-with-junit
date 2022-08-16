package com.venkat.service;

import java.util.UUID;

public class OrderIdGenerator {
    public String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}
