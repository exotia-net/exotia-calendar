package net.exotia.plugins.calendar.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UtilPlayer {
    private int step;
    private long lastObtained;
}
