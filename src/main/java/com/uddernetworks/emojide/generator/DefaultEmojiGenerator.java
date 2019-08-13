package com.uddernetworks.emojide.generator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.uddernetworks.emojide.generator.LetterGenerator.*;

public class DefaultEmojiGenerator implements EmojiGenerator {

    private static final int WIDTH = 256;
    private static final int HEIGHT = 256;

    public static void main(String[] args) {
        new DefaultEmojiGenerator().generate();
    }

    @Override
    public void generate() {
        var parent = new File("generated_emojis");
        parent.mkdirs();

        // Generate text

        Arrays.asList(
                new FontData("", Color.WHITE), // White
                new FontData("o", new Color(0xCC7832), true), // Orange
                new FontData("g", new Color(0x6A8759)), // Green
                new FontData("b", new Color(0x6897BB)), // Blue
                new FontData("a", new Color(0x808080)), // Gray
                new FontData("l", new Color(0x666666)) // Light Gray
        ).forEach(fontData -> Arrays.stream(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".split("")).forEach(character -> drawAndSave(parent, character, fontData)));

        // Generate color palette
        generateColorPalette(parent,
                "blue1", new Color(47, 128, 237),
                "blue2", new Color(45, 156, 219),
                "blue3", new Color(86, 204, 242),
                "discord", new Color(54, 57, 63),
                "gray1", new Color(51, 51, 51),
                "gray2", new Color(79, 79, 79),
                "gray3", new Color(130, 130, 130),
                "gray4", new Color(189, 189, 189),
                "gray5", new Color(224, 224, 224),
                "gray6", new Color(242, 242, 242),
                "green1", new Color(33, 150, 83),
                "green2", new Color(39, 174, 96),
                "green3", new Color(111, 207, 151),
                "orange", new Color(242, 153, 74),
                "purple1", new Color(155, 81, 224),
                "purple2", new Color(187, 107, 217),
                "red", new Color(235, 87, 87),
                "transparent", new Color(0, 0, 0, 0),
                "yellow", new Color(242, 201, 76));
    }

    private void generateColorPalette(File parent, Object... objects) {
        if (objects.length % 2 != 0) throw new IllegalArgumentException("Argument must be a number divisible by 2");
        for (int i = 0; i < objects.length; i += 2) {
            var name = (String) objects[i];
            var color = (Color) objects[i + 1];

            var saveFile = new File(parent, name + ".png");
            if (saveFile.exists()) continue;

            var image = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setPaint(color);
            graphics.fillRect(0, 0, 25, 25);
            graphics.dispose();

            try {
                ImageIO.write(image, "png", saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawAndSave(File parent, String character, FontData fontData) {
        var saveFile = new File(parent, fontData.prefix + ((int) character.charAt(0)) + ".png");
        if (saveFile.exists()) return;
        BufferedImage image = new BufferedImage(WIDTH + 50, HEIGHT + 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(TRANSPARENT);
        graphics.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);
        graphics.setPaint(fontData.color);
        graphics.setFont(new Font("Consolas", fontData.bold ? Font.BOLD : Font.PLAIN, 256)); // 192pts
        FontMetrics metrics = graphics.getFontMetrics();
        int x = (WIDTH - metrics.stringWidth(character)) / 2;
        x += 25;
        int y = ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
        y += 25;
        graphics.drawString(character, x, y);
        graphics.dispose();

        var values = createGrid(image);
        toGrid(image, values);

        var trimmed = trim(values);

        image = makeImage(trimmed);

        image = fitTo(image, WIDTH, HEIGHT);

        try {
            ImageIO.write(image, "png", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage fitTo(BufferedImage image, int width, int height) {
        var larger = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var posX = (larger.getWidth() / 2D) - (image.getWidth() / 2D);
        var posY = (larger.getHeight() / 2D) - (image.getHeight() / 2D);
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, (int) posX, (int) posY);
        graphics.dispose();
        return larger;
    }

    private BufferedImage makeImage(double[][] values) {
        BufferedImage image = new BufferedImage(values[0].length, values.length, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int val = (int) (values[y][x]);
                image.setRGB(x, y, val);
            }
        }

        return image;
    }

    class FontData {
        private String prefix;
        private Color color;
        private boolean bold;

        public FontData(String prefix, Color color) {
            this(prefix, color, false);
        }

        public FontData(String prefix, Color color, boolean bold) {
            this.prefix = prefix;
            this.color = color;
            this.bold = bold;
        }

        public String getPrefix() {
            return prefix;
        }

        public Color getColor() {
            return color;
        }

        public boolean isBold() {
            return bold;
        }
    }

}
