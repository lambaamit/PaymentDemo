package com.cca.payment.utility;

import java.sql.Timestamp;

public class DateUtility {

    private DateUtility() {
    }

    public static Timestamp getCurrentTimeStamp() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        return new Timestamp(currentDate.getTime());
    }
}
