package com.uddernetworks.emojide.discord.command;

public class CommandArg {

    private String input;

    public CommandArg(String input) {
        this.input = input;
    }

    public String getString() {
        return input;
    }

    public int getInt() {
        return Integer.parseInt(input);
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(input);
    }

    public long getLong() {
        return Long.parseLong(input);
    }

    public short getShort() {
        return Short.parseShort(input);
    }

    public byte getByte() {
        return Byte.parseByte(input);
    }

}
