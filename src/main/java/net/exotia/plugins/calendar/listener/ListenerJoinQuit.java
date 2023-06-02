package net.exotia.plugins.calendar.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.utils.UtilPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerJoinQuit implements Listener {
    @Inject
    private UtilPlayers utilPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        utilPlayers.addPlayer(player.getUniqueId(),0,0);
    }
}