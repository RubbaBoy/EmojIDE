package com.uddernetworks.emojide.data;

import com.uddernetworks.emojide.data.document.BasicDocument;
import com.uddernetworks.emojide.data.document.Document;
import com.uddernetworks.emojide.main.EmojIDE;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicDatabaseManager implements DatabaseManager {

    private static Logger LOGGER = LoggerFactory.getLogger(BasicDatabaseManager.class);

    private EmojIDE emojIDE;
    private DataSource dataSource;

    @SQLBound
    private String getDocument;

    @SQLBound
    private String getDocuments;

    @SQLBound
    private String createDocument;

    @SQLBound
    private String modifyDocument;

    @SQLBound
    private String removeDocument;

    public BasicDatabaseManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

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

        try (var connection = this.dataSource.getConnection();
             var useMySQL = connection.prepareStatement("SET DATABASE SQL SYNTAX MYS TRUE")) {
            useMySQL.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while trying to set MySQL dialect", e);
        }

        Stream.of("documents.sql").forEach(table -> {
            try (var reader = new BufferedReader(new InputStreamReader(BasicDatabaseManager.class.getResourceAsStream("/" + table)));
                 var connection = dataSource.getConnection();
                 var statement = connection.prepareStatement(reader.lines().collect(Collectors.joining("\n")))) {
                statement.executeUpdate();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });

        LOGGER.info("Initialized DatabaseManager in {}ms", System.currentTimeMillis() - start);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private List<GenericMap> iterResultSet(ResultSet resultSet) throws SQLException {
        var meta = resultSet.getMetaData();
        int columns = meta.getColumnCount();

        var rows = new ArrayList<GenericMap>();
        while (resultSet.next()) {
            var row = new GenericMap();
            for(int i = 1; i <= columns; ++i){
                row.put(meta.getColumnName(i).toLowerCase(), resultSet.getObject(i));
            }
            rows.add(row);
        }

        return List.copyOf(rows);
    }

    private Document documentFrom(GenericMap map) {
        return new BasicDocument(emojIDE.getDocumentManager(), map.get("name"), map.get("author"), map.get("content"));
    }

    @Override
    public List<Document> getAllDocuments() {
        try (var connection = dataSource.getConnection();
            var statement = connection.prepareStatement(getDocuments)) {
            return iterResultSet(statement.executeQuery())
                    .stream()
                    .map(this::documentFrom)
                    .collect(Collectors.toUnmodifiableList());
        } catch (Exception e) {
            LOGGER.error("Error during #getAllDocuments", e);
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<Document> getDocument(String name) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(getDocument)) {
            statement.setString(1, name);
            return iterResultSet(statement.executeQuery())
                    .stream()
                    .map(this::documentFrom)
                    .findFirst();
        } catch (SQLException e) {
            LOGGER.error("Error during #getDocument(String)", e);
            return Optional.empty();
        }
    }

    @Override
    public void insertDocument(Document document) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(createDocument)) {
            statement.setString(1, document.getName());
            statement.setLong(2, document.getAuthor());
            statement.setString(3, document.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error during #insertDocument(Document)", e);
        }
    }

    @Override
    public void updateDocument(Document document) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(modifyDocument)) {
            statement.setString(1, document.getContent());
            statement.setLong(2, document.getAuthor());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error during #updateDocument(Document)", e);
        }
    }

    @Override
    public void removeDocument(Document document) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(removeDocument)) {
            statement.setString(1, document.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error during #removeDocument(Document)", e);
        }
    }
}
