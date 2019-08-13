package com.uddernetworks.emojide.ide;

import com.uddernetworks.emojide.discord.DefaultEmojiManager;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.utils.Commandline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ConsolePiper {

    private static Logger LOGGER = LoggerFactory.getLogger(ConsolePiper.class);

    private final StaticTextFrame textFrame;
    private List<CompletableFuture> running = new ArrayList<>();

    public ConsolePiper(StaticTextFrame textFrame) {
        this.textFrame = textFrame;
    }

    public void pipeCommand(List<String> command, File directory) {
        pipeCommand(command, directory, "Piped");
    }

    public void pipeCommand(List<String> command, File directory, String threadName) {
        Commandline.runInheritedCommand(command, directory, true, pipeCommand(threadName));
    }

    public Consumer<Process> pipeCommand(Consumer<Process> processCreate) {
        return pipeCommand("Piped");
    }

    public Consumer<Process> pipeCommand(String threadName) {
        if (!running.isEmpty()) {
            running.forEach(future -> future.cancel(true));
            running.clear();
        }
        textFrame.setText("");
        textFrame.refresh();

        return process -> {
            inheritIO(process.getInputStream(), threadName);
            inheritIO(process.getErrorStream(), threadName);
        };
    }

    private void inheritIO(InputStream inputStream, String threadName) {
        running.add(CompletableFuture.runAsync(() -> {
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNextLine()) {
                var line = sc.nextLine();
                synchronized (textFrame) {
                    LOGGER.info("[{}] {}", threadName, line);
                    textFrame.setText(textFrame.getText() + line);
                    textFrame.refresh();
                }
            }
        }));
    }
}
