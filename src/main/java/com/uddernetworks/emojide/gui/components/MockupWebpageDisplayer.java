package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Displays emojis in an image outputted to <code>mockup.png</code>
 * Not meant for real world usage.
 */
public class MockupWebpageDisplayer implements Displayer {

    private static Logger LOGGER = LoggerFactory.getLogger(MockupWebpageDisplayer.class);

    private static final int EMOJI_LENGTH = 18;
    private static final int MAX_MESSAGE_LENGTH = 2000;
    private static final int MAX_EMOJIS_PER_LINE = 111;

    private static final int MOCKUP_EMOJI_SIZE = 33;

    public static AtomicBoolean UPDATED = new AtomicBoolean();

    private EmojIDE emojIDE;
    private EmojiComponent child;
    private boolean displaying;
    private List<Message> messages = new ArrayList<>();
    private Emoji filler;

    /**
     * Creates a {@link MockupWebpageDisplayer}.
     *
     * @param emojIDE The {@link EmojIDE} instance
     */
    public MockupWebpageDisplayer(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        this.filler = StaticEmoji.TRANSPARENT;

        // No need to have the render engine running
        // RenderEngine.start();
    }

    @Override
    public void setChild(EmojiComponent component) {
        setChild(component, false);
    }

    @Override
    public void setChild(EmojiComponent component, boolean autoUpdate) {
        if (this.child != null) throw new RuntimeException("Child has already been set for this Displayer!");
        this.child = component;
        if (autoUpdate) update();
    }

    @Override
    public EmojiComponent getChild() {
        return child;
    }

    @Override
    public boolean isDisplaying() {
        return displaying;
    }

    private BufferedImage fallback;
    private Map<Long, BufferedImage> imageCache = new HashMap<>();

    @Override
    public void update() {
        try {
            var start = System.currentTimeMillis();

            if (fallback == null)
                fallback = readImage("fallback", "https://cdn.discordapp.com/emojis/608702900105117714.png");

            displaying = true;
            boolean sendMessages = this.messages.isEmpty();
            if (sendMessages && this.child.getWidth() > MAX_EMOJIS_PER_LINE)
                throw new InvalidComponentException("Invalid width of " + this.child.getWidth() + " (Max is " + MAX_EMOJIS_PER_LINE + ")");
            var emotes = this.child.getCachedRender();
            if (emotes[0].length != this.child.getWidth() || emotes.length != this.child.getHeight())
                throw new InvalidComponentException("Incorrect render dimensions received. Got " + emotes[0].length + "x" + emotes.length + ", expected " + this.child.getWidth() + "x" + this.child.getHeight());

            var input = new AtomicReference<>(Files.readString(Paths.get("mockup-display.html")).replace("CONFIG_WEB_HOST", EmojIDE.getConfigManager().getConfig().get("web.host")));

            var x = new AtomicInteger();
            var y = new AtomicInteger();
            for (Emoji[] line : emotes) {
                Arrays.stream(line)
                        .map(emote -> emote == null ? this.filler : emote)
                        .map(StaticEmoji.class::cast)
                        .map(StaticEmoji::getRelativePath)
                        .forEach(url -> input.set(replaceIn(input.get(), x.getAndIncrement(), y.get(), "file:///" + new File(url).getAbsolutePath().replace(File.separator, "//"))));
                y.incrementAndGet();
                x.set(0);
            }

            Files.write(Paths.get("mockup-display.html"), input.get().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            UPDATED.set(true);
            LOGGER.info("Wrote to in {}ms", System.currentTimeMillis() - start);
        } catch (Exception e) {
            LOGGER.error("Error making webpage", e);
        }
    }

    private String replaceIn(String input, int x, int y, String url) {
        return input.replaceAll("aria-label=\"" + x + "x" + y + "\" src=\".*?\"", "aria-label=\"" + x + "x" + y + "\" src=\"" + url + "\"");
    }

    private BufferedImage readImage(String name, String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException ex) {
            LOGGER.error("Error reading image for " + name + " at URL " + url, ex);
            return null;
        }
    }

    @Override
    public void stop(TextChannel channel) {
        channel.purgeMessages(this.messages);
        this.messages.clear();
    }

    @Override
    public EmojIDE getEmojIDE() {
        return emojIDE;
    }

    @Override
    public EmojiManager getEmojiManager() {
        return this.emojIDE.getEmojiManager();
    }
}
