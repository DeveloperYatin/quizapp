package com.dev.yatin.quizservice.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

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

    // Static map for O(1) lookup by name
    private static final Map<String, OptionType> NAME_MAP = new HashMap<>();

    static {
        for (OptionType type : values()) {
            NAME_MAP.put(type.name().toLowerCase(), type);
        }
    }

    @JsonCreator
    public static OptionType fromString(String name) {
        if (name == null) return null;
        return NAME_MAP.get(name.toLowerCase());
    }
} 