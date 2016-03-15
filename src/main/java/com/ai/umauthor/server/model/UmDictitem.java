package com.ai.umauthor.server.model;

import java.io.Serializable;

public class UmDictitem extends UmDictitemKey implements Serializable {
    private String itemValue;

    private String parentItemKey;

    private Integer itemOrder;

    private String itemState;

    private String itemDesc;

    private String itemExtValue1;

    private String itemExtValue2;

    private static final long serialVersionUID = 1L;

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public String getParentItemKey() {
        return parentItemKey;
    }

    public void setParentItemKey(String parentItemKey) {
        this.parentItemKey = parentItemKey == null ? null : parentItemKey.trim();
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState == null ? null : itemState.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getItemExtValue1() {
        return itemExtValue1;
    }

    public void setItemExtValue1(String itemExtValue1) {
        this.itemExtValue1 = itemExtValue1 == null ? null : itemExtValue1.trim();
    }

    public String getItemExtValue2() {
        return itemExtValue2;
    }

    public void setItemExtValue2(String itemExtValue2) {
        this.itemExtValue2 = itemExtValue2 == null ? null : itemExtValue2.trim();
    }
}