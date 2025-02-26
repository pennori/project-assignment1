package com.assignment.calculator.strategy;

/**
 * 계산 전략을 정의하는 인터페이스.
 */
public interface CalculationStrategy {

    /**
     * 두 숫자를 이용하여 계산을 수행.
     *
     * @param left 첫 번째 피연산자
     * @param right 두 번째 피연산자
     * @return 계산 결과
     */
    double calculate(double left, double right);
}