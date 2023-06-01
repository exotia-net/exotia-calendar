package net.exotia.plugins.calendar.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.command.permission.RequiredPermissions;
import dev.rollczi.litecommands.handle.PermissionHandler;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.command.CommandSender;

public class HandlerUnauthorized implements PermissionHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, RequiredPermissions requiredPermissions) {
        UtilMessage.sendMessage(sender, configurationMessage.getCommandsNoPermission().getFailed());
    }
}
