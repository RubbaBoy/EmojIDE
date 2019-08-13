package com.uddernetworks.emojide.main;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;

import java.util.List;

public class DefaultConfigManager implements ConfigManager {

    private String fileName;

    private String primaryToken;
    private List<Long> servers;
    private FileConfig config;

    public DefaultConfigManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void init() {
        config = CommentedFileConfig.builder(this.fileName).autosave().build();
        config.load();
        primaryToken = config.get("bots.primary-token");
        servers = config.get("bots.servers");
    }

    @Override
    public String getPrimaryToken() {
        return primaryToken;
    }

    @Override
    public List<Long> getServers() {
        return servers;
    }

    @Override
    public FileConfig getConfig() {
        return config;
    }
}
