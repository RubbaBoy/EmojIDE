package com.uddernetworks.emojide.discord.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.uddernetworks.emojide.discord.command.CommandResult.*;

public class CommandManager extends ListenerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);

    private static Map<Command, Object> commands = new HashMap<>();
    private static Map<Command, Method> defaultMethod = new HashMap<>();
    private static Map<Command, List<ArgumentMethodEntry>> arguments = new HashMap<>();

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var member = event.getMember();
        var channel = event.getTextChannel();
        var stripped = event.getMessage().getContentStripped();
        if (!stripped.startsWith("!")) return;
        var args = stripped.split("\\s+");
        var name = args[0].substring(1);
        args = Arrays.stream(args).skip(1).toArray(String[]::new);
        Optional<Command> annotationOptional = getCommandAnnotation(name);
        if (member == null || annotationOptional.isEmpty()) return;
        Command annotation = annotationOptional.get();

        Object executor = commands.get(annotation);

        if ((annotation.maxArgs() != -1 && args.length > annotation.maxArgs()) || (annotation.minArgs() != -1 && args.length < annotation.minArgs())) {
            sendError(annotation, executor, member, channel, "Invalid argument count");
            return;
        }

        if (!member.hasPermission(annotation.permission())) {
            sendError(annotation, executor, member, channel, "You don't have permission to perform this action");
            return;
        }

        for (ArgumentMethodEntry entry : arguments.get(annotation)) {
            if (invokeArgMethod(member, channel, entry.getKey(), entry.getValue(), args, executor, annotation) == SUCCESS)
                return;
        }

        sendError(annotation, executor, member, channel, "Invalid arguments: " + String.join(" ", args));
    }

    public CommandManager registerCommand(Object instance) {
        if (!instance.getClass().isAnnotationPresent(Command.class)) {
            throw new RuntimeException("Error trying to register command. No Command annotation for class.");
        }

        Command command = instance.getClass().getAnnotation(Command.class);

        if (commandRegistered(command.name())) {
            throw new RuntimeException("Error trying to register command. Command name already registered.");
        }

        commands.put(command, instance);
        populateArguments(instance.getClass());
        return this;
    }

    private boolean commandRegistered(String command) {
        return commands.keySet().stream().anyMatch(cmd -> cmd.name().equalsIgnoreCase(command));
    }

    public void populateArguments(Class<?> clazz) {
        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        Command command = clazz.getAnnotation(Command.class);

        methods.stream().filter(method -> method.isAnnotationPresent(ArgumentError.class)).findFirst().map(method -> defaultMethod.put(command, method));

        List<ArgumentMethodEntry> entries = new ArrayList<>();
        methods.stream().filter(method -> method.isAnnotationPresent(Argument.class)).forEach(method -> entries.add(new ArgumentMethodEntry(method.getAnnotation(Argument.class), method)));

        arguments.put(command, entries);
    }

    private Optional<Command> getCommandAnnotation(String name) {
        return commands.keySet().stream().filter(cmd -> cmd.name().equalsIgnoreCase(name) || Arrays.stream(cmd.aliases()).anyMatch(alias -> alias.equalsIgnoreCase(name))).findFirst();
    }

    private CommandResult invokeArgMethod(Member member, TextChannel channel, Argument argument, Method method, String[] args, Object instance, Command command) {
        List<String> realArgs = getRealArguments(argument.format(), String.join(" ", args).trim());

        if (realArgs == null) return INVALID_SYNTAX;

        ArgumentList argumentList = new ArgumentList();

        realArgs.forEach(realArg -> argumentList.add(new CommandArg(realArg)));

        if (!member.hasPermission(argument.permission())) {
            sendError(command, instance, member, channel, "You don't have permission to preform this action");
            return INVALID_PERMISSION;
        }

        if (argumentList.isEmpty()) {
            try {
                method.invoke(instance, member, channel);
                return SUCCESS;
            } catch (ReflectiveOperationException ignored) {}
        }

        try {
            method.invoke(instance, member, channel, argumentList);
            return SUCCESS;
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Error while invoking command", e);
        }

        return INVALID_SYNTAX;
    }

    private void sendError(Command annotation, Object executor, Member member, TextChannel channel, String message) {
        if (defaultMethod.containsKey(annotation)) {
            try {
                defaultMethod.get(annotation).invoke(executor, member, channel, message);
            } catch (ReflectiveOperationException e) {
                LOGGER.error("Error while invoking default command method", e);
            }
        }
    }

    private List<String> getRealArguments(String template, String input) {
        List<String> templateArgs = Arrays.asList(template.toLowerCase().split("\\s+"));
        List<String> realArgs = getQuotes(input);
        List<String> ret = new ArrayList<>();
        if ("".equals(template) && (input == null || "".equals(input))) return ret;

        if (templateArgs.size() > realArgs.size()) return null;

        for (int i = 0; i < templateArgs.size(); i++) {
            String currentTemplate = templateArgs.get(i);
            String currentReal = realArgs.get(i);
            if (!currentTemplate.equalsIgnoreCase("*") && !currentTemplate.equalsIgnoreCase("@p") && !currentTemplate.equalsIgnoreCase(currentReal)) {
                if (currentTemplate.startsWith("[") && currentTemplate.endsWith("]")) {
                    String debracketed = currentTemplate.substring(1, currentTemplate.length() - 1);
                    List<String> parts = Arrays.asList(debracketed.split(","));

                    if (!parts.contains(currentReal.toLowerCase())) return null;
                    ret.add(realArgs.get(i));
                    continue;
                }

                return null;
            } else if (currentTemplate.equalsIgnoreCase("*") || currentTemplate.equalsIgnoreCase("@p")) {
                ret.add(currentReal);
            } else if (currentTemplate.equalsIgnoreCase("*~")) {
                ret.add(currentReal);

                for (int i2 = 0; i2 < templateArgs.size() - i - 1; i2++) {
                    ret.add(i, ret.get(i) + " " + currentReal);
                }
                return ret;
            }
        }

        return ret;
    }

    private List<String> getQuotes(String input) {
        List<String> matched = new ArrayList<>();
        Matcher regexMatcher = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'").matcher(input);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                matched.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                matched.add(regexMatcher.group(2));
            } else {
                matched.add(regexMatcher.group());
            }
        }
        return matched;
    }

    private static class ArgumentMethodEntry implements Map.Entry<Argument, Method> {
        private Argument arg;
        private Method method;

        private ArgumentMethodEntry(Argument arg, Method method) {
            this.arg = arg;
            this.method = method;
        }

        @Override
        public Argument getKey() {
            return arg;
        }

        @Override
        public Method getValue() {
            return method;
        }

        @Override
        public Method setValue(Method method) {
            Method old = this.method;
            this.method = method;
            return old;
        }
    }

}
