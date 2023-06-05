package net.exotia.plugins.calendar.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.calendar.calendar.player.CalendarPlayer;
import net.exotia.plugins.calendar.calendar.player.CalendarPlayers;
import net.exotia.plugins.calendar.calendar.rewards.Reward;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationRewards;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class GuiCalendar {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private ConfigurationRewards configurationRewards;
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
        List<Integer> slotsStreak = guiTemplate.getSlotsStreak();
        HashMap<String, GuiButton> buttons = guiTemplate.getButtons();
        int step = calendarPlayer.getStep();
        int streakDays = calendarPlayer.getStreakDays();

        GuiButton expired = buttons.get("wygasly");
        GuiButton normalNotObtained = buttons.get("zwykly_zamkniety");
        GuiButton normalObtained = buttons.get("zwykly_otwarty");
        GuiButton bonusNotObtained = buttons.get("bonusowy_zamkniety");
        GuiButton bonusObtained = buttons.get("bonusowy_otwarty");
        GuiButton streakActive = buttons.get("streak_aktywny");
        GuiButton streakNotActive = buttons.get("streak_wygasly");

        if (!calendarPlayer.canObtain()) gui.setItem(slotsRewards.get(step), expired.getButton(player, sounds.getError(), UtilMessage.convertComponent(expired.getDisplayName()), UtilMessage.convertComponent(expired.getLore(), "Brak")));
        else {
            gui.setItem(slotsRewards.get(step), ItemBuilder.from(normalNotObtained.getItem(UtilMessage.convertComponent(normalNotObtained.getDisplayName(), String.valueOf(step + 1)), UtilMessage.convertComponent(normalNotObtained.getLore(), "Brak"))).asGuiItem(event -> {
                for (Reward reward : configurationRewards.getRewards().get(step))
                    reward.rewardPlayer(player);
                UtilMessage.playSound(player, sounds.getActivate());
                UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getObtain(), String.valueOf(step + 1));
                gui.updateItem(slotsRewards.get(step), normalObtained.getButton(player, sounds.getStep(), UtilMessage.convertComponent(normalObtained.getDisplayName(), String.valueOf(step + 1)), UtilMessage.convertComponent(normalNotObtained.getLore(), "Brak")));
                gui.updateItem(slotsStreak.get(streakDays), streakActive.getButton(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
            }));
        }

        for (int i = 0; i < step; i++) gui.setItem(slotsRewards.get(i), normalObtained.getButton(player, sounds.getStep(), UtilMessage.convertComponent(normalObtained.getDisplayName(), String.valueOf(i+1)), UtilMessage.convertComponent(normalObtained.getLore(), "Brak")));
        for (int i = step + 1; i < slotsRewards.size(); i++) gui.setItem(slotsRewards.get(i), expired.getButton(player, sounds.getError(), UtilMessage.convertComponent(expired.getDisplayName(), String.valueOf(i+1)), UtilMessage.convertComponent(expired.getLore(), "Brak")));
        gui.setItem(slotsRewards.get(slotsRewards.size() - 1), expired.getButton(player, sounds.getError(), UtilMessage.convertComponent(expired.getDisplayName(), String.valueOf(slotsRewards.size() - 1)), UtilMessage.convertComponent(expired.getLore(), "Brak")));

        if (streakDays == slotsRewards.size() - 2 && step == 5) {
            gui.setItem(slotsRewards.get(slotsRewards.size() - 1), ItemBuilder.from(bonusNotObtained.getItem(UtilMessage.convertComponent(bonusNotObtained.getDisplayName()), UtilMessage.convertComponent(bonusNotObtained.getLore(), "Brak"))).asGuiItem(event -> {
                UtilMessage.playSound(player, sounds.getActivate());
                gui.updateItem(slotsRewards.get(slotsRewards.size() - 1), bonusObtained.getButton(player, sounds.getStep(), UtilMessage.convertComponent(bonusObtained.getDisplayName()), UtilMessage.convertComponent(bonusObtained.getLore(), "Brak")));
                calendarPlayer.addStreak();
            }));
        }
        else if (streakDays == slotsRewards.size() - 1) gui.setItem(slotsRewards.get(slotsRewards.size() - 1), bonusObtained.getButton(player, sounds.getStep(), UtilMessage.convertComponent(bonusObtained.getDisplayName()), UtilMessage.convertComponent(bonusObtained.getLore(), "Brak")));
        else gui.setItem(slotsRewards.get(slotsRewards.size() - 1), expired.getButton(player, sounds.getError(), UtilMessage.convertComponent(bonusNotObtained.getDisplayName(), String.valueOf(slotsRewards.size() - 1)), UtilMessage.convertComponent(expired.getLore(), "Brak")));

        if (streakDays == slotsStreak.size()) {
            gui.updateTitle(UtilMessage.getString(guiTemplate.getAlternateTitle()));
            for (int slot : slotsStreak) gui.setItem(slot, streakActive.getButton(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
        }
        else {
            for (int slot : slotsStreak) gui.setItem(slot, streakNotActive.getButton(player, sounds.getError(), UtilMessage.convertComponent(streakNotActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakNotActive.getLore())));
            for (int i = 0; i < streakDays; i++) gui.setItem(slotsStreak.get(i), streakActive.getButton(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
        }
    }
}