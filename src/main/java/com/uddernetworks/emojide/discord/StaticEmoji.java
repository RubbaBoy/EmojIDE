package com.uddernetworks.emojide.discord;

/**
 * A statically-created {@link Emoji} for text, colors, and other things from files or unicodes.
 */
public enum StaticEmoji implements Emoji {

    // Defaults
    FILLER("black_large_square", "U+2b1b"),
    BORDER("white_large_square", "U+2b1c"),
    DISCORD("discord", "generated_emojis/discord.png"),
    TRANSPARENT("transparent", "generated_emojis/transparent.png"),

    GRAY1("gray1", "generated_emojis/gray1.png"),
    GRAY2("gray2", "generated_emojis/gray2.png"),
    GRAY3("gray3", "generated_emojis/gray3.png"),
    GRAY4("gray4", "generated_emojis/gray4.png"),
    GRAY5("gray5", "generated_emojis/gray5.png"),
    GRAY6("gray6", "generated_emojis/gray6.png"),
    RED("red", "generated_emojis/red.png"),
    ORANGE("orange", "generated_emojis/orange.png"),
    YELLOW("yellow", "generated_emojis/yellow.png"),
    GREEN1("green1", "generated_emojis/green1.png"),
    GREEN2("green2", "generated_emojis/green2.png"),
    GREEN3("green3", "generated_emojis/green3.png"),
    BLUE1("blue1", "generated_emojis/blue1.png"),
    BLUE2("blue2", "generated_emojis/blue2.png"),
    BLUE3("blue3", "generated_emojis/blue3.png"),
    PURPLE1("purple1", "generated_emojis/purple1.png"),
    PURPLE2("purple2", "generated_emojis/purple2.png"),

    BACKSPACE("backspace", "keyboard_emojis/backspace.png"),
    INS("ins", "keyboard_emojis/ins.png"),
    HOME("home", "keyboard_emojis/home.png"),
    PG_UP("pg_up", "keyboard_emojis/pg_up.png"),
    TAB("tab", "keyboard_emojis/tab.png"),
    DEL("del", "keyboard_emojis/del.png"),
    END("end", "keyboard_emojis/end.png"),
    PG_DOWN("pg_down", "keyboard_emojis/pg_down.png"),
    CAPS_LOCK("caps_lock", "keyboard_emojis/caps_lock.png"),
    ENTERL("enterl", "keyboard_emojis/enterl.png"),
    ENTERR("enterr", "keyboard_emojis/enterr.png"),
    SHIFTL("shiftl", "keyboard_emojis/shiftl.png"),
    SHIFTR("shiftr", "keyboard_emojis/shiftr.png"),
    UP("up", "keyboard_emojis/up.png"),
    CTRL("ctrl", "keyboard_emojis/ctrl.png"),
    ICON("icon", "keyboard_emojis/icon.png"),
    ALT("alt", "keyboard_emojis/alt.png"),
    SPACEL("spacel", "keyboard_emojis/spacel.png"),
    SPACEC("spacec", "keyboard_emojis/spacec.png"),
    SPACER("spacer", "keyboard_emojis/spacer.png"),
    FN("fn", "keyboard_emojis/fn.png"),
    CONTEXT("context", "keyboard_emojis/context.png"),
    LEFT("left", "keyboard_emojis/left.png"),
    DOWN("down", "keyboard_emojis/down.png"),
    RIGHT("right", "keyboard_emojis/right.png"),
    PADDING("padding", "keyboard_emojis/padding.png"),
    CURSOR("cursor", "keyboard_emojis/cursor.png"),

