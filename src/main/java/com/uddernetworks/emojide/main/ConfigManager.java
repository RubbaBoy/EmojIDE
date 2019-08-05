package com.uddernetworks.emojide.main;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {

    private String fileName;
    private Config config;

    private String primaryToken;
    private List<String> uploadingTokens;
    private List<Long> servers;

    public ConfigManager(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        this.config = ConfigFactory.load(this.fileName);
        this.primaryToken = this.config.getString("bots.primary-token");
        this.uploadingTokens = this.config.getStringList("bots.uploaders");
        this.servers = this.config.getStringList("bots.servers").stream().map(Long::valueOf).collect(Collectors.toList());
    }

    public String getPrimaryToken() {
        return this.primaryToken;
    }

    public List<String> getUploadingTokens() {
        return this.uploadingTokens;
    }

    public List<Long> getServers() {
        return servers;
    }
}
