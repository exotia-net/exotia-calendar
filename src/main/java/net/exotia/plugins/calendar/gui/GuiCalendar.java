package net.exotia.plugins.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import net.exotia.plugins.calendar.calendar.CalendarPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class GuiCalendar {
    public static void openCalendar(Player player, CalendarPlayer calendarPlayer, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
        GuiTemplate guiTemplate = configurationGui.getGuis().get("calendar");
        Gui gui = Gui.gui().title(UtilMessage.getComponent("<white>" + guiTemplate.getTitle())).rows(guiTemplate.getSize()).create();
        GuiTemplate.setupGui(gui, guiTemplate.getSlotsEmpty(), configurationMessage);
        fillInventory(gui, guiTemplate, calendarPlayer, configurationMessage);
        gui.open(player);
    }

    public static void fillInventory(Gui gui, GuiTemplate guiTemplate, CalendarPlayer calendarPlayer, ConfigurationMessage configurationMessage) {
        List<Integer> slots = guiTemplate.getSlotsRewards();
        HashMap<String, GuiButton> buttons = guiTemplate.getButtons();
        int step = calendarPlayer.getStep();
        gui.setItem(slots.get(step), ItemBuilder.from(buttons.get("zwykly_zamkniety").getItem()).asGuiItem(event -> {
            if (!calendarPlayer.stepUp()) return;
            gui.update();
            UtilMessage.playSound((Player) event.getWhoClicked(), configurationMessage.getSounds().getSuccess());
        }));
        for (int i = 0; i < step; i++)
            gui.setItem(slots.get(i), ItemBuilder.from(buttons.get("zwykly_otwarty").getItem()).asGuiItem());
        for (int i = step + 1; i < slots.size(); i++)
            gui.setItem(slots.get(i), ItemBuilder.from(buttons.get("wygasly").getItem()).asGuiItem(event -> {
                UtilMessage.playSound((Player) event.getWhoClicked(), configurationMessage.getSounds().getError());
            }));
        if (step != slots.size()) gui.setItem(slots.get(slots.size() - 1), ItemBuilder.from(buttons.get("wygasly").getItem()).asGuiItem());
        else gui.setItem(slots.get(slots.size() - 1), ItemBuilder.from(buttons.get("bonusowy_zamkniety").getItem()).asGuiItem());
    }
}
