package net.exotia.plugins.calendar.gui.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.gui.GuiButton;
import net.exotia.plugins.calendar.gui.GuiInventory;
import net.exotia.plugins.calendar.utils.UtilMessage;
import net.exotia.plugins.calendar.utils.UtilPlayer;
import net.exotia.plugins.calendar.utils.UtilValidate;
import org.bukkit.entity.Player;

import java.util.List;

public class GuiCalendar {
    public static void openCalendar(String name, Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, UtilPlayer utilPlayer) {
        Gui gui = Gui.gui().title(UtilMessage.getComponent("<white>" + configurationGui.getGuis().get(name).getTitle())).rows(configurationGui.getGuis().get(name).getSize()).create();
        List<Integer> slots = configurationGui.getGuis().get(name).getSlotsRewards();
        GuiInventory.setupGui(name, gui, configurationGui, configurationMessage);
        int iteration = 0;
        for (int slot : slots) {
            iteration++;
            GuiButton button = buttons.get(key);
            if (utilPlayer.getStep() == iteration)
            for (int slot : button.getSlots()) {
                if (!UtilValidate.validateReward()) return gui.setItem(slot, ItemBuilder.);
                gui.setItem(slot, ItemBuilder.from(button.getItem()).asGuiItem(event -> button.getAction().runAction((Player) event.getWhoClicked(), configurationGui, configurationMessage)));
            }
        }
        gui.open(player);
    }
}
