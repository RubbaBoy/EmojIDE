package com.uddernetworks.emojide.data.document;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface DocumentManager {

    /**
     * Gets all {@link Document}s in the database. This is not cached due to the relative slowness of the IDE, so this
     * will not be called many times. See implementation for exceptions that may occur with this rule.
     *
     * @return All {@link Document}s
     */
    CompletableFuture<List<Document>> getAllDocuments();

    /**
     * Gets the {@link Document} with the given unique name, if it exists.
     *
     * @param name The name to fetch
     * @return The {@link Document}, if existent
     */
    CompletableFuture<Optional<Document>> getDocument(String name);

    /**
     * Creates and inserts a {@link Document} with the given parameters and with empty content.
     *
     * @param name The name of the {@link Document}
     * @param authorId The author's Discord ID of the {@link Document}
     * @return The {@link Document}'s {@link CompletableFuture}
     */
    CompletableFuture<Document> createDocument(String name, long authorId);

    /**
     * Inserts a given {@link Document}'s contents to the database.
     *
     * @param document The {@link Document} to get the content of
     * @return The {@link CompletableFuture} of the task
     */
    CompletableFuture<Void> updateDocument(Document document);

    /**
     * Removes a given {@link Document} from the database.
     *
     * @param document The {@link Document} to remove
     * @return The {@link CompletableFuture} of the task
     */
    CompletableFuture<Void> removeDocument(Document document);

}
