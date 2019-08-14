package com.uddernetworks.emojide.data.document;

import com.uddernetworks.emojide.data.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class DefaultDocumentManager implements DocumentManager {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultDocumentManager.class);

    private DatabaseManager databaseManager;

    public DefaultDocumentManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public CompletableFuture<List<Document>> getAllDocuments() {
        return CompletableFuture.supplyAsync(() -> databaseManager.getAllDocuments());
    }

    @Override
    public CompletableFuture<Optional<Document>> getDocument(String name) {
        return CompletableFuture.supplyAsync(() -> databaseManager.getDocument(name));
    }

    @Override
    public CompletableFuture<Document> createDocument(String name, long authorId) {
        return CompletableFuture.supplyAsync(() -> {
            var document = new BasicDocument(this, name, authorId);
            databaseManager.insertDocument(document);
            return document;
        });
    }

    @Override
    public CompletableFuture<Void> updateDocument(Document document) {
        return CompletableFuture.runAsync(() -> databaseManager.updateDocument(document));
    }

    @Override
    public CompletableFuture<Void> removeDocument(Document document) {
        return CompletableFuture.runAsync(() -> databaseManager.removeDocument(document));
    }
}
