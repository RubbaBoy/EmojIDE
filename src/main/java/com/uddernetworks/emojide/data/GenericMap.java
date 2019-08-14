package com.uddernetworks.emojide.data;

import java.util.HashMap;

/**
 * This is some sketchy shit that really shouldn't be used anywhere, but is.
 * This class assumes whatever you get from it is whatever type you want, without checking anything. Ths is prone to
 * cause errors.
 */
class GenericMap extends HashMap<String, Object> {
    public <T> T get(String key) {
        return (T) super.get(key);
    }
}
