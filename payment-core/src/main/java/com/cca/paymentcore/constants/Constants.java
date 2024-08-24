package com.cca.paymentcore.constants;

public class Constants {

    public static final String USER_AUTH = "user_auth";

    public static final String YOUR_ACCOUNT_IS = "Your account is ";

    public static final String ACCOUNT_STATUS_ERROR_MESSAGE = ".You are not allowed for Login. Please contact Customer Care";

    public static final String TEMPORARY_PROFILE_DETAIL = "temporary_profile_detail";

    public static final String USER_DEVICE_DETAIL = "user_device_detail";
    public static final String TEMP_LOCK_DETAILS = "TEMP_LOCK_DETAILS";
    public static final String NOTIFICATION_SETTINGS = "notification_settings";
    public static final String MM_REQUEST_IDENTIFIER = "mmReqIdentifier";


    public static final String BASIC = "Basic";

    public static final Long MINUTES_FOR_PERMANENT_LOCK = 60L;
    public static final Long TEMPORARY_LOCK_TIME_IN_MINS = 10L;
    public static final String USER_PROFILE = "user_profile";
    public static final String AUTH_URL = "/api/auth/v1";
    public static final String RETRIEVE_TOKEN = "/retrieveToken";

    public static final String VALIDATE_ROLE_USER = "/validate-user-password";

    public static final String TRANSACTION_ALL_BLOCK = "/block-management-controller/transaction-all-block";
    public static final String CHANNEL = "channel";
    public static final String APPLICANT_DETAILS = "applicant_details";

    public static final String APP_LANGUAGE = "appLanguage";

    public static final String ACCESS_TOKEN = "access_token";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String TOKEN_TYPE = "token_type";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String EXPIRES_IN = "expires_in";
    public static final String SCOPE = "scope";
    public static final String JTI = "jti";

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss";

    public static final String USER_SUB_TYPE = "userSubType";

    public static final String USER_TYPE = "userType";

    public static final String REQUEST_ID = "requestId";

    public static final String IS_BIOMETRIC_ENABLED = "isBiometricEnabled";
    public static final String IS_FIRST_TIME_ACCESS = "isFirstTimeAccess";

    public static final String RESPONSE_CODE = "responseCode";
    public static final String RESPONSE_MSG = "message";
    public static final String RESPONSE_STATUS = "status";
    public static final String EMAIL = "email";
    public static final String USER_CODE = "usercode";
    public static final String USER_NAME = "username";
    public static final String USER_PROFILE_CODE = "userprofilecode";
    public static final String MOBILE = "mobile";
    public static final String TEMPORARY_LOCK_TYPE = "TEMPORARY";
    public static final String PERMANENT_LOCK_TYPE = "PERMANENT";

    public static final String PERMANENT_LOCK_MESSAGE = "Account Permanently Locked";

    public static final String TEMPORARY_LOCK_MESSAGE = "Account Temporarily Locked";
    public static final Long TEMPORARY_LOCK_STAMP = 900000L;
    public static final String SUCCESS_STATUS = "SUCCESS";
    public static final String FAILURE_STATUS = "FAILURE";

    public static final String ROLE_MATRIX_SERVICE_UNAVAILABLE = "Role Matrix Service Unavailable";
    public static final String APP_SESSION_EXPIRED = "App Session Expired";
    public static final String TOKEN_EXPIRED = "Your Session is expired. Please login again to use the application!";
    public static final int TOKEN_EXPIRED_CODE = 500;
    public static final String INVALID_REQUEST_STATUS = "INVALID REQUEST";

    public static final int FAILURE_CODE = 500;

    public static final int INVALID_REQUEST_CODE = 500;

    public static final String TXN_CODE = "txnCode";
    public static final String CHECKSUM = "checksum";
    public static final String REQID = "reqId";
    public static final String SUCCESS_MSG = "Request Completed Successfully.";

    public static final String REQUEST_START_TIME = "requestStartTime";

