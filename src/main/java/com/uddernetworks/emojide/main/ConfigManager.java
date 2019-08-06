package com.uddernetworks.emojide.main;

import java.util.List;

public interface ConfigManager {

    /**
     * Initializes all config fields.
     */
    void init();

    /**
     * Gets the primary EmojIDE bot token to use.
     *
     * @return The token
     */
    String getPrimaryToken();

    /**
     * Gets a list of servers the bot is in and has admin access to, to upload emojis.
     *
     * @return A list of server IDs
     */
    List<Long> getServers();
}
