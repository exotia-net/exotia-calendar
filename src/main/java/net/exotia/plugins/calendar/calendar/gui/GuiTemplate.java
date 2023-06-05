package net.exotia.plugins.calendar.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

@Getter
public class GuiTemplate extends OkaeriConfig {
    private String title;
    private String alternateTitle;
    private int size;
    private List<Integer> slotsEmpty;
    private List<Integer> slotsRewards;
    private List<Integer> slotsStreak;
    private HashMap<String, GuiButton> buttons;

    public static void setupGui(BaseGui gui, List<Integer> emptySlots, ConfigurationMessage configurationMessage) {
        GuiItem filling = ItemBuilder.from(Material.AIR).asGuiItem();
        gui.setItem(emptySlots, filling);
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> UtilMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
    }
}