package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.ImageFrame;
import net.dv8tion.jda.api.entities.Emote;

import java.util.ArrayList;
import java.util.List;

public class ImagePiece implements Emoji {
    private static List<ImagePiece> values = new ArrayList<>();

    private String name;
    private String display;
    private Emote emote;

    public ImagePiece(String name, String display, Emote emote) {
        this.name = name;
        this.display = display;
        this.emote = emote;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplay() {
        return null;
    }
}
