package com.assignment.calculator.evaluator;

import com.assignment.calculator.constants.messages.CalculationMessages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    private ExpressionEvaluator evaluator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        evaluator = new ExpressionEvaluator();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("덧셈이 유효한 경우의 검증")
    void testValidAddition() {
        // Given
        String expression = "5 + 3";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.LEFT_OPERAND_LABEL + "5.0" + System.lineSeparator() +
                CalculationMessages.OPERATOR_LABEL + "+" + System.lineSeparator() +
                CalculationMessages.RIGHT_OPERAND_LABEL + "3.0" + System.lineSeparator() +
                CalculationMessages.RESULT_MESSAGE + "8.0" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("뺄셈이 유효한 경우의 검증")
    void testValidSubtraction() {
        // Given
        String expression = "10 - 4";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.LEFT_OPERAND_LABEL + "10.0" + System.lineSeparator() +
                CalculationMessages.OPERATOR_LABEL + "-" + System.lineSeparator() +
                CalculationMessages.RIGHT_OPERAND_LABEL + "4.0" + System.lineSeparator() +
                CalculationMessages.RESULT_MESSAGE + "6.0" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("곱셈이 유효한 경우의 검증")
    void testValidMultiplication() {
        // Given
        String expression = "7 * 6";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.LEFT_OPERAND_LABEL + "7.0" + System.lineSeparator() +
                CalculationMessages.OPERATOR_LABEL + "*" + System.lineSeparator() +
                CalculationMessages.RIGHT_OPERAND_LABEL + "6.0" + System.lineSeparator() +
                CalculationMessages.RESULT_MESSAGE + "42.0" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("나눗셈이 유효한 경우의 검증")
    void testValidDivision() {
        // Given
        String expression = "20 / 4";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.LEFT_OPERAND_LABEL + "20.0" + System.lineSeparator() +
                CalculationMessages.OPERATOR_LABEL + "/" + System.lineSeparator() +
                CalculationMessages.RIGHT_OPERAND_LABEL + "4.0" + System.lineSeparator() +
                CalculationMessages.RESULT_MESSAGE + "5.0" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("제로로 나누는 경우의 검증")
    void testDivisionByZero() {
        // Given
        String expression = "10 / 0";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.LEFT_OPERAND_LABEL + "10.0" + System.lineSeparator() +
                CalculationMessages.OPERATOR_LABEL + "/" + System.lineSeparator() +
                CalculationMessages.RIGHT_OPERAND_LABEL + "0.0" + System.lineSeparator() +
                CalculationMessages.DIVISION_BY_ZERO_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("지원되지 않는 연산자의 검증")
    void testUnsupportedOperator() {
        // Given
        String expression = "8 ^ 2";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.INVALID_EXPRESSION_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("잘못된 수식의 검증")
    void testInvalidExpression() {
        // Given
        String expression = "invalid expression";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.INVALID_EXPRESSION_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("숫자 포맷 예외의 검증")
    void testNumberFormatException() {
        // Given
        String expression = "5 + abc";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.INVALID_EXPRESSION_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    @DisplayName("공백이 추가된 수식의 검증")
    void testExpressionWithExtraSpaces() {
        // Given
        String expression = "  12   +   8  ";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.INVALID_EXPRESSION_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("연산자가 누락된 수식의 검증")
    void testExpressionMissingOperator() {
        // Given
        String expression = "5 6";
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.INVALID_EXPRESSION_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("수식에 특수문자가 포함된 경우의 검증")
    void testExpressionWithSpecialCharacters() {
        // Given
        String expression = "5 + @";
        Pattern pattern = Pattern.compile("(-?\\d+)\\s*([+\\-*/])\\s*(-?\\d+)");

        // When
        evaluator.evaluateExpression(expression, pattern);

        // Then
        String expectedOutput = CalculationMessages.INVALID_EXPRESSION_MESSAGE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}