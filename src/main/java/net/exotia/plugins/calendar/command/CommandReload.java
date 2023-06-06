package net.exotia.plugins.calendar.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationFactory;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationRewards;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "calendar", aliases = {"kalendarz", "dzienne"})
@Permission("exotia.calendar.command.admin")
public class CommandReload {
    @Inject
    private ConfigurationFactory configurationFactory;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private ConfigurationRewards configurationRewards;

    @Execute(route = "admin reload")
    public void reload(CommandSender sender) {
        try {
            configurationMessage.load(true);
            configurationGui.load(true);
            configurationRewards.load(true);
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getSuccess());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        } catch (OkaeriException error) {
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getFailed());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
            configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");
            configurationRewards = configurationFactory.produce(ConfigurationRewards.class, "rewards.yml");
        }
    }
}
