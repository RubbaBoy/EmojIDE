package com.uddernetworks.emojide.discord;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {

    public static void main(String[] args) throws IOException {
        separateImages(ImageIO.read(new File("welcome_text.png")));
    }

    private static void separateImages(BufferedImage input) throws IOException {
        var iconSize = 256;

        var col = input.getWidth() / iconSize;
        var row = input.getHeight() / iconSize;

        for (int yBlock = 0; yBlock < row; yBlock++) {
            for (int xBlock = 0; xBlock < col; xBlock++) {
                BufferedImage subImage = input.getSubimage(xBlock * iconSize, yBlock * iconSize, iconSize, iconSize);
                ImageIO.write(subImage, "png", new File("welcome_emojis/" + xBlock + "w" + yBlock + ".png"));
                System.out.println(xBlock + "w" + yBlock);
            }
        }
    }

}
