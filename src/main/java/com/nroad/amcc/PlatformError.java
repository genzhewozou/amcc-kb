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
    
    /**
     * kb error
     * code [4001~4999]
     */
    KNOWLEDGEBASE_IS_NULL(4001,"KnowledgeBase is null"),
    ORGANIZATIONID_CAN_NOT_BE_NULL(4002,"OrganizationId can not be null"),
    KNOWLEDGEBASEID_CAN_NOT_BE_NULL(4003,"KnowledgeBaseId can not be null"),
    FIND_WORDS_CAN_NOT_BE_NULL(4004,"Find words can not be null"),
    PARENTID_IS_EXIST(4005,"Parentid is exist");
	
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
