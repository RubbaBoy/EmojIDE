package com.uddernetworks.emojide.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.uddernetworks.emojide.generator.DefaultEmojiGenerator.CharAlign.*;
import static com.uddernetworks.emojide.generator.LetterGenerator.*;

public class DefaultEmojiGenerator implements EmojiGenerator {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultEmojiGenerator.class);

    private static final int WIDTH = 256;
    private static final int HEIGHT = 256;

    private final Map<Character, CharAlign> CHAR_ALIGNMENTS = Map.of(
            '.', BOTTOM,
            ',', BOTTOM,
            '\'', TOP,
            '"', TOP,
            '^', TOP,
            '`', TOP,
            '*', TOP,
            ';', LEFT,
            ':', LEFT,
            '!', LEFT
    );

    public static void main(String[] args) {
        new DefaultEmojiGenerator().generate();
    }

    @Override
    public void generate() {
        var parent = new File("generated_emojis");
        parent.mkdirs();

        // Generate text

        try {
            var font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\FiraCode-Medium.ttf"));
        } catch (FontFormatException | IOException e) {
            LOGGER.error("Error loading Fira Code Medium", e);
        }

        Arrays.asList(
                new FontData("", Color.WHITE, "Consolas"), // White
                new FontData("t", Color.WHITE, new Color(0x3C3F41), "Consolas"), // White for IntelliJ's unselected tab text
                new FontData("e", Color.WHITE, new Color(0x4E5254), "Consolas"), // White for IntelliJ's selected tab text
                new FontData("o", new Color(0xCC7832), "Consolas", true), // Orange
                new FontData("g", new Color(0x6A8759), "Consolas"), // Green
                new FontData("b", new Color(0x6897BB), "Consolas"), // Blue
                new FontData("a", new Color(0x808080), "Consolas"), // Gray
                new FontData("l", new Color(0x666666), "Consolas"), // Light Gray
                new FontData("f", Color.WHITE, "Fira Code Medium"), // White
                new FontData("ft", Color.WHITE, new Color(0x3C3F41), "Fira Code Medium"), // White for IntelliJ's unselected tab text
                new FontData("fe", Color.WHITE, new Color(0x4E5254), "Fira Code Medium") ,// White for IntelliJ's selected tab text
                new FontData("fo", new Color(0xCC7832), "Fira Code Medium", true), // Orange
                new FontData("fg", new Color(0x6A8759), "Fira Code Medium"), // Green
                new FontData("fb", new Color(0x6897BB), "Fira Code Medium"), // Blue
                new FontData("fa", new Color(0x808080), "Fira Code Medium"), // Gray
                new FontData("fl", new Color(0x666666), "Fira Code Medium") // Light Gray
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

        try {
            generateIntelliJ();
        } catch (IOException e) {
            LOGGER.error("An error occurred while generating the IntelliJ base template", e);
        }
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
        var image = new BufferedImage(WIDTH + 200, HEIGHT + 200, BufferedImage.TYPE_INT_ARGB);
        var graphics = image.createGraphics();
        graphics.setPaint(TRANSPARENT);
        graphics.fillRect(0, 0, WIDTH + 200, HEIGHT + 200);
        graphics.setPaint(fontData.color);
        graphics.setFont(new Font(fontData.font, fontData.bold ? Font.BOLD : Font.PLAIN, 256)); // 192pts
        FontMetrics metrics = graphics.getFontMetrics();
        int x = (WIDTH - metrics.stringWidth(character)) / 2;
        x += 100;
        int y = ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
        y += 100;
        graphics.drawString(character, x, y);
        graphics.dispose();

        var values = createGrid(image);
        toGrid(image, values);

        var trimmed = trim(values);

        image = makeImage(trimmed);

        switch (CHAR_ALIGNMENTS.getOrDefault(character.charAt(0), MIDDLE)) {
            case TOP:
                image = alignTop(image);
                break;
            case MIDDLE:
                image = alignMiddle(image);
                break;
            case BOTTOM:
                image = alignBottom(image);
                break;
            case LEFT:
                image = alignLeft(image);
                break;
            case RIGHT:
                image = alignRight(image);
                break;
        }

        try {
            ImageIO.write(setBackground(image, fontData.backgroundColor), "png", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage setBackground(BufferedImage input, Color background) {
        var image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        var graphics = image.createGraphics();
        graphics.setPaint(background);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.drawImage(input, null, 0, 0);
        graphics.dispose();
        return image;
    }

    private BufferedImage alignTop(BufferedImage image) {
        var larger = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        var posX = (larger.getWidth() / 2D) - (image.getWidth() / 2D);
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, (int) posX, 0);
        graphics.dispose();
        return larger;
    }

    private BufferedImage alignMiddle(BufferedImage image) {
        var larger = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        var posX = (larger.getWidth() / 2D) - (image.getWidth() / 2D);
        var posY = (larger.getHeight() / 2D) - (image.getHeight() / 2D);
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, (int) posX, (int) posY);
        graphics.dispose();
        return larger;
    }

    private BufferedImage alignBottom(BufferedImage image) {
        var larger = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        var posX = (larger.getWidth() / 2D) - (image.getWidth() / 2D);
        var posY = larger.getHeight() - image.getHeight();
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, (int) posX, posY);
        graphics.dispose();
        return larger;
    }

    private BufferedImage alignLeft(BufferedImage image) {
        var larger = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        var posY = (larger.getHeight() / 2D) - (image.getHeight() / 2D);
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, 0, (int) posY);
        graphics.dispose();
        return larger;
    }

    private BufferedImage alignRight(BufferedImage image) {
        var larger = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        var posX = larger.getWidth() - image.getWidth();
        var posY = (larger.getHeight() / 2D) - (image.getHeight() / 2D);
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, posX, (int) posY);
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

    enum CharAlign {
        TOP, MIDDLE, BOTTOM, LEFT, RIGHT
    }

    static class FontData {
        private String prefix;
        private Color color;
        private Color backgroundColor;
        private String font;
        private boolean bold;

        public FontData(String prefix, Color color, String font) {
            this(prefix, color, TRANSPARENT, font, false);
        }

        public FontData(String prefix, Color color, Color backgroundColor, String font) {
            this(prefix, color, backgroundColor, font, false);
        }

        public FontData(String prefix, Color color, String font, boolean bold) {
            this(prefix, color, TRANSPARENT, font, bold);
        }

        public FontData(String prefix, Color color, Color backgroundColor, String font, boolean bold) {
            this.prefix = prefix;
            this.color = color;
            this.backgroundColor = backgroundColor;
            this.font = font;
            this.bold = bold;
        }

        public String getPrefix() {
            return prefix;
        }

        public Color getColor() {
            return color;
        }

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        public String getFont() {
            return font;
        }

        public boolean isBold() {
            return bold;
        }
    }

    private void generateIntelliJ() throws IOException {
        var image = ImageIO.read(new File("image_inputs/intellij_base.png"));

        var iconSize = 33;

        var col = image.getWidth() / iconSize;
        var row = image.getHeight() / iconSize;

        for (int yBlock = 0; yBlock < row; yBlock++) {
            for (int xBlock = 0; xBlock < col; xBlock++) {
                BufferedImage subImage = image.getSubimage(xBlock * iconSize, yBlock * iconSize, iconSize, iconSize);
                if (hasTransparent(subImage)) continue;
                ImageIO.write(subImage, "png", new File("ide_emojis/intellij/gen/" + xBlock + "j" + yBlock + ".png"));
//                LOGGER.info("IJ_{}j{}(\"{}j{}\", \"ide_emojis/intellij/gen/{}j{}.png\"),", xBlock, yBlock, xBlock, yBlock, xBlock, yBlock);
            }
        }
    }

    private boolean hasTransparent(BufferedImage image) {
        for (int x = 0; x < 33; x++) {
            for (int y = 0; y < 33; y++) {
                if (new Color(image.getRGB(x, y), true).getAlpha() == 255) return false;
            }
        }
        return true;
    }

}
