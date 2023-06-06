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

    public void addRewards(List<String> rewards) {
        lore.addAll(rewards);
    }

    public ItemStack getItem(String... placeholders) {
        return UtilItem.getItem(id, type, UtilMessage.convertComponent(displayName, placeholders), UtilMessage.convertComponent(lore, placeholders), 1);
    }

    public GuiItem getGuiItem(Player player, String sound, String... placeholders) {
        return ItemBuilder.from(UtilItem.getItem(id, type, UtilMessage.convertComponent(displayName, placeholders), UtilMessage.convertComponent(lore, placeholders), 1)).asGuiItem(event -> UtilMessage.playSound(player, sound));
    }
}
