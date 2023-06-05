package net.exotia.plugins.calendar.utils;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class UtilItem {
    public static ItemStack getItem(String id, String type, String displayName, List<String> lore, int amount) {
        ItemStack item = (type.equalsIgnoreCase("minecraft")) ? new ItemStack(Material.valueOf(id.toUpperCase())) : OraxenItems.getItemById(id).build();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(amount);
        return item;
    }
}
