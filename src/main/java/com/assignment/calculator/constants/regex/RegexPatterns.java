package com.assignment.calculator.constants.regex;

public class RegexPatterns {
    // 정규식 설명:
    // ([0-9]+(?:\.[0-9]+)?) : 첫 번째 숫자 그룹 (정수 또는 소수)
    // \s*                  : 연산자 앞의 공백 문자 (0개 이상)
    // ([+\-*/])            : 산술 연산자 그룹 (+, -, *, / 중 하나)
    // \s*                  : 연산자 뒤의 공백 문자 (0개 이상)
    // ([0-9]+(?:\.[0-9]+)?) : 두 번째 숫자 그룹 (정수 또는 소수)
    public static final String BASIC_EXPRESSION_PATTERN = "([0-9]+(?:\\.[0-9]+)?)\\s*([+\\-*/])\\s*([0-9]+(?:\\.[0-9]+)?)";

    private RegexPatterns() {
        throw new UnsupportedOperationException("이 클래스는 인스턴스화할 수 없습니다.");
    }
}
