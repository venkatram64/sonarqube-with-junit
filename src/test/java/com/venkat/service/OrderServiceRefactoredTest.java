package com.venkat.service;

import com.venkat.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//https://github.com/rieckpil/blog-tutorials/blob/master/mockito-tips-and-tricks/src/test/java/de/rieckpil/blog/then/UserServiceWithMockitoExtensionTest.java
//https://rieckpil.de/mocking-static-methods-with-mockito-java-kotlin/
public class OrderServiceRefactoredTest {

    private OrderIdGenerator orderIdGenerator = mock(OrderIdGenerator.class);
    private Clock clock = mock(Clock.class);

    private OrderServiceRefactored customer; //system under test (SUT)

    @BeforeEach
    public void setup(){
        customer = new OrderServiceRefactored(clock, orderIdGenerator);
    }

    @Test
    public void uuIdShouldBe(){

        when(orderIdGenerator.generateOrderId()).thenReturn("8d8b30e3-de52-4f1c-a71c-9905a8043dac");

        //LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020, 1, 1, 12, 0);
        LocalDateTime defaultLocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        Clock fixedClock = Clock.fixed(defaultLocalDateTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        when(clock.instant()).thenReturn(fixedClock.instant());
        when(clock.getZone()).thenReturn(fixedClock.getZone());

        Order result = customer.createOrder("IPhone 11", 1L, null);

        assertEquals("8d8b30e3-de52-4f1c-a71c-9905a8043dac", result.getId());
        assertEquals(defaultLocalDateTime, result.getCreationDate());
    }



}