package com.dev.yatin.quizapp.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OptionType {
    optionA("A"),
    optionB("B"),
    optionC("C"),
    optionD("D");

    private final String value;

    OptionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static OptionType fromString(String name) {
        for (OptionType type : OptionType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}