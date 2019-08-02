package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiContainer;

import java.util.Arrays;

public class WindowFrame extends EmojiContainer {

    public WindowFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }

    @Override
   public Emoji[][] render() {
        var rows = new Emoji[height][];
        for (int i = 0; i < height; i++) rows[i] = new Emoji[width];
        drawFrame(rows);
        this.positionedComponents.forEach(positioned -> drawTo(positioned, rows));
        return rows;
    }

    private void drawFrame(Emoji[][] rows) {
        var border = displayer.getEmojiManager().getEmoji("red");
        Arrays.fill(rows[0], border);
        Arrays.fill(rows[rows.length - 1], border);
        for (int y = 0; y < rows.length; y++) {
            rows[y][0] = border;
            rows[y][rows[0].length - 1] = border;
        }
    }

}
