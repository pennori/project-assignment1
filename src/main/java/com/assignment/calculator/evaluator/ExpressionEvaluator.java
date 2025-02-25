package com.assignment.calculator.evaluator;

import com.assignment.calculator.constants.messages.CalculationMessages;
import com.assignment.calculator.strategy.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {
    private final Map<String, CalculationStrategy> calculationStrategies;

    public ExpressionEvaluator() {
        calculationStrategies = createCalculationStrategies();
    }

    private Map<String, CalculationStrategy> createCalculationStrategies() {
        Map<String, CalculationStrategy> strategies = new HashMap<>();
        strategies.put("+", new AdditionStrategy());
        strategies.put("-", new SubtractionStrategy());
        strategies.put("*", new MultiplicationStrategy());
        strategies.put("/", new DivisionStrategy());

        return strategies;
    }

    public void evaluateExpression(String expression, Pattern pattern) {
        ParsedExpression parsed = parseExpression(expression, pattern);
        if (parsed == null) {
            System.out.println(CalculationMessages.INVALID_EXPRESSION_MESSAGE);
            return;
        }

        displayOperands(parsed);

        CalculationStrategy strategy = calculationStrategies.get(parsed.operator());
        if (strategy == null) {
            System.out.println(CalculationMessages.INVALID_EXPRESSION_MESSAGE);
            return;
        }

        double result = strategy.calculate(parsed.leftOperand(), parsed.rightOperand());
        if (Double.isNaN(result)) {
            System.out.println(CalculationMessages.DIVISION_BY_ZERO_MESSAGE);
        } else {
            System.out.println(CalculationMessages.RESULT_MESSAGE + result);
        }
    }

    private ParsedExpression parseExpression(String expression, Pattern pattern) {
        Matcher matcher = pattern.matcher(expression);
        if (matcher.matches()) {
            try {
                double left = Double.parseDouble(matcher.group(1));
                String operator = matcher.group(2);
                double right = Double.parseDouble(matcher.group(3));

                return new ParsedExpression(left, operator, right);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    private void displayOperands(ParsedExpression parsed) {
        System.out.println(CalculationMessages.LEFT_OPERAND_LABEL + parsed.leftOperand());
        System.out.println(CalculationMessages.OPERATOR_LABEL + parsed.operator());
        System.out.println(CalculationMessages.RIGHT_OPERAND_LABEL + parsed.rightOperand());
    }

    private record ParsedExpression(double leftOperand, String operator, double rightOperand) {
    }
}