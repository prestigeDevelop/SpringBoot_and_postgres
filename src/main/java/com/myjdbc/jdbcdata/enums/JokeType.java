package com.myjdbc.jdbcdata.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum JokeType {
    GENERAL("general"),
    KNOCK_KNOCK("knock-knock"),
    PROGRAMMING("programming"),
    DAD("dad");

    private final String value;

    JokeType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static JokeType fromString(String text) {
        for (JokeType type : JokeType.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown joke type: " + text);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
