package com.example.calculator;

import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorApplicationTests {

	@Autowired
	private CalculatorService calculatorService;

	@Test
	public void testSum() {
		int result = calculatorService.sum(2, 3);
		assertEquals(5, result);
	}
}
