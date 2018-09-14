package com.pegasie.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomUtil {
    public static String getRandomString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getRandomTime() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
}
