package net.exotia.plugins.calendar.utils;

import net.exotia.plugins.calendar.CalendarPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class UtilMessage {
    private static Component replacePlaceholders(String message, String... values) {
        for (int i = 1; i <= values.length; i++) message = message.replace("%value_" + i + "%", values[i - 1]);
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public static void sendMessage(CommandSender sender, String message, String... values) {
        CalendarPlugin.getAudiences().sender(sender).sendMessage(replacePlaceholders(message, values));
    }

    public static String getMessage(String message) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<white>" + message));
    }

    public static Component getComponent(String message) {
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public static String convertComponent(String string, String... values) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<white>" + string, values));
    }

    public static List<String> convertComponent(List<String> list, String... values) {
        return list.stream().map(line -> LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<gray>" + line, values))).toList();
    }

    public static void playSound(Player player, String soundName) {
        player.playSound(player, Sound.valueOf(soundName.toUpperCase()), 1F, 1F);
    }
}