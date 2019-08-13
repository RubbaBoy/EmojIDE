package com.uddernetworks.emojide.discord.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentList {

    private List<CommandArg> args = new ArrayList<>();
    private int currentIndex = 0;

    public ArgumentList() {}

    public ArgumentList(CommandArg... args) {
        this.args.addAll(Arrays.asList(args));
    }

    public void add(CommandArg commandArg) {
        args.add(commandArg);
    }

    public CommandArg nextArg() {
        currentIndex++;
        return args.get(currentIndex - 1);
    }

    public boolean isEmpty() {
        return args.isEmpty();
    }

}
