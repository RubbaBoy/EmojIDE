package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.data.document.Document;
import com.uddernetworks.emojide.gui.EmptyContainerFrame;
import com.uddernetworks.emojide.gui.HighlightedTextFrame;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.components.theme.ThemeDependantRendering;
import com.uddernetworks.emojide.gui.tabbed.TabbedFrame;
import com.uddernetworks.emojide.gui.tabs.Tab;
import com.uddernetworks.emojide.ide.ConsolePiper;
import com.uddernetworks.emojide.ide.FunctionController;
import com.uddernetworks.emojide.main.EmojIDE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.uddernetworks.emojide.gui.tabbed.TabbedFrameConstants.AVAILABLE_TEXT_HEIGHT;

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
        int textHeight = ThemeDependantRendering.getThemeConstant(TabbedFrame.class, AVAILABLE_TEXT_HEIGHT);
        var highlightFrame = new HighlightedTextFrame(displayer, 54, textHeight - 1, document.getContent());
        highlightFrame.getTextBlock().onChange(text -> {
            LOGGER.info("Changed to: \n{}", text);
            document.setContent(text);
        });

        var component = new EmptyContainerFrame(displayer, 56, textHeight + 5)
                .addChild(highlightFrame, 1, 1);
//                .addChild(new OutputFrame(displayer, 56, 5).setOutput(outputFrame), 0, textHeight);

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
