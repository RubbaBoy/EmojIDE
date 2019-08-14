package com.uddernetworks.emojide.discord;

/**
 * A statically-created {@link Emoji} for text, colors, and other things from files or unicodes.
 */
public enum StaticEmoji implements Emoji {

    // Defaults
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

    // Keyboard emojis
    BACKSPACE("backspace", "keyboard_emojis/backspace.png"),
    INS("ins", "keyboard_emojis/ins.png"),
    HOME("home", "keyboard_emojis/home.png"),
    PG_UP("pg_up", "keyboard_emojis/pg_up.png"),
    TAB("tab", "keyboard_emojis/tab.png"),
    DEL("del", "keyboard_emojis/del.png"),
    END("end", "keyboard_emojis/end.png"),
    PG_DOWN("pg_down", "keyboard_emojis/pg_down.png"),
    CAPS_LOCK("caps_lock", "keyboard_emojis/caps_lock.png"),
    CAPS_LOCK_ACTIVE("caps_locka", "keyboard_emojis/caps_lock_active.png"),
    ENTERL("enterl", "keyboard_emojis/enterl.png"),
    ENTERR("enterr", "keyboard_emojis/enterr.png"),
    SHIFTL("shiftl", "keyboard_emojis/shiftl.png"),
    SHIFTR("shiftr", "keyboard_emojis/shiftr.png"),
    UP("up", "keyboard_emojis/up.png"),
    CTRL("ctrl", "keyboard_emojis/ctrl.png"),
    CTRL_ACTIVE("ctrla", "keyboard_emojis/ctrl_active.png"),
    ICON("icon", "keyboard_emojis/icon.png"),
    ALT("alt", "keyboard_emojis/alt.png"),
    ALT_ACTIVE("alta", "keyboard_emojis/alt_active.png"),
    SPACEL("spacel", "keyboard_emojis/spacel.png"),
    SPACEC("spacec", "keyboard_emojis/spacec.png"),
    SPACER("spacer", "keyboard_emojis/spacer.png"),
    FN("fn", "keyboard_emojis/fn.png"),
    FN_ACTIVE("fna", "keyboard_emojis/fn_active.png"),
    CONTEXT("context", "keyboard_emojis/context.png"),
    LEFT("left", "keyboard_emojis/left.png"),
    DOWN("down", "keyboard_emojis/down.png"),
    RIGHT("right", "keyboard_emojis/right.png"),
    PADDING("padding", "keyboard_emojis/padding.png"),
    CURSOR("cursor", "keyboard_emojis/cursor.png"),

    // IDE emojis
    LTAB_SEPARATOR("lts", "ide_emojis/ltab_separator.png"),
    RTAB_SEPARATOR("rts", "ide_emojis/rtab_separator.png"),
    TTABBED_FRAME("ttf", "ide_emojis/ttabbed_frame.png"),
    BTABBED_FRAME("btf", "ide_emojis/btabbed_frame.png"),
    LTABBED_FRAME("ltf", "ide_emojis/ltabbed_frame.png"),
    RTABBED_FRAME("rtfs", "ide_emojis/rtabbed_frame.png"),
    CTABBED_FRAME("ctf", "ide_emojis/ctabbed_frame.png"), // Center tabbed frame
    RTAB_CORNER_SELECTED("rtcs", "ide_emojis/rtab_corner_selected.png"),
    LTAB_CORNER_SELECTED("ltcs", "ide_emojis/ltab_corner_selected.png"),
    TTABBED_FRAME_SELECTED("ttfs", "ide_emojis/ttabbed_frame_selected.png"),
    LTAB_SEPARATOR_SELECTED("ltss", "ide_emojis/ltab_separator_selected.png"),
    RTAB_SEPARATOR_SELECTED("rtss", "ide_emojis/rtab_separator_selected.png"),
    BL_FRAME("blf", "ide_emojis/blframe.png"), // Bottom Left Square
    TL_FRAME("tlf", "ide_emojis/tlframe.png"), // Top Left Square
    BR_FRAME("brf", "ide_emojis/brframe.png"), // Bottom Right Square
    TR_FRAME("trf", "ide_emojis/trframe.png"), // Top Right Square

    // Welcome Tab
    W_0w0("0w0", "welcome_emojis/name/0w0.png"),
    W_1w0("1w0", "welcome_emojis/name/1w0.png"),
    W_2w0("2w0", "welcome_emojis/name/2w0.png"),
    W_3w0("3w0", "welcome_emojis/name/3w0.png"),
    W_4w0("4w0", "welcome_emojis/name/4w0.png"),
    W_5w0("5w0", "welcome_emojis/name/5w0.png"),
    W_6w0("6w0", "welcome_emojis/name/6w0.png"),
    W_7w0("7w0", "welcome_emojis/name/7w0.png"),
    W_0w1("0w1", "welcome_emojis/name/0w1.png"),
    W_1w1("1w1", "welcome_emojis/name/1w1.png"),
    W_2w1("2w1", "welcome_emojis/name/2w1.png"),
    W_3w1("3w1", "welcome_emojis/name/3w1.png"),
    W_4w1("4w1", "welcome_emojis/name/4w1.png"),
    W_5w1("5w1", "welcome_emojis/name/5w1.png"),
    W_6w1("6w1", "welcome_emojis/name/6w1.png"),
    W_7w1("7w1", "welcome_emojis/name/7w1.png"),

    //<editor-fold desc="White Text">
    A_32("32", "generated_emojis/32.png"), // ASCII White
    A_33("33", "generated_emojis/33.png"), // ASCII White !
    A_34("34", "generated_emojis/34.png"), // ASCII White "
    A_35("35", "generated_emojis/35.png"), // ASCII White #
    A_36("36", "generated_emojis/36.png"), // ASCII White $
    A_37("37", "generated_emojis/37.png"), // ASCII White %
    A_38("38", "generated_emojis/38.png"), // ASCII White &
    A_39("39", "generated_emojis/39.png"), // ASCII White '
    A_40("40", "generated_emojis/40.png"), // ASCII White (
    A_41("41", "generated_emojis/41.png"), // ASCII White )
    A_42("42", "generated_emojis/42.png"), // ASCII White *
    A_43("43", "generated_emojis/43.png"), // ASCII White +
    A_44("44", "generated_emojis/44.png"), // ASCII White ,
    A_45("45", "generated_emojis/45.png"), // ASCII White -
    A_46("46", "generated_emojis/46.png"), // ASCII White .
    A_47("47", "generated_emojis/47.png"), // ASCII White /
    A_48("48", "generated_emojis/48.png"), // ASCII White 0
    A_49("49", "generated_emojis/49.png"), // ASCII White 1
    A_50("50", "generated_emojis/50.png"), // ASCII White 2
    A_51("51", "generated_emojis/51.png"), // ASCII White 3
    A_52("52", "generated_emojis/52.png"), // ASCII White 4
    A_53("53", "generated_emojis/53.png"), // ASCII White 5
    A_54("54", "generated_emojis/54.png"), // ASCII White 6
    A_55("55", "generated_emojis/55.png"), // ASCII White 7
    A_56("56", "generated_emojis/56.png"), // ASCII White 8
    A_57("57", "generated_emojis/57.png"), // ASCII White 9
    A_58("58", "generated_emojis/58.png"), // ASCII White :
    A_59("59", "generated_emojis/59.png"), // ASCII White ;
    A_60("60", "generated_emojis/60.png"), // ASCII White <
    A_61("61", "generated_emojis/61.png"), // ASCII White =
    A_62("62", "generated_emojis/62.png"), // ASCII White >
    A_63("63", "generated_emojis/63.png"), // ASCII White ?
    A_64("64", "generated_emojis/64.png"), // ASCII White @
    A_65("65", "generated_emojis/65.png"), // ASCII White A
    A_66("66", "generated_emojis/66.png"), // ASCII White B
    A_67("67", "generated_emojis/67.png"), // ASCII White C
    A_68("68", "generated_emojis/68.png"), // ASCII White D
    A_69("69", "generated_emojis/69.png"), // ASCII White E
    A_70("70", "generated_emojis/70.png"), // ASCII White F
    A_71("71", "generated_emojis/71.png"), // ASCII White G
    A_72("72", "generated_emojis/72.png"), // ASCII White H
    A_73("73", "generated_emojis/73.png"), // ASCII White I
    A_74("74", "generated_emojis/74.png"), // ASCII White J
    A_75("75", "generated_emojis/75.png"), // ASCII White K
    A_76("76", "generated_emojis/76.png"), // ASCII White L
    A_77("77", "generated_emojis/77.png"), // ASCII White M
    A_78("78", "generated_emojis/78.png"), // ASCII White N
    A_79("79", "generated_emojis/79.png"), // ASCII White O
    A_80("80", "generated_emojis/80.png"), // ASCII White P
    A_81("81", "generated_emojis/81.png"), // ASCII White Q
    A_82("82", "generated_emojis/82.png"), // ASCII White R
    A_83("83", "generated_emojis/83.png"), // ASCII White S
    A_84("84", "generated_emojis/84.png"), // ASCII White T
    A_85("85", "generated_emojis/85.png"), // ASCII White U
    A_86("86", "generated_emojis/86.png"), // ASCII White V
    A_87("87", "generated_emojis/87.png"), // ASCII White W
    A_88("88", "generated_emojis/88.png"), // ASCII White X
    A_89("89", "generated_emojis/89.png"), // ASCII White Y
    A_90("90", "generated_emojis/90.png"), // ASCII White Z
    A_91("91", "generated_emojis/91.png"), // ASCII White [
    A_92("92", "generated_emojis/92.png"), // ASCII White \
    A_93("93", "generated_emojis/93.png"), // ASCII White ]
    A_94("94", "generated_emojis/94.png"), // ASCII White ^
    A_95("95", "generated_emojis/95.png"), // ASCII White _
    A_96("96", "generated_emojis/96.png"), // ASCII White `
    A_97("97", "generated_emojis/97.png"), // ASCII White a
    A_98("98", "generated_emojis/98.png"), // ASCII White b
    A_99("99", "generated_emojis/99.png"), // ASCII White c
    A_100("100", "generated_emojis/100.png"), // ASCII White d
    A_101("101", "generated_emojis/101.png"), // ASCII White e
    A_102("102", "generated_emojis/102.png"), // ASCII White f
    A_103("103", "generated_emojis/103.png"), // ASCII White g
    A_104("104", "generated_emojis/104.png"), // ASCII White h
    A_105("105", "generated_emojis/105.png"), // ASCII White i
    A_106("106", "generated_emojis/106.png"), // ASCII White j
    A_107("107", "generated_emojis/107.png"), // ASCII White k
    A_108("108", "generated_emojis/108.png"), // ASCII White l
    A_109("109", "generated_emojis/109.png"), // ASCII White m
    A_110("110", "generated_emojis/110.png"), // ASCII White n
    A_111("111", "generated_emojis/111.png"), // ASCII White o
    A_112("112", "generated_emojis/112.png"), // ASCII White p
    A_113("113", "generated_emojis/113.png"), // ASCII White q
    A_114("114", "generated_emojis/114.png"), // ASCII White r
    A_115("115", "generated_emojis/115.png"), // ASCII White s
    A_116("116", "generated_emojis/116.png"), // ASCII White t
    A_117("117", "generated_emojis/117.png"), // ASCII White u
    A_118("118", "generated_emojis/118.png"), // ASCII White v
    A_119("119", "generated_emojis/119.png"), // ASCII White w
    A_120("120", "generated_emojis/120.png"), // ASCII White x
    A_121("121", "generated_emojis/121.png"), // ASCII White y
    A_122("122", "generated_emojis/122.png"), // ASCII White z
    A_123("123", "generated_emojis/123.png"), // ASCII White {
    A_124("124", "generated_emojis/124.png"), // ASCII White |
    A_125("125", "generated_emojis/125.png"), // ASCII White }
    A_126("126", "generated_emojis/126.png"), // ASCII White ~
//</editor-fold>

