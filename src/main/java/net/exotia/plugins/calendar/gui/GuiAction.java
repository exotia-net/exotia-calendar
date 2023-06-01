package net.exotia.plugins.calendar.gui;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Builder
@Getter
public class GuiAction extends OkaeriConfig {
    private String name;
    private String value;

    public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
        ActionType.valueOf(name.toUpperCase()).runAction(player, configurationGui, configurationMessage, value);
    }

    public enum ActionType {
        OPEN {
            public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value) {
                GuiType.valueOf(value.toUpperCase()).open(player, configurationGui, configurationMessage);
            }
        },
        GIVE_ITEM {
            public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value) {
                player.getInventory().addItem(new ItemStack(Material.valueOf(value.toUpperCase())));
            }
        };

        public abstract void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value);
    }
}