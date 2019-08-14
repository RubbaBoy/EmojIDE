package com.uddernetworks.emojide.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicDatabaseManager implements DatabaseManager {

    private static Logger LOGGER = LoggerFactory.getLogger(BasicDatabaseManager.class);

    private DataSource dataSource;

    @SQLBound
    private String getDocuments;

    @SQLBound
    private String createDocument;

    @SQLBound
    private String modifyDocument;

    @Override
    public void init() {
        long start = System.currentTimeMillis();
        var config = new HikariConfig();
        var sqlBinder = new BasicSQLBinder();

        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        var filePath = new File("database/emojide");
        filePath.getParentFile().mkdirs();
        config.setJdbcUrl("jdbc:hsqldb:file:" + filePath);
        config.setUsername("SA");
        config.setPassword("");

        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "1000");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "8192");

        dataSource = new HikariDataSource(config);

        sqlBinder.createBindings(this);

        Stream.of("documents.sql").forEach(table -> {
            try (var reader = new BufferedReader(new InputStreamReader(BasicDatabaseManager.class.getResourceAsStream("/" + table)));
                 var connection = dataSource.getConnection();
                 var statement = connection.prepareStatement(reader.lines().collect(Collectors.joining("\n")))) {
                statement.executeUpdate();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });

        try (var connection = this.dataSource.getConnection();
             var useMySQL = connection.prepareStatement("SET DATABASE SQL SYNTAX MYS TRUE")) {
            useMySQL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Initialized DatabaseManager in {}ms", System.currentTimeMillis() - start);
    }

}