    //<editor-fold desc="Orange Text">
    A_O32("o32", "generated_emojis/o32.png"), // ASCII Orange
    A_O33("o33", "generated_emojis/o33.png"), // ASCII Orange !
    A_O34("o34", "generated_emojis/o34.png"), // ASCII Orange "
    A_O35("o35", "generated_emojis/o35.png"), // ASCII Orange #
    A_O36("o36", "generated_emojis/o36.png"), // ASCII Orange $
    A_O37("o37", "generated_emojis/o37.png"), // ASCII Orange %
    A_O38("o38", "generated_emojis/o38.png"), // ASCII Orange &
    A_O39("o39", "generated_emojis/o39.png"), // ASCII Orange '
    A_O40("o40", "generated_emojis/o40.png"), // ASCII Orange (
    A_O41("o41", "generated_emojis/o41.png"), // ASCII Orange )
    A_O42("o42", "generated_emojis/o42.png"), // ASCII Orange *
    A_O43("o43", "generated_emojis/o43.png"), // ASCII Orange +
    A_O44("o44", "generated_emojis/o44.png"), // ASCII Orange ,
    A_O45("o45", "generated_emojis/o45.png"), // ASCII Orange -
    A_O46("o46", "generated_emojis/o46.png"), // ASCII Orange .
    A_O47("o47", "generated_emojis/o47.png"), // ASCII Orange /
    A_O48("o48", "generated_emojis/o48.png"), // ASCII Orange 0
    A_O49("o49", "generated_emojis/o49.png"), // ASCII Orange 1
    A_O50("o50", "generated_emojis/o50.png"), // ASCII Orange 2
    A_O51("o51", "generated_emojis/o51.png"), // ASCII Orange 3
    A_O52("o52", "generated_emojis/o52.png"), // ASCII Orange 4
    A_O53("o53", "generated_emojis/o53.png"), // ASCII Orange 5
    A_O54("o54", "generated_emojis/o54.png"), // ASCII Orange 6
    A_O55("o55", "generated_emojis/o55.png"), // ASCII Orange 7
    A_O56("o56", "generated_emojis/o56.png"), // ASCII Orange 8
    A_O57("o57", "generated_emojis/o57.png"), // ASCII Orange 9
    A_O58("o58", "generated_emojis/o58.png"), // ASCII Orange :
    A_O59("o59", "generated_emojis/o59.png"), // ASCII Orange ;
    A_O60("o60", "generated_emojis/o60.png"), // ASCII Orange <
    A_O61("o61", "generated_emojis/o61.png"), // ASCII Orange =
    A_O62("o62", "generated_emojis/o62.png"), // ASCII Orange >
    A_O63("o63", "generated_emojis/o63.png"), // ASCII Orange ?
    A_O64("o64", "generated_emojis/o64.png"), // ASCII Orange @
    A_O65("o65", "generated_emojis/o65.png"), // ASCII Orange A
    A_O66("o66", "generated_emojis/o66.png"), // ASCII Orange B
    A_O67("o67", "generated_emojis/o67.png"), // ASCII Orange C
    A_O68("o68", "generated_emojis/o68.png"), // ASCII Orange D
    A_O69("o69", "generated_emojis/o69.png"), // ASCII Orange E
    A_O70("o70", "generated_emojis/o70.png"), // ASCII Orange F
    A_O71("o71", "generated_emojis/o71.png"), // ASCII Orange G
    A_O72("o72", "generated_emojis/o72.png"), // ASCII Orange H
    A_O73("o73", "generated_emojis/o73.png"), // ASCII Orange I
    A_O74("o74", "generated_emojis/o74.png"), // ASCII Orange J
    A_O75("o75", "generated_emojis/o75.png"), // ASCII Orange K
    A_O76("o76", "generated_emojis/o76.png"), // ASCII Orange L
    A_O77("o77", "generated_emojis/o77.png"), // ASCII Orange M
    A_O78("o78", "generated_emojis/o78.png"), // ASCII Orange N
    A_O79("o79", "generated_emojis/o79.png"), // ASCII Orange O
    A_O80("o80", "generated_emojis/o80.png"), // ASCII Orange P
    A_O81("o81", "generated_emojis/o81.png"), // ASCII Orange Q
    A_O82("o82", "generated_emojis/o82.png"), // ASCII Orange R
    A_O83("o83", "generated_emojis/o83.png"), // ASCII Orange S
    A_O84("o84", "generated_emojis/o84.png"), // ASCII Orange T
    A_O85("o85", "generated_emojis/o85.png"), // ASCII Orange U
    A_O86("o86", "generated_emojis/o86.png"), // ASCII Orange V
    A_O87("o87", "generated_emojis/o87.png"), // ASCII Orange W
    A_O88("o88", "generated_emojis/o88.png"), // ASCII Orange X
    A_O89("o89", "generated_emojis/o89.png"), // ASCII Orange Y
    A_O90("o90", "generated_emojis/o90.png"), // ASCII Orange Z
    A_O91("o91", "generated_emojis/o91.png"), // ASCII Orange [
    A_O92("o92", "generated_emojis/o92.png"), // ASCII Orange \
    A_O93("o93", "generated_emojis/o93.png"), // ASCII Orange ]
    A_O94("o94", "generated_emojis/o94.png"), // ASCII Orange ^
    A_O95("o95", "generated_emojis/o95.png"), // ASCII Orange _
    A_O96("o96", "generated_emojis/o96.png"), // ASCII Orange `
    A_O97("o97", "generated_emojis/o97.png"), // ASCII Orange a
    A_O98("o98", "generated_emojis/o98.png"), // ASCII Orange b
    A_O99("o99", "generated_emojis/o99.png"), // ASCII Orange c
    A_O100("o100", "generated_emojis/o100.png"), // ASCII Orange d
    A_O101("o101", "generated_emojis/o101.png"), // ASCII Orange e
    A_O102("o102", "generated_emojis/o102.png"), // ASCII Orange f
    A_O103("o103", "generated_emojis/o103.png"), // ASCII Orange g
    A_O104("o104", "generated_emojis/o104.png"), // ASCII Orange h
    A_O105("o105", "generated_emojis/o105.png"), // ASCII Orange i
    A_O106("o106", "generated_emojis/o106.png"), // ASCII Orange j
    A_O107("o107", "generated_emojis/o107.png"), // ASCII Orange k
    A_O108("o108", "generated_emojis/o108.png"), // ASCII Orange l
    A_O109("o109", "generated_emojis/o109.png"), // ASCII Orange m
    A_O110("o110", "generated_emojis/o110.png"), // ASCII Orange n
    A_O111("o111", "generated_emojis/o111.png"), // ASCII Orange o
    A_O112("o112", "generated_emojis/o112.png"), // ASCII Orange p
    A_O113("o113", "generated_emojis/o113.png"), // ASCII Orange q
    A_O114("o114", "generated_emojis/o114.png"), // ASCII Orange r
    A_O115("o115", "generated_emojis/o115.png"), // ASCII Orange s
    A_O116("o116", "generated_emojis/o116.png"), // ASCII Orange t
    A_O117("o117", "generated_emojis/o117.png"), // ASCII Orange u
    A_O118("o118", "generated_emojis/o118.png"), // ASCII Orange v
    A_O119("o119", "generated_emojis/o119.png"), // ASCII Orange w
    A_O120("o120", "generated_emojis/o120.png"), // ASCII Orange x
    A_O121("o121", "generated_emojis/o121.png"), // ASCII Orange y
    A_O122("o122", "generated_emojis/o122.png"), // ASCII Orange z
    A_O123("o123", "generated_emojis/o123.png"), // ASCII Orange {
    A_O124("o124", "generated_emojis/o124.png"), // ASCII Orange |
    A_O125("o125", "generated_emojis/o125.png"), // ASCII Orange }
    A_O126("o126", "generated_emojis/o126.png"), // ASCII Orange ~
//</editor-fold>

    //<editor-fold desc="Green Text">
    A_G32("g32", "generated_emojis/g32.png"), // ASCII Green
    A_G33("g33", "generated_emojis/g33.png"), // ASCII Green !
    A_G34("g34", "generated_emojis/g34.png"), // ASCII Green "
    A_G35("g35", "generated_emojis/g35.png"), // ASCII Green #
    A_G36("g36", "generated_emojis/g36.png"), // ASCII Green $
    A_G37("g37", "generated_emojis/g37.png"), // ASCII Green %
    A_G38("g38", "generated_emojis/g38.png"), // ASCII Green &
    A_G39("g39", "generated_emojis/g39.png"), // ASCII Green '
    A_G40("g40", "generated_emojis/g40.png"), // ASCII Green (
    A_G41("g41", "generated_emojis/g41.png"), // ASCII Green )
    A_G42("g42", "generated_emojis/g42.png"), // ASCII Green *
    A_G43("g43", "generated_emojis/g43.png"), // ASCII Green +
    A_G44("g44", "generated_emojis/g44.png"), // ASCII Green ,
    A_G45("g45", "generated_emojis/g45.png"), // ASCII Green -
    A_G46("g46", "generated_emojis/g46.png"), // ASCII Green .
    A_G47("g47", "generated_emojis/g47.png"), // ASCII Green /
    A_G48("g48", "generated_emojis/g48.png"), // ASCII Green 0
    A_G49("g49", "generated_emojis/g49.png"), // ASCII Green 1
    A_G50("g50", "generated_emojis/g50.png"), // ASCII Green 2
    A_G51("g51", "generated_emojis/g51.png"), // ASCII Green 3
    A_G52("g52", "generated_emojis/g52.png"), // ASCII Green 4
    A_G53("g53", "generated_emojis/g53.png"), // ASCII Green 5
    A_G54("g54", "generated_emojis/g54.png"), // ASCII Green 6
    A_G55("g55", "generated_emojis/g55.png"), // ASCII Green 7
    A_G56("g56", "generated_emojis/g56.png"), // ASCII Green 8
    A_G57("g57", "generated_emojis/g57.png"), // ASCII Green 9
    A_G58("g58", "generated_emojis/g58.png"), // ASCII Green :
    A_G59("g59", "generated_emojis/g59.png"), // ASCII Green ;
    A_G60("g60", "generated_emojis/g60.png"), // ASCII Green <
    A_G61("g61", "generated_emojis/g61.png"), // ASCII Green =
    A_G62("g62", "generated_emojis/g62.png"), // ASCII Green >
    A_G63("g63", "generated_emojis/g63.png"), // ASCII Green ?
    A_G64("g64", "generated_emojis/g64.png"), // ASCII Green @
    A_G65("g65", "generated_emojis/g65.png"), // ASCII Green A
    A_G66("g66", "generated_emojis/g66.png"), // ASCII Green B
    A_G67("g67", "generated_emojis/g67.png"), // ASCII Green C
    A_G68("g68", "generated_emojis/g68.png"), // ASCII Green D
    A_G69("g69", "generated_emojis/g69.png"), // ASCII Green E
    A_G70("g70", "generated_emojis/g70.png"), // ASCII Green F
    A_G71("g71", "generated_emojis/g71.png"), // ASCII Green G
    A_G72("g72", "generated_emojis/g72.png"), // ASCII Green H
    A_G73("g73", "generated_emojis/g73.png"), // ASCII Green I
    A_G74("g74", "generated_emojis/g74.png"), // ASCII Green J
    A_G75("g75", "generated_emojis/g75.png"), // ASCII Green K
    A_G76("g76", "generated_emojis/g76.png"), // ASCII Green L
    A_G77("g77", "generated_emojis/g77.png"), // ASCII Green M
    A_G78("g78", "generated_emojis/g78.png"), // ASCII Green N
    A_G79("g79", "generated_emojis/g79.png"), // ASCII Green O
    A_G80("g80", "generated_emojis/g80.png"), // ASCII Green P
    A_G81("g81", "generated_emojis/g81.png"), // ASCII Green Q
    A_G82("g82", "generated_emojis/g82.png"), // ASCII Green R
    A_G83("g83", "generated_emojis/g83.png"), // ASCII Green S
    A_G84("g84", "generated_emojis/g84.png"), // ASCII Green T
    A_G85("g85", "generated_emojis/g85.png"), // ASCII Green U
    A_G86("g86", "generated_emojis/g86.png"), // ASCII Green V
    A_G87("g87", "generated_emojis/g87.png"), // ASCII Green W
    A_G88("g88", "generated_emojis/g88.png"), // ASCII Green X
    A_G89("g89", "generated_emojis/g89.png"), // ASCII Green Y
    A_G90("g90", "generated_emojis/g90.png"), // ASCII Green Z
    A_G91("g91", "generated_emojis/g91.png"), // ASCII Green [
    A_G92("g92", "generated_emojis/g92.png"), // ASCII Green \
    A_G93("g93", "generated_emojis/g93.png"), // ASCII Green ]
    A_G94("g94", "generated_emojis/g94.png"), // ASCII Green ^
    A_G95("g95", "generated_emojis/g95.png"), // ASCII Green _
    A_G96("g96", "generated_emojis/g96.png"), // ASCII Green `
    A_G97("g97", "generated_emojis/g97.png"), // ASCII Green a
    A_G98("g98", "generated_emojis/g98.png"), // ASCII Green b
    A_G99("g99", "generated_emojis/g99.png"), // ASCII Green c
    A_G100("g100", "generated_emojis/g100.png"), // ASCII Green d
    A_G101("g101", "generated_emojis/g101.png"), // ASCII Green e
    A_G102("g102", "generated_emojis/g102.png"), // ASCII Green f
    A_G103("g103", "generated_emojis/g103.png"), // ASCII Green g
    A_G104("g104", "generated_emojis/g104.png"), // ASCII Green h
    A_G105("g105", "generated_emojis/g105.png"), // ASCII Green i
    A_G106("g106", "generated_emojis/g106.png"), // ASCII Green j
    A_G107("g107", "generated_emojis/g107.png"), // ASCII Green k
    A_G108("g108", "generated_emojis/g108.png"), // ASCII Green l
    A_G109("g109", "generated_emojis/g109.png"), // ASCII Green m
    A_G110("g110", "generated_emojis/g110.png"), // ASCII Green n
    A_G111("g111", "generated_emojis/g111.png"), // ASCII Green o
    A_G112("g112", "generated_emojis/g112.png"), // ASCII Green p
    A_G113("g113", "generated_emojis/g113.png"), // ASCII Green q
    A_G114("g114", "generated_emojis/g114.png"), // ASCII Green r
    A_G115("g115", "generated_emojis/g115.png"), // ASCII Green s
    A_G116("g116", "generated_emojis/g116.png"), // ASCII Green t
    A_G117("g117", "generated_emojis/g117.png"), // ASCII Green u
    A_G118("g118", "generated_emojis/g118.png"), // ASCII Green v
    A_G119("g119", "generated_emojis/g119.png"), // ASCII Green w
    A_G120("g120", "generated_emojis/g120.png"), // ASCII Green x
    A_G121("g121", "generated_emojis/g121.png"), // ASCII Green y
    A_G122("g122", "generated_emojis/g122.png"), // ASCII Green z
    A_G123("g123", "generated_emojis/g123.png"), // ASCII Green {
    A_G124("g124", "generated_emojis/g124.png"), // ASCII Green |
    A_G125("g125", "generated_emojis/g125.png"), // ASCII Green }
    A_G126("g126", "generated_emojis/g126.png"), // ASCII Green ~
//</editor-fold>

