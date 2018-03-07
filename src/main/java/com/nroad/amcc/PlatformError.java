package com.nroad.amcc;

import java.util.stream.Stream;

/**
 * Platform error.
 *
 * @author zhoupeng
 */
public enum PlatformError {
    /**
     * system error
     * code [1~999]
     */
    BAD_REQUEST(400, "Bad request"),
    FORBIDDEN(403, "Forbidden"),
    SERVER_INTERNAL_ERROR(500, "Server internal error!"),
    KNOWLEDGEBASE_IS_NULL(4001,"KnowledgeBase Is Null!"),
    ORGANIZATIONID_CAN_NOT_BE_NULL(4002,"OrganizationId Can Not Be Null!"),
    KNOWLEDGEBASEID_CAN_NOT_BE_NULL(4003,"KnowledgeBaseId Can Not Be Null!"),
    FIND_WORDS_CAN_NOT_BE_NULL(4004,"Find Words Can Not Be Null!"),
    PARENTID_IS_EXIST(4005,"Parentid Is Exist!");
    private final int code;

    private final String description;

    PlatformError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PlatformError of(int code) {
        return Stream.of(values())
                .filter(e -> e.getCode() == code)
                .findFirst()
                .orElse(SERVER_INTERNAL_ERROR);
    }
}
