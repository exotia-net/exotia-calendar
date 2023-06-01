package net.exotia.plugins.calendar.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationPlugin;
import net.exotia.plugins.calendar.gui.GuiButton;
import net.exotia.plugins.calendar.utils.UtilMessage;
import net.exotia.plugins.calendar.utils.UtilPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class ListenerJoinQuit implements Listener {
    @Inject
    private UtilPlayers utilPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        utilPlayers.addPlayer(player.getUniqueId(),0,0);
    }
}