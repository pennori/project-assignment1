package com.assignment.calculator.template;

public abstract class AppTemplate {
    public final void run() {
        initializeApp();
        while (shouldContinueRunning()) {
            processInput();
        }
        terminateApp();
    }

    protected abstract void initializeApp();
    protected abstract boolean shouldContinueRunning();
    protected abstract void processInput();
    protected abstract void terminateApp();
}