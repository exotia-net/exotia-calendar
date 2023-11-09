package net.exotia.plugins.calendar.utils;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UtilMessage {
    private static final HashMap<UUID, BossBar> bossBars = new HashMap<>();

    private static Component replacePlaceholders(String message, String... values) {
        for (int i = 1; i <= values.length; i++) message = message.replace("%value_" + i + "%", values[i - 1]);
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public static void sendMessage(CommandSender sender, String message, String... values) {
        sender.sendMessage(replacePlaceholders(message, values));
    }

    public static void playSound(Player player, String soundName) {
        player.playSound(player, Sound.valueOf(soundName.toUpperCase()), 1F, 1F);
    }
}