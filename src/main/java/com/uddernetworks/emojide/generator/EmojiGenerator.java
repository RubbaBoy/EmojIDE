package com.uddernetworks.emojide.generator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.uddernetworks.emojide.generator.LetterGenerator.*;

public class EmojiGenerator {

    private static final int WIDTH = 256;
    private static final int HEIGHT = 256;

    public static void main(String[] args) {
        var parent = new File("emojis");
        Arrays.stream("!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".split("")).forEach(character -> drawAndSave(parent, character));
    }

    private static void drawAndSave(File parent, String character) {
        BufferedImage image = new BufferedImage(WIDTH + 50, HEIGHT + 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(TRANSPARENT);
        graphics.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);
        graphics.setPaint(Color.white);
        graphics.setFont(new Font("Consolas", Font.PLAIN, 256)); // 192pts
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
            ImageIO.write(image, "png", new File(parent, ((int) character.charAt(0)) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage fitTo(BufferedImage image, int width, int height) {
        var larger = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var posX = (larger.getWidth() / 2D) - (image.getWidth() / 2D);
        var posY = (larger.getHeight() / 2D) - (image.getHeight() / 2D);
        var graphics = larger.createGraphics();
        graphics.drawImage(image, null, (int) posX, (int) posY);
        graphics.dispose();
        return larger;
    }

    private static BufferedImage makeImage(double[][] values) {
        BufferedImage image = new BufferedImage(values[0].length, values.length, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int val = (int) (values[y][x]);
                image.setRGB(x, y, val);
            }
        }

        return image;
    }

}
