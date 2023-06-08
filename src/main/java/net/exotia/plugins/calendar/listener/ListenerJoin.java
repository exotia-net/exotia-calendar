package net.exotia.plugins.calendar.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.player.CalendarPlayer;
import net.exotia.plugins.calendar.calendar.player.CalendarPlayers;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerJoin implements Listener {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private CalendarPlayers calendarPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CalendarPlayer calendarPlayer = calendarPlayers.getPlayer(player.getUniqueId());

        if (!calendarPlayer.canObtain(configurationGui.getGuis().get("calendar").getSlotsRewards().size())) return;

        calendarPlayer.addNotObtained(calendarPlayer.getStep());
        calendarPlayer.addStep(configurationGui.getGuis().get("calendar").getSlotsRewards().size());
        UtilMessage.sendMessage(player, configurationMessage.getEventsJoin().getObtainable(), String.valueOf(calendarPlayer.getStep()));
    }
}