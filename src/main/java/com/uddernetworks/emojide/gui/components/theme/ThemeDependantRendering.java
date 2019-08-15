package com.uddernetworks.emojide.gui.components.theme;

import com.uddernetworks.emojide.gui.theme.Theme;
import com.uddernetworks.emojide.main.EmojIDE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ThemeDependantRendering {

    private static EmojIDE emojIDE;
    private static Map<Class<?>, Map<Theme, Function>> themeImpls = new HashMap<>();

    public ThemeDependantRendering(EmojIDE emojIDE) {
        ThemeDependantRendering.emojIDE = emojIDE;
    }

    public static <T> void registerImplementation(Class<T> baseClass, Theme theme, Function<T, ThemeImplementor> implementation) {
        themeImpls.computeIfAbsent(baseClass, i -> new HashMap<>()).put(theme, implementation);
    }

    public static <T> T getImplementation(Object base) {
        var theme = emojIDE.getThemeManager().getActive();
        var impl = themeImpls.getOrDefault(base.getClass(), Collections.emptyMap()).get(theme);
        if (impl == null) {
            throw new RuntimeException("A fatal exception has occurred! The class " + base.getClass().getCanonicalName() + " does not hold an implementation for the theme " + theme.getName());
        }
        return (T) impl.apply(base);
    }
}
