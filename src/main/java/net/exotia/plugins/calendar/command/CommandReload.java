package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationPlugin;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "exotiacalendar", aliases = "ec")
@Permission("exotia.calendar.command.reload")
public class CommandReload {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;

    @Execute(route = "reload", aliases = {"przeladuj"})
    public void reload(CommandSender sender) {
        try {
            configurationMessage.load(true);
            configurationPlugin.load(true);
            configurationGui.load(true);
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getSuccess());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        } catch (OkaeriException error) {
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getFailed());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            error.printStackTrace();
        }
    }
}
