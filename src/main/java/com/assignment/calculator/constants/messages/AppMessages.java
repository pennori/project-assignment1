package com.assignment.calculator.constants.messages;

public class AppMessages {
    public static final String PROMPT_MESSAGE = "수식을 입력해주세요 (예: 1.1 + 2.34). 프로그램을 종료하려면 'exit'을 입력하세요. : ";
    public static final String EXIT_MESSAGE = "프로그램을 종료합니다.";
    public static final String MAX_ITERATION_MESSAGE = "최대 반복 횟수에 도달하여 프로그램을 종료합니다.";

    private AppMessages() {
        throw new UnsupportedOperationException("이 클래스는 인스턴스화할 수 없습니다.");
    }
}
