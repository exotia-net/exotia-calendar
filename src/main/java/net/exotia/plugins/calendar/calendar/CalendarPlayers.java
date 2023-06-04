package net.exotia.plugins.calendar.calendar;

import java.util.HashMap;
import java.util.UUID;

public class CalendarPlayers {
    private final HashMap<UUID, CalendarPlayer> calendarPlayers = new HashMap<>();

    public CalendarPlayer getPlayer(UUID playerUUID) {
        return calendarPlayers.get(playerUUID);
    }

    public void addPlayer(UUID playerUUID, int step, long lastObtained, int streakDays) {
        calendarPlayers.put(playerUUID,
            CalendarPlayer.builder()
                .step(step)
                .lastObtained(lastObtained)
                .streakDays(streakDays)
                .build()
        );
    }
}