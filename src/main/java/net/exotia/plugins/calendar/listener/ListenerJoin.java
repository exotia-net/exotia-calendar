package net.exotia.plugins.calendar.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.bridge.api.ExotiaBridgeProvider;
import net.exotia.bridge.api.entities.CalendarUser;
import net.exotia.bridge.api.user.ApiUser;
import net.exotia.bridge.api.user.ApiUserService;
import net.exotia.plugins.calendar.calendar.ServiceCalendar;
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

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CalendarUser calendarUser = ServiceCalendar.getCalendar(player);

        if (!calendarUser.canObtain(configurationGui.getGuiCalendar().getSlotsRewards().size())) return;

        calendarUser.addNotObtained(calendarUser.getStep());
        calendarUser.addStep(configurationGui.getGuiCalendar().getSlotsRewards().size());
        ExotiaBridgeProvider.getProvider().getUserService().getUser(player.getUniqueId()).setCalendar(calendarUser);
        ApiUserService userService = ExotiaBridgeProvider.getProvider().getUserService();
        ApiUser apiUser = userService.getUser(player.getUniqueId());

        userService.saveCalendar(apiUser);

        UtilMessage.sendMessage(player, configurationMessage.getEventsJoin().getObtainable(), String.valueOf(calendarUser.getStep()));
    }
}