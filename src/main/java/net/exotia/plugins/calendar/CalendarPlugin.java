package net.exotia.plugins.calendar;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import lombok.Getter;
import net.exotia.plugins.calendar.command.CommandReload;
import net.exotia.plugins.calendar.command.CommandCalendar;
import net.exotia.plugins.calendar.configuration.ConfigurationFactory;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationPlugin;
import net.exotia.plugins.calendar.handler.HandlerInvalid;
import net.exotia.plugins.calendar.handler.HandlerUnauthorized;
import net.exotia.plugins.calendar.listener.ListenerJoinQuit;
import net.exotia.plugins.calendar.utils.UtilMessage;
import net.exotia.plugins.calendar.utils.UtilPlayers;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class CalendarPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    @Getter
    private static Plugin plugin;
    @Getter
    private static BukkitAudiences audiences;
    private ConfigurationPlugin configurationPlugin;
    private ConfigurationMessage configurationMessage;
    private ConfigurationGui configurationGui;

    @Override
    public void onEnable() {
        plugin = this;
        audiences = BukkitAudiences.create(this);

        injector.registerInjectable(plugin);
        injector.registerInjectable(injector);

        setupConfiguration();
        setupUtils();
        setupCommands();
        setupEvents();
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
        configurationPlugin = configurationFactory.produce(ConfigurationPlugin.class, "config.yml");
        injector.registerInjectable(configurationPlugin);
        configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
        injector.registerInjectable(configurationMessage);
        configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");
        injector.registerInjectable(configurationGui);
    }

    private void setupUtils() {
        injector.registerInjectable(new UtilPlayers());
    }

    private void setupCommands() {
        LiteBukkitFactory.builder(this.getServer(), "exotia.net")
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), UtilMessage.getMessage(configurationMessage.getCommandsPlayer().getOffline())))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>(UtilMessage.getMessage(configurationMessage.getCommandsPlayer().getOnly())))
                .commandInstance(
                        injector.createInstance(CommandReload.class),
                        injector.createInstance(CommandCalendar.class)
                )
                .invalidUsageHandler(injector.createInstance(HandlerInvalid.class))
                .permissionHandler(injector.createInstance(HandlerUnauthorized.class))
                .register();
    }

    private void setupEvents() {
        Stream.of(
                injector.createInstance(ListenerJoinQuit.class)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void cleanUp() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        configurationPlugin.save();
        configurationMessage.save();
        configurationGui.save();
    }
}