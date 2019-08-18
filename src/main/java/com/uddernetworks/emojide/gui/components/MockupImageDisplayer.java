package com.uddernetworks.emojide.gui.components;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Displays emojis in an image outputted to <code>mockup.png</code>
 * Not meant for real world usage.
 */
public class MockupImageDisplayer implements Displayer {

    private static Logger LOGGER = LoggerFactory.getLogger(MockupImageDisplayer.class);

    private static final int EMOJI_LENGTH = 18;
    private static final int MAX_MESSAGE_LENGTH = 2000;
    private static final int MAX_EMOJIS_PER_LINE = 111;

    private static final int MOCKUP_EMOJI_SIZE = 33;

    private EmojIDE emojIDE;
    private TextChannel channel;
    private EmojiComponent child;
    private boolean displaying;
    private List<Message> messages = new ArrayList<>();
    private Emoji filler;
    private FileConfig config = EmojIDE.getConfigManager().getConfig();

    /**
     * Creates a {@link MockupImageDisplayer}.
     *
     * @param emojIDE The {@link EmojIDE} instance
     * @param channel The channel to send the image to
     */
    public MockupImageDisplayer(EmojIDE emojIDE, TextChannel channel) {
        this.emojIDE = emojIDE;
        this.channel = channel;
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
        if (fallback == null)
            fallback = readImage("fallback", "https://cdn.discordapp.com/emojis/608702900105117714.png");

        displaying = true;
        boolean sendMessages = this.messages.isEmpty();
        if (sendMessages && this.child.getWidth() > MAX_EMOJIS_PER_LINE)
            throw new InvalidComponentException("Invalid width of " + this.child.getWidth() + " (Max is " + MAX_EMOJIS_PER_LINE + ")");
        var emotes = this.child.getCachedRender();
        if (emotes[0].length != this.child.getWidth() || emotes.length != this.child.getHeight())
            throw new InvalidComponentException("Incorrect render dimensions received. Got " + emotes[0].length + "x" + emotes.length + ", expected " + this.child.getWidth() + "x" + this.child.getHeight());


        var mockup = new BufferedImage(emotes[0].length * MOCKUP_EMOJI_SIZE, emotes.length * MOCKUP_EMOJI_SIZE, BufferedImage.TYPE_INT_ARGB);
        var mockupGraphics = mockup.createGraphics();
        var x = new AtomicInteger();
        var y = new AtomicInteger();
        for (Emoji[] line : emotes) {
            Arrays.stream(line)
                    .map(emote -> emote == null ? this.filler : emote)
                    .map(StaticEmoji.class::cast)
                    .map(emoji -> imageCache.computeIfAbsent(emoji.getId(), e -> scaleImage(readImage(emoji.getName(), new File(emoji.getRelativePath())))))
                    .map(emote -> emote == null ? fallback : emote)
                    .forEach(image -> {
                        mockupGraphics.drawImage(image, null, x.get(), y.get());
                        x.addAndGet(MOCKUP_EMOJI_SIZE);
                    });

            LOGGER.info("Line");
            y.addAndGet(MOCKUP_EMOJI_SIZE);
            x.set(0);
        }

        mockupGraphics.dispose();
        var mockupFile = new File("mockup.png");
        try {
            LOGGER.info("Writing@");
            ImageIO.write(mockup, "png", mockupFile);
            channel.sendFile(mockupFile).queue();
        } catch (IOException e) {
            LOGGER.error("Error while writing or sending mockup image", e);
        }
    }

    private BufferedImage readImage(String name, File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            LOGGER.error("Error reading image for " + name + " at file location " + file.getAbsolutePath(), ex);
            return null;
        }
    }

    private BufferedImage readImage(String name, String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException ex) {
            LOGGER.error("Error reading image for " + name + " at URL " + url, ex);
            return null;
        }
    }

    private BufferedImage scaleImage(BufferedImage input) {
        if (input == null) return null;
        var res = new BufferedImage(MOCKUP_EMOJI_SIZE, MOCKUP_EMOJI_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = res.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(input, 0, 0, MOCKUP_EMOJI_SIZE, MOCKUP_EMOJI_SIZE, null);
        g2.dispose();
        return res;
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
