package com.uddernetworks.emojide.data.document;

public class BasicDocument implements Document {

    private DocumentManager documentManager;

    private final String name;
    private final long author;
    private String content;

    public BasicDocument(DocumentManager documentManager, String name, long author) {
        this(documentManager, name, author, "");
    }

    public BasicDocument(DocumentManager documentManager, String name, long author, String content) {
        this.documentManager = documentManager;
        this.name = name;
        this.author = author;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getAuthor() {
        return author;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
        documentManager.updateDocument(this);
    }
}
