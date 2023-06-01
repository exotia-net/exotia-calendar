package net.exotia.plugins.calendar.utils;

import java.util.HashMap;
import java.util.UUID;

public class UtilPlayers {
    private final HashMap<UUID, UtilPlayer> utilPlayers = new HashMap<>();

    public UtilPlayer getPlayer(UUID playerUUID) {
        return utilPlayers.get(playerUUID);
    }

    public void addPlayer(UUID playerUUID, int step, long lastObtained) {
        utilPlayers.put(playerUUID,
            UtilPlayer.builder()
                .step(step)
                .lastObtained(lastObtained).build());
    }
}