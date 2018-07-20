package com.nroad.amcc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * REST error response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestError {
    @JsonProperty
    private final String error;

    @JsonProperty
    private final int code;

    @JsonProperty
    private final String description;

    @JsonProperty
    private final String url;

    @JsonCreator
    private RestError(
            @JsonProperty("error") String error,
            @JsonProperty("code") int code,
            @JsonProperty("description") String description,
            @JsonProperty("url") String url) {
        this.error = error;
        this.code = code;
        this.description = description;
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Create a rest error from error, code, description and url.
     *
     * @param error error.
     * @param url   details url.
     * @return rest error response.
     */
    public static RestError of(PlatformError error, String description, String url) {
        return new RestError(error.name(), error.getCode(), description, url);
    }

    /**
     * Convert platform error to rest error response.
     *
     * @param error error.
     * @return rest error response.
     */
    public static RestError of(PlatformError error) {
        return of(error, error.getDescription(), null);
    }

    /**
     * Convert platform error to rest error response.
     *
     * @param ex exception.
     * @return rest error response.
     */
    public static RestError of(PlatformException ex) {
        return of(ex.getError());
    }

    /**
     * Create a rest error from error, code, description and url.
     *
     * @param error error.
     * @param url   details url.
     * @return rest error response.
     */
    public static RestError of(SystemError error, String description, String url) {
        return new RestError(error.name(), error.getCode(), description, url);
    }

    /**
     * Convert platform error to rest error response.
     *
     * @param error error.
     * @return rest error response.
     */
    public static RestError of(SystemError error) {
        return of(error, error.getDescription(), null);
    }

    /**
     * Convert platform error to rest error response.
     *
     * @param ex exception.
     * @return rest error response.
     */
    public static RestError of(SystemException ex) {
        return of(ex.getError());
    }
}
