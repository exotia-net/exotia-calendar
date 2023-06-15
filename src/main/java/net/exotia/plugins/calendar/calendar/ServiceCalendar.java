package net.exotia.plugins.calendar.calendar;

import net.exotia.bridge.api.ExotiaBridgeProvider;
import net.exotia.bridge.api.entities.CalendarUser;
import net.exotia.bridge.api.user.ApiUserService;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ServiceCalendar {
    public static CalendarUser getCalendar(Player player) {
        ApiUserService userService = ExotiaBridgeProvider.getProvider().getUserService();
        CalendarUser calendarUser = userService.getUser(player.getUniqueId()).getCalendar();
        if (calendarUser == null) calendarUser = new CalendarUser(0, new ArrayList<>(), 0, 0);
        return calendarUser;
    }
}