    //<editor-fold desc="Blue Text">
    A_B32("b32", "generated_emojis/b32.png"), // ASCII Blue
    A_B33("b33", "generated_emojis/b33.png"), // ASCII Blue !
    A_B34("b34", "generated_emojis/b34.png"), // ASCII Blue "
    A_B35("b35", "generated_emojis/b35.png"), // ASCII Blue #
    A_B36("b36", "generated_emojis/b36.png"), // ASCII Blue $
    A_B37("b37", "generated_emojis/b37.png"), // ASCII Blue %
    A_B38("b38", "generated_emojis/b38.png"), // ASCII Blue &
    A_B39("b39", "generated_emojis/b39.png"), // ASCII Blue '
    A_B40("b40", "generated_emojis/b40.png"), // ASCII Blue (
    A_B41("b41", "generated_emojis/b41.png"), // ASCII Blue )
    A_B42("b42", "generated_emojis/b42.png"), // ASCII Blue *
    A_B43("b43", "generated_emojis/b43.png"), // ASCII Blue +
    A_B44("b44", "generated_emojis/b44.png"), // ASCII Blue ,
    A_B45("b45", "generated_emojis/b45.png"), // ASCII Blue -
    A_B46("b46", "generated_emojis/b46.png"), // ASCII Blue .
    A_B47("b47", "generated_emojis/b47.png"), // ASCII Blue /
    A_B48("b48", "generated_emojis/b48.png"), // ASCII Blue 0
    A_B49("b49", "generated_emojis/b49.png"), // ASCII Blue 1
    A_B50("b50", "generated_emojis/b50.png"), // ASCII Blue 2
    A_B51("b51", "generated_emojis/b51.png"), // ASCII Blue 3
    A_B52("b52", "generated_emojis/b52.png"), // ASCII Blue 4
    A_B53("b53", "generated_emojis/b53.png"), // ASCII Blue 5
    A_B54("b54", "generated_emojis/b54.png"), // ASCII Blue 6
    A_B55("b55", "generated_emojis/b55.png"), // ASCII Blue 7
    A_B56("b56", "generated_emojis/b56.png"), // ASCII Blue 8
    A_B57("b57", "generated_emojis/b57.png"), // ASCII Blue 9
    A_B58("b58", "generated_emojis/b58.png"), // ASCII Blue :
    A_B59("b59", "generated_emojis/b59.png"), // ASCII Blue ;
    A_B60("b60", "generated_emojis/b60.png"), // ASCII Blue <
    A_B61("b61", "generated_emojis/b61.png"), // ASCII Blue =
    A_B62("b62", "generated_emojis/b62.png"), // ASCII Blue >
    A_B63("b63", "generated_emojis/b63.png"), // ASCII Blue ?
    A_B64("b64", "generated_emojis/b64.png"), // ASCII Blue @
    A_B65("b65", "generated_emojis/b65.png"), // ASCII Blue A
    A_B66("b66", "generated_emojis/b66.png"), // ASCII Blue B
    A_B67("b67", "generated_emojis/b67.png"), // ASCII Blue C
    A_B68("b68", "generated_emojis/b68.png"), // ASCII Blue D
    A_B69("b69", "generated_emojis/b69.png"), // ASCII Blue E
    A_B70("b70", "generated_emojis/b70.png"), // ASCII Blue F
    A_B71("b71", "generated_emojis/b71.png"), // ASCII Blue G
    A_B72("b72", "generated_emojis/b72.png"), // ASCII Blue H
    A_B73("b73", "generated_emojis/b73.png"), // ASCII Blue I
    A_B74("b74", "generated_emojis/b74.png"), // ASCII Blue J
    A_B75("b75", "generated_emojis/b75.png"), // ASCII Blue K
    A_B76("b76", "generated_emojis/b76.png"), // ASCII Blue L
    A_B77("b77", "generated_emojis/b77.png"), // ASCII Blue M
    A_B78("b78", "generated_emojis/b78.png"), // ASCII Blue N
    A_B79("b79", "generated_emojis/b79.png"), // ASCII Blue O
    A_B80("b80", "generated_emojis/b80.png"), // ASCII Blue P
    A_B81("b81", "generated_emojis/b81.png"), // ASCII Blue Q
    A_B82("b82", "generated_emojis/b82.png"), // ASCII Blue R
    A_B83("b83", "generated_emojis/b83.png"), // ASCII Blue S
    A_B84("b84", "generated_emojis/b84.png"), // ASCII Blue T
    A_B85("b85", "generated_emojis/b85.png"), // ASCII Blue U
    A_B86("b86", "generated_emojis/b86.png"), // ASCII Blue V
    A_B87("b87", "generated_emojis/b87.png"), // ASCII Blue W
    A_B88("b88", "generated_emojis/b88.png"), // ASCII Blue X
    A_B89("b89", "generated_emojis/b89.png"), // ASCII Blue Y
    A_B90("b90", "generated_emojis/b90.png"), // ASCII Blue Z
    A_B91("b91", "generated_emojis/b91.png"), // ASCII Blue [
    A_B92("b92", "generated_emojis/b92.png"), // ASCII Blue \
    A_B93("b93", "generated_emojis/b93.png"), // ASCII Blue ]
    A_B94("b94", "generated_emojis/b94.png"), // ASCII Blue ^
    A_B95("b95", "generated_emojis/b95.png"), // ASCII Blue _
    A_B96("b96", "generated_emojis/b96.png"), // ASCII Blue `
    A_B97("b97", "generated_emojis/b97.png"), // ASCII Blue a
    A_B98("b98", "generated_emojis/b98.png"), // ASCII Blue b
    A_B99("b99", "generated_emojis/b99.png"), // ASCII Blue c
    A_B100("b100", "generated_emojis/b100.png"), // ASCII Blue d
    A_B101("b101", "generated_emojis/b101.png"), // ASCII Blue e
    A_B102("b102", "generated_emojis/b102.png"), // ASCII Blue f
    A_B103("b103", "generated_emojis/b103.png"), // ASCII Blue g
    A_B104("b104", "generated_emojis/b104.png"), // ASCII Blue h
    A_B105("b105", "generated_emojis/b105.png"), // ASCII Blue i
    A_B106("b106", "generated_emojis/b106.png"), // ASCII Blue j
    A_B107("b107", "generated_emojis/b107.png"), // ASCII Blue k
    A_B108("b108", "generated_emojis/b108.png"), // ASCII Blue l
    A_B109("b109", "generated_emojis/b109.png"), // ASCII Blue m
    A_B110("b110", "generated_emojis/b110.png"), // ASCII Blue n
    A_B111("b111", "generated_emojis/b111.png"), // ASCII Blue o
    A_B112("b112", "generated_emojis/b112.png"), // ASCII Blue p
    A_B113("b113", "generated_emojis/b113.png"), // ASCII Blue q
    A_B114("b114", "generated_emojis/b114.png"), // ASCII Blue r
    A_B115("b115", "generated_emojis/b115.png"), // ASCII Blue s
    A_B116("b116", "generated_emojis/b116.png"), // ASCII Blue t
    A_B117("b117", "generated_emojis/b117.png"), // ASCII Blue u
    A_B118("b118", "generated_emojis/b118.png"), // ASCII Blue v
    A_B119("b119", "generated_emojis/b119.png"), // ASCII Blue w
    A_B120("b120", "generated_emojis/b120.png"), // ASCII Blue x
    A_B121("b121", "generated_emojis/b121.png"), // ASCII Blue y
    A_B122("b122", "generated_emojis/b122.png"), // ASCII Blue z
    A_B123("b123", "generated_emojis/b123.png"), // ASCII Blue {
    A_B124("b124", "generated_emojis/b124.png"), // ASCII Blue |
    A_B125("b125", "generated_emojis/b125.png"), // ASCII Blue }
    A_B126("b126", "generated_emojis/b126.png"), // ASCII Blue ~
//</editor-fold>

    //<editor-fold desc="Gray Text">
    A_A32("a32", "generated_emojis/a32.png"), // ASCII Gray
    A_A33("a33", "generated_emojis/a33.png"), // ASCII Gray !
    A_A34("a34", "generated_emojis/a34.png"), // ASCII Gray "
    A_A35("a35", "generated_emojis/a35.png"), // ASCII Gray #
    A_A36("a36", "generated_emojis/a36.png"), // ASCII Gray $
    A_A37("a37", "generated_emojis/a37.png"), // ASCII Gray %
    A_A38("a38", "generated_emojis/a38.png"), // ASCII Gray &
    A_A39("a39", "generated_emojis/a39.png"), // ASCII Gray '
    A_A40("a40", "generated_emojis/a40.png"), // ASCII Gray (
    A_A41("a41", "generated_emojis/a41.png"), // ASCII Gray )
    A_A42("a42", "generated_emojis/a42.png"), // ASCII Gray *
    A_A43("a43", "generated_emojis/a43.png"), // ASCII Gray +
    A_A44("a44", "generated_emojis/a44.png"), // ASCII Gray ,
    A_A45("a45", "generated_emojis/a45.png"), // ASCII Gray -
    A_A46("a46", "generated_emojis/a46.png"), // ASCII Gray .
    A_A47("a47", "generated_emojis/a47.png"), // ASCII Gray /
    A_A48("a48", "generated_emojis/a48.png"), // ASCII Gray 0
    A_A49("a49", "generated_emojis/a49.png"), // ASCII Gray 1
    A_A50("a50", "generated_emojis/a50.png"), // ASCII Gray 2
    A_A51("a51", "generated_emojis/a51.png"), // ASCII Gray 3
    A_A52("a52", "generated_emojis/a52.png"), // ASCII Gray 4
    A_A53("a53", "generated_emojis/a53.png"), // ASCII Gray 5
    A_A54("a54", "generated_emojis/a54.png"), // ASCII Gray 6
    A_A55("a55", "generated_emojis/a55.png"), // ASCII Gray 7
    A_A56("a56", "generated_emojis/a56.png"), // ASCII Gray 8
    A_A57("a57", "generated_emojis/a57.png"), // ASCII Gray 9
    A_A58("a58", "generated_emojis/a58.png"), // ASCII Gray :
    A_A59("a59", "generated_emojis/a59.png"), // ASCII Gray ;
    A_A60("a60", "generated_emojis/a60.png"), // ASCII Gray <
    A_A61("a61", "generated_emojis/a61.png"), // ASCII Gray =
    A_A62("a62", "generated_emojis/a62.png"), // ASCII Gray >
    A_A63("a63", "generated_emojis/a63.png"), // ASCII Gray ?
    A_A64("a64", "generated_emojis/a64.png"), // ASCII Gray @
    A_A65("a65", "generated_emojis/a65.png"), // ASCII Gray A
    A_A66("a66", "generated_emojis/a66.png"), // ASCII Gray B
    A_A67("a67", "generated_emojis/a67.png"), // ASCII Gray C
    A_A68("a68", "generated_emojis/a68.png"), // ASCII Gray D
    A_A69("a69", "generated_emojis/a69.png"), // ASCII Gray E
    A_A70("a70", "generated_emojis/a70.png"), // ASCII Gray F
    A_A71("a71", "generated_emojis/a71.png"), // ASCII Gray G
    A_A72("a72", "generated_emojis/a72.png"), // ASCII Gray H
    A_A73("a73", "generated_emojis/a73.png"), // ASCII Gray I
    A_A74("a74", "generated_emojis/a74.png"), // ASCII Gray J
    A_A75("a75", "generated_emojis/a75.png"), // ASCII Gray K
    A_A76("a76", "generated_emojis/a76.png"), // ASCII Gray L
    A_A77("a77", "generated_emojis/a77.png"), // ASCII Gray M
    A_A78("a78", "generated_emojis/a78.png"), // ASCII Gray N
    A_A79("a79", "generated_emojis/a79.png"), // ASCII Gray O
    A_A80("a80", "generated_emojis/a80.png"), // ASCII Gray P
    A_A81("a81", "generated_emojis/a81.png"), // ASCII Gray Q
    A_A82("a82", "generated_emojis/a82.png"), // ASCII Gray R
    A_A83("a83", "generated_emojis/a83.png"), // ASCII Gray S
    A_A84("a84", "generated_emojis/a84.png"), // ASCII Gray T
    A_A85("a85", "generated_emojis/a85.png"), // ASCII Gray U
    A_A86("a86", "generated_emojis/a86.png"), // ASCII Gray V
    A_A87("a87", "generated_emojis/a87.png"), // ASCII Gray W
    A_A88("a88", "generated_emojis/a88.png"), // ASCII Gray X
    A_A89("a89", "generated_emojis/a89.png"), // ASCII Gray Y
    A_A90("a90", "generated_emojis/a90.png"), // ASCII Gray Z
    A_A91("a91", "generated_emojis/a91.png"), // ASCII Gray [
    A_A92("a92", "generated_emojis/a92.png"), // ASCII Gray \
    A_A93("a93", "generated_emojis/a93.png"), // ASCII Gray ]
    A_A94("a94", "generated_emojis/a94.png"), // ASCII Gray ^
    A_A95("a95", "generated_emojis/a95.png"), // ASCII Gray _
    A_A96("a96", "generated_emojis/a96.png"), // ASCII Gray `
    A_A97("a97", "generated_emojis/a97.png"), // ASCII Gray a
    A_A98("a98", "generated_emojis/a98.png"), // ASCII Gray b
    A_A99("a99", "generated_emojis/a99.png"), // ASCII Gray c
    A_A100("a100", "generated_emojis/a100.png"), // ASCII Gray d
    A_A101("a101", "generated_emojis/a101.png"), // ASCII Gray e
    A_A102("a102", "generated_emojis/a102.png"), // ASCII Gray f
    A_A103("a103", "generated_emojis/a103.png"), // ASCII Gray g
    A_A104("a104", "generated_emojis/a104.png"), // ASCII Gray h
    A_A105("a105", "generated_emojis/a105.png"), // ASCII Gray i
    A_A106("a106", "generated_emojis/a106.png"), // ASCII Gray j
    A_A107("a107", "generated_emojis/a107.png"), // ASCII Gray k
    A_A108("a108", "generated_emojis/a108.png"), // ASCII Gray l
    A_A109("a109", "generated_emojis/a109.png"), // ASCII Gray m
    A_A110("a110", "generated_emojis/a110.png"), // ASCII Gray n
    A_A111("a111", "generated_emojis/a111.png"), // ASCII Gray o
    A_A112("a112", "generated_emojis/a112.png"), // ASCII Gray p
    A_A113("a113", "generated_emojis/a113.png"), // ASCII Gray q
    A_A114("a114", "generated_emojis/a114.png"), // ASCII Gray r
    A_A115("a115", "generated_emojis/a115.png"), // ASCII Gray s
    A_A116("a116", "generated_emojis/a116.png"), // ASCII Gray t
    A_A117("a117", "generated_emojis/a117.png"), // ASCII Gray u
    A_A118("a118", "generated_emojis/a118.png"), // ASCII Gray v
    A_A119("a119", "generated_emojis/a119.png"), // ASCII Gray w
    A_A120("a120", "generated_emojis/a120.png"), // ASCII Gray x
    A_A121("a121", "generated_emojis/a121.png"), // ASCII Gray y
    A_A122("a122", "generated_emojis/a122.png"), // ASCII Gray z
    A_A123("a123", "generated_emojis/a123.png"), // ASCII Gray {
    A_A124("a124", "generated_emojis/a124.png"), // ASCII Gray |
    A_A125("a125", "generated_emojis/a125.png"), // ASCII Gray }
    A_A126("a126", "generated_emojis/a126.png"), // ASCII Gray ~
//</editor-fold>

    //<editor-fold desc="Light Gray Text">
    A_L32("l32", "generated_emojis/l32.png"), // ASCII Light Gray
    A_L33("l33", "generated_emojis/l33.png"), // ASCII Light Gray !
    A_L34("l34", "generated_emojis/l34.png"), // ASCII Light Gray "
    A_L35("l35", "generated_emojis/l35.png"), // ASCII Light Gray #
    A_L36("l36", "generated_emojis/l36.png"), // ASCII Light Gray $
    A_L37("l37", "generated_emojis/l37.png"), // ASCII Light Gray %
    A_L38("l38", "generated_emojis/l38.png"), // ASCII Light Gray &
    A_L39("l39", "generated_emojis/l39.png"), // ASCII Light Gray '
    A_L40("l40", "generated_emojis/l40.png"), // ASCII Light Gray (
    A_L41("l41", "generated_emojis/l41.png"), // ASCII Light Gray )
    A_L42("l42", "generated_emojis/l42.png"), // ASCII Light Gray *
    A_L43("l43", "generated_emojis/l43.png"), // ASCII Light Gray +
    A_L44("l44", "generated_emojis/l44.png"), // ASCII Light Gray ,
    A_L45("l45", "generated_emojis/l45.png"), // ASCII Light Gray -
    A_L46("l46", "generated_emojis/l46.png"), // ASCII Light Gray .
    A_L47("l47", "generated_emojis/l47.png"), // ASCII Light Gray /
    A_L48("l48", "generated_emojis/l48.png"), // ASCII Light Gray 0
    A_L49("l49", "generated_emojis/l49.png"), // ASCII Light Gray 1
    A_L50("l50", "generated_emojis/l50.png"), // ASCII Light Gray 2
    A_L51("l51", "generated_emojis/l51.png"), // ASCII Light Gray 3
    A_L52("l52", "generated_emojis/l52.png"), // ASCII Light Gray 4
    A_L53("l53", "generated_emojis/l53.png"), // ASCII Light Gray 5
    A_L54("l54", "generated_emojis/l54.png"), // ASCII Light Gray 6
    A_L55("l55", "generated_emojis/l55.png"), // ASCII Light Gray 7
    A_L56("l56", "generated_emojis/l56.png"), // ASCII Light Gray 8
    A_L57("l57", "generated_emojis/l57.png"), // ASCII Light Gray 9
    A_L58("l58", "generated_emojis/l58.png"), // ASCII Light Gray :
    A_L59("l59", "generated_emojis/l59.png"), // ASCII Light Gray ;
    A_L60("l60", "generated_emojis/l60.png"), // ASCII Light Gray <
    A_L61("l61", "generated_emojis/l61.png"), // ASCII Light Gray =
    A_L62("l62", "generated_emojis/l62.png"), // ASCII Light Gray >
    A_L63("l63", "generated_emojis/l63.png"), // ASCII Light Gray ?
    A_L64("l64", "generated_emojis/l64.png"), // ASCII Light Gray @
    A_L65("l65", "generated_emojis/l65.png"), // ASCII Light Gray A
    A_L66("l66", "generated_emojis/l66.png"), // ASCII Light Gray B
    A_L67("l67", "generated_emojis/l67.png"), // ASCII Light Gray C
    A_L68("l68", "generated_emojis/l68.png"), // ASCII Light Gray D
    A_L69("l69", "generated_emojis/l69.png"), // ASCII Light Gray E
    A_L70("l70", "generated_emojis/l70.png"), // ASCII Light Gray F
    A_L71("l71", "generated_emojis/l71.png"), // ASCII Light Gray G
    A_L72("l72", "generated_emojis/l72.png"), // ASCII Light Gray H
    A_L73("l73", "generated_emojis/l73.png"), // ASCII Light Gray I
    A_L74("l74", "generated_emojis/l74.png"), // ASCII Light Gray J
    A_L75("l75", "generated_emojis/l75.png"), // ASCII Light Gray K
    A_L76("l76", "generated_emojis/l76.png"), // ASCII Light Gray L
    A_L77("l77", "generated_emojis/l77.png"), // ASCII Light Gray M
    A_L78("l78", "generated_emojis/l78.png"), // ASCII Light Gray N
    A_L79("l79", "generated_emojis/l79.png"), // ASCII Light Gray O
    A_L80("l80", "generated_emojis/l80.png"), // ASCII Light Gray P
    A_L81("l81", "generated_emojis/l81.png"), // ASCII Light Gray Q
    A_L82("l82", "generated_emojis/l82.png"), // ASCII Light Gray R
    A_L83("l83", "generated_emojis/l83.png"), // ASCII Light Gray S
    A_L84("l84", "generated_emojis/l84.png"), // ASCII Light Gray T
    A_L85("l85", "generated_emojis/l85.png"), // ASCII Light Gray U
    A_L86("l86", "generated_emojis/l86.png"), // ASCII Light Gray V
    A_L87("l87", "generated_emojis/l87.png"), // ASCII Light Gray W
    A_L88("l88", "generated_emojis/l88.png"), // ASCII Light Gray X
    A_L89("l89", "generated_emojis/l89.png"), // ASCII Light Gray Y
    A_L90("l90", "generated_emojis/l90.png"), // ASCII Light Gray Z
    A_L91("l91", "generated_emojis/l91.png"), // ASCII Light Gray [
    A_L92("l92", "generated_emojis/l92.png"), // ASCII Light Gray \
    A_L93("l93", "generated_emojis/l93.png"), // ASCII Light Gray ]
    A_L94("l94", "generated_emojis/l94.png"), // ASCII Light Gray ^
    A_L95("l95", "generated_emojis/l95.png"), // ASCII Light Gray _
    A_L96("l96", "generated_emojis/l96.png"), // ASCII Light Gray `
    A_L97("l97", "generated_emojis/l97.png"), // ASCII Light Gray a
    A_L98("l98", "generated_emojis/l98.png"), // ASCII Light Gray b
    A_L99("l99", "generated_emojis/l99.png"), // ASCII Light Gray c
    A_L100("l100", "generated_emojis/l100.png"), // ASCII Light Gray d
    A_L101("l101", "generated_emojis/l101.png"), // ASCII Light Gray e
    A_L102("l102", "generated_emojis/l102.png"), // ASCII Light Gray f
    A_L103("l103", "generated_emojis/l103.png"), // ASCII Light Gray g
    A_L104("l104", "generated_emojis/l104.png"), // ASCII Light Gray h
    A_L105("l105", "generated_emojis/l105.png"), // ASCII Light Gray i
    A_L106("l106", "generated_emojis/l106.png"), // ASCII Light Gray j
    A_L107("l107", "generated_emojis/l107.png"), // ASCII Light Gray k
    A_L108("l108", "generated_emojis/l108.png"), // ASCII Light Gray l
    A_L109("l109", "generated_emojis/l109.png"), // ASCII Light Gray m
    A_L110("l110", "generated_emojis/l110.png"), // ASCII Light Gray n
    A_L111("l111", "generated_emojis/l111.png"), // ASCII Light Gray o
    A_L112("l112", "generated_emojis/l112.png"), // ASCII Light Gray p
    A_L113("l113", "generated_emojis/l113.png"), // ASCII Light Gray q
    A_L114("l114", "generated_emojis/l114.png"), // ASCII Light Gray r
    A_L115("l115", "generated_emojis/l115.png"), // ASCII Light Gray s
    A_L116("l116", "generated_emojis/l116.png"), // ASCII Light Gray t
    A_L117("l117", "generated_emojis/l117.png"), // ASCII Light Gray u
    A_L118("l118", "generated_emojis/l118.png"), // ASCII Light Gray v
    A_L119("l119", "generated_emojis/l119.png"), // ASCII Light Gray w
    A_L120("l120", "generated_emojis/l120.png"), // ASCII Light Gray x
    A_L121("l121", "generated_emojis/l121.png"), // ASCII Light Gray y
    A_L122("l122", "generated_emojis/l122.png"), // ASCII Light Gray z
    A_L123("l123", "generated_emojis/l123.png"), // ASCII Light Gray {
    A_L124("l124", "generated_emojis/l124.png"), // ASCII Light Gray |
    A_L125("l125", "generated_emojis/l125.png"), // ASCII Light Gray }
    A_L126("l126", "generated_emojis/l126.png"), // ASCII Light Gray ~
//</editor-fold>

    //<editor-fold desc="Fira Code White Text">
    A_F32("f32", "generated_emojis/f32.png"), // ASCII White
    A_F33("f33", "generated_emojis/f33.png"), // ASCII White !
    A_F34("f34", "generated_emojis/f34.png"), // ASCII White "
    A_F35("f35", "generated_emojis/f35.png"), // ASCII White #
    A_F36("f36", "generated_emojis/f36.png"), // ASCII White $
    A_F37("f37", "generated_emojis/f37.png"), // ASCII White %
    A_F38("f38", "generated_emojis/f38.png"), // ASCII White &
    A_F39("f39", "generated_emojis/f39.png"), // ASCII White '
    A_F40("f40", "generated_emojis/f40.png"), // ASCII White (
    A_F41("f41", "generated_emojis/f41.png"), // ASCII White )
    A_F42("f42", "generated_emojis/f42.png"), // ASCII White *
    A_F43("f43", "generated_emojis/f43.png"), // ASCII White +
    A_F44("f44", "generated_emojis/f44.png"), // ASCII White ,
    A_F45("f45", "generated_emojis/f45.png"), // ASCII White -
    A_F46("f46", "generated_emojis/f46.png"), // ASCII White .
    A_F47("f47", "generated_emojis/f47.png"), // ASCII White /
    A_F48("f48", "generated_emojis/f48.png"), // ASCII White 0
    A_F49("f49", "generated_emojis/f49.png"), // ASCII White 1
    A_F50("f50", "generated_emojis/f50.png"), // ASCII White 2
    A_F51("f51", "generated_emojis/f51.png"), // ASCII White 3
    A_F52("f52", "generated_emojis/f52.png"), // ASCII White 4
    A_F53("f53", "generated_emojis/f53.png"), // ASCII White 5
    A_F54("f54", "generated_emojis/f54.png"), // ASCII White 6
    A_F55("f55", "generated_emojis/f55.png"), // ASCII White 7
    A_F56("f56", "generated_emojis/f56.png"), // ASCII White 8
    A_F57("f57", "generated_emojis/f57.png"), // ASCII White 9
    A_F58("f58", "generated_emojis/f58.png"), // ASCII White :
    A_F59("f59", "generated_emojis/f59.png"), // ASCII White ;
    A_F60("f60", "generated_emojis/f60.png"), // ASCII White <
    A_F61("f61", "generated_emojis/f61.png"), // ASCII White =
    A_F62("f62", "generated_emojis/f62.png"), // ASCII White >
    A_F63("f63", "generated_emojis/f63.png"), // ASCII White ?
    A_F64("f64", "generated_emojis/f64.png"), // ASCII White @
    A_F65("f65", "generated_emojis/f65.png"), // ASCII White A
    A_F66("f66", "generated_emojis/f66.png"), // ASCII White B
    A_F67("f67", "generated_emojis/f67.png"), // ASCII White C
    A_F68("f68", "generated_emojis/f68.png"), // ASCII White D
    A_F69("f69", "generated_emojis/f69.png"), // ASCII White E
    A_F70("f70", "generated_emojis/f70.png"), // ASCII White F
    A_F71("f71", "generated_emojis/f71.png"), // ASCII White G
    A_F72("f72", "generated_emojis/f72.png"), // ASCII White H
    A_F73("f73", "generated_emojis/f73.png"), // ASCII White I
    A_F74("f74", "generated_emojis/f74.png"), // ASCII White J
    A_F75("f75", "generated_emojis/f75.png"), // ASCII White K
    A_F76("f76", "generated_emojis/f76.png"), // ASCII White L
    A_F77("f77", "generated_emojis/f77.png"), // ASCII White M
    A_F78("f78", "generated_emojis/f78.png"), // ASCII White N
    A_F79("f79", "generated_emojis/f79.png"), // ASCII White O
    A_F80("f80", "generated_emojis/f80.png"), // ASCII White P
    A_F81("f81", "generated_emojis/f81.png"), // ASCII White Q
    A_F82("f82", "generated_emojis/f82.png"), // ASCII White R
    A_F83("f83", "generated_emojis/f83.png"), // ASCII White S
    A_F84("f84", "generated_emojis/f84.png"), // ASCII White T
    A_F85("f85", "generated_emojis/f85.png"), // ASCII White U
    A_F86("f86", "generated_emojis/f86.png"), // ASCII White V
    A_F87("f87", "generated_emojis/f87.png"), // ASCII White W
    A_F88("f88", "generated_emojis/f88.png"), // ASCII White X
    A_F89("f89", "generated_emojis/f89.png"), // ASCII White Y
    A_F90("f90", "generated_emojis/f90.png"), // ASCII White Z
    A_F91("f91", "generated_emojis/f91.png"), // ASCII White [
    A_F92("f92", "generated_emojis/f92.png"), // ASCII White \
    A_F93("f93", "generated_emojis/f93.png"), // ASCII White ]
    A_F94("f94", "generated_emojis/f94.png"), // ASCII White ^
    A_F95("f95", "generated_emojis/f95.png"), // ASCII White _
    A_F96("f96", "generated_emojis/f96.png"), // ASCII White `
    A_F97("f97", "generated_emojis/f97.png"), // ASCII White a
    A_F98("f98", "generated_emojis/f98.png"), // ASCII White b
    A_F99("f99", "generated_emojis/f99.png"), // ASCII White c
    A_F100("f100", "generated_emojis/f100.png"), // ASCII White d
    A_F101("f101", "generated_emojis/f101.png"), // ASCII White e
    A_F102("f102", "generated_emojis/f102.png"), // ASCII White f
    A_F103("f103", "generated_emojis/f103.png"), // ASCII White g
    A_F104("f104", "generated_emojis/f104.png"), // ASCII White h
    A_F105("f105", "generated_emojis/f105.png"), // ASCII White i
    A_F106("f106", "generated_emojis/f106.png"), // ASCII White j
    A_F107("f107", "generated_emojis/f107.png"), // ASCII White k
    A_F108("f108", "generated_emojis/f108.png"), // ASCII White l
    A_F109("f109", "generated_emojis/f109.png"), // ASCII White m
    A_F110("f110", "generated_emojis/f110.png"), // ASCII White n
    A_F111("f111", "generated_emojis/f111.png"), // ASCII White o
    A_F112("f112", "generated_emojis/f112.png"), // ASCII White p
    A_F113("f113", "generated_emojis/f113.png"), // ASCII White q
    A_F114("f114", "generated_emojis/f114.png"), // ASCII White r
    A_F115("f115", "generated_emojis/f115.png"), // ASCII White s
    A_F116("f116", "generated_emojis/f116.png"), // ASCII White t
    A_F117("f117", "generated_emojis/f117.png"), // ASCII White u
    A_F118("f118", "generated_emojis/f118.png"), // ASCII White v
    A_F119("f119", "generated_emojis/f119.png"), // ASCII White w
    A_F120("f120", "generated_emojis/f120.png"), // ASCII White x
    A_F121("f121", "generated_emojis/f121.png"), // ASCII White y
    A_F122("f122", "generated_emojis/f122.png"), // ASCII White z
    A_F123("f123", "generated_emojis/f123.png"), // ASCII White {
    A_F124("f124", "generated_emojis/f124.png"), // ASCII White |
    A_F125("f125", "generated_emojis/f125.png"), // ASCII White }
    A_F126("f126", "generated_emojis/f126.png"), // ASCII White ~
//</editor-fold>

    //<editor-fold desc="Fira Code Orange Text">
    A_FO32("fo32", "generated_emojis/fo32.png"), // ASCII Orange
    A_FO33("fo33", "generated_emojis/fo33.png"), // ASCII Orange !
    A_FO34("fo34", "generated_emojis/fo34.png"), // ASCII Orange "
    A_FO35("fo35", "generated_emojis/fo35.png"), // ASCII Orange #
    A_FO36("fo36", "generated_emojis/fo36.png"), // ASCII Orange $
    A_FO37("fo37", "generated_emojis/fo37.png"), // ASCII Orange %
    A_FO38("fo38", "generated_emojis/fo38.png"), // ASCII Orange &
    A_FO39("fo39", "generated_emojis/fo39.png"), // ASCII Orange '
    A_FO40("fo40", "generated_emojis/fo40.png"), // ASCII Orange (
    A_FO41("fo41", "generated_emojis/fo41.png"), // ASCII Orange )
    A_FO42("fo42", "generated_emojis/fo42.png"), // ASCII Orange *
    A_FO43("fo43", "generated_emojis/fo43.png"), // ASCII Orange +
    A_FO44("fo44", "generated_emojis/fo44.png"), // ASCII Orange ,
    A_FO45("fo45", "generated_emojis/fo45.png"), // ASCII Orange -
    A_FO46("fo46", "generated_emojis/fo46.png"), // ASCII Orange .
    A_FO47("fo47", "generated_emojis/fo47.png"), // ASCII Orange /
    A_FO48("fo48", "generated_emojis/fo48.png"), // ASCII Orange 0
    A_FO49("fo49", "generated_emojis/fo49.png"), // ASCII Orange 1
    A_FO50("fo50", "generated_emojis/fo50.png"), // ASCII Orange 2
    A_FO51("fo51", "generated_emojis/fo51.png"), // ASCII Orange 3
    A_FO52("fo52", "generated_emojis/fo52.png"), // ASCII Orange 4
    A_FO53("fo53", "generated_emojis/fo53.png"), // ASCII Orange 5
    A_FO54("fo54", "generated_emojis/fo54.png"), // ASCII Orange 6
    A_FO55("fo55", "generated_emojis/fo55.png"), // ASCII Orange 7
    A_FO56("fo56", "generated_emojis/fo56.png"), // ASCII Orange 8
    A_FO57("fo57", "generated_emojis/fo57.png"), // ASCII Orange 9
    A_FO58("fo58", "generated_emojis/fo58.png"), // ASCII Orange :
    A_FO59("fo59", "generated_emojis/fo59.png"), // ASCII Orange ;
    A_FO60("fo60", "generated_emojis/fo60.png"), // ASCII Orange <
    A_FO61("fo61", "generated_emojis/fo61.png"), // ASCII Orange =
    A_FO62("fo62", "generated_emojis/fo62.png"), // ASCII Orange >
    A_FO63("fo63", "generated_emojis/fo63.png"), // ASCII Orange ?
    A_FO64("fo64", "generated_emojis/fo64.png"), // ASCII Orange @
    A_FO65("fo65", "generated_emojis/fo65.png"), // ASCII Orange A
    A_FO66("fo66", "generated_emojis/fo66.png"), // ASCII Orange B
    A_FO67("fo67", "generated_emojis/fo67.png"), // ASCII Orange C
    A_FO68("fo68", "generated_emojis/fo68.png"), // ASCII Orange D
    A_FO69("fo69", "generated_emojis/fo69.png"), // ASCII Orange E
    A_FO70("fo70", "generated_emojis/fo70.png"), // ASCII Orange F
    A_FO71("fo71", "generated_emojis/fo71.png"), // ASCII Orange G
    A_FO72("fo72", "generated_emojis/fo72.png"), // ASCII Orange H
    A_FO73("fo73", "generated_emojis/fo73.png"), // ASCII Orange I
    A_FO74("fo74", "generated_emojis/fo74.png"), // ASCII Orange J
    A_FO75("fo75", "generated_emojis/fo75.png"), // ASCII Orange K
    A_FO76("fo76", "generated_emojis/fo76.png"), // ASCII Orange L
    A_FO77("fo77", "generated_emojis/fo77.png"), // ASCII Orange M
    A_FO78("fo78", "generated_emojis/fo78.png"), // ASCII Orange N
    A_FO79("fo79", "generated_emojis/fo79.png"), // ASCII Orange O
    A_FO80("fo80", "generated_emojis/fo80.png"), // ASCII Orange P
    A_FO81("fo81", "generated_emojis/fo81.png"), // ASCII Orange Q
    A_FO82("fo82", "generated_emojis/fo82.png"), // ASCII Orange R
    A_FO83("fo83", "generated_emojis/fo83.png"), // ASCII Orange S
    A_FO84("fo84", "generated_emojis/fo84.png"), // ASCII Orange T
    A_FO85("fo85", "generated_emojis/fo85.png"), // ASCII Orange U
    A_FO86("fo86", "generated_emojis/fo86.png"), // ASCII Orange V
    A_FO87("fo87", "generated_emojis/fo87.png"), // ASCII Orange W
    A_FO88("fo88", "generated_emojis/fo88.png"), // ASCII Orange X
    A_FO89("fo89", "generated_emojis/fo89.png"), // ASCII Orange Y
    A_FO90("fo90", "generated_emojis/fo90.png"), // ASCII Orange Z
    A_FO91("fo91", "generated_emojis/fo91.png"), // ASCII Orange [
    A_FO92("fo92", "generated_emojis/fo92.png"), // ASCII Orange \
    A_FO93("fo93", "generated_emojis/fo93.png"), // ASCII Orange ]
    A_FO94("fo94", "generated_emojis/fo94.png"), // ASCII Orange ^
    A_FO95("fo95", "generated_emojis/fo95.png"), // ASCII Orange _
    A_FO96("fo96", "generated_emojis/fo96.png"), // ASCII Orange `
    A_FO97("fo97", "generated_emojis/fo97.png"), // ASCII Orange a
    A_FO98("fo98", "generated_emojis/fo98.png"), // ASCII Orange b
    A_FO99("fo99", "generated_emojis/fo99.png"), // ASCII Orange c
    A_FO100("fo100", "generated_emojis/fo100.png"), // ASCII Orange d
    A_FO101("fo101", "generated_emojis/fo101.png"), // ASCII Orange e
    A_FO102("fo102", "generated_emojis/fo102.png"), // ASCII Orange f
    A_FO103("fo103", "generated_emojis/fo103.png"), // ASCII Orange g
    A_FO104("fo104", "generated_emojis/fo104.png"), // ASCII Orange h
    A_FO105("fo105", "generated_emojis/fo105.png"), // ASCII Orange i
    A_FO106("fo106", "generated_emojis/fo106.png"), // ASCII Orange j
    A_FO107("fo107", "generated_emojis/fo107.png"), // ASCII Orange k
    A_FO108("fo108", "generated_emojis/fo108.png"), // ASCII Orange l
    A_FO109("fo109", "generated_emojis/fo109.png"), // ASCII Orange m
    A_FO110("fo110", "generated_emojis/fo110.png"), // ASCII Orange n
    A_FO111("fo111", "generated_emojis/fo111.png"), // ASCII Orange o
    A_FO112("fo112", "generated_emojis/fo112.png"), // ASCII Orange p
    A_FO113("fo113", "generated_emojis/fo113.png"), // ASCII Orange q
    A_FO114("fo114", "generated_emojis/fo114.png"), // ASCII Orange r
    A_FO115("fo115", "generated_emojis/fo115.png"), // ASCII Orange s
    A_FO116("fo116", "generated_emojis/fo116.png"), // ASCII Orange t
    A_FO117("fo117", "generated_emojis/fo117.png"), // ASCII Orange u
    A_FO118("fo118", "generated_emojis/fo118.png"), // ASCII Orange v
    A_FO119("fo119", "generated_emojis/fo119.png"), // ASCII Orange w
    A_FO120("fo120", "generated_emojis/fo120.png"), // ASCII Orange x
    A_FO121("fo121", "generated_emojis/fo121.png"), // ASCII Orange y
    A_FO122("fo122", "generated_emojis/fo122.png"), // ASCII Orange z
    A_FO123("fo123", "generated_emojis/fo123.png"), // ASCII Orange {
    A_FO124("fo124", "generated_emojis/fo124.png"), // ASCII Orange |
    A_FO125("fo125", "generated_emojis/fo125.png"), // ASCII Orange }
    A_FO126("fo126", "generated_emojis/fo126.png"), // ASCII Orange ~
//</editor-fold>

    //<editor-fold desc="Fira Code Green Text">
    A_FG32("fg32", "generated_emojis/fg32.png"), // ASCII Green
    A_FG33("fg33", "generated_emojis/fg33.png"), // ASCII Green !
    A_FG34("fg34", "generated_emojis/fg34.png"), // ASCII Green "
    A_FG35("fg35", "generated_emojis/fg35.png"), // ASCII Green #
    A_FG36("fg36", "generated_emojis/fg36.png"), // ASCII Green $
    A_FG37("fg37", "generated_emojis/fg37.png"), // ASCII Green %
    A_FG38("fg38", "generated_emojis/fg38.png"), // ASCII Green &
    A_FG39("fg39", "generated_emojis/fg39.png"), // ASCII Green '
    A_FG40("fg40", "generated_emojis/fg40.png"), // ASCII Green (
    A_FG41("fg41", "generated_emojis/fg41.png"), // ASCII Green )
    A_FG42("fg42", "generated_emojis/fg42.png"), // ASCII Green *
    A_FG43("fg43", "generated_emojis/fg43.png"), // ASCII Green +
    A_FG44("fg44", "generated_emojis/fg44.png"), // ASCII Green ,
    A_FG45("fg45", "generated_emojis/fg45.png"), // ASCII Green -
    A_FG46("fg46", "generated_emojis/fg46.png"), // ASCII Green .
    A_FG47("fg47", "generated_emojis/fg47.png"), // ASCII Green /
    A_FG48("fg48", "generated_emojis/fg48.png"), // ASCII Green 0
    A_FG49("fg49", "generated_emojis/fg49.png"), // ASCII Green 1
    A_FG50("fg50", "generated_emojis/fg50.png"), // ASCII Green 2
    A_FG51("fg51", "generated_emojis/fg51.png"), // ASCII Green 3
    A_FG52("fg52", "generated_emojis/fg52.png"), // ASCII Green 4
    A_FG53("fg53", "generated_emojis/fg53.png"), // ASCII Green 5
    A_FG54("fg54", "generated_emojis/fg54.png"), // ASCII Green 6
    A_FG55("fg55", "generated_emojis/fg55.png"), // ASCII Green 7
    A_FG56("fg56", "generated_emojis/fg56.png"), // ASCII Green 8
    A_FG57("fg57", "generated_emojis/fg57.png"), // ASCII Green 9
    A_FG58("fg58", "generated_emojis/fg58.png"), // ASCII Green :
    A_FG59("fg59", "generated_emojis/fg59.png"), // ASCII Green ;
    A_FG60("fg60", "generated_emojis/fg60.png"), // ASCII Green <
    A_FG61("fg61", "generated_emojis/fg61.png"), // ASCII Green =
    A_FG62("fg62", "generated_emojis/fg62.png"), // ASCII Green >
    A_FG63("fg63", "generated_emojis/fg63.png"), // ASCII Green ?
    A_FG64("fg64", "generated_emojis/fg64.png"), // ASCII Green @
    A_FG65("fg65", "generated_emojis/fg65.png"), // ASCII Green A
    A_FG66("fg66", "generated_emojis/fg66.png"), // ASCII Green B
    A_FG67("fg67", "generated_emojis/fg67.png"), // ASCII Green C
    A_FG68("fg68", "generated_emojis/fg68.png"), // ASCII Green D
    A_FG69("fg69", "generated_emojis/fg69.png"), // ASCII Green E
    A_FG70("fg70", "generated_emojis/fg70.png"), // ASCII Green F
    A_FG71("fg71", "generated_emojis/fg71.png"), // ASCII Green G
    A_FG72("fg72", "generated_emojis/fg72.png"), // ASCII Green H
    A_FG73("fg73", "generated_emojis/fg73.png"), // ASCII Green I
    A_FG74("fg74", "generated_emojis/fg74.png"), // ASCII Green J
    A_FG75("fg75", "generated_emojis/fg75.png"), // ASCII Green K
    A_FG76("fg76", "generated_emojis/fg76.png"), // ASCII Green L
    A_FG77("fg77", "generated_emojis/fg77.png"), // ASCII Green M
    A_FG78("fg78", "generated_emojis/fg78.png"), // ASCII Green N
    A_FG79("fg79", "generated_emojis/fg79.png"), // ASCII Green O
    A_FG80("fg80", "generated_emojis/fg80.png"), // ASCII Green P
    A_FG81("fg81", "generated_emojis/fg81.png"), // ASCII Green Q
    A_FG82("fg82", "generated_emojis/fg82.png"), // ASCII Green R
    A_FG83("fg83", "generated_emojis/fg83.png"), // ASCII Green S
    A_FG84("fg84", "generated_emojis/fg84.png"), // ASCII Green T
    A_FG85("fg85", "generated_emojis/fg85.png"), // ASCII Green U
    A_FG86("fg86", "generated_emojis/fg86.png"), // ASCII Green V
    A_FG87("fg87", "generated_emojis/fg87.png"), // ASCII Green W
    A_FG88("fg88", "generated_emojis/fg88.png"), // ASCII Green X
    A_FG89("fg89", "generated_emojis/fg89.png"), // ASCII Green Y
    A_FG90("fg90", "generated_emojis/fg90.png"), // ASCII Green Z
    A_FG91("fg91", "generated_emojis/fg91.png"), // ASCII Green [
    A_FG92("fg92", "generated_emojis/fg92.png"), // ASCII Green \
    A_FG93("fg93", "generated_emojis/fg93.png"), // ASCII Green ]
    A_FG94("fg94", "generated_emojis/fg94.png"), // ASCII Green ^
    A_FG95("fg95", "generated_emojis/fg95.png"), // ASCII Green _
    A_FG96("fg96", "generated_emojis/fg96.png"), // ASCII Green `
    A_FG97("fg97", "generated_emojis/fg97.png"), // ASCII Green a
    A_FG98("fg98", "generated_emojis/fg98.png"), // ASCII Green b
    A_FG99("fg99", "generated_emojis/fg99.png"), // ASCII Green c
    A_FG100("fg100", "generated_emojis/fg100.png"), // ASCII Green d
    A_FG101("fg101", "generated_emojis/fg101.png"), // ASCII Green e
    A_FG102("fg102", "generated_emojis/fg102.png"), // ASCII Green f
    A_FG103("fg103", "generated_emojis/fg103.png"), // ASCII Green g
    A_FG104("fg104", "generated_emojis/fg104.png"), // ASCII Green h
    A_FG105("fg105", "generated_emojis/fg105.png"), // ASCII Green i
    A_FG106("fg106", "generated_emojis/fg106.png"), // ASCII Green j
    A_FG107("fg107", "generated_emojis/fg107.png"), // ASCII Green k
    A_FG108("fg108", "generated_emojis/fg108.png"), // ASCII Green l
    A_FG109("fg109", "generated_emojis/fg109.png"), // ASCII Green m
    A_FG110("fg110", "generated_emojis/fg110.png"), // ASCII Green n
    A_FG111("fg111", "generated_emojis/fg111.png"), // ASCII Green o
    A_FG112("fg112", "generated_emojis/fg112.png"), // ASCII Green p
    A_FG113("fg113", "generated_emojis/fg113.png"), // ASCII Green q
    A_FG114("fg114", "generated_emojis/fg114.png"), // ASCII Green r
    A_FG115("fg115", "generated_emojis/fg115.png"), // ASCII Green s
    A_FG116("fg116", "generated_emojis/fg116.png"), // ASCII Green t
    A_FG117("fg117", "generated_emojis/fg117.png"), // ASCII Green u
    A_FG118("fg118", "generated_emojis/fg118.png"), // ASCII Green v
    A_FG119("fg119", "generated_emojis/fg119.png"), // ASCII Green w
    A_FG120("fg120", "generated_emojis/fg120.png"), // ASCII Green x
    A_FG121("fg121", "generated_emojis/fg121.png"), // ASCII Green y
    A_FG122("fg122", "generated_emojis/fg122.png"), // ASCII Green z
    A_FG123("fg123", "generated_emojis/fg123.png"), // ASCII Green {
    A_FG124("fg124", "generated_emojis/fg124.png"), // ASCII Green |
    A_FG125("fg125", "generated_emojis/fg125.png"), // ASCII Green }
    A_FG126("fg126", "generated_emojis/fg126.png"), // ASCII Green ~
//</editor-fold>

