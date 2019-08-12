package com.uddernetworks.emojide.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
    String event();
    boolean overrideSuspend() default false;

    /**
     * The higher the priority, the sooner the event will be received.
     *
     * @return The priority
     */
    Priority priority() default Priority.NORMAL;
}
