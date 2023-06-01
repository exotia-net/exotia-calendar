package net.exotia.plugins.calendar.gui;

import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.gui.inventory.GuiPaginated;
import net.exotia.plugins.calendar.gui.inventory.GuiStatic;
import org.bukkit.entity.Player;

public enum GuiType {
    CALENDAR {
        public void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
            GuiStatic.openStatic(this.name().toLowerCase(), player, configurationGui, configurationMessage);
        }
    };

    public abstract void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage);
}