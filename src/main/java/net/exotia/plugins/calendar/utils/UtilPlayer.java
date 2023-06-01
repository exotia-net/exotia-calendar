package net.exotia.plugins.calendar.utils;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UtilPlayer {
    private int step;
    private long lastObtained;
}
