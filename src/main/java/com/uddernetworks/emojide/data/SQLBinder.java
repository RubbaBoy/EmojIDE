package com.uddernetworks.emojide.data;

import java.io.IOException;

public interface SQLBinder {

    /**
     * Binds all fields from a given object with the annotation {@link SQLBound} to their respectiveSQL: file contents.
     * 
     * @param object The instance to  set fields to
     */
    void createBindings(Object object);

    /**
     * Gets the string query from the resource file given.
     *
     * @param name The resource file to read
     * @return The string contents of it
     * @throws IOException If there are issues when creating/accessing the pool
     */
    String getQuery(String name) throws IOException;
}
