package com.uddernetworks.emojide.overengineering;

public class ObjectEntry<T> {

    private T object;
    private boolean singleton;
    private boolean expires;
    private long expiresAt;

    public ObjectEntry(T object, boolean singleton, boolean expires, long expiresAt) {
        this.object = object;
        this.singleton = singleton;
        this.expires = expires;
        this.expiresAt = expiresAt;
    }

    public T getObject() {
        return object;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isExpires() {
        return expires;
    }

    public long getExpiresAt() {
        return expiresAt;
    }
}
