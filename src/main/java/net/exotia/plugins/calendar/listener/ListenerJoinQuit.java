package net.exotia.plugins.calendar.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.CalendarPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerJoinQuit implements Listener {
    @Inject
    private CalendarPlayers calendarPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        calendarPlayers.addPlayer(event.getPlayer().getUniqueId(),0,0, 0);
    }
}