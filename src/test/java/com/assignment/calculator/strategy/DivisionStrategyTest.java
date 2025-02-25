package com.assignment.calculator.strategy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DivisionStrategyTest {

    private DivisionStrategy divisionStrategy;

    @BeforeEach
    public void setUp() {
        divisionStrategy = new DivisionStrategy();
    }

    @Test
    @DisplayName("정상적인 나눗셈: 10.0을 2.0으로 나눌 때 결과는 5.0")
    public void testCalculate_NormalDivision() {
        // Given
        double left = 10.0;
        double right = 2.0;

        // When
        double result = divisionStrategy.calculate(left, right);

        // Then
        double expected = 5.0;
        assertEquals(expected, result, "10.0 / 2.0의 결과는 5.0");
    }

    @Test
    @DisplayName("소수 나눗셈: 10.5를 0.5로 나눌 때 결과는 21.0")
    public void testCalculate_DecimalDivision() {
        // Given
        double left = 10.5;
        double right = 0.5;

        // When
        double result = divisionStrategy.calculate(left, right);

        // Then
        double expected = 21.0;
        assertEquals(expected, result, "10.5 / 0.5의 결과는 21.0");
    }

    @Test
    @DisplayName("1로 나누기: 7.0을 1.0으로 나눌 때 결과는 7.0")
    public void testCalculate_DivisionByOne() {
        // Given
        double left = 7.0;
        double right = 1.0;

        // When
        double result = divisionStrategy.calculate(left, right);

        // Then
        double expected = 7.0;
        assertEquals(expected, result, "7.0 / 1.0의 결과는 7.0");
    }

    @Test
    @DisplayName("0을 어떤 숫자로 나눌 때: 0.0을 5.0으로 나눌 때 결과는 0.0")
    public void testCalculate_ZeroDividend() {
        // Given
        double left = 0.0;
        double right = 5.0;

        // When
        double result = divisionStrategy.calculate(left, right);

        // Then
        double expected = 0.0;
        assertEquals(expected, result, "0.0 / 5.0의 결과는 0.0");
    }

    @Test
    @DisplayName("0으로 나눌 때 결과는 NaN")
    public void testCalculate_DivisionByZero() {
        // Given
        double left = 10.0;
        double right = 0.0;

        // When
        double result = divisionStrategy.calculate(left, right);

        // Then
        assertTrue(Double.isNaN(result), "0으로 나눌 때 결과는 NaN");
    }
    
}