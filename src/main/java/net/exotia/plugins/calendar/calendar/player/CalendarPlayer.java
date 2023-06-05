package net.exotia.plugins.calendar.calendar.player;

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

    public void addStep(int size) {
        if (this.step >= size) return;
        if (hasStreak()) streakDays++;
        step++;
        lastObtained = Instant.now().toEpochMilli();
    }

    public void addStreak() {
        streakDays++;
    }

    public boolean canObtain() {
        LocalDateTime last = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastObtained), ZoneId.systemDefault());
        return LocalDateTime.now().isAfter(last.plusDays(1).withHour(6));
    }

    private boolean hasStreak() {
        if (lastObtained == 0) return true;
        if (streakDays == 4) return false;
        LocalDateTime last = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastObtained), ZoneId.systemDefault());
        return LocalDateTime.now().minusHours(40).isBefore(last);
    }
}
