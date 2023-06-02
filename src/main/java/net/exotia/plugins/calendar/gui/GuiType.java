package net.exotia.plugins.calendar.gui;

import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.gui.inventory.GuiCalendar;
import net.exotia.plugins.calendar.utils.UtilPlayer;
import org.bukkit.entity.Player;

public enum GuiType {
    CALENDAR {
        public void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, UtilPlayer utilPlayer) {
            GuiCalendar.openCalendar(this.name().toLowerCase(), player, configurationGui, configurationMessage, utilPlayer);
        }
    };

    public abstract void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, UtilPlayer utilPlayer);
}