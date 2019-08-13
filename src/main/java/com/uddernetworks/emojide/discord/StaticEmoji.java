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
    TTABBED_FRAME_SELECTED("ttfs", "ide_emojis/ttabbed_frame_selected.png"),
    LTAB_SEPARATOR_SELECTED("ltss", "ide_emojis/ltab_separator_selected.png"),
    RTAB_SEPARATOR_SELECTED("rtss", "ide_emojis/rtab_separator_selected.png"),

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
