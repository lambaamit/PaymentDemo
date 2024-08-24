package com.cca.paymentcore.utility;

import com.cca.paymentcore.constants.ApplicationConstants;
import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Utility {

    public static final String SERVER_ID = Utility.getServerId();
    private static final SecureRandom rnd = new SecureRandom();
    public static Long lastRequestIdRandomTime = System.currentTimeMillis() / 1000;
    public static List<Integer> lastRequestIdListInTime = new ArrayList<>();

    private Utility() {
    }

    public static long getRequestId() {
        try {
            Long currTime = System.currentTimeMillis() / 1000;
            if (currTime > lastRequestIdRandomTime) {
                lastRequestIdRandomTime = currTime;
                lastRequestIdListInTime.clear();
            }
            boolean loopFlag = true;
            Integer random = null;
            while (loopFlag) {
                random = rnd.nextInt(10000);
                loopFlag = lastRequestIdListInTime.contains(random);
            }
            lastRequestIdListInTime.add(random);
            StringBuilder s = new StringBuilder();
            s = s.append(currTime);
            s = s.append(SERVER_ID);
            s = s.append(random);
            return Long.valueOf(String.valueOf(s));
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Long getRequestId(HttpServletRequest request) {
        try {
            return Optional.ofNullable(request.getAttribute(ApplicationConstants.REQUEST_ID)).map(String::valueOf)
                    .map(Long::parseLong).orElse(Utility.getRequestId());
        } catch (Exception e) {
            return Utility.getRequestId();
        }
    }

    public static String getServerId() {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            return ip.substring(ip.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSystemHostName() {
        InetAddress ip;
        String hostname = null;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (UnknownHostException e) {
            return null;
        }
        return hostname;
    }

    public static String getIp() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return null;
        }
        return String.valueOf(ip);
    }

    public static String getDateTimeFromPattern(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Timestamp getCurrentTimeStampObject() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getClientIp(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return String.valueOf(ipAddress);
    }

    public static Long getCronTimeDiff(Instant startTime, Instant endTime) {
        Duration executionDuration = Duration.between(startTime, endTime);
        return executionDuration.toMillis();
    }
}