    public static final String APPLICATION_NAME = "appName";
    public static final String APP_VERSION = "appVersion";
    public static final String PORTALNAME = "portalname";
    public static final String PORTAL_NAME_MERCHANT_PORTAL = "merchant-portal";
    public static final String PORTAL_DEVICE_ADDRESS = "portaldeviceaddress";
    public static final String BY_PASSING_URLS = "By Passing Urls";
    public static final String CREATING_REQUEST_WRAPPER = "Creating Request Wrapper";
    public static final String REQUEST_WRAPPER_IS_NULL = "Request Wrapper is Null";
    public static final String VALIDATING_HEADER_PARAMETERS = "Validating Header Parameters";
    public static final String ERROR_WHILE_VALIDATING_APP_HEADERS = "Error While Validating App Headers";
    public static final String CREATING_RESPONSE_WRAPPER = "Creating Response Wrapper";
    public static final String RESPONSE_STATUS_VALUE = "Response Status :";
    public static final String UPDATING_PORTAL_DEVICE_DETAILS = "Updating Portal Device Details";
    public static final String GETTING_USER_PROFILE_FROM_USER_ID = "Getting User Profile From User Id";
    public static final String INITIALIZING_DEVICE_DETAILS = "Initializing Device Details";
    public static final String INSIDE_SAVE_DEVICE_DETAILS_FOR_PORTAL_FUNCTION = "Inside SaveDeviceDetailsForPortal Function";
    public static final String SAVING_PORTAL_DEVICE_DETAILS = "Saving Portal Device Details";
    public static final String UPDATING_PORTAL_TRUSTED_DEVICE = "Updating Portal Trusted Device";
    public static final String UPDATING_DEVICE_DETAILS_FOR_APP = "Updating Device Details For App";
    public static final String UPDATING_DEVICE_STATUS = "Updating Device Status";
    public static final String UPDATING_TRUSTED_DEVICE_ID_AND_TYPE = "Updating Trusted Device Id And Type";
    public static final String UPDATING_BIOMETRIC_STATUS_FOR_USER_ID = "Updating Biometric Status for UserId: ";
    public static final String DEVICE_DETAILS_UPDATED_SUCCESSFULLY_FOR_USER_ID = "Device Details Updated Successfully For UserId : ";
    public static final String UPDATING_DEVICE_DETAILS_WITH_NEW_TRUSTED_DEVICE = "Updating Device Details With New Trusted Device";
    public static final String UPDATING_SUBSCRIBER_ID = "Updating Subscriber Id";
    public static final String USER_NAME_FOR_NOTIFICATION = "UserName For Notification: ";
    public static final String ACCOUNT_TEMPORARILY_LOCKED = "Account Temporarily Locked";
    public static final String MINS = " Mins";
    public static final String FOUND_NOTIFICATION = "Found Notification";
    public static final String VALIDATING_DEVICE_BLOCK = "Validating Device Block";
    public static final String VALIDATING_CHANNEL_BLOCK_STATUS = "Validating Channel Block Status";
    public static final String AUTHENTICATION_FAILED_FOR_USER_NAME = "Authentication Failed For UserName: ";
    public static final String VALIDATING_CRED_FOR_SYSTEM_PORTAL_WITH_INPUT_REQUEST = "Validating Cred For System Portal with Input Request: ";
    public static final String RESPONSE_FROM_ROLE_MATRIX_SERVICE = "Response From Role Matrix Service : ";
    public static final String FORCED_PASSWORD_MODE_IS_ENABLED_PLEASE_RESET_YOUR_PASSWORD = "Forced password mode is enabled please reset your password";
    public static final String EXPIRED_PASSWORD_MODE_IS_ENABLED_PLEASE_RESET_YOUR_PASSWORD = "Expired password mode is enabled please reset your password";
    public static final String PROFILE_IN_AUTH_MODE = "Profile In Auth Mode";
    public static final String VALIDATING_ACCESS_TOKEN_WITH_PAYLOAD_MAP = "Validating Access Token With Payload Map: ";
    public static final String EXPIRY_TIME = "Expiry Time";
    public static final String INVALID_TOKEN_1 = "Invalid Token ";
    public static final String TOKEN_EXPIRED_WITH_USER_NAME = "Token Expired with UserName: ";
    public static final String INVALID_CLIENT_KEY = "Invalid Client Key";
    public static final String INVALID_USER_ID_FOR_USER_PROFILE = "Invalid UserId for UserProfile";
    public static final String VALIDATING_ADDITIONAL_PARAMETERS_FOR_PORTAL_USER_FOR_USERNAME = "Validating Additional Parameters for Portal User for Username: ";
    public static final String INVALID_ACCOUNT_STATUS_FOR_PORTAL_USER = "Invalid Account Status For Portal User";
    public static final String VALIDATION_FOR_USER_TRUSTED_DEVICE = "Validation For UserTrusted Device  : ";
    public static final String VALIDATION_FOR_DEVICE_BLOCK_STATUS = "Validation For Device Block Status : ";
    public static final String VALIDATING_CHANNEL_BLOCK_STATUS_CC = "Validating Channel Block Status : ";
    public static final String VALIDATING_ACCOUNT_STATUS = "Validating Account Status : ";
    public static final String VALIDATING_CUSTOMER_STATUS = "Validating Customer Status : ";
    public static final String INVALID_AUTH_CREDS_FOR_SYS_PORTAL_USER = "Invalid Auth Creds For SysPortal User";
    public static final String INVALID_CREDS_FOR_APP = "Invalid Creds For App";
    public static final String PROFILE_STATUS = "Profile Status : ";
    public static final String DEVICE_BLOCK_FOR_PARTICULAR_CREDS = "Device Block For Particular Creds";
    public static final String CHANNEL_BLOCKED_FOR_LOGIN = "Channel Blocked For Login";
    public static final String INVALID_PIN_PASSWORD_FROM_HSM = "Invalid Pin/Password From HSM";
    public static final String VALIDATING_CREDS_FROM_HSM_WITH_REQUEST = "Validating Creds From HSM with Request: ";
    public static final String HSM_RESPONSE = "HSM Response: ";
    public static final String RESPONSE_RESULT_FOR_INVALID = "Response Result For invalid : ";
    public static final String UPDATING_IP_ADDRESS_FOR_USER_AUTH_FOR_USER_ID = "Updating Ip Address For UserAuth for UserId: ";
    public static final String INITIATING_LOGIN = "Initiating Login ";
    public static final String DECRYPTING_THE_USERNAME = "Decrypting the Username";
    public static final String MINUTES = " Minutes";
    public static final String BLOCKED = "BLOCKED";
    public static final String EMPTY_TOKEN = "Empty Token";
    public static final String SETTING_TOKEN_IN_RESPONSE_OBJECT = "Setting Token in Response Object  ";
    public static final String GENERATING_NEW_ACCESS_TOKEN = "Generating New Access Token: ";
    public static final String INSIDE_LOGIN_LOCKING_IMPL = "Inside Login Locking Impl:";
    public static final String BLOCKING_TRANSACTION = "Blocking Transaction";
    public static final String ALL_TRANSACTION_SUCCESSFULLY_BLOCKED = "All Transaction Successfully Blocked";
    public static final String CHANNEL_BLOCKING = "Channel Blocking";
    public static final String DEVICE_BLOCKED_SUCCESSFULLY = "Device Blocked Successfully";
    public static final String VALIDATING_CHANNEL_AGAINST_BLOCK_ALLOWED_CHANNELS = "Validating Channel Against Block Allowed Channels";
    public static final String CHANNEL_SUCCESSFULLY_MATCHED_AGAINST_BLOCK_ALLOWED_CHANNELS = "Channel Successfully Matched Against Block Allowed Channels";
    public static final String APPLYING_DEVICE_BLOCK = "Applying Device Block";
    public static final String VALIDATING_USER_TYPE_WITH_RESPECT_TO_BLOCK_ALLOWED_USER_TYPES = "Validating UserType With Respect To Block Allowed UserTypes";
    public static final String USER_TYPE_MATCHED_WITH_RESPECT_TO_BLOCK_ALLOWED_USER_TYPES = "UserType Matched With Respect To Block Allowed UserTypes";
    public static final String SAVING_AUTH_INSTANCE = "Saving Auth Instance";
    public static final String CREATING_AUDIT_DATA = "Creating Audit Data";
    public static final String SENDING_DATA_FOR_AUDIT_LOGGING = "Sending Data For Audit Logging";
    public static final String ADDING_DATA_TO_BE_LOGGED_FOR_TRANSACTIONS = "Adding Data to be Logged for Transactions";
    public static final String USER_TYPE_VALUE_FROM_CUSTOM = "UserType Value from Custom:";
    public static final String USER_TYPE_VALUE_FROM_DIRECT = "UserType Value from Direct:";
    public static final String CHANNEL_VALUE_FROM_DIRECT = "Channel Value from Direct:";
    public static final int ROLE_MATRIX_SUCCESS = 20000;
    public static final String USER_NOT_AUTHORIZED_FOR_THAT_ROLE = "User Not Authorized For that Role";
    public static final String DIGITAL_KEY = "digitalKey";
    public static final String INVALID_DATA = "INVALID DATA";
    public static final int INVALID_DATA_CODE = 6017;
    public static final String CHANEL_PARTNER_PORTAL = "chanel-partner-portal";

