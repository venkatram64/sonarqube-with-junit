package com.venkat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setup(){//test Fixture
        calculator = new Calculator();
    }

    @Test
    public void addTest() {
        //arrange
        int a = 10;
        int b = 20;
        //act
        int sum = calculator.add(a,b);
        //assert
        assertEquals(30, sum);
    }

    /*@Test(expected = RuntimeException.class)
    public void exceptionTest(){
        throw new RuntimeException("Oops!");
    }*/

    @Test
    public void testIt(){
        Calculator calc = Mockito.mock(Calculator.class);
    }
}