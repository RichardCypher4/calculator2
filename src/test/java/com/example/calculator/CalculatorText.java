package com.example.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorText {
    @Test
    public void testSum() {
        int result = 2 + 3;
        assertEquals(5, result);
    }
}
