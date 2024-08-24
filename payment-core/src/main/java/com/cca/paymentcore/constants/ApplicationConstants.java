package com.cca.paymentcore.constants;

public final class ApplicationConstants {

    // ----------------------------------- Header Constants -----------------------------------
    public static final String REQUEST_ID = "requestId";
    public static final String CHANNEL_HEADER = "channel";
    public static final String AUTHORIZATION = "Authorization";
    public static final String UNIQUE_IDENTIFIER = "uniqueIdentifier";
    public static final String DEVICE_ID = "deviceId";
    public static final String CLIENT = "client";
    public static final String APP_VERSION = "appVersion";
    public static final String MM_REQUEST_IDENTIFIER = "mmReqIdentifier";
    public static final String CHECK_SUM = "checksum";
    public static final String DIGITAL_KEY = "digitalKey";
    public static final String CUSTOMER_ID = "customerId";
    public static final String OS_MODEL_NAME = "OSMODELNAME";
    public static final String OS_VERSION = "OSVERSION";
    public static final String OS_NAME = "OSNAME";
    public static final String OS_FIRMWARE_BUILD = "OSFIRMWAREBUILD";
    public static final String OS_PRODUCT_NAME = "OSPRODUCTNAME";
    public static final String SUBSCRIBER_ID = "subscriberId";
    public static final String PORTAL_DEVICE_ADDRESS = "portaldeviceaddress";

    // ---------------------------------------------Message Constants---------------------------------------------
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "Failure";
    public static final String ERROR = "error";
    public static final String SYSTEM_PORTAL = "SYSTEM_PORTAL";
    public static final String RMS_PORTAL = "RMS_PORTAL";
    public static final String APP = "APP";
    public static final String UID_HEADER_IS_MISSING = "UID Header Is Missing";
    public static final String TOKEN_INVALID = "Token Invalid";
    public static final String TRANSACTION_FAILED = "Transaction Failed";
    public static final String INSUFFICIENT_CREDIT_CARD_BALANCE = "Insufficient Credit Card Balance";
    public static final String TRANSACTION_SUCCESSFULL = "Transaction Successfull";

    // ---------------------------------------------Enums---------------------------------------------
    public static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";

    // ---------------------------------------------Encryption Constants---------------------------------------------
    public static final String CHANNEL_VALUE_FOR_ENY_DEC = "Channel Value For Eny/DEC : ";
    public static final String HASH_GENERATED_SEQUENCE = "Hash Generated Sequence: ";
    public static final String HASH_GENERATED_AFTER_CREATION = "Hash Generated After Creation : ";
    public static final String TREE_MAP_GENERATED = ", Tree Map generated :{} ";
    public static final String ADD_MM_REQ_IDENTIFIER_WEB_MM_REQ_IDENTIFIER =
            ", addMMReqIdentifier, web mmReqIdentifier :{} ";
    public static final String THERE_WAS_SOME_ERROR_PROCESSING_REQUEST_WRAPPER =
            "There was some error processing RequestWrapper: ";
    public static final String LOG_NEW_PREFIX =
            "Log date Time :{}, Log level:{} System IP Address:{}, Request ID:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, API:{},Class Name:{}, Log Message:{}";

    // ------------------------------------------------ Log Constants ------------------------------------------------
    public static final String ERROR_LOG_PREFIX =
            "Log date Time :{}, Log level:{}, System IP Address:{}, Request ID:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, API:{},Class Name:{}, Error Message:{}";
    public static final String LOG_APPROVAL_PREFIX =
            "Log date Time :{}, Log level:{}, System IP Address:{}, Approval Code:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, Request Type:{},Class Name:{}, Log Message:{}";
    public static final String ERROR_LOG_APPROVAL_PREFIX =
            "Log date Time :{}, Log level:{}, System IP Address:{}, Approval Code:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, Request Type:{},Class Name:{}, Error Message:{}";
    public static final String LOG_PREFIX =
            "Log date Time :{0}, Log level:{1}, System IP Address:{2}, Request ID:{3}, Customer Identifier:{4}, Transaction Name:{5}, Transaction ID:{6}, API:{7}, Log Message:{8}";
    public static final String REQUEST_START_TIME = "requestStartTime";

    // ---------------------------------------------Request Constants--------------------------------------------------
    public static final String INVALID_REQUEST_STATUS = "INVALID REQUEST";
    public static final String RESPONSE_CODE = "responseCode";
    public static final String RESPONSE_MSG = "message";
    public static final String RESPONSE_STATUS = "status";
    // -----------------------------------------------Api Constants-----------------------------------------------------
    public static final String API_VERSION = "/v1";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String INVALID_DATA = "Invalid Data";


    // ------------------------------------------------ResponseConstants------------------------------------------------

    public enum CHANNEL {
        APP,
        WEB,
        USSD,
        SYSTEM_PORTAL,
        API
    }
}
