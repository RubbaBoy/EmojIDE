package com.uddernetworks.emojide.data;

import com.uddernetworks.emojide.data.document.Document;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DatabaseManager {

    /**
     * Initializes tables and queries for the DatabaseManager.
     */
    void init();

    /**
     * Gets a {@link Connection} from HikariCP.
     *
     * @return A {@link Connection}
     * @throws SQLException If bad stuff happens
     */
    Connection getConnection() throws SQLException;

    /**
     * Gets all {@link Document}s in the database.
     *
     * @return All {@link Document}s
     */
    List<Document> getAllDocuments();

    /**
     * Gets the {@link Document} with the given unique name, if it exists.
     *
     * @param name The name to fetch
     * @return The {@link Document}, if existent
     */
    Optional<Document> getDocument(String name);

    /**
     * Creates and inserts a given {@link Document}.
     *
     * @param document The {@link Document} to insert
     */
    void insertDocument(Document document);

    /**
     * Inserts a given {@link Document}'s contents to the database.
     *
     * @param document The {@link Document} to get the content of
     */
    void updateDocument(Document document);

    /**
     * Removes a given {@link Document} from the database.
     *
     * @param document The {@link Document} to remove
     */
    void removeDocument(Document document);
}
