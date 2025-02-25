package com.assignment.calculator.strategy;

public class MultiplicationStrategy implements CalculationStrategy {
    @Override
    public double calculate(double left, double right) {
        return left * right;
    }
}

