package com.cca.paymentcore.enums;

public enum Channel {

    APP, WEB, USSD, SYSTEM_PORTAL, CC_PORTAL, RMS_PORTAL;

    public static Channel getChannel(String channelValue) {
        Channel type = null;

        for (Channel channelType : values()) {
            if (channelType.toString().equalsIgnoreCase(channelValue)) {
                type = channelType;
                break;
            }
        }
        return type;
    }
}
