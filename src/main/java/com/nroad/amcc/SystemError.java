package com.nroad.amcc;

import java.util.stream.Stream;

/**
 * System error.
 */
public enum SystemError {
	
    SERVER_INTERNAL_ERROR(1, "System internal error");

    private final int code;

    private final String description;

    SystemError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static SystemError of(int code) {
        return Stream.of(values())
                .filter(e -> e.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown system error: " + code));
    }
}
