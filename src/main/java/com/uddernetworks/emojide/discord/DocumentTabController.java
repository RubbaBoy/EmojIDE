package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.data.document.Document;
import com.uddernetworks.emojide.gui.TabbedFrame;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.tabs.Tab;

public interface DocumentTabController {

    /**
     * Adds the given {@link Document} to the {@link TabbedFrame}. This listens to any text updates and updates the
     * database and {@link Document} appropriately.
     *
     * @param document The {@link Document} to add
     */
    void addTab(Document document);

    /**
     * Removes the associated {@link EmojiComponent}'s {@link Document} from the database, and the tab controller,
     * switching tabs if necessary.
     *
     * @param tab The tab to remove
     */
    void removeTab(Tab tab);
}
