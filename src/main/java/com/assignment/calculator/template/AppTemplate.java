package com.assignment.calculator.template;

/**
 * 애플리케이션의 템플릿을 정의하는 추상 클래스.
 */
public abstract class AppTemplate {

    /**
     * 애플리케이션 실행의 주요 흐름을 관리.
     */
    public final void run() {
        initializeApp(); // 애플리케이션 초기화
        while (shouldContinueRunning()) { // 애플리케이션이 계속 실행되어야 하는지 확인
            processInput(); // 사용자 입력 처리
        }
        terminateApp(); // 애플리케이션 종료 처리
    }

    /**
     * 애플리케이션을 초기화하는 추상 메서드.
     */
    protected abstract void initializeApp();

    /**
     * 애플리케이션이 계속 실행되어야 하는지를 결정하는 추상 메서드.
     *
     * @return 계속 실행할 경우 true, 그렇지 않으면 false
     */
    protected abstract boolean shouldContinueRunning();

    /**
     * 사용자 입력을 처리하는 추상 메서드.
     */
    protected abstract void processInput();

    /**
     * 애플리케이션을 종료하는 추상 메서드.
     */
    protected abstract void terminateApp();
}