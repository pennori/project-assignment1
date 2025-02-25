// InputHandlerTest.java
package com.assignment.calculator.input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private InputHandler inputHandler;

    @BeforeEach
    void setUp() {
        inputHandler = new InputHandler();
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        inputHandler.close();
    }

    @Test
    @DisplayName("사용자 입력을 정상적으로 읽어오는지 테스트")
    void testGetInput() {
        // Given
        String simulatedInput = "테스트 입력";
        testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        // 기존 Scanner 닫기
        inputHandler.close();
        // InputHandler 재생성
        inputHandler = new InputHandler();

        // When
        String input = inputHandler.getInput();

        // Then
        assertEquals(simulatedInput, input);
    }

    @Test
    @DisplayName("InputHandler의 close 메서드가 예외 없이 정상적으로 실행되는지 테스트")
    void testClose() {
        // Given

        // When, Then
        assertDoesNotThrow(() -> inputHandler.close());
    }
}