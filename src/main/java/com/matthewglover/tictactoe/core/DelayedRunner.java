package com.matthewglover.tictactoe.core;

import java.util.concurrent.FutureTask;

public class DelayedRunner {
    public static void run(int milliSecondsDelay, Runnable runnable) {
        FutureTask<Void> sleeper = new FutureTask<>(() -> {
            Thread.sleep(milliSecondsDelay);
            runnable.run();
            return null;
        });

        try {
            new Thread(sleeper).start();
        } catch (Exception e) {
        }
    }
}
