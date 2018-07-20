package com.nroad.amcc;

/**
 * System exception.
 */
@SuppressWarnings("serial")
public abstract class SystemException extends RuntimeException {
    /** System error. */
    private final SystemError error;

    public SystemException(SystemError error) {
        this(error, null);
    }

    public SystemException(SystemError error, String message) {
        this(error, message, null);
    }

    public SystemException(SystemError error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public final SystemError getError() {
        return error;
    }

    public static SystemException of(SystemError error) {
        return new SystemException(error, error.getDescription()) {};
    }

    public static SystemException of(SystemError error, String message) {
        return new SystemException(error, message) {};
    }

    public static SystemException of(SystemError error, Throwable cause) {
        return new SystemException(error, error.getDescription(), cause) {};
    }
}
