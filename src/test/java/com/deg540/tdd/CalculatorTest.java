package com.deg540.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void add_two_numbers() throws Exception {
        Calculator calculator = new Calculator();

        int result = calculator.add(1, 2);

        assertEquals(3, result);
    }

    @Test
    public void multiply_two_numbers() throws Exception {
        Calculator calculator = new Calculator();

        int result = calculator.multiply(1, 2);

        assertEquals(2, result);
    }
}

