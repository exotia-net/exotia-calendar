package net.exotia.plugins.calendar.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import net.exotia.plugins.calendar.utils.UtilItem;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Builder
@Getter
public class GuiButton extends OkaeriConfig {
    private String id;
    private String type;
    private String displayName;
    private List<String> lore;

    public GuiItem getButton(Player player, String sound, String displayName, List<String> lore) {
        return ItemBuilder.from(UtilItem.getItem(id, type, displayName, lore, 1)).asGuiItem(event -> UtilMessage.playSound(player, sound));
    }

    public ItemStack getItem(String displayName, List<String> lore) {
        return UtilItem.getItem(id, type, displayName, lore, 1);
    }
}
