package com.uddernetworks.emojide.main;

import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultConfigManager implements ConfigManager {

    private String fileName;

    private String primaryToken;
    private List<Long> servers;

    public DefaultConfigManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void init() {
        var config = ConfigFactory.load(this.fileName);
        this.primaryToken = config.getString("bots.primary-token");
        this.servers = config.getStringList("bots.servers").stream().map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public String getPrimaryToken() {
        return this.primaryToken;
    }

    @Override
    public List<Long> getServers() {
        return servers;
    }
}
