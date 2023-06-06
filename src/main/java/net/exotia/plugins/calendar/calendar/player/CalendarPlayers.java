package net.exotia.plugins.calendar.calendar.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CalendarPlayers {
    private final HashMap<UUID, CalendarPlayer> calendarPlayers = new HashMap<>();

    public CalendarPlayer getPlayer(UUID playerUUID) {
        CalendarPlayer calendarPlayer = calendarPlayers.get(playerUUID);
        if (calendarPlayer != null) return calendarPlayer;
        CalendarPlayer newCalendarPlayer = CalendarPlayer.builder()
            .step(0)
            .lastObtained(0)
            .streakDays(0)
            .notObtainedRewards(new ArrayList<>())
            .build();
        calendarPlayers.put(playerUUID, newCalendarPlayer);
        return newCalendarPlayer;
    }

    public void resetPlayer(UUID playerUUID) {
        calendarPlayers.remove(playerUUID);
        calendarPlayers.put(playerUUID,
            CalendarPlayer.builder()
                .step(0)
                .lastObtained(0)
                .streakDays(0)
                .notObtainedRewards(new ArrayList<>())
                .build()
        );
    }

    public void addPlayer(UUID playerUUID, int step, long lastObtained, int streakDays, List<Integer> notObtainedRewards) {
        calendarPlayers.put(playerUUID,
            CalendarPlayer.builder()
                .step(step)
                .lastObtained(lastObtained)
                .streakDays(streakDays)
                .notObtainedRewards(notObtainedRewards)
                .build()
        );
    }
}