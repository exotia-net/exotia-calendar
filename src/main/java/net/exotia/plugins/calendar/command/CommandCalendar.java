package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.CalendarService;
import net.exotia.plugins.calendar.calendar.gui.GuiCalendar;
import org.bukkit.entity.Player;

import java.time.Instant;

@Command(name = "calendar", aliases = {"kalendarz", "dzienne"})
@Permission("exotia.calendar.command.calendar")
public class CommandCalendar {
    @Inject
    private GuiCalendar guiCalendar;

    @Execute
    public void openGUI(Player player) {
        guiCalendar.open(player);
    }

    @Execute(name = "reset")
    @Permission("exotia.calendar.command.admin")
    public void reset(@Arg Player player) {
//        CalendarService.getCalendar(player).reset();
    }

    @Execute(name = "last")
    @Permission("exotia.calendar.command.admin")
    public void setLast(@Arg Player player, @Arg Instant last) {
//        ServiceCalendar.getCalendar(player).setLastObtained(last.toEpochMilli());
    }

    @Execute(name = "step")
    @Permission("exotia.calendar.command.admin")
    public void setStep(@Arg Player player, @Arg int step) {
//        ServiceCalendar.getCalendar(player).setStep(step);
    }

    @Execute(name = "streak")
    @Permission("exotia.calendar.command.admin")
    public void setStreakDays(@Arg Player player, @Arg int streakDays) {
//        ServiceCalendar.getCalendar(player).setStreakDays(streakDays);
    }

    @Execute(name = "set")
    @Permission("exotia.calendar.command.admin")
    public void setEverything(@Arg Player player, @Arg Instant last, @Arg int step, @Arg int streakDays) {
//        CalendarUser calendarUser = ServiceCalendar.getCalendar(player);
//        calendarUser.setLastObtained(last.toEpochMilli());
//        calendarUser.setStep(step);
//        calendarUser.setStreakDays(streakDays);
    }
}
