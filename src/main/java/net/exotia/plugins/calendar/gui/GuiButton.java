package net.exotia.plugins.calendar.gui;

import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Builder
@Getter
@Setter
public class GuiButton extends OkaeriConfig {
    private List<Integer> slots;
    private String oraxenID;
    private String displayName;
    private List<String> lore;
    private GuiAction action;

    public ItemStack getItem() {
        ItemStack item = OraxenItems.getItemById(oraxenID) != null ? OraxenItems.getItemById(oraxenID).build() : new ItemStack(Material.AIR);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return new ItemStack(Material.AIR);
        meta.setDisplayName(UtilMessage.getMessage(displayName));
        meta.setLore(UtilMessage.getMessages(lore));
        item.setItemMeta(meta);
        return item;
    }
}
