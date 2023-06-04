package net.exotia.plugins.calendar.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

@Builder
@Getter
@Setter
public class CalendarPlayer {
    private int step;
    private long lastObtained;
    private int streakDays;

    public boolean stepUp() {
        return canObtain();
    }

    public void addStep() {
        if (hasStreak()) streakDays++;
        step++;
        lastObtained = Instant.now().toEpochMilli();
    }

    private boolean canObtain() {
        LocalDateTime last = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastObtained), ZoneId.systemDefault());
        return LocalDateTime.now().isAfter(last.plusDays(1).withHour(6));
    }

    private boolean hasStreak() {
        LocalDateTime last = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastObtained), ZoneId.systemDefault());
        return LocalDateTime.now().minusHours(40).isBefore(last);
    }
}
