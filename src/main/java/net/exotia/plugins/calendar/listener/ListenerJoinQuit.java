package net.exotia.plugins.calendar.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.CalendarPlayers;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class ListenerJoinQuit implements Listener {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private CalendarPlayers calendarPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (calendarPlayers.getPlayer(playerUUID) == null) calendarPlayers.addPlayer(playerUUID,0,0, 0);
        if (calendarPlayers.getPlayer(playerUUID).canObtain()) UtilMessage.sendMessage(player, configurationMessage.getEventsJoin().getObtainable(), String.valueOf(calendarPlayers.getPlayer(playerUUID).getStep() + 1));
    }
}