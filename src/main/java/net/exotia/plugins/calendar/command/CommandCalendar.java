package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.gui.GuiCalendar;
import net.exotia.plugins.calendar.calendar.CalendarPlayers;
import org.bukkit.entity.Player;

import java.time.Instant;

@Route(name = "calendar", aliases = {"kalendarz", "dzienne"})
@Permission("exotia.calendar.command.calendar")
public class CommandCalendar {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private CalendarPlayers calendarPlayers;

    @Execute
    public void openGUI(Player player) {
        GuiCalendar.openCalendar(player, calendarPlayers.getPlayer(player.getUniqueId()), configurationGui, configurationMessage);
    }

    @Execute(route = "time")
    public void setTime(Player player, @Arg Instant last) {
        calendarPlayers.getPlayer(player.getUniqueId()).setLastObtained(last.toEpochMilli());
    }
}
