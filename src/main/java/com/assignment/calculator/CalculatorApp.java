package com.assignment.calculator;

import com.assignment.calculator.constants.messages.AppMessages;
import com.assignment.calculator.constants.messages.CalculationMessages;
import com.assignment.calculator.constants.regex.RegexPatterns;
import com.assignment.calculator.evaluator.ExpressionEvaluator;
import com.assignment.calculator.input.InputHandler;
import com.assignment.calculator.template.AppTemplate;

import java.util.regex.Pattern;

/**
 * 계산기 애플리케이션의 주요 클래스.
 */
public class CalculatorApp extends AppTemplate {
    // 최대 반복 횟수 설정
    private static final int MAX_ITERATIONS = 1000;
    // 애플리케이션 실행 상태를 나타내는 변수
    private boolean running;
    // 현재 반복 횟수
    private int currentIteration;
    // 수식의 패턴을 저장하는 정규식 패턴
    private Pattern expressionPattern;
    // 수식을 평가하는 객체
    private ExpressionEvaluator expressionEvaluator;
    // 사용자 입력을 처리하는 객체
    private InputHandler userInputHandler;

    /**
     * 애플리케이션 초기화 메서드
     */
    @Override
    protected void initializeApp() {
        running = true;
        currentIteration = 0;
        // 기본 수식 패턴 컴파일
        expressionPattern = Pattern.compile(RegexPatterns.BASIC_EXPRESSION_PATTERN);
        expressionEvaluator = new ExpressionEvaluator();
        userInputHandler = new InputHandler();
    }

    /**
     * 애플리케이션이 계속 실행되어야 하는지 여부를 판단하는 메서드
     * @return 실행 상태 및 반복 횟수가 최대치 미만인지 여부
     */
    @Override
    protected boolean shouldContinueRunning() {
        return running && currentIteration < MAX_ITERATIONS;
    }

    /**
     * 사용자 입력을 처리하는 메서드
     */
    @Override
    protected void processInput() {
        // 사용자에게 입력을 요청
        System.out.print(AppMessages.PROMPT_MESSAGE);
        String input = userInputHandler.getInput();

        if (input != null) {
            input = input.trim();
        }

        // 입력이 비어있거나 null인 경우 오류 메시지 출력
        if (input == null || input.isEmpty()) {
            System.out.println(CalculationMessages.INVALID_EXPRESSION_MESSAGE);
            return;
        }

        // "exit" 입력 시 애플리케이션 종료
        if (input.equalsIgnoreCase("exit")) {
            running = false;
            System.out.println(AppMessages.EXIT_MESSAGE);
            return;
        }

        // 수식을 평가하고 현재 반복 횟수 증가
        expressionEvaluator.evaluateExpression(input, expressionPattern);
        currentIteration++;
    }

    /**
     * 애플리케이션 종료 시 호출되는 메서드
     */
    @Override
    protected void terminateApp() {
        this.running = false;

        // 최대 반복 횟수에 도달한 경우 메시지 출력
        if (currentIteration >= MAX_ITERATIONS) {
            System.out.println(AppMessages.MAX_ITERATION_MESSAGE);
        }

        // 입력 핸들러 리소스 해제
        userInputHandler.close();
    }
}