package net.exotia.plugins.calendar.utils;

import java.time.Instant;

public class UtilValidate {
    public static Boolean validateReward(long latestObtain) {
        return latestObtain + 79200000 <= Instant.now().toEpochMilli();
    }
}
