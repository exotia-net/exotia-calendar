package net.exotia.plugins.calendar.calendar.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Builder
@Getter
@Setter
public class CalendarPlayer {
    private int step;
    private List<Integer> notObtainedRewards;
    private long lastObtained;
    private int streakDays;

    private boolean hasStreak() {
        if (lastObtained == 0) return true;
        LocalDateTime last = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastObtained), ZoneId.systemDefault());
        return LocalDateTime.now().minusHours(40).isBefore(last);
    }

    public void addStep(int size) {
        if (this.step >= size) return;
        if (hasStreak()) streakDays++;
        step++;
        lastObtained = Instant.now().toEpochMilli();
    }

    public void addStreak() {
        streakDays++;
    }

    public void addNotObtained(int step) {
        notObtainedRewards.add(step);
    }

    public void removeNotObtained(int step) {
        if (notObtainedRewards.contains(step)) notObtainedRewards.remove(step);
    }

    public boolean canObtain() {
        return LocalDateTime.now().isAfter(LocalDateTime.ofInstant(Instant.ofEpochMilli(lastObtained), ZoneId.systemDefault()).plusDays(1).withHour(6));
    }
}
