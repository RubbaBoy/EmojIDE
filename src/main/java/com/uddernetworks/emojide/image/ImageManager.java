package com.uddernetworks.emojide.image;

import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.ImagePiece;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simplenet.utility.IntPair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImageManager {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageManager.class);

    private JDA jda;
    private EmojIDE emojIDE;
    private EmojiManager emojiManager;

    public ImageManager(EmojIDE emojIDE) {
        this.jda = emojIDE.getJda();
        this.emojIDE = emojIDE;
        this.emojiManager = emojIDE.getEmojiManager();


        LOGGER.info("Loading images...");

        Arrays.stream(PreloadedImage.values()).forEach(preloadedImage -> {
            try {

                var existing = getExisting(preloadedImage);
                if (preloadedImage.getWidth() * preloadedImage.getHeight() == existing.size()) {
                    LOGGER.info("[{}] Image already exists", preloadedImage.getName());
                    return;
                }

                if (!existing.isEmpty()) LOGGER.info("[{}] Removing {} emojis", preloadedImage.getName(), existing.size());
                existing.forEach(emote -> emote.delete().complete());

                var width = preloadedImage.getWidth();
                var height = preloadedImage.getHeight();
                var input = ImageIO.read(preloadedImage.getFile());
                var iconWidth = (int) Math.floor(input.getWidth() / (double) width);
                var iconHeight = (int) Math.floor(input.getHeight() / (double) height);

                LOGGER.info("[{}] Icon dimensions: {}x{}", preloadedImage.getName(), iconWidth, iconHeight);

                for (int yBlock = 0; yBlock < height; yBlock++) {
                    for (int xBlock = 0; xBlock < width; xBlock++) {
                        BufferedImage subImage = input.getSubimage(xBlock * iconWidth, yBlock * iconHeight, iconWidth, iconHeight);

                        var name = xBlock + preloadedImage.getName() + yBlock;
                        var file = new File(name + ".png");
                        ImageIO.write(subImage, "png", file);
                        var emoteOptional = this.emojiManager.uploadEmote(name, file);
                        file.delete();
                        if (emoteOptional.isEmpty()) {
                            LOGGER.error("[{}] Error uploading {}x{}", preloadedImage.getName(), xBlock, yBlock);
                            continue;
                        }

                        preloadedImage.setPiece(new ImagePiece(name, name, emoteOptional.get()), xBlock, yBlock);
                    }
                }

            } catch (IOException e) {
                LOGGER.error("[" + preloadedImage.getName() + "] Error while processing PreloadedImage", e);
            }

            LOGGER.info("[{}] Finished loading", preloadedImage.getName());
        });
    }

    private List<Emote> getExisting(PreloadedImage image) {
        var existing = new ArrayList<Emote>();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                existing.addAll(this.jda.getEmotesByName(x + image.getName() + y, false));
            }
        }
        return existing;
    }
}
