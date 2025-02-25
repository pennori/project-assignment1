package com.assignment.calculator.constants.messages;

public class CalculationMessages {
    public static final String INVALID_EXPRESSION_MESSAGE = "유효한 수식을 입력해주세요.";
    public static final String DIVISION_BY_ZERO_MESSAGE = "0으로 나눌 수 없습니다.";
    public static final String RESULT_MESSAGE = "결과 : ";
    public static final String LEFT_OPERAND_LABEL = "좌측 피연산자 : ";
    public static final String OPERATOR_LABEL = "연산자 : ";
    public static final String RIGHT_OPERAND_LABEL = "우측 피연산자 : ";

    private CalculationMessages() {
        throw new UnsupportedOperationException("이 클래스는 인스턴스화할 수 없습니다.");
    }
}
