package com.nroad.amcc;

/**
 * Platform exception.
 */
@SuppressWarnings("serial")
public abstract class PlatformException extends RuntimeException {
    /** Platform error. */
    private final PlatformError error;

    public PlatformException(PlatformError error) {
        this(error, null);
    }

    public PlatformException(PlatformError error, String message) {
        this(error, message, null);
    }

    public PlatformException(PlatformError error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public final PlatformError getError() {
        return error;
    }

    public static PlatformException of(PlatformError error) {
        return new PlatformException(error, error.getDescription()) {};
    }

    public static PlatformException of(PlatformError error, String message) {
        return new PlatformException(error, message) {};
    }

    public static PlatformException of(PlatformError error, Throwable cause) {
        return new PlatformException(error, error.getDescription(), cause) {};
    }
}
