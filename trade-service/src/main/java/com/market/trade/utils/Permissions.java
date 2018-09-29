package com.market.trade.utils;

public enum Permissions {
    TRADE_PERMISSION("CAN_TRADE"),
    ADMIN("ADMIN");

    private String value;

    Permissions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
