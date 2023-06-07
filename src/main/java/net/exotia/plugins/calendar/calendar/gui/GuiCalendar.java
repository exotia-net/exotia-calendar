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
        ConfigurationMessage.Sounds sounds = configurationMessage.getSounds();
        GuiTemplate guiTemplate = configurationGui.getGuis().get("calendar");
        Gui gui = Gui.gui().title(UtilMessage.getComponent("<white>" + guiTemplate.getTitle())).rows(guiTemplate.getSize()).create();
        CalendarPlayer calendarPlayer = calendarPlayers.getPlayer(player.getUniqueId());
        HashMap<String, GuiButton> buttons = guiTemplate.getButtons();
        HashMap<Integer, List<Reward>> rewards = configurationRewards.getRewards();
        List<Integer> slotsRewards = guiTemplate.getSlotsRewards();
        List<Integer> slotsStreak = guiTemplate.getSlotsStreak();

        GuiTemplate.setupGui(gui, guiTemplate.getSlotsEmpty(), configurationMessage);

        for (int i = 0; i < slotsRewards.size() - 1; i++) gui.setItem(slotsRewards.get(i), buttons.get("zwykly_otwarty").getGuiItem(player, sounds.getStep()));

        for (int i = 0; i < calendarPlayer.getNotObtainedRewards().size(); i++) {
            int slot = i;
            gui.setItem(slotsRewards.get(slot), ItemBuilder.from(buttons.get("zwykly_zamkniety").getItem()).asGuiItem(event -> {
                for (Reward reward : rewards.get(slot)) reward.rewardPlayer(player);
                gui.updateItem(slotsRewards.get(slot), ItemBuilder.from(buttons.get("zwykly_otwarty").getItem()).asGuiItem());
                calendarPlayer.removeNotObtained(slot);
            }));
        }



        gui.open(player);
    }

    public void fill() {
//        ConfigurationMessage.Sounds sounds = configurationMessage.getSounds();
//        int step = calendarPlayer.getStep();
//        int streakDays = calendarPlayer.getStreakDays();
//        List<Integer> notObtainedRewards =
//
//        GuiButton expired = buttons.get("wygasly");
//        GuiButton normalNotObtained = buttons.get("zwykly_zamkniety");
//        GuiButton normalObtained = buttons.get("zwykly_otwarty");
//        GuiButton bonusNotObtained = buttons.get("bonusowy_zamkniety");
//        GuiButton bonusObtained = buttons.get("bonusowy_otwarty");
//        GuiButton streakActive = buttons.get("streak_aktywny");
//        GuiButton streakNotActive = buttons.get("streak_wygasly");
//
//        if (!calendarPlayer.canObtain()) gui.setItem(slotsRewards.get(step), expired.getGuiItem(player, sounds.getError(), UtilMessage.convertComponent(expired.getDisplayName()), UtilMessage.convertComponent(expired.getLore(), "Brak")));
//        else {
//            calendarPlayer.addNotObtained(step);
//            gui.setItem(slotsRewards.get(step), ItemBuilder.from(normalNotObtained.getItem(UtilMessage.convertComponent(normalNotObtained.getDisplayName(), String.valueOf(step + 1)), UtilMessage.convertComponent(normalNotObtained.getLore(), "Brak"))).asGuiItem(event -> {
//                calendarPlayer.removeNotObtained(step);
//                for (Reward reward : configurationRewards.getRewards().get(step))
//                    reward.rewardPlayer(player);
//                UtilMessage.playSound(player, sounds.getActivate());
//                UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getObtain(), String.valueOf(step + 1));
//                gui.updateItem(slotsRewards.get(step), normalObtained.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(normalObtained.getDisplayName(), String.valueOf(step + 1)), UtilMessage.convertComponent(normalNotObtained.getLore(), "Brak")));
//                gui.updateItem(slotsStreak.get(streakDays), streakActive.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
//            }));
//        }
//
//        for (int i = 0; i < step; i++) gui.setItem(slotsRewards.get(i), normalObtained.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(normalObtained.getDisplayName(), String.valueOf(i+1)), UtilMessage.convertComponent(normalObtained.getLore(), "Brak")));
//        for (int i = step + 1; i < slotsRewards.size(); i++) gui.setItem(slotsRewards.get(i), expired.getGuiItem(player, sounds.getError(), UtilMessage.convertComponent(expired.getDisplayName(), String.valueOf(i+1)), UtilMessage.convertComponent(expired.getLore(), "Brak")));
//        gui.setItem(slotsRewards.get(slotsRewards.size() - 1), expired.getGuiItem(player, sounds.getError(), UtilMessage.convertComponent(expired.getDisplayName(), String.valueOf(slotsRewards.size() - 1)), UtilMessage.convertComponent(expired.getLore(), "Brak")));
//
//        if (streakDays == slotsRewards.size() - 2 && step == 5) {
//            gui.setItem(slotsRewards.get(slotsRewards.size() - 1), ItemBuilder.from(bonusNotObtained.getItem(UtilMessage.convertComponent(bonusNotObtained.getDisplayName()), UtilMessage.convertComponent(bonusNotObtained.getLore(), "Brak"))).asGuiItem(event -> {
//                UtilMessage.playSound(player, sounds.getActivate());
//                gui.updateItem(slotsRewards.get(slotsRewards.size() - 1), bonusObtained.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(bonusObtained.getDisplayName()), UtilMessage.convertComponent(bonusObtained.getLore(), "Brak")));
//                calendarPlayer.addStreak();
//            }));
//        }
//        else if (streakDays == slotsRewards.size() - 1) gui.setItem(slotsRewards.get(slotsRewards.size() - 1), bonusObtained.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(bonusObtained.getDisplayName()), UtilMessage.convertComponent(bonusObtained.getLore(), "Brak")));
//        else gui.setItem(slotsRewards.get(slotsRewards.size() - 1), expired.getGuiItem(player, sounds.getError(), UtilMessage.convertComponent(bonusNotObtained.getDisplayName(), String.valueOf(slotsRewards.size() - 1)), UtilMessage.convertComponent(expired.getLore(), "Brak")));
//
//        if (streakDays == slotsStreak.size()) {
//            gui.updateTitle(UtilMessage.getString(guiTemplate.getAlternateTitle()));
//            for (int slot : slotsStreak) gui.setItem(slot, streakActive.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
//        }
//        else {
//            for (int slot : slotsStreak) gui.setItem(slot, streakNotActive.getGuiItem(player, sounds.getError(), UtilMessage.convertComponent(streakNotActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakNotActive.getLore())));
//            for (int i = 0; i < streakDays; i++) gui.setItem(slotsStreak.get(i), streakActive.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
//        }
//
//        for (int slot : notObtainedRewards) {
//            gui.setItem(slotsRewards.get(slot), ItemBuilder.from(normalNotObtained.getItem(UtilMessage.convertComponent(normalNotObtained.getDisplayName(), String.valueOf(step + 1)), UtilMessage.convertComponent(normalNotObtained.getLore(), "Brak"))).asGuiItem(event -> {
//                calendarPlayer.removeNotObtained(slot);
//                for (Reward reward : configurationRewards.getRewards().get(slot))
//                    reward.rewardPlayer(player);
//                UtilMessage.playSound(player, sounds.getActivate());
//                UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getObtain(), String.valueOf(step + 1));
//                gui.updateItem(slotsRewards.get(slot), normalObtained.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(normalObtained.getDisplayName(), String.valueOf(step + 1)), UtilMessage.convertComponent(normalNotObtained.getLore(), "Brak")));
//                gui.updateItem(slotsStreak.get(streakDays), streakActive.getGuiItem(player, sounds.getStep(), UtilMessage.convertComponent(streakActive.getDisplayName(), String.valueOf(streakDays)), UtilMessage.convertComponent(streakActive.getLore())));
//            }));
//        }
    }
}