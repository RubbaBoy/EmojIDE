package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.data.document.Document;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.gui.CustomRenderedContainerFrame;
import com.uddernetworks.emojide.gui.EmptyContainerFrame;
import com.uddernetworks.emojide.gui.HighlightedTextFrame;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.tabbed.TabbedFrame;
import com.uddernetworks.emojide.gui.tabs.Tab;
import com.uddernetworks.emojide.ide.ConsolePiper;
import com.uddernetworks.emojide.ide.FunctionController;
import com.uddernetworks.emojide.main.EmojIDE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultDocumentTabController implements DocumentTabController {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultDocumentTabController.class);

    private EmojIDE emojIDE;
    private Displayer displayer;
    private TabbedFrame tabbedFrame;
    private StaticTextFrame outputFrame;
    private Map<EmojiComponent, Document> documents = new HashMap<>();

    public DefaultDocumentTabController(EmojIDE emojIDE, Displayer displayer, TabbedFrame tabbedFrame) {
        this.emojIDE = emojIDE;
        this.displayer = displayer;
        this.tabbedFrame = tabbedFrame;

        outputFrame = new StaticTextFrame(displayer, 54, 4).setText("<Console Output>");
        new FunctionController(emojIDE, displayer, tabbedFrame, new ConsolePiper(outputFrame));
    }

    @Override
    public void addTab(Document document) {
        var highlightFrame = new HighlightedTextFrame(displayer, 54, 18, document.getContent());
        highlightFrame.getTextBlock().onChange(text -> {
            LOGGER.info("Changed to: \n{}", text);
            document.setContent(text);
        });

        var component = new EmptyContainerFrame(displayer, 56, 20)
                .addChild(highlightFrame, 1, 1)
                .addChild(new CustomRenderedContainerFrame(displayer, 56, 5)
                        .addRenderer(initial -> Arrays.fill(initial[0], StaticEmoji.CTABBED_FRAME))
                        .addChild(outputFrame, 1, 1), 0, 15);

        documents.put(component, document);
        tabbedFrame.addTab(document.getName(), component, true);
        tabbedFrame.refresh();
    }

    @Override
    public void removeTab(Tab tab) {
        Optional.ofNullable(documents.get(tab.getComponent())).ifPresent(document -> {
            tabbedFrame.removeTab(tab);
            emojIDE.getDocumentManager().removeDocument(document);
        });
    }
}
