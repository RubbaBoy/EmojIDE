package com.uddernetworks.emojide.event;

public enum Priority {
    LUDICROUSLY_HIGH(6),
    SUPER_DUPER_HIGH(5),
    VERY_HIGH(4),
    HIGH(3),
    SLIGHTLY_HIGHER_THAN_NORMAL(2),
    NORMAL(1),
    LOW(0);

    private int priority;

    Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
