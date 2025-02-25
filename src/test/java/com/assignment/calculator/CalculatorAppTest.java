package com.assignment.calculator;

import com.assignment.calculator.evaluator.ExpressionEvaluator;
import com.assignment.calculator.input.InputHandler;
import com.assignment.calculator.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorAppTest {

    private CalculatorApp calculatorApp;
    private InputHandler mockInputHandler;
    private ExpressionEvaluator mockExpressionEvaluator;

    @BeforeEach
    void setUp() {
        calculatorApp = new CalculatorApp();
        calculatorApp.initializeApp();

        mockInputHandler = mock(InputHandler.class);
        mockExpressionEvaluator = mock(ExpressionEvaluator.class);

        // 초기화 메서드 호출 이후 리플렉션을 사용하여 프라이빗 필드 주입
        TestUtils.setPrivateField(calculatorApp, "userInputHandler", mockInputHandler);
        TestUtils.setPrivateField(calculatorApp, "expressionEvaluator", mockExpressionEvaluator);
    }

    @Test
    @DisplayName("애플리케이션이 올바르게 초기화되는지 확인")
    void testInitializeApp() {
        // Given - When : setUp()

        // Then
        assertTrue((boolean) TestUtils.getPrivateField(calculatorApp, "running"));
        assertEquals(0, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
        assertNotNull(TestUtils.getPrivateField(calculatorApp, "expressionPattern"));
        assertNotNull(TestUtils.getPrivateField(calculatorApp, "expressionEvaluator"));
        assertNotNull(TestUtils.getPrivateField(calculatorApp, "userInputHandler"));
    }

    @Test
    @DisplayName("애플리케이션이 계속 실행되어야 하는 조건을 만족하는지 확인")
    void testShouldContinueRunning_True() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "running", true);
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 10);

        // When
        boolean result = calculatorApp.shouldContinueRunning();

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("애플리케이션이 실행을 중지해야 하는 조건을 만족하는지 확인")
    void testShouldContinueRunning_False_RunningFalse() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "running", false);
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 10);

        // When
        boolean result = calculatorApp.shouldContinueRunning();

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("애플리케이션이 최대 반복 횟수에 도달했는지 확인")
    void testShouldContinueRunning_False_MaxIterations() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "running", true);
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 1000);

        // When
        boolean result = calculatorApp.shouldContinueRunning();

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("사용자가 종료 명령을 입력했을 때 애플리케이션이 올바르게 반응하는지 확인")
    void testProcessInput_Exit() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("exit");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        assertFalse((boolean) TestUtils.getPrivateField(calculatorApp, "running"));
    }

    @Test
    @DisplayName("사용자가 대문자 EXIT를 입력했을 때 애플리케이션이 올바르게 반응하는지 확인")
    void testProcessInput_UpperCaseExit() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("EXIT");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        assertFalse((boolean) TestUtils.getPrivateField(calculatorApp, "running"));
    }

    @Test
    @DisplayName("사용자가 소수점을 포함하는 수식을 입력했을 때 올바르게 평가되는지 확인")
    void testProcessInput_FloatingPointNumbers() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("1.5 + 2.75");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("1.5 + 2.75"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("사용자가 공백이 포함된 수식을 입력했을 때 올바르게 평가되는지 확인")
    void testProcessInput_ExpressionWithSpaces() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("  3 +  7  ");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("  3 +  7  "), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("사용자가 수식을 입력했을 때 올바르게 평가되는지 확인")
    void testProcessInput_EvaluateExpression() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("2 + 2");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("2 + 2"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("사용자가 잘못된 수식을 입력했을 때 올바르게 처리되는지 확인")
    void testProcessInput_InvalidExpression() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("invalid");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("invalid"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("사용자가 지원되지 않는 연산자를 입력했을 때 올바르게 처리되는지 확인")
    void testProcessInput_UnsupportedOperator() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("5 % 2");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("5 % 2"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("사용자가 0으로 나누는 연산을 입력했을 때 올바르게 처리되는지 확인")
    void testProcessInput_DivisionByZero() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("5 / 0");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("5 / 0"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("사용자가 빈 입력을 했을 때 애플리케이션의 정상 작동 확인")
    void testProcessInput_EmptyInput() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(0)).evaluateExpression(anyString(), any(Pattern.class));
        assertEquals(0, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("최대 반복 횟수에 도달했을 때 애플리케이션이 정상적으로 종료되는지 확인")
    void testTerminateApp_MaxIterations() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 1000);

        // When
        calculatorApp.terminateApp();

        // Then
        verify(mockInputHandler, times(1)).close();
    }

    @Test
    @DisplayName("정상적인 종료 과정에서 애플리케이션이 올바르게 종료되는지 확인")
    void testTerminateApp_Normal() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 10);

        // When
        calculatorApp.terminateApp();

        // Then
        verify(mockInputHandler, times(1)).close();
    }

    @Test
    @DisplayName("사용자가 null 입력을 했을 때 애플리케이션의 정상 작동 확인")
    void testProcessInput_NullInput() {
        // Given
        when(mockInputHandler.getInput()).thenReturn(null);

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        // Ensure no expressions are evaluated and iteration is not incremented
        verify(mockExpressionEvaluator, times(0)).evaluateExpression(anyString(), any(Pattern.class));
        assertEquals(0, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("올바른 수식을 입력했을 때에만 반복 횟수를 증가시키는지 확인")
    void testProcessInput_ValidExpressionIncrementsIteration() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 0);
        when(mockInputHandler.getInput()).thenReturn("3 + 5");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("3 + 5"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("숫자 단독 입력 시 애플리케이션의 동작 확인")
    void testProcessInput_NumericOnly() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("42");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("42"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("유효하지 않은 패턴 입력 시 애플리케이션의 동작 확인")
    void testProcessInput_InvalidPattern() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("#bad-input");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("#bad-input"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("매우 큰 숫자를 포함하는 수식을 올바르게 처리하는지 확인")
    void testProcessInput_LargeNumbers() {
        // Given
        when(mockInputHandler.getInput()).thenReturn("999999999 * 999999999");

        // When
        calculatorApp.processInput();

        // Then
        verify(mockInputHandler, times(1)).getInput();
        verify(mockExpressionEvaluator, times(1)).evaluateExpression(eq("999999999 * 999999999"), any(Pattern.class));
        assertEquals(1, TestUtils.getPrivateField(calculatorApp, "currentIteration"));
    }

    @Test
    @DisplayName("애플리케이션 종료 후 내부 상태가 초기화되는지 확인")
    void testTerminateAppResetsState() {
        // Given
        TestUtils.setPrivateField(calculatorApp, "running", true);
        TestUtils.setPrivateField(calculatorApp, "currentIteration", 500);

        // When
        calculatorApp.terminateApp();

        // Then
        assertFalse((boolean) TestUtils.getPrivateField(calculatorApp, "running"));
        assertEquals(500, TestUtils.getPrivateField(calculatorApp, "currentIteration")); // State remains valid until reset.
    }
}