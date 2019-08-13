package com.uddernetworks.emojide.discord.command;

import net.dv8tion.jda.api.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Command {
    String name();
    String[] aliases() default "";
    Permission[] permission() default {};
    int minArgs() default -1;
    int maxArgs() default -1;
}
