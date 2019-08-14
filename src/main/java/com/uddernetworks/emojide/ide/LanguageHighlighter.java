package com.uddernetworks.emojide.ide;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.gui.text.TextBlock;
import com.uddernetworks.emojide.ide.lexer.javascript.JavaScriptLexer;
import com.uddernetworks.emojide.ide.lexer.javascript.JavaScriptParser;
import org.antlr.v4.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LanguageHighlighter {

    private static Logger LOGGER = LoggerFactory.getLogger(LanguageHighlighter.class);

    public static final Map<Integer, String> COLORS = Map.of(
            0x000000, "", // White
            0xCC7832, "o", // Orange
            0x6A8759, "g", // Green
            0x6897BB, "b", // Blue
            0x808080, "a", // Gray
            0x666666, "l" // Light Gray
    );

    private static Map<List<String>, Integer> tokenMap = Map.of(
            Arrays.asList("'null'", "BooleanLiteral", "'break'", "'do'", "'instanceof'", "'typeof'", "'case'", "'else'", "'new'", "'var'", "'catch'", "'finally'", "'return'", "'void'", "'continue'", "'for'", "'switch'", "'while'", "'debugger'", "'function'", "'this'", "'with'", "'default'", "'if'", "'throw'", "'delete'", "'in'", "'try'", "'class'", "'enum'", "'extends'", "'super'", "'const'", "'export'", "'import'", "'implements'", "'let'", "'private'", "'public'", "'interface'", "'package'", "'protected'", "'static'", "'yield'"), 0xCC7832, // Orange
            Arrays.asList("StringLiteral", "TemplateStringLiteral"), 0x6A8759, // Green
            Arrays.asList("DecimalLiteral", "HexIntegerLiteral", "OctalIntegerLiteral", "OctalIntegerLiteral2", "BinaryIntegerLiteral"), 0x6897BB, // Blue
            Arrays.asList("0", "'['", "']'", "'('", "')'", "'{'", "'}'", "';'", "','", "'='", "'?'", "':'", "'...'", "'.'", "'++'", "'--'", "'+'", "'-'", "'~'", "'!'", "'*'", "'/'", "'%'", "'>>'", "'<<'", "'>>>'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'==='", "'!=='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", "'>>>='", "'&='", "'^='", "'|='", "'=>'", "Identifier", "WhiteSpaces", "LineTerminator"), 0x000000, // Black
            Arrays.asList("MultiLineComment", "SingleLineComment"), 0x808080, // Gray
            Arrays.asList("HtmlComment", "CDataComment"), 0x666666, // Light Gray
            Arrays.asList("RegularExpressionLiteral"), 0x780078, // Dark Pink/Purple
            Arrays.asList("UnexpectedCharacter"), 0xFF0000 // Red
    );

    public Optional<Color> getColor(String tokenName) {
        return getColorHex(tokenName).map(Color::new);
    }

    public Optional<Integer> getColorHex(String tokenName) {
        var optional = tokenMap.entrySet().stream().filter(entry -> entry.getKey().contains(tokenName)).findFirst().map(Map.Entry::getValue);
        if (optional.isEmpty()) LOGGER.error("No color found for token {}", tokenName);
        return optional;
    }

    public Emoji[][] highlightText(EmojiManager emojiManager, TextBlock textBlock) {
        var emojiGrid = textBlock.toEmoji(emojiManager);

        var blockText = textBlock.getText();
        var lines = blockText.split("\n");

        var input = CharStreams.fromString(blockText);
        var lex = new JavaScriptLexer(input);
        lex.setTokenFactory(new CommonTokenFactory(true));
        var tokens = new UnbufferedTokenStream<CommonToken>(lex);
        var parser = new JavaScriptParser(tokens);
        parser.setBuildParseTree(false);
        var vocabulary = parser.getVocabulary();

        var i2 = 0;
        for (Token token; (token = tokens.get(i2)).getType() != Token.EOF; i2++, tokens.consume()) {
            if (token.getText().isBlank()) continue;
            var from = token.getCharPositionInLine();
            var to = from + token.getText().length();
            var tokenName = vocabulary.getDisplayName(token.getType());
            var colorOptional = getColorHex(tokenName);
            var color = colorOptional.orElse(0x000000);

            var line = lines[token.getLine() - 1];
            for (int i = from; i < Math.min(to, line.length()); i++) {
                var letter = line.charAt(i);
//                LOGGER.info("Letter {} is {} ({})", letter, color, tokenName);
                emojiGrid[token.getLine() - 1][i] = emojiManager.getTextEmoji(COLORS.get(color) + (int) letter);
            }
        }
        return emojiGrid;
    }

}
