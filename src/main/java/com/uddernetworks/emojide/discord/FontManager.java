package com.uddernetworks.emojide.discord;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.uddernetworks.emojide.main.EmojIDE;

public class FontManager {

    private EmojIDE emojIDE;
    private FileConfig config;
    private Font active;

    public FontManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        config = EmojIDE.getConfigManager().getConfig();

        int fontOrdinal = config.getOrElse("ide.font", -1);
        if (fontOrdinal == -1) config.set("ide.font", fontOrdinal = 0);
        active = Font.values()[fontOrdinal];
    }

    public Font getActive() {
        return active;
    }

    public void setActive(Font active) {
        config.set("ide.font", active.ordinal());
        this.active = active;
    }
}
