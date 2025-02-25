package com.assignment.calculator;

import com.assignment.calculator.constants.messages.AppMessages;
import com.assignment.calculator.constants.messages.CalculationMessages;
import com.assignment.calculator.constants.regex.RegexPatterns;
import com.assignment.calculator.evaluator.ExpressionEvaluator;
import com.assignment.calculator.input.InputHandler;
import com.assignment.calculator.template.AppTemplate;

import java.util.regex.Pattern;

public class CalculatorApp extends AppTemplate {
    private static final int MAX_ITERATIONS = 1000;
    private boolean running;
    private int currentIteration;
    private Pattern expressionPattern;
    private ExpressionEvaluator expressionEvaluator;
    private InputHandler userInputHandler;

    @Override
    protected void initializeApp() {
        running = true;
        currentIteration = 0;
        expressionPattern = Pattern.compile(RegexPatterns.BASIC_EXPRESSION_PATTERN);
        expressionEvaluator = new ExpressionEvaluator();
        userInputHandler = new InputHandler();
    }

    @Override
    protected boolean shouldContinueRunning() {
        return running && currentIteration < MAX_ITERATIONS;
    }

    @Override
    protected void processInput() {
        System.out.print(AppMessages.PROMPT_MESSAGE);
        String input = userInputHandler.getInput();

        if (input != null) {
            input = input.trim();
        }

        if (input == null || input.isEmpty()) {
            System.out.println(CalculationMessages.INVALID_EXPRESSION_MESSAGE);
            return;
        }

        if (input.equalsIgnoreCase("exit")) {
            running = false;
            System.out.println(AppMessages.EXIT_MESSAGE);
            return;
        }

        expressionEvaluator.evaluateExpression(input, expressionPattern);
        currentIteration++;
    }

    @Override
    protected void terminateApp() {
        this.running = false;

        if (currentIteration >= MAX_ITERATIONS) {
            System.out.println(AppMessages.MAX_ITERATION_MESSAGE);
        }

        userInputHandler.close();
    }
}