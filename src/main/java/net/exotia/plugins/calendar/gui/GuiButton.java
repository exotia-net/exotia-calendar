package net.exotia.plugins.calendar.gui;

import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Builder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Builder
public class GuiButton extends OkaeriConfig {
    private String id;
    private String type;

    public ItemStack getItem() {
        if (type.equalsIgnoreCase("minecraft")) return new ItemStack(Material.valueOf(id.toUpperCase()));
        return OraxenItems.getItemById(id).build();
    }
}
