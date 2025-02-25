package com.assignment.calculator.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultiplicationStrategyTest {

    private MultiplicationStrategy multiplicationStrategy;

    @BeforeEach
    public void setUp() {
        multiplicationStrategy = new MultiplicationStrategy();
    }

    @Test
    @DisplayName("양수 두 개의 곱셈 테스트")
    public void testCalculateWithPositiveNumbers() {
        // Given
        double a = 5.0;
        double b = 3.0;

        // When
        double result = multiplicationStrategy.calculate(a, b);

        // Then
        assertEquals(15.0, result, "5.0 * 3.0은 15.0");
    }

    @Test
    @DisplayName("0과 양수의 곱셈 테스트")
    public void testCalculateWithZero() {
        // Given
        double a = 0.0;
        double b = 5.0;

        // When
        double result = multiplicationStrategy.calculate(a, b);

        // Then
        assertEquals(0.0, result, "0.0 * 5.0은 0.0");
    }

    @Test
    @DisplayName("0과 0의 곱셈 테스트")
    public void testCalculateWithBothZero() {
        // Given
        double a = 0.0;
        double b = 0.0;

        // When
        double result = multiplicationStrategy.calculate(a, b);

        // Then
        assertEquals(0.0, result, "0.0 * 0.0은 0.0");
    }
}