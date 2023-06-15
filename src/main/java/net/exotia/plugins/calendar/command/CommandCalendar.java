package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.ServiceCalendar;
import net.exotia.plugins.calendar.calendar.gui.GuiCalendar;
import org.bukkit.entity.Player;

import java.time.Instant;

@Route(name = "calendar", aliases = {"kalendarz", "dzienne"})
@Permission("exotia.calendar.command.calendar")
public class CommandCalendar {
    @Inject
    private GuiCalendar guiCalendar;

    @Execute
    public void openGUI(Player player) {
        guiCalendar.open(player);
    }

    @Route(name = "admin reset")
    @Permission("exotia.calendar.command.admin")
    public void reset(@Arg Player player) {
        ServiceCalendar.getCalendar(player).reset();
    }

    @Execute(required = 1)
    @Route(name = "admin last")
    @Permission("exotia.calendar.command.admin")
    public void setLast(@Arg Player player, @Arg Instant last) {
//        ServiceCalendar.getCalendar(player).setLastObtained(last.toEpochMilli());
    }

    @Execute(required = 1)
    @Route(name = "admin step")
    @Permission("exotia.calendar.command.admin")
    public void setStep(@Arg Player player, @Arg int step) {
//        ServiceCalendar.getCalendar(player).setStep(step);
    }

    @Execute(required = 1)
    @Route(name = "admin streak")
    @Permission("exotia.calendar.command.admin")
    public void setStreakDays(@Arg Player player, @Arg int streakDays) {
//        ServiceCalendar.getCalendar(player).setStreakDays(streakDays);
    }

    @Execute(required = 3)
    @Route(name = "admin set")
    @Permission("exotia.calendar.command.admin")
    public void setEverything(@Arg Player player, @Arg Instant last, @Arg int step, @Arg int streakDays) {
//        CalendarUser calendarUser = ServiceCalendar.getCalendar(player);
//        calendarUser.setLastObtained(last.toEpochMilli());
//        calendarUser.setStep(step);
//        calendarUser.setStreakDays(streakDays);
    }
}