    //<editor-fold desc="Fira Code Blue Text">
    A_FB32("fb32", "generated_emojis/fb32.png"), // ASCII Blue
    A_FB33("fb33", "generated_emojis/fb33.png"), // ASCII Blue !
    A_FB34("fb34", "generated_emojis/fb34.png"), // ASCII Blue "
    A_FB35("fb35", "generated_emojis/fb35.png"), // ASCII Blue #
    A_FB36("fb36", "generated_emojis/fb36.png"), // ASCII Blue $
    A_FB37("fb37", "generated_emojis/fb37.png"), // ASCII Blue %
    A_FB38("fb38", "generated_emojis/fb38.png"), // ASCII Blue &
    A_FB39("fb39", "generated_emojis/fb39.png"), // ASCII Blue '
    A_FB40("fb40", "generated_emojis/fb40.png"), // ASCII Blue (
    A_FB41("fb41", "generated_emojis/fb41.png"), // ASCII Blue )
    A_FB42("fb42", "generated_emojis/fb42.png"), // ASCII Blue *
    A_FB43("fb43", "generated_emojis/fb43.png"), // ASCII Blue +
    A_FB44("fb44", "generated_emojis/fb44.png"), // ASCII Blue ,
    A_FB45("fb45", "generated_emojis/fb45.png"), // ASCII Blue -
    A_FB46("fb46", "generated_emojis/fb46.png"), // ASCII Blue .
    A_FB47("fb47", "generated_emojis/fb47.png"), // ASCII Blue /
    A_FB48("fb48", "generated_emojis/fb48.png"), // ASCII Blue 0
    A_FB49("fb49", "generated_emojis/fb49.png"), // ASCII Blue 1
    A_FB50("fb50", "generated_emojis/fb50.png"), // ASCII Blue 2
    A_FB51("fb51", "generated_emojis/fb51.png"), // ASCII Blue 3
    A_FB52("fb52", "generated_emojis/fb52.png"), // ASCII Blue 4
    A_FB53("fb53", "generated_emojis/fb53.png"), // ASCII Blue 5
    A_FB54("fb54", "generated_emojis/fb54.png"), // ASCII Blue 6
    A_FB55("fb55", "generated_emojis/fb55.png"), // ASCII Blue 7
    A_FB56("fb56", "generated_emojis/fb56.png"), // ASCII Blue 8
    A_FB57("fb57", "generated_emojis/fb57.png"), // ASCII Blue 9
    A_FB58("fb58", "generated_emojis/fb58.png"), // ASCII Blue :
    A_FB59("fb59", "generated_emojis/fb59.png"), // ASCII Blue ;
    A_FB60("fb60", "generated_emojis/fb60.png"), // ASCII Blue <
    A_FB61("fb61", "generated_emojis/fb61.png"), // ASCII Blue =
    A_FB62("fb62", "generated_emojis/fb62.png"), // ASCII Blue >
    A_FB63("fb63", "generated_emojis/fb63.png"), // ASCII Blue ?
    A_FB64("fb64", "generated_emojis/fb64.png"), // ASCII Blue @
    A_FB65("fb65", "generated_emojis/fb65.png"), // ASCII Blue A
    A_FB66("fb66", "generated_emojis/fb66.png"), // ASCII Blue B
    A_FB67("fb67", "generated_emojis/fb67.png"), // ASCII Blue C
    A_FB68("fb68", "generated_emojis/fb68.png"), // ASCII Blue D
    A_FB69("fb69", "generated_emojis/fb69.png"), // ASCII Blue E
    A_FB70("fb70", "generated_emojis/fb70.png"), // ASCII Blue F
    A_FB71("fb71", "generated_emojis/fb71.png"), // ASCII Blue G
    A_FB72("fb72", "generated_emojis/fb72.png"), // ASCII Blue H
    A_FB73("fb73", "generated_emojis/fb73.png"), // ASCII Blue I
    A_FB74("fb74", "generated_emojis/fb74.png"), // ASCII Blue J
    A_FB75("fb75", "generated_emojis/fb75.png"), // ASCII Blue K
    A_FB76("fb76", "generated_emojis/fb76.png"), // ASCII Blue L
    A_FB77("fb77", "generated_emojis/fb77.png"), // ASCII Blue M
    A_FB78("fb78", "generated_emojis/fb78.png"), // ASCII Blue N
    A_FB79("fb79", "generated_emojis/fb79.png"), // ASCII Blue O
    A_FB80("fb80", "generated_emojis/fb80.png"), // ASCII Blue P
    A_FB81("fb81", "generated_emojis/fb81.png"), // ASCII Blue Q
    A_FB82("fb82", "generated_emojis/fb82.png"), // ASCII Blue R
    A_FB83("fb83", "generated_emojis/fb83.png"), // ASCII Blue S
    A_FB84("fb84", "generated_emojis/fb84.png"), // ASCII Blue T
    A_FB85("fb85", "generated_emojis/fb85.png"), // ASCII Blue U
    A_FB86("fb86", "generated_emojis/fb86.png"), // ASCII Blue V
    A_FB87("fb87", "generated_emojis/fb87.png"), // ASCII Blue W
    A_FB88("fb88", "generated_emojis/fb88.png"), // ASCII Blue X
    A_FB89("fb89", "generated_emojis/fb89.png"), // ASCII Blue Y
    A_FB90("fb90", "generated_emojis/fb90.png"), // ASCII Blue Z
    A_FB91("fb91", "generated_emojis/fb91.png"), // ASCII Blue [
    A_FB92("fb92", "generated_emojis/fb92.png"), // ASCII Blue \
    A_FB93("fb93", "generated_emojis/fb93.png"), // ASCII Blue ]
    A_FB94("fb94", "generated_emojis/fb94.png"), // ASCII Blue ^
    A_FB95("fb95", "generated_emojis/fb95.png"), // ASCII Blue _
    A_FB96("fb96", "generated_emojis/fb96.png"), // ASCII Blue `
    A_FB97("fb97", "generated_emojis/fb97.png"), // ASCII Blue a
    A_FB98("fb98", "generated_emojis/fb98.png"), // ASCII Blue b
    A_FB99("fb99", "generated_emojis/fb99.png"), // ASCII Blue c
    A_FB100("fb100", "generated_emojis/fb100.png"), // ASCII Blue d
    A_FB101("fb101", "generated_emojis/fb101.png"), // ASCII Blue e
    A_FB102("fb102", "generated_emojis/fb102.png"), // ASCII Blue f
    A_FB103("fb103", "generated_emojis/fb103.png"), // ASCII Blue g
    A_FB104("fb104", "generated_emojis/fb104.png"), // ASCII Blue h
    A_FB105("fb105", "generated_emojis/fb105.png"), // ASCII Blue i
    A_FB106("fb106", "generated_emojis/fb106.png"), // ASCII Blue j
    A_FB107("fb107", "generated_emojis/fb107.png"), // ASCII Blue k
    A_FB108("fb108", "generated_emojis/fb108.png"), // ASCII Blue l
    A_FB109("fb109", "generated_emojis/fb109.png"), // ASCII Blue m
    A_FB110("fb110", "generated_emojis/fb110.png"), // ASCII Blue n
    A_FB111("fb111", "generated_emojis/fb111.png"), // ASCII Blue o
    A_FB112("fb112", "generated_emojis/fb112.png"), // ASCII Blue p
    A_FB113("fb113", "generated_emojis/fb113.png"), // ASCII Blue q
    A_FB114("fb114", "generated_emojis/fb114.png"), // ASCII Blue r
    A_FB115("fb115", "generated_emojis/fb115.png"), // ASCII Blue s
    A_FB116("fb116", "generated_emojis/fb116.png"), // ASCII Blue t
    A_FB117("fb117", "generated_emojis/fb117.png"), // ASCII Blue u
    A_FB118("fb118", "generated_emojis/fb118.png"), // ASCII Blue v
    A_FB119("fb119", "generated_emojis/fb119.png"), // ASCII Blue w
    A_FB120("fb120", "generated_emojis/fb120.png"), // ASCII Blue x
    A_FB121("fb121", "generated_emojis/fb121.png"), // ASCII Blue y
    A_FB122("fb122", "generated_emojis/fb122.png"), // ASCII Blue z
    A_FB123("fb123", "generated_emojis/fb123.png"), // ASCII Blue {
    A_FB124("fb124", "generated_emojis/fb124.png"), // ASCII Blue |
    A_FB125("fb125", "generated_emojis/fb125.png"), // ASCII Blue }
    A_FB126("fb126", "generated_emojis/fb126.png"), // ASCII Blue ~
//</editor-fold>

    //<editor-fold desc="Fira Code Gray Text">
    A_FA32("fa32", "generated_emojis/fa32.png"), // ASCII Gray
    A_FA33("fa33", "generated_emojis/fa33.png"), // ASCII Gray !
    A_FA34("fa34", "generated_emojis/fa34.png"), // ASCII Gray "
    A_FA35("fa35", "generated_emojis/fa35.png"), // ASCII Gray #
    A_FA36("fa36", "generated_emojis/fa36.png"), // ASCII Gray $
    A_FA37("fa37", "generated_emojis/fa37.png"), // ASCII Gray %
    A_FA38("fa38", "generated_emojis/fa38.png"), // ASCII Gray &
    A_FA39("fa39", "generated_emojis/fa39.png"), // ASCII Gray '
    A_FA40("fa40", "generated_emojis/fa40.png"), // ASCII Gray (
    A_FA41("fa41", "generated_emojis/fa41.png"), // ASCII Gray )
    A_FA42("fa42", "generated_emojis/fa42.png"), // ASCII Gray *
    A_FA43("fa43", "generated_emojis/fa43.png"), // ASCII Gray +
    A_FA44("fa44", "generated_emojis/fa44.png"), // ASCII Gray ,
    A_FA45("fa45", "generated_emojis/fa45.png"), // ASCII Gray -
    A_FA46("fa46", "generated_emojis/fa46.png"), // ASCII Gray .
    A_FA47("fa47", "generated_emojis/fa47.png"), // ASCII Gray /
    A_FA48("fa48", "generated_emojis/fa48.png"), // ASCII Gray 0
    A_FA49("fa49", "generated_emojis/fa49.png"), // ASCII Gray 1
    A_FA50("fa50", "generated_emojis/fa50.png"), // ASCII Gray 2
    A_FA51("fa51", "generated_emojis/fa51.png"), // ASCII Gray 3
    A_FA52("fa52", "generated_emojis/fa52.png"), // ASCII Gray 4
    A_FA53("fa53", "generated_emojis/fa53.png"), // ASCII Gray 5
    A_FA54("fa54", "generated_emojis/fa54.png"), // ASCII Gray 6
    A_FA55("fa55", "generated_emojis/fa55.png"), // ASCII Gray 7
    A_FA56("fa56", "generated_emojis/fa56.png"), // ASCII Gray 8
    A_FA57("fa57", "generated_emojis/fa57.png"), // ASCII Gray 9
    A_FA58("fa58", "generated_emojis/fa58.png"), // ASCII Gray :
    A_FA59("fa59", "generated_emojis/fa59.png"), // ASCII Gray ;
    A_FA60("fa60", "generated_emojis/fa60.png"), // ASCII Gray <
    A_FA61("fa61", "generated_emojis/fa61.png"), // ASCII Gray =
    A_FA62("fa62", "generated_emojis/fa62.png"), // ASCII Gray >
    A_FA63("fa63", "generated_emojis/fa63.png"), // ASCII Gray ?
    A_FA64("fa64", "generated_emojis/fa64.png"), // ASCII Gray @
    A_FA65("fa65", "generated_emojis/fa65.png"), // ASCII Gray A
    A_FA66("fa66", "generated_emojis/fa66.png"), // ASCII Gray B
    A_FA67("fa67", "generated_emojis/fa67.png"), // ASCII Gray C
    A_FA68("fa68", "generated_emojis/fa68.png"), // ASCII Gray D
    A_FA69("fa69", "generated_emojis/fa69.png"), // ASCII Gray E
    A_FA70("fa70", "generated_emojis/fa70.png"), // ASCII Gray F
    A_FA71("fa71", "generated_emojis/fa71.png"), // ASCII Gray G
    A_FA72("fa72", "generated_emojis/fa72.png"), // ASCII Gray H
    A_FA73("fa73", "generated_emojis/fa73.png"), // ASCII Gray I
    A_FA74("fa74", "generated_emojis/fa74.png"), // ASCII Gray J
    A_FA75("fa75", "generated_emojis/fa75.png"), // ASCII Gray K
    A_FA76("fa76", "generated_emojis/fa76.png"), // ASCII Gray L
    A_FA77("fa77", "generated_emojis/fa77.png"), // ASCII Gray M
    A_FA78("fa78", "generated_emojis/fa78.png"), // ASCII Gray N
    A_FA79("fa79", "generated_emojis/fa79.png"), // ASCII Gray O
    A_FA80("fa80", "generated_emojis/fa80.png"), // ASCII Gray P
    A_FA81("fa81", "generated_emojis/fa81.png"), // ASCII Gray Q
    A_FA82("fa82", "generated_emojis/fa82.png"), // ASCII Gray R
    A_FA83("fa83", "generated_emojis/fa83.png"), // ASCII Gray S
    A_FA84("fa84", "generated_emojis/fa84.png"), // ASCII Gray T
    A_FA85("fa85", "generated_emojis/fa85.png"), // ASCII Gray U
    A_FA86("fa86", "generated_emojis/fa86.png"), // ASCII Gray V
    A_FA87("fa87", "generated_emojis/fa87.png"), // ASCII Gray W
    A_FA88("fa88", "generated_emojis/fa88.png"), // ASCII Gray X
    A_FA89("fa89", "generated_emojis/fa89.png"), // ASCII Gray Y
    A_FA90("fa90", "generated_emojis/fa90.png"), // ASCII Gray Z
    A_FA91("fa91", "generated_emojis/fa91.png"), // ASCII Gray [
    A_FA92("fa92", "generated_emojis/fa92.png"), // ASCII Gray \
    A_FA93("fa93", "generated_emojis/fa93.png"), // ASCII Gray ]
    A_FA94("fa94", "generated_emojis/fa94.png"), // ASCII Gray ^
    A_FA95("fa95", "generated_emojis/fa95.png"), // ASCII Gray _
    A_FA96("fa96", "generated_emojis/fa96.png"), // ASCII Gray `
    A_FA97("fa97", "generated_emojis/fa97.png"), // ASCII Gray a
    A_FA98("fa98", "generated_emojis/fa98.png"), // ASCII Gray b
    A_FA99("fa99", "generated_emojis/fa99.png"), // ASCII Gray c
    A_FA100("fa100", "generated_emojis/fa100.png"), // ASCII Gray d
    A_FA101("fa101", "generated_emojis/fa101.png"), // ASCII Gray e
    A_FA102("fa102", "generated_emojis/fa102.png"), // ASCII Gray f
    A_FA103("fa103", "generated_emojis/fa103.png"), // ASCII Gray g
    A_FA104("fa104", "generated_emojis/fa104.png"), // ASCII Gray h
    A_FA105("fa105", "generated_emojis/fa105.png"), // ASCII Gray i
    A_FA106("fa106", "generated_emojis/fa106.png"), // ASCII Gray j
    A_FA107("fa107", "generated_emojis/fa107.png"), // ASCII Gray k
    A_FA108("fa108", "generated_emojis/fa108.png"), // ASCII Gray l
    A_FA109("fa109", "generated_emojis/fa109.png"), // ASCII Gray m
    A_FA110("fa110", "generated_emojis/fa110.png"), // ASCII Gray n
    A_FA111("fa111", "generated_emojis/fa111.png"), // ASCII Gray o
    A_FA112("fa112", "generated_emojis/fa112.png"), // ASCII Gray p
    A_FA113("fa113", "generated_emojis/fa113.png"), // ASCII Gray q
    A_FA114("fa114", "generated_emojis/fa114.png"), // ASCII Gray r
    A_FA115("fa115", "generated_emojis/fa115.png"), // ASCII Gray s
    A_FA116("fa116", "generated_emojis/fa116.png"), // ASCII Gray t
    A_FA117("fa117", "generated_emojis/fa117.png"), // ASCII Gray u
    A_FA118("fa118", "generated_emojis/fa118.png"), // ASCII Gray v
    A_FA119("fa119", "generated_emojis/fa119.png"), // ASCII Gray w
    A_FA120("fa120", "generated_emojis/fa120.png"), // ASCII Gray x
    A_FA121("fa121", "generated_emojis/fa121.png"), // ASCII Gray y
    A_FA122("fa122", "generated_emojis/fa122.png"), // ASCII Gray z
    A_FA123("fa123", "generated_emojis/fa123.png"), // ASCII Gray {
    A_FA124("fa124", "generated_emojis/fa124.png"), // ASCII Gray |
    A_FA125("fa125", "generated_emojis/fa125.png"), // ASCII Gray }
    A_FA126("fa126", "generated_emojis/fa126.png"), // ASCII Gray ~
//</editor-fold>

    //<editor-fold desc="Fira Code Light Gray Text">
    A_FL32("fl32", "generated_emojis/fl32.png"), // ASCII Light Gray
    A_FL33("fl33", "generated_emojis/fl33.png"), // ASCII Light Gray !
    A_FL34("fl34", "generated_emojis/fl34.png"), // ASCII Light Gray "
    A_FL35("fl35", "generated_emojis/fl35.png"), // ASCII Light Gray #
    A_FL36("fl36", "generated_emojis/fl36.png"), // ASCII Light Gray $
    A_FL37("fl37", "generated_emojis/fl37.png"), // ASCII Light Gray %
    A_FL38("fl38", "generated_emojis/fl38.png"), // ASCII Light Gray &
    A_FL39("fl39", "generated_emojis/fl39.png"), // ASCII Light Gray '
    A_FL40("fl40", "generated_emojis/fl40.png"), // ASCII Light Gray (
    A_FL41("fl41", "generated_emojis/fl41.png"), // ASCII Light Gray )
    A_FL42("fl42", "generated_emojis/fl42.png"), // ASCII Light Gray *
    A_FL43("fl43", "generated_emojis/fl43.png"), // ASCII Light Gray +
    A_FL44("fl44", "generated_emojis/fl44.png"), // ASCII Light Gray ,
    A_FL45("fl45", "generated_emojis/fl45.png"), // ASCII Light Gray -
    A_FL46("fl46", "generated_emojis/fl46.png"), // ASCII Light Gray .
    A_FL47("fl47", "generated_emojis/fl47.png"), // ASCII Light Gray /
    A_FL48("fl48", "generated_emojis/fl48.png"), // ASCII Light Gray 0
    A_FL49("fl49", "generated_emojis/fl49.png"), // ASCII Light Gray 1
    A_FL50("fl50", "generated_emojis/fl50.png"), // ASCII Light Gray 2
    A_FL51("fl51", "generated_emojis/fl51.png"), // ASCII Light Gray 3
    A_FL52("fl52", "generated_emojis/fl52.png"), // ASCII Light Gray 4
    A_FL53("fl53", "generated_emojis/fl53.png"), // ASCII Light Gray 5
    A_FL54("fl54", "generated_emojis/fl54.png"), // ASCII Light Gray 6
    A_FL55("fl55", "generated_emojis/fl55.png"), // ASCII Light Gray 7
    A_FL56("fl56", "generated_emojis/fl56.png"), // ASCII Light Gray 8
    A_FL57("fl57", "generated_emojis/fl57.png"), // ASCII Light Gray 9
    A_FL58("fl58", "generated_emojis/fl58.png"), // ASCII Light Gray :
    A_FL59("fl59", "generated_emojis/fl59.png"), // ASCII Light Gray ;
    A_FL60("fl60", "generated_emojis/fl60.png"), // ASCII Light Gray <
    A_FL61("fl61", "generated_emojis/fl61.png"), // ASCII Light Gray =
    A_FL62("fl62", "generated_emojis/fl62.png"), // ASCII Light Gray >
    A_FL63("fl63", "generated_emojis/fl63.png"), // ASCII Light Gray ?
    A_FL64("fl64", "generated_emojis/fl64.png"), // ASCII Light Gray @
    A_FL65("fl65", "generated_emojis/fl65.png"), // ASCII Light Gray A
    A_FL66("fl66", "generated_emojis/fl66.png"), // ASCII Light Gray B
    A_FL67("fl67", "generated_emojis/fl67.png"), // ASCII Light Gray C
    A_FL68("fl68", "generated_emojis/fl68.png"), // ASCII Light Gray D
    A_FL69("fl69", "generated_emojis/fl69.png"), // ASCII Light Gray E
    A_FL70("fl70", "generated_emojis/fl70.png"), // ASCII Light Gray F
    A_FL71("fl71", "generated_emojis/fl71.png"), // ASCII Light Gray G
    A_FL72("fl72", "generated_emojis/fl72.png"), // ASCII Light Gray H
    A_FL73("fl73", "generated_emojis/fl73.png"), // ASCII Light Gray I
    A_FL74("fl74", "generated_emojis/fl74.png"), // ASCII Light Gray J
    A_FL75("fl75", "generated_emojis/fl75.png"), // ASCII Light Gray K
    A_FL76("fl76", "generated_emojis/fl76.png"), // ASCII Light Gray L
    A_FL77("fl77", "generated_emojis/fl77.png"), // ASCII Light Gray M
    A_FL78("fl78", "generated_emojis/fl78.png"), // ASCII Light Gray N
    A_FL79("fl79", "generated_emojis/fl79.png"), // ASCII Light Gray O
    A_FL80("fl80", "generated_emojis/fl80.png"), // ASCII Light Gray P
    A_FL81("fl81", "generated_emojis/fl81.png"), // ASCII Light Gray Q
    A_FL82("fl82", "generated_emojis/fl82.png"), // ASCII Light Gray R
    A_FL83("fl83", "generated_emojis/fl83.png"), // ASCII Light Gray S
    A_FL84("fl84", "generated_emojis/fl84.png"), // ASCII Light Gray T
    A_FL85("fl85", "generated_emojis/fl85.png"), // ASCII Light Gray U
    A_FL86("fl86", "generated_emojis/fl86.png"), // ASCII Light Gray V
    A_FL87("fl87", "generated_emojis/fl87.png"), // ASCII Light Gray W
    A_FL88("fl88", "generated_emojis/fl88.png"), // ASCII Light Gray X
    A_FL89("fl89", "generated_emojis/fl89.png"), // ASCII Light Gray Y
    A_FL90("fl90", "generated_emojis/fl90.png"), // ASCII Light Gray Z
    A_FL91("fl91", "generated_emojis/fl91.png"), // ASCII Light Gray [
    A_FL92("fl92", "generated_emojis/fl92.png"), // ASCII Light Gray \
    A_FL93("fl93", "generated_emojis/fl93.png"), // ASCII Light Gray ]
    A_FL94("fl94", "generated_emojis/fl94.png"), // ASCII Light Gray ^
    A_FL95("fl95", "generated_emojis/fl95.png"), // ASCII Light Gray _
    A_FL96("fl96", "generated_emojis/fl96.png"), // ASCII Light Gray `
    A_FL97("fl97", "generated_emojis/fl97.png"), // ASCII Light Gray a
    A_FL98("fl98", "generated_emojis/fl98.png"), // ASCII Light Gray b
    A_FL99("fl99", "generated_emojis/fl99.png"), // ASCII Light Gray c
    A_FL100("fl100", "generated_emojis/fl100.png"), // ASCII Light Gray d
    A_FL101("fl101", "generated_emojis/fl101.png"), // ASCII Light Gray e
    A_FL102("fl102", "generated_emojis/fl102.png"), // ASCII Light Gray f
    A_FL103("fl103", "generated_emojis/fl103.png"), // ASCII Light Gray g
    A_FL104("fl104", "generated_emojis/fl104.png"), // ASCII Light Gray h
    A_FL105("fl105", "generated_emojis/fl105.png"), // ASCII Light Gray i
    A_FL106("fl106", "generated_emojis/fl106.png"), // ASCII Light Gray j
    A_FL107("fl107", "generated_emojis/fl107.png"), // ASCII Light Gray k
    A_FL108("fl108", "generated_emojis/fl108.png"), // ASCII Light Gray l
    A_FL109("fl109", "generated_emojis/fl109.png"), // ASCII Light Gray m
    A_FL110("fl110", "generated_emojis/fl110.png"), // ASCII Light Gray n
    A_FL111("fl111", "generated_emojis/fl111.png"), // ASCII Light Gray o
    A_FL112("fl112", "generated_emojis/fl112.png"), // ASCII Light Gray p
    A_FL113("fl113", "generated_emojis/fl113.png"), // ASCII Light Gray q
    A_FL114("fl114", "generated_emojis/fl114.png"), // ASCII Light Gray r
    A_FL115("fl115", "generated_emojis/fl115.png"), // ASCII Light Gray s
    A_FL116("fl116", "generated_emojis/fl116.png"), // ASCII Light Gray t
    A_FL117("fl117", "generated_emojis/fl117.png"), // ASCII Light Gray u
    A_FL118("fl118", "generated_emojis/fl118.png"), // ASCII Light Gray v
    A_FL119("fl119", "generated_emojis/fl119.png"), // ASCII Light Gray w
    A_FL120("fl120", "generated_emojis/fl120.png"), // ASCII Light Gray x
    A_FL121("fl121", "generated_emojis/fl121.png"), // ASCII Light Gray y
    A_FL122("fl122", "generated_emojis/fl122.png"), // ASCII Light Gray z
    A_FL123("fl123", "generated_emojis/fl123.png"), // ASCII Light Gray {
    A_FL124("fl124", "generated_emojis/fl124.png"), // ASCII Light Gray |
    A_FL125("fl125", "generated_emojis/fl125.png"), // ASCII Light Gray }
    A_FL126("fl126", "generated_emojis/fl126.png"), // ASCII Light Gray ~
//</editor-fold>

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
