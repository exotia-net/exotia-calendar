package net.exotia.plugins.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Builder;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Builder
public class GuiButton extends OkaeriConfig {
    private String id;
    private String type;

    public GuiItem getButton(Player player, String sound) {
        return ItemBuilder.from(getItem()).asGuiItem(event -> UtilMessage.playSound(player, sound));
    }

    public ItemStack getItem() {
        if (type.equalsIgnoreCase("minecraft")) return new ItemStack(Material.valueOf(id.toUpperCase()));
        return OraxenItems.getItemById(id).build();
    }
}