    public static final String TOKEN_EXPIRED_MESSAGE_CODE = "MSG-700004";
    public static final String UNAUTHORIZED_SIM_BINDING_MESSAGE_CODE = "MSG-700005";
    public static final String INVALID_APP_SESSION_MESSAGE_CODE = "MSG-700006";
    public static final String AUTH = "auth";
    public static final String LANGUAGE_NAME = "appLanguage";
    public static final String SUBSCRIBER_ID = "subscriberId";
    public static final String USER_TYPE_HEADER = "userType";
    public static final String APP_NAME = "appName";
    public static final int APP_SESSION_EXPIRED_CODE = 5099;
    public static final int INVALID_TOKEN_CODE = 5100;
    public static final int ERROR_CODE = 500;
    public static final String DEVICE_ID = "deviceId";
    public static final String DEVICE_NAME = "deviceName";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String DEVICE_MANUFACTURER = "deviceManufacturer";
    public static final String DEVICE_MAC_ADDRESS = "deviceMacAddress";
    public static final String DEVICE_MANUFACTURING_YEAR = "deviceManufacturingYear";
    public static final String DEVICE_IMEI_NUMBER = "deviceImeiNo";
    public static final String OS_MODEL_NAME = "OSMODELNAME";
    public static final String OS_VERSION = "OSVERSION";
    public static final String OS_NAME = "OSNAME";
    public static final String OS_FIRMWARE_BUILD = "OSFIRMWAREBUILD";
    public static final String OS_PRODUCT_NAME = "OSPRODUCTNAME";
    public static final String AUTHORIZATION = "Authorization";
    public static final String USER_ID = "userId";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String MM_REQ_IDENTIFIER = "mmReqIdentifier";
    public static final String PARAMETER_MISSING = "Parameter Missing";
    public static final String RESOURCE_NOT_AVAILABLE = "Resource not available";
    public static final String URI_NOT_FOUND = "URI not found";
    public static final String CLIENT = "client";
    public static final String CLIENT_SECRET = "client_secret";
    public static final Integer TEMPORARY_LOCK_COUNTER = 3;
    public static final int SUCCESS_CODE = 20000;
    public static final int ROLE_MATRIX_FAILURE_CODE = 0;
    public static final int ROLE_MATRIX_SUCCESS_CODE = 200;
    public static final int ROLE_MATRIX_FORCED_PASSWORD_CODE = 2515;
    public static final int ROLE_MATRIX_EXPIRED_PASSWORD_CODE = 2514;
    public static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
    public static final String UN_AUTHORIZED_CLIENT_ID_MESSAGE = "UN-AUTHORIZED CLIENT ";
    public static final int UNAUTHORIZED_CLIENT_ID = 4009;
    public static final String APPLICATION_JSON = "application/json";
    public static final String RESPONSE_DATA = "data";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final String AT = "@";
    public static final String INVALID_USERNAME_PLEASE_TRY_AGAIN = "Invalid Username or Password";
    public static final String UNAUTHORIZED_LOGIN = "Login Credentials you've entered is incorrect..!! Please Check & Retry.";
    public static final String INVALID_SESSION = "Invalid App Session. Please login again to use the application!";
    public static final String DEVICE_BLOCK = "Device Temporarily Blocked . Time Remaining to Unlock:";
    public static final String CHANNEL_BLOCK = "Channel Temporarily Blocked . Time Remaining to Unlock:";
    public static final String FORCED_PASSWORD_MODE = "Forced password mode is enabled please reset your password";
    public static final String EXPIRED_PASSWORD_MODE = "Expired password mode is enabled please reset your password";
    public static final String UNAUTHORIZED_SIM_BINDING = "Please insert SIM on slot 1 which was inserted at app activation time and try again";
    public static final String AUTH_MODE = "Please set your PIN by dialing *167#";
    public static final String PROFILE_STATUS_SUSPENDED = "Your Account is Suspended . Please Contact Customer Care";
    public static final String DEVICE_TYPE_ANDROID = "Android";
    public static final String LOG_NEW_PREFIX = "Log date Time :{}, Log level:{},Container id:{}, System IP Address:{},Source ID:{}, Request ID:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, API:{}, Log Message:{}";
    public static final String INSIDE_API = "Inside Api";
    public static final String ERROR_WHILE_ENHANCEING_OAUTH_TOKEN = "Error while enhancing oauth token : {}";
    public static final String VALIDATE_ACCESS_TOKEN_PAYLOAD = "validateAccessTokenPayload=== Error while decoding jwt token : {} ";
    public static final String ERROR_WHILE_RETREVING_CLIENT_DATA = "Error while retreiving client data :{}";
    public static final String ERROR_ENCOUNTERED = "Error Encountered : ";
    public static final String INSIDE_SERVICE_IMPL = "Inside Service Impl";
    public static final String ERROR_IN_LOGIN = "Error in login, Exception : {}";
    public static final String ERROR_CREATE_RESPONSE_WRAPPER = "Error createResponseWrapper :{}";
    public static final String ERROR_WHILE_RETREIVING_REQUEST_FAILURE_OBJECT = ", Error while retreiving request failure object : {} ";
    public static final String THERE_WAS_SOME_DECRYPTING_RESOURCE = "There was some decrypting resource : {}";
    public static final String THERE_WAS_SOME_ERROR_PROCESSING_REQUEST_WRAPPER = "There was some error processing RequestWrapper: {}";
    public static final String ERROR_WHILE_RETREIVING_USERID_FROM_REQUEST = "Error while retreiving userId from request:{}";
    public static final String WRONG_PIN_NEW_DEVICE_LOGIN = "Wrong PIN New Device Login";
    public static final String WRONG_PIN_WHITELISTED_DEVICE_LOGIN = "Wrong PIN Already Whitelisted Device Login";
    public static final String WRONG_PIN_TRANSACTION = "Wrong PIN Input in any transaction (App Login/USSD Channel)";
    public static final String WRONG_PIN_RESET_PIN = "Wrong PIN in Self PIN Reset (App/USSD)";
    public static final String WRONG_PASSWORD_WEB_LOGIN = "Wrong Password in Web Login";
    public static final String WRONG_PIN_USSD_BALANCE_CHECK = "Wrong PIN in USSD Balance Check";
    public static final String ALL_TRANSACTION = "All Transaction";
    public static final String RID_TYPE = "RI";
    public static final String SMS = "SMS";
    public static final String PUSH = "PUSH";
    public static final String EXCEPTION_WHILE_CREATING_REFERENCE_ID = "Error While creating reference Id";
    public static final Integer MAX_ID_COUNT = 10;
    public static final String APP_LANGUAGE_BN = "bn";
    public static final String APP_LANGUAGE_EN = "en";
    public static final String MESSAGE_NOT_CONFIGURED = "Message Not Configured";
    public static final String ERROR = "ERROR";
    public static final String DEFAULT_FAILURE_MESSAGE_BN = "আপনার প্রবেশ করানো লগইন শংসাপত্রগুলি ভুল..!! অনুগ্রহ করে চেক করুন এবং পুনরায় চেষ্টা করুন।";
    public static final String DEFAULT_FAILURE_MESSAGE_EN = "Login Credentials you've entered is incorrect..!! Please Check & Retry.";
    public static final String DEFAULT_SUCCESS_MESSAGE_BN = "সফলতা";
    public static final String DEFAULT_SUCCESS_MESSAGE_EN = "SUCCESS";
    public static final String NAGAD_LOG_NEW_PREFIX = "Log date Time :{}, Log level:{},Container id:{}, System IP Address:{},Source ID:{}, Request ID:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, API:{},Class Name:{}, Log Message:{}";
    public static final String NAGAD_ERROR_LOG_PREFIX = "Log date Time :{}, Log level:{},Container id:{}, System IP Address:{},Source ID:{}, Request ID:{}, Customer Identifier:{}, Transaction Name:{}, Transaction ID:{}, API:{},Class Name:{}, Error Message:{}";
    public static final String NAGAD_LOG_HEADER_PREFIX = "Log date Time :{}, Log level:{},Container id:{}, System IP Address:{},Source ID:{}, Request ID:{},Class Name:{}, Channel:{}, DeviceId:{}, DeviceType:{}, AppLanguage:{},Portal Identifier:{}, Portal Device Address:{} , Authorization:{}";
    public static final String SYSTEM_INITIATED = "SYSTEM_INTITIATED";
    public static final String BLOCK_ALL_TRANSACTION = "Block All Transaction";

    private Constants() {
    }
    public enum HSMPINDATATYPE {
        PASSWORD, WALLET_PIN, TRANSACTION_PIN
    }

    public enum CHANNEL {
        APP, WEB, USSD, SYSTEM_PORTAL
    }

    public enum VERIFYPINEVENT {
        VERIFY_PIN_NEW_DEVICE, VERIFY_PIN_WHITELISTED_DEVICE, VERIFY_PIN_TRANSACTION, VERIFY_PIN_RESET_PIN, VERIFY_PIN_USSD_BALANCE_CHECK, VERIFY_PASSWORD
    }
}
