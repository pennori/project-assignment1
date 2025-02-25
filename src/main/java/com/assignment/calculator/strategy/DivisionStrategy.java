package com.assignment.calculator.strategy;

public class DivisionStrategy implements CalculationStrategy {
    @Override
    public double calculate(double left, double right) {
        if (right != 0) {
            return left / right;
        } else {
            return Double.NaN;
        }
    }
}

