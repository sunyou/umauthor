package com.ai.umauthor.server.model;

import java.io.Serializable;

public class UmDictitemKey implements Serializable {
    private String dictKey;

    private String itemKey;

    private static final long serialVersionUID = 1L;

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey == null ? null : itemKey.trim();
    }
}