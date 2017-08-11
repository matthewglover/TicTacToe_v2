package com.matthewglover.tictactoe.core;

public class ImmediateRunner implements Runner {
    @Override
    public void run(int milliSecondsDelay, Runnable runnable) {
        runnable.run();
    }
}
