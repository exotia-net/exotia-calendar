package net.exotia.plugins.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.CalendarPlayer;
import net.exotia.plugins.calendar.calendar.CalendarPlayers;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class GuiCalendar {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private CalendarPlayers calendarPlayers;

    public void open(Player player) {
        GuiTemplate guiTemplate = configurationGui.getGuis().get("calendar");
        Gui gui = Gui.gui().title(UtilMessage.getComponent("<white>" + guiTemplate.getTitle())).rows(guiTemplate.getSize()).create();

        GuiTemplate.setupGui(gui, guiTemplate.getSlotsEmpty(), configurationMessage);
        fill(gui, guiTemplate, player);

        gui.open(player);
    }

    public void fill(Gui gui, GuiTemplate guiTemplate, Player player) {
        CalendarPlayer calendarPlayer = calendarPlayers.getPlayer(player.getUniqueId());
        ConfigurationMessage.Sounds sounds = configurationMessage.getSounds();
        List<Integer> slotsRewards = guiTemplate.getSlotsRewards();
        HashMap<String, GuiButton> buttons = guiTemplate.getButtons();
        int step = calendarPlayer.getStep();
        int streakDays = calendarPlayer.getStreakDays();

        Bukkit.broadcastMessage("Step: " + step); // REMOVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        Bukkit.broadcastMessage("Streak: " + streakDays); // REMOVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE

        if (!calendarPlayer.stepUp()) gui.setItem(slotsRewards.get(step), buttons.get("wygasly").getButton(player, sounds.getError()));
        else {
            gui.setItem(slotsRewards.get(step), ItemBuilder.from(buttons.get("zwykly_zamkniety").getItem()).asGuiItem(event -> {
                UtilMessage.playSound(player, sounds.getActivate());
                gui.updateItem(slotsRewards.get(step), buttons.get("zwykly_otwarty").getButton(player, sounds.getStep()));
                calendarPlayer.addStep();
                calendarPlayer.setLastObtained(Instant.now().toEpochMilli() - 86400000); // REMOVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
            }));
        }

        for (int i = 0; i < step; i++) gui.setItem(slotsRewards.get(i), buttons.get("zwykly_otwarty").getButton(player, sounds.getStep()));
        for (int i = step + 1; i < slotsRewards.size(); i++) gui.setItem(slotsRewards.get(i), ItemBuilder.from(buttons.get("wygasly").getItem()).asGuiItem(event -> UtilMessage.playSound(player, sounds.getError())));

        if (step >= slotsRewards.size() - 2 && step < slotsRewards.size()) {
            if (streakDays == slotsRewards.size() - 3) {
                gui.setItem(slotsRewards.get(slotsRewards.size() - 1), ItemBuilder.from(buttons.get("bonusowy_zamkniety").getItem()).asGuiItem(event -> {
                    UtilMessage.playSound(player, sounds.getActivate());
                    gui.updateItem(slotsRewards.get(slotsRewards.size() - 1), buttons.get("bonusowy_otwarty").getButton(player, sounds.getStep()));
                }));
            }
            else if (streakDays == slotsRewards.size() - 2) gui.setItem(slotsRewards.get(slotsRewards.size() - 1), buttons.get("bonusowy_otwarty").getButton(player, sounds.getStep()));
            else gui.setItem(slotsRewards.get(slotsRewards.size() - 1), buttons.get("wygasly").getButton(player, sounds.getError()));
        }
        else gui.setItem(slotsRewards.get(slotsRewards.size() - 1), buttons.get("wygasly").getButton(player, sounds.getError()));

        fillStreak(gui, guiTemplate, player);
    }

    public void fillStreak(Gui gui, GuiTemplate guiTemplate, Player player) {
        ConfigurationMessage.Sounds sounds = configurationMessage.getSounds();
        List<Integer> slotsStreak = guiTemplate.getSlotsStreak();
        HashMap<String, GuiButton> buttons = guiTemplate.getButtons();
        int streakDays = calendarPlayers.getPlayer(player.getUniqueId()).getStreakDays();

        if (streakDays == slotsStreak.size()) {
            gui.updateTitle(UtilMessage.getString(guiTemplate.getAlternateTitle()));
            for (int slot : slotsStreak) {
                gui.setItem(slot, buttons.get("streak_aktywny").getButton(player, sounds.getStep()));
            }
            return;
        }

        for (int slot : slotsStreak) gui.setItem(slot, buttons.get("streak_wygasly").getButton(player, sounds.getError()));
        for (int i = 0; i <= streakDays; i++) gui.setItem(slotsStreak.get(i), buttons.get("streak_aktywny").getButton(player, sounds.getStep()));
    }
}