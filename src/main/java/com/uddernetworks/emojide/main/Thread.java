package com.uddernetworks.emojide.main;

public class Thread {
    public static void sleep(long milliseconds) {
        try {
            java.lang.Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }
}
