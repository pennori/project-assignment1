package com.assignment.calculator.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdditionStrategyTest {

    private AdditionStrategy additionStrategy;

    @BeforeEach
    void setUp() {
        additionStrategy = new AdditionStrategy();
    }

    @Test
    @DisplayName("두 양수를 더할 때 올바른 결과를 반환해야 한다")
    void testCalculateWithPositiveNumbers() {
        // Given
        double left = 5.0;
        double right = 3.0;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(8.0, result, "5.0 + 3.0은 8.0");
    }

    @Test
    @DisplayName("0과 다른 수를 더할 때 올바른 결과를 반환해야 한다")
    void testCalculateWithZero() {
        // Given
        double left = 0.0;
        double right = 5.0;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(5.0, result, "0.0 + 5.0은 5.0");
    }
    
    @Test
    @DisplayName("같은 수를 더할 때 올바른 결과를 반환해야 한다")
    void testCalculateAddingSameNumber() {
        // Given
        double left = 4.0;
        double right = 4.0;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(8.0, result, "4.0 + 4.0은 8.0");
    }

    @Test
    @DisplayName("큰 양수의 계산 검증")
    void testCalculateWithLargePositiveNumbers() {
        // Given
        double left = Double.MAX_VALUE / 2;
        double right = Double.MAX_VALUE / 2;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(Double.MAX_VALUE, result, "MAX_VALUE / 2 + MAX_VALUE / 2은 MAX_VALUE");
    }

    @Test
    @DisplayName("두 0의 합이 올바른 결과를 반환해야 한다")
    void testCalculateWithBothZeros() {
        // Given
        double left = 0.0;
        double right = 0.0;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(0.0, result, "0.0 + 0.0은 0.0");
    }

    @Test
    @DisplayName("소수점 숫자의 더하기가 올바른 결과를 반환해야 한다")
    void testCalculateWithFractionalNumbers() {
        // Given
        double left = 0.5;
        double right = 0.75;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(1.25, result, "0.5 + 0.75은 1.25");
    }

    @Test
    @DisplayName("작은 소수점 숫자의 더하기가 올바른 결과를 반환해야 한다")
    void testCalculateWithVerySmallNumbers() {
        // Given
        double left = 1e-308;
        double right = 1e-308;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(2e-308, result, "1e-308 + 1e-308은 2e-308");
    }

    @Test
    @DisplayName("무한대와 다른 수를 더할 때 무한대를 반환해야 한다")
    void testCalculateWithInfinity() {
        // Given
        double left = Double.POSITIVE_INFINITY;
        double right = 42.0;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(Double.POSITIVE_INFINITY, result, "Infinity + 42.0은 Infinity");
    }

    @Test
    @DisplayName("NaN을 다른 수와 더했을 때 NaN을 반환해야 한다")
    void testCalculateWithNaN() {
        // Given
        double left = Double.NaN;
        double right = 7.0;

        // When
        double result = additionStrategy.calculate(left, right);

        // Then
        assertEquals(Double.NaN, result, 0.0, "NaN + 7.0은 NaN");
    }
}