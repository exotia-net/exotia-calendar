package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.gui.GuiType;
import net.exotia.plugins.calendar.utils.UtilPlayers;
import org.bukkit.entity.Player;

@Route(name = "calendar", aliases = {"kalendarz", "dzienne"})
@Permission("exotia.calendar.command.calendar")
public class CommandCalendar {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private UtilPlayers utilPlayers;

    @Execute
    public void openGUI(Player player) {
        GuiType.CALENDAR.open(player, configurationGui, configurationMessage, utilPlayers.getPlayer(player.getUniqueId()));
    }
}