    A_32("32", "generated_emojis/32.png"), // ASCII
    A_33("33", "generated_emojis/33.png"), // ASCII !
    A_34("34", "generated_emojis/34.png"), // ASCII "
    A_35("35", "generated_emojis/35.png"), // ASCII #
    A_36("36", "generated_emojis/36.png"), // ASCII $
    A_37("37", "generated_emojis/37.png"), // ASCII %
    A_38("38", "generated_emojis/38.png"), // ASCII &
    A_39("39", "generated_emojis/39.png"), // ASCII '
    A_40("40", "generated_emojis/40.png"), // ASCII (
    A_41("41", "generated_emojis/41.png"), // ASCII )
    A_42("42", "generated_emojis/42.png"), // ASCII *
    A_43("43", "generated_emojis/43.png"), // ASCII +
    A_44("44", "generated_emojis/44.png"), // ASCII ,
    A_45("45", "generated_emojis/45.png"), // ASCII -
    A_46("46", "generated_emojis/46.png"), // ASCII .
    A_47("47", "generated_emojis/47.png"), // ASCII /
    A_48("48", "generated_emojis/48.png"), // ASCII 0
    A_49("49", "generated_emojis/49.png"), // ASCII 1
    A_50("50", "generated_emojis/50.png"), // ASCII 2
    A_51("51", "generated_emojis/51.png"), // ASCII 3
    A_52("52", "generated_emojis/52.png"), // ASCII 4
    A_53("53", "generated_emojis/53.png"), // ASCII 5
    A_54("54", "generated_emojis/54.png"), // ASCII 6
    A_55("55", "generated_emojis/55.png"), // ASCII 7
    A_56("56", "generated_emojis/56.png"), // ASCII 8
    A_57("57", "generated_emojis/57.png"), // ASCII 9
    A_58("58", "generated_emojis/58.png"), // ASCII :
    A_59("59", "generated_emojis/59.png"), // ASCII ;
    A_60("60", "generated_emojis/60.png"), // ASCII <
    A_61("61", "generated_emojis/61.png"), // ASCII =
    A_62("62", "generated_emojis/62.png"), // ASCII >
    A_63("63", "generated_emojis/63.png"), // ASCII ?
    A_64("64", "generated_emojis/64.png"), // ASCII @
    A_65("65", "generated_emojis/65.png"), // ASCII A
    A_66("66", "generated_emojis/66.png"), // ASCII B
    A_67("67", "generated_emojis/67.png"), // ASCII C
    A_68("68", "generated_emojis/68.png"), // ASCII D
    A_69("69", "generated_emojis/69.png"), // ASCII E
    A_70("70", "generated_emojis/70.png"), // ASCII F
    A_71("71", "generated_emojis/71.png"), // ASCII G
    A_72("72", "generated_emojis/72.png"), // ASCII H
    A_73("73", "generated_emojis/73.png"), // ASCII I
    A_74("74", "generated_emojis/74.png"), // ASCII J
    A_75("75", "generated_emojis/75.png"), // ASCII K
    A_76("76", "generated_emojis/76.png"), // ASCII L
    A_77("77", "generated_emojis/77.png"), // ASCII M
    A_78("78", "generated_emojis/78.png"), // ASCII N
    A_79("79", "generated_emojis/79.png"), // ASCII O
    A_80("80", "generated_emojis/80.png"), // ASCII P
    A_81("81", "generated_emojis/81.png"), // ASCII Q
    A_82("82", "generated_emojis/82.png"), // ASCII R
    A_83("83", "generated_emojis/83.png"), // ASCII S
    A_84("84", "generated_emojis/84.png"), // ASCII T
    A_85("85", "generated_emojis/85.png"), // ASCII U
    A_86("86", "generated_emojis/86.png"), // ASCII V
    A_87("87", "generated_emojis/87.png"), // ASCII W
    A_88("88", "generated_emojis/88.png"), // ASCII X
    A_89("89", "generated_emojis/89.png"), // ASCII Y
    A_90("90", "generated_emojis/90.png"), // ASCII Z
    A_91("91", "generated_emojis/91.png"), // ASCII [
    A_92("92", "generated_emojis/92.png"), // ASCII \
    A_93("93", "generated_emojis/93.png"), // ASCII ]
    A_94("94", "generated_emojis/94.png"), // ASCII ^
    A_95("95", "generated_emojis/95.png"), // ASCII _
    A_96("96", "generated_emojis/96.png"), // ASCII `
    A_97("97", "generated_emojis/97.png"), // ASCII a
    A_98("98", "generated_emojis/98.png"), // ASCII b
    A_99("99", "generated_emojis/99.png"), // ASCII c
    A_100("100", "generated_emojis/100.png"), // ASCII d
    A_101("101", "generated_emojis/101.png"), // ASCII e
    A_102("102", "generated_emojis/102.png"), // ASCII f
    A_103("103", "generated_emojis/103.png"), // ASCII g
    A_104("104", "generated_emojis/104.png"), // ASCII h
    A_105("105", "generated_emojis/105.png"), // ASCII i
    A_106("106", "generated_emojis/106.png"), // ASCII j
    A_107("107", "generated_emojis/107.png"), // ASCII k
    A_108("108", "generated_emojis/108.png"), // ASCII l
    A_109("109", "generated_emojis/109.png"), // ASCII m
    A_110("110", "generated_emojis/110.png"), // ASCII n
    A_111("111", "generated_emojis/111.png"), // ASCII o
    A_112("112", "generated_emojis/112.png"), // ASCII p
    A_113("113", "generated_emojis/113.png"), // ASCII q
    A_114("114", "generated_emojis/114.png"), // ASCII r
    A_115("115", "generated_emojis/115.png"), // ASCII s
    A_116("116", "generated_emojis/116.png"), // ASCII t
    A_117("117", "generated_emojis/117.png"), // ASCII u
    A_118("118", "generated_emojis/118.png"), // ASCII v
    A_119("119", "generated_emojis/119.png"), // ASCII w
    A_120("120", "generated_emojis/120.png"), // ASCII x
    A_121("121", "generated_emojis/121.png"), // ASCII y
    A_122("122", "generated_emojis/122.png"), // ASCII z
    A_123("123", "generated_emojis/123.png"), // ASCII {
    A_124("124", "generated_emojis/124.png"), // ASCII |
    A_125("125", "generated_emojis/125.png"), // ASCII }
    A_126("126", "generated_emojis/126.png"), // ASCII ~
    ;

    private String name;
    private String relativePath;
    private String unicode;

    private String display;

    // Only for custom emojis
    private long id;

    StaticEmoji(String name, String unicodeOrPath) {
        this.name = name;

        if (unicodeOrPath.startsWith("U+")) {
            this.unicode = unicodeOrPath;
        } else {
            this.relativePath = unicodeOrPath;
        }
    }

    /**
     * If the emoji comes from a file.
     *
     * @return If the emoji is custom
     */
    public boolean isCustom() {
        return this.unicode == null;
    }

    /**
     * Gets the generated file path of the emoji
     *
     * @return The generated file path of the emoji
     */
    public String getRelativePath() {
        return relativePath;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets a string that may be reacted to a message via
     * {@link net.dv8tion.jda.api.entities.Message#addReaction(String)}.
     *
     * @return The string to react with
     */
    public String getReact() {
        return isCustom() ? this.name + ":" + this.id : this.unicode;
    }

    /**
     * Gets the unicode if {@link #isCustom()} returns false.
     *
     * @return The unicode of the emoji
     */
    public String getUnicode() {
        return unicode;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    /**
     * Sets the display text of the emoji, gotten via {@link #getDisplay()}.
     *
     * @param display The display text of the emoji
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * Gets the Discord ID of the emoji.
     *
     * @return The Discord ID of the emoji
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the Discord ID of the emoji.
     *
     * @param id The Discord ID of the emoji to set
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Emoji[" + name + "]";
    }
}
