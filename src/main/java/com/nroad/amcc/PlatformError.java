package com.nroad.amcc;

import java.util.stream.Stream;

/**
 * Platform error.
 */
public enum PlatformError {
	
	/**
	 * default error
	 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_FOUND(404, "Not Found"),
	UNKNOWN_ERROR(9999,"An unknown error"),
	
	/**
	 * tenant error
	 */
    OPERATION_ACCESS_DENIED(1001, "Operation access denied"),
    BAD_REQUEST(1002, "Invalid request"),


    APPLICATION_ALREADY_EXISTS(1101, "Application already exists"),
    APPLICATION_NOT_FOUND(1102, "Application not found"),
    APPLICATION_GROUP_NOT_FOUND(1103, "Application group not found"),
    APPLICATION_GROUP_ALREADY_EXISTS(1104, "Application group already exists"),
    APPLICATION_OAUTH_CLIENT_NOT_FOUND(1105, "Application oauth client not found"),

    TENANT_REGISTRATION_NOT_FOUND(1200, "Tenant Registration not found"),
    TENANT_ALREADY_EXISTS(1201, "Tenant already exists"),
    TENANT_NOT_FOUND(1202, "Tenant not found"),
    TENANT_DELETE_HAS_DIVISION(1203, "Tenant has division(s) can not delete"),
    ORGANIZATION_ALREADY_EXISTS(1203, "Organization already exists"),
    ORGANIZATION_NOT_FOUND(1204, "Organization not found"),
    ORGANIZATION_HAS_CHILDREN(1205, "Organization has children when delete"),
    TENANT_USER_NOT_FOUND(1206, "Tenant user not found"),

    USER_ALREADY_EXISTS(1301, "User already exists"),
    USER_NOT_FOUND(1302, "User not found"),
    USER_NOT_IN_GROUP(1303, "User not in group"),
    USER_NOT_IN_ORG(1304, "User not in org"),
    USER_NOT_IN_DEPARTMENT(1305, "User not in department"),
    USER_NOT_MEMBER(1306, "User is not a member"),
    USER_NAMES_CONFLICT(1307, "More then one user with same name"),
    MISSING_USER_INFORMATION(1308, "Missing user information"),
    EMPLOYEE_NOT_FOUND(1311, "User employee not found"),

    GROUP_ALREADY_EXISTS(1410, "Group already exists"),
    GROUP_NOT_FOUND(1411, "Group not found"),
    NOT_GROUP_MEMBER(1412, "User is not member of group"),

    AUTHORITY_NOT_FOUND(1501, "Authority not found"),
    
    /**
     * call error
     * code [2000~2999]
     */
    
    /**
     * crm error
     * code [3000~3999]
     */
	CRM_TENANT_NOT_EXIST(3001,"Crm tenant not exist"),
	CRM_ORG_NOT_EXIST(3002,"Crm org not exist"),
	CRM_ORG_NAME_ALREADY_EXIST(3003,"Crm org name already exists"),
	CRM_DEPT_NOT_EXIST(3004,"Crm dept not exist"),
	CRM_DEPT_NAME_ALREADY_EXIST(3005,"Crm dept name already exist"),
	CRM_CUSTOMER_TEMPLATE_NOT_EXIST(3006,"Crm customer template not exist"),
	CRM_CUSTOMER_TEMPLATE_ALREADY_EXIST(3007,"Crm customer template already exist"),
	CRM_CUST_NOT_EXIST(3008,"Crm cust not exist"),
	CRM_CUST_PHONE_NOT_BLANK(3009,"Crm cust phone is not blank"),
	CRM_CUST_PHONE_ALREADY_EXIST(3010,"Crm cust phone already exist"),
	CRM_CUST_UPLOAD_FILE_NOT_BLANK(3011,"Crm cust upload file not blank"),
	CRM_USER_NOT_EXIST(3012,"Crm user not exist"),
	CRM_APPOINTED_CALL_NOT_EXIST(3013,"Crm appointed call not exist"),
	CRM_CALLOUT_TASK_NOT_EXIST(3014,"Crm callout task not exist"),
	CRM_ROLE_NOT_EXIST(3015,"Crm role not exist"),
	CRM_TOUCH_RECORD_TYPE_NOT_EXIST(3016,"Crm touch record type not exist"),
	CRM_CUSTOMER_TEMPLATE_ITEM_TYPE_NOT_EXIST(3017,"Crm customer template item type not exist"),
	CRM_CUSTOMER_TEMPLATE_IS_WRONG(3018,"Crm customer template is wrong"),
	CRM_APPOINTED_CALL_ALREADY_EXIST(3019,"Crm appointed call already exist"),
	CRM_UNKNOW_ERROR(3999,"Tenant org dept not exist"),

    /**
     * kb error
     * code [4000~4999]
     */
    KNOWLEDGEBASE_IS_NULL(4001,"KnowledgeBase is null"),
    ORGANIZATIONID_CAN_NOT_BE_NULL(4002,"OrganizationId can not be null"),
    KNOWLEDGEBASEID_CAN_NOT_BE_NULL(4003,"KnowledgeBaseId can not be null"),
    FIND_WORDS_CAN_NOT_BE_NULL(4004,"Find words can not be null"),
    PARENTID_IS_EXIST(4005,"Parentid is exist"),
    KB_UPLOAD_FILE_NOT_BLANK(4006,"Kb upload file not blank"),
    KB_UNKNOW_ERROR(4007,"Tenant org dept not exist"),
    KB_OVER_ERROR(4008,"Not Allowed Over 500"),
    NOT_MATCH_PROFESSION(4009,"Not match Profession"),
    KB_Incomplete_Information(4010,"Information not complete "),
    KB_Phone_NotExist(4011,"Phone not exist "),
	
    /**
     * sms error
     * code [5000~5999]
     */
	SMS_TEMPLATE_NOT_EXIST(5001,"Sms template not exist");
	
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
                .orElseThrow(() -> new RuntimeException("Unknown platform error: " + code));
    }
}
