package net.exotia.plugins.calendar;

import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import dev.rollczi.litecommands.annotations.LiteCommandsAnnotations;
import dev.rollczi.litecommands.bukkit.LiteCommandsBukkit;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.exotia.plugins.calendar.calendar.gui.GuiCalendar;
import net.exotia.plugins.calendar.command.CommandCalendar;
import net.exotia.plugins.calendar.command.CommandReload;
import net.exotia.plugins.calendar.configuration.ConfigurationFactory;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationRewards;
import net.exotia.plugins.calendar.database.DatabaseService;
import net.exotia.plugins.calendar.handler.HandlerInvalid;
import net.exotia.plugins.calendar.handler.HandlerUnauthorized;
import net.exotia.plugins.calendar.listener.ListenerJoin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class CalendarPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    private final ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
    private ConfigurationMessage configurationMessage;
    private ConfigurationGui configurationGui;
    private ConfigurationRewards configurationRewards;

    @Override
    public void onEnable() {
        injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector.createInstance(DatabaseService.class));
        injector.registerInjectable(injector);
        injector.registerInjectable(configurationFactory);

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
        configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
        configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");
        configurationRewards = configurationFactory.produce(ConfigurationRewards.class, "rewards.yml");

        injector.registerInjectable(configurationMessage);
        injector.registerInjectable(configurationGui);
        injector.registerInjectable(configurationRewards);
    }

    private void setupUtils() {
        injector.registerInjectable(injector.createInstance(GuiCalendar.class));
    }

    private void setupCommands() {
        LiteCommandsBukkit.builder("exotia.net", this)
                .extension(new LiteAdventureExtension<CommandSender>()
                        .miniMessage(true)
                        .legacyColor(true)
                        .colorizeArgument(true)
                        .serializer(MiniMessage.miniMessage())
                )
                .commands(LiteCommandsAnnotations.of(
                                this.injector.createInstance(CommandReload.class),
                                this.injector.createInstance(CommandCalendar.class)
                        )
                )
                .invalidUsage(this.injector.createInstance(HandlerInvalid.class))
                .missingPermission(this.injector.createInstance(HandlerUnauthorized.class))
                .build();
    }

    private void setupEvents() {
        Stream.of(
            injector.createInstance(ListenerJoin.class)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void cleanUp() {
        configurationMessage.save();
        configurationGui.save();
        configurationRewards.save();
    }
}