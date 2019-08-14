package com.uddernetworks.emojide.data.document;

public interface Document {

    /**
     * Gets the unique name of the document.
     *
     * @return The name of the document
     */
    String getName();

    /**
     * Gets the Discord ID of the document's creator.
     *
     * @return The creator of the document
     */
    long getAuthor();

    /**
     * Gets the contents of the document.
     *
     * @return The contents of the document
     */
    String getContent();

    /**
     * Sets the content of the document, also updating any {@link DocumentManager} of the action.
     *
     * @param content The new contents of the document
     */
    void setContent(String content);

}
