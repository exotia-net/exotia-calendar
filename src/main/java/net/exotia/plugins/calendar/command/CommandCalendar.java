package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.CalendarPlayers;
import net.exotia.plugins.calendar.gui.GuiCalendar;
import org.bukkit.entity.Player;

import java.time.Instant;

@Route(name = "calendar", aliases = {"kalendarz", "dzienne"})
@Permission("exotia.calendar.command.calendar")
public class CommandCalendar {
    @Inject
    private CalendarPlayers calendarPlayers;
    @Inject
    private GuiCalendar guiCalendar;

    @Execute
    public void openGUI(Player player) {
        guiCalendar.open(player);
    }

    @Route(name = "admin last")
    @Permission("exotia.calendar.command.admin")
    public void setLast(@Arg Player player, @Arg Instant last) {
        calendarPlayers.getPlayer(player.getUniqueId()).setLastObtained(last.toEpochMilli());
    }

    @Route(name = "admin step")
    @Permission("exotia.calendar.command.admin")
    public void setStep(@Arg Player player, @Arg int step) {
        calendarPlayers.getPlayer(player.getUniqueId()).setStep(step);
    }

    @Route(name = "admin streak")
    @Permission("exotia.calendar.command.admin")
    public void setStreakDays(@Arg Player player, @Arg int streakDays) {
        calendarPlayers.getPlayer(player.getUniqueId()).setStreakDays(streakDays);
    }

    @Route(name = "admin everything")
    @Permission("exotia.calendar.command.admin")
    public void setEverything(@Arg Player player, @Arg Instant last, @Arg int step, @Arg int streakDays) {
        calendarPlayers.getPlayer(player.getUniqueId()).setLastObtained(last.toEpochMilli());
        calendarPlayers.getPlayer(player.getUniqueId()).setStep(step);
        calendarPlayers.getPlayer(player.getUniqueId()).setStreakDays(streakDays);
    }
}
