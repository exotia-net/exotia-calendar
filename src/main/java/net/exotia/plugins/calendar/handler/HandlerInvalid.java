package net.exotia.plugins.calendar.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HandlerInvalid implements InvalidUsageHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        List<String> schematics = schematic.getSchematics();
        if (schematics.size() == 1) {
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsInvalid().getInvalid());
            UtilMessage.playSound((Player) sender, configurationMessage.getSounds().getError());
            return;
        }
        UtilMessage.sendMessage(sender, configurationMessage.getCommandsInvalid().getInvalidUsage());
        UtilMessage.playSound((Player) sender, configurationMessage.getSounds().getError());
        schematics.forEach(sch -> sender.sendMessage("&8 >> &7" + sch));
    }
}
