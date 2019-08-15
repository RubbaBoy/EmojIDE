package com.uddernetworks.emojide.gui.theme;

import com.uddernetworks.emojide.main.ChoosableEnum;

public enum Theme implements ChoosableEnum {
    DEFAULT("Default"),
    INTELLIJ("IntelliJ");

    private String name;

    Theme(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
