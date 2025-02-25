package com.assignment.calculator.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubtractionStrategyTest {

    private final SubtractionStrategy subtractionStrategy = new SubtractionStrategy();

    @Test
    @DisplayName("두 양수의 뺄셈을 테스트")
    void calculate_PositiveNumbers() {
        // Given
        double left = 10.0;
        double right = 5.0;
        double expected = 5.0;

        // When
        double result = subtractionStrategy.calculate(left, right);

        // Then
        assertEquals(expected, result, "10.0 - 5.0은 5.0");
    }

    @Test
    @DisplayName("피연산자 중 하나가 0인 뺄셈을 테스트")
    void calculate_Zero() {
        // Given
        double left = 0.0;
        double right = 5.0;
        double expected = -5.0;

        // When
        double result = subtractionStrategy.calculate(left, right);

        // Then
        assertEquals(expected, result, "0.0 - 5.0은 -5.0");
    }
}