package net.exotia.plugins.calendar.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.exotia.plugins.calendar.CalendarPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UtilMessage {
    private static Component replacePlaceholders(String message, String... values) {
        for (int i = 1; i <= values.length; i++) message = message.replace("%value_" + i + "%", values[i - 1]);
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    private static Component replacePlaceholders(String message, List<String> values) {
        for (int i = 1; i <= values.size(); i++) message = message.replace("%value_" + i + "%", values.get(i - 1));
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public static void sendMessage(CommandSender sender, String message, String... values) {
        CalendarPlugin.getAudiences().sender(sender).sendMessage(replacePlaceholders(message, values));
    }

    public static void sendMessage(Player player, String message, String... values) {
        CalendarPlugin.getAudiences().player(player).sendMessage(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, message), values));
    }

    public static void sendTitle(Player player, String title, List<String> titleValues, String subtitle, List<String> subtitleValues) {
        CalendarPlugin.getAudiences().player(player).showTitle(Title.title(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, title), titleValues), replacePlaceholders(PlaceholderAPI.setPlaceholders(player, subtitle), subtitleValues)));
    }

    public static String getMessage(String message) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<white>" + message));
    }

    public static List<String> getMessages(List<String> messages) {
        List<String> serializedMessages = new ArrayList<>();
        if (messages == null) return serializedMessages;
        for (String message : messages)
            serializedMessages.add(LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<white>" + message)));
        return serializedMessages;
    }

    public static Component getComponent(String message) {
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public static String getMessage(Player player, String message, String... values) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, message), values));
    }

    public static void playSound(Player player, String soundName) {
        player.playSound(player, Sound.valueOf(soundName.toUpperCase()), 1F, 1F);
    }
}