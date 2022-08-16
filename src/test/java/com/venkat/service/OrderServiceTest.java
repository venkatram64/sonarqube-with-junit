package com.venkat.service;

import com.venkat.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private final OrderService customer = new OrderService();
    private UUID defaultUuid = null;
    private LocalDateTime defaultLocalDateTime = null;

    @BeforeEach
    public void setup(){
        defaultUuid = UUID.randomUUID();//UUID.fromString("4d7cfb51-82cb-34ae-a53c-3a44e53c666f");

        defaultLocalDateTime = LocalDateTime.of(2019, 2, 3, 10, 12);
    }

    @Test
    void uuIdShouldBe(){
        try(MockedStatic<UUID> mockedStatic = Mockito.mockStatic(UUID.class)){

            mockedStatic.when(UUID::randomUUID).thenReturn(defaultUuid); //stubbed

            Order result = customer.createOrder("MSI Laptop",3L, null);
            System.out.println(UUID.randomUUID());
            assertEquals(defaultUuid.toString(), result.getId());
        }
        System.out.println(UUID.randomUUID());
        //UUID u = UUID.nameUUIDFromBytes(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        //System.out.println(u.toString());
    }

    @Test
    void createDateShouldBe() {
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = customer.createOrder("MSI Laptop", 1L, "11");
            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }

}