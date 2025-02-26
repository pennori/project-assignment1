package com.assignment.calculator.evaluator;

import com.assignment.calculator.constants.messages.CalculationMessages;
import com.assignment.calculator.strategy.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 표현식을 평가하는 클래스
 */
public class ExpressionEvaluator {
    // 연산자와 해당 전략을 매핑하는 맵
    private final Map<String, CalculationStrategy> calculationStrategies;

    /**
     * 생성자: 계산 전략을 초기화함
     */
    public ExpressionEvaluator() {
        calculationStrategies = createCalculationStrategies();
    }

    /**
     * 모든 계산 전략을 생성하고 매핑하는 메서드
     * @return 연산자와 전략이 매핑된 맵
     */
    private Map<String, CalculationStrategy> createCalculationStrategies() {
        Map<String, CalculationStrategy> strategies = new HashMap<>();
        strategies.put("+", new AdditionStrategy());
        strategies.put("-", new SubtractionStrategy());
        strategies.put("*", new MultiplicationStrategy());
        strategies.put("/", new DivisionStrategy());
        return strategies;
    }

    /**
     * 주어진 표현식을 평가하는 메서드
     * @param expression 평가할 수식
     * @param pattern 수식을 파싱하기 위한 정규식 패턴
     */
    public void evaluateExpression(String expression, Pattern pattern) {
        ParsedExpression parsed = parseExpression(expression, pattern);

        // 수식이 유효하지 않은 경우 메시지 출력
        if (parsed == null) {
            System.out.println(CalculationMessages.INVALID_EXPRESSION_MESSAGE);
            return;
        }

        // 피연산자 출력
        displayOperands(parsed);

        // 해당 연산자에 맞는 전략 가져오기
        CalculationStrategy strategy = calculationStrategies.get(parsed.operator());

        // 전략이 없는 경우 메시지 출력
        if (strategy == null) {
            System.out.println(CalculationMessages.INVALID_EXPRESSION_MESSAGE);
            return;
        }

        // 계산 수행
        double result = strategy.calculate(parsed.leftOperand(), parsed.rightOperand());

        // 결과 출력 또는 오류 메시지 출력
        if (Double.isNaN(result)) {
            System.out.println(CalculationMessages.DIVISION_BY_ZERO_MESSAGE);
        } else {
            System.out.println(CalculationMessages.RESULT_MESSAGE + result);
        }
    }

    /**
     * 수식을 파싱하여 피연산자와 연산자를 추출하는 메서드
     * @param expression 수식 문자열
     * @param pattern 파싱을 위한 정규식 패턴
     * @return 파싱된 표현식 또는 null
     */
    private ParsedExpression parseExpression(String expression, Pattern pattern) {
        Matcher matcher = pattern.matcher(expression);

        // 패턴에 맞는 경우
        if (matcher.matches()) {
            try {
                double left = Double.parseDouble(matcher.group(1));
                String operator = matcher.group(2);
                double right = Double.parseDouble(matcher.group(3));
                return new ParsedExpression(left, operator, right);
            } catch (NumberFormatException e) {
                // 숫자 변환 실패 시 null 반환
                return null;
            }
        }
        // 패턴에 맞지 않는 경우 null 반환
        return null;
    }

    /**
     * 파싱된 피연산자와 연산자를 출력하는 메서드
     * @param parsed 파싱된 표현식
     */
    private void displayOperands(ParsedExpression parsed) {
        System.out.println(CalculationMessages.LEFT_OPERAND_LABEL + parsed.leftOperand());
        System.out.println(CalculationMessages.OPERATOR_LABEL + parsed.operator());
        System.out.println(CalculationMessages.RIGHT_OPERAND_LABEL + parsed.rightOperand());
    }

    /**
     * 파싱된 표현식을 나타내는 레코드
     */
    private record ParsedExpression(double leftOperand, String operator, double rightOperand) {
    }
}