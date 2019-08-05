package com.uddernetworks.emojide.image;

import com.uddernetworks.emojide.discord.ImagePiece;

import java.io.File;

public enum PreloadedImage {

    SHREK(new File("shrek.png"), "s", 12, 6);

    private File file;
    private String name;
    private int width;
    private int height;

    private ImagePiece[][] pieces;

    PreloadedImage(File file, String name, int width, int height) {
        this.file = file;
        this.name = name;
        this.width = width;
        this.height = height;

        this.pieces = new ImagePiece[height][];
        for (int y = 0; y < height; y++) this.pieces[y] = new ImagePiece[width];
    }

    public void setPiece(ImagePiece piece, int x, int y) {
        this.pieces[y][x] = piece;
    }

    public ImagePiece[][] getPieces() {
        return pieces;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
