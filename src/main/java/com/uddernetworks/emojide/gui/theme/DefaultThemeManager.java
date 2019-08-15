package com.uddernetworks.emojide.gui.theme;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.uddernetworks.emojide.discord.font.Font;
import com.uddernetworks.emojide.main.EmojIDE;

import java.util.function.Supplier;

public class DefaultThemeManager implements ThemeManager {

    private EmojIDE emojIDE;
    private FileConfig config;
    private Theme active;

    public DefaultThemeManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        config = EmojIDE.getConfigManager().getConfig();

        int themeOrdinal = config.getOrElse("ide.theme", -1);
        if (themeOrdinal == -1) config.set("ide.theme", themeOrdinal = 0);
        active = Theme.values()[themeOrdinal];
    }

    @Override
    public Theme getActive() {
        return active;
    }

    @Override
    public void setActive(Theme active) {
        config.set("ide.theme", active.ordinal());
        this.active = active;
    }

    @Override
    public void forTheme(Runnable def, Runnable intellij) {
        new Runnable[] {def, intellij}[active.ordinal()].run();
    }

    @Override
    public <T> T getForTheme(Supplier<T> def, Supplier<T> intellij) {
        return (T) new Supplier<?>[] {def, intellij}[active.ordinal()].get();
    }
}
