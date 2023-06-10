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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        int streakDays = calendarPlayer.getStreakDays();

        if (calendarPlayer.getStep() == slotsRewards.size() && calendarPlayer.getNotObtainedRewards().isEmpty()) {
            UtilMessage.playSound(player, sounds.getActivate());
            UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getFinished());
            return;
        }

        GuiTemplate.setupGui(gui, guiTemplate.getSlotsEmpty(), configurationMessage);

        HashMap<Integer, List<String>> itemNames = new HashMap<>();

        for (int index : rewards.keySet()) {
            List<String> itemList = rewards.get(index).stream()
                    .map(reward -> "- " + reward.getDisplayName())
                    .collect(Collectors.toList());
            itemNames.put(index, itemList);
        }

        for (int i = 0; i < slotsRewards.size() - 1; i++) gui.setItem(slotsRewards.get(i), buttons.get("zwykly_wygasly").getGuiItem(player, sounds.getError(), itemNames.get(i), String.valueOf(i + 1)));
        for (int i = 0; i <= calendarPlayer.getStep() - 1; i++) gui.setItem(slotsRewards.get(i), buttons.get("zwykly_otwarty").getGuiItem(player, sounds.getStep(), itemNames.get(i), String.valueOf(i + 1)));

        slotsStreak.forEach(slot -> gui.setItem(slot, buttons.get("streak_wygasly").getGuiItem(player, sounds.getStep(), new ArrayList<>(), String.valueOf(streakDays))));
        for (int i = 0; i < streakDays; i++) gui.setItem(slotsStreak.get(i), buttons.get("streak_aktywny").getGuiItem(player, sounds.getStep(), new ArrayList<>(), String.valueOf(streakDays)));

        for (int i = 0; i < calendarPlayer.getNotObtainedRewards().size(); i++) {
            int index = i;
            int slot = calendarPlayer.getNotObtainedRewards().get(index);
            gui.setItem(slotsRewards.get(slot), ItemBuilder.from(buttons.get("zwykly_zamkniety").getItem(itemNames.get(index), String.valueOf(slot + 1))).asGuiItem(event -> {
                UtilMessage.playSound(player, sounds.getActivate());
                UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getObtain(), String.valueOf(slot + 1));
                for (Reward reward : rewards.get(index)) reward.rewardPlayer(player);
                gui.updateItem(slotsRewards.get(slot), buttons.get("zwykly_otwarty").getGuiItem(player, sounds.getStep(), itemNames.get(index), String.valueOf(index + 1)));
                calendarPlayer.removeNotObtained(index);
            }));
        }

        int slot = slotsRewards.get(slotsRewards.size() - 1);

        if (streakDays > slotsStreak.size()) {
            gui.setItem(slot, buttons.get("bonusowy_otwarty").getGuiItem(player, sounds.getStep(), itemNames.get(slotsRewards.size() - 1)));
            return;
        }

        if (streakDays != slotsStreak.size()) {
            gui.setItem(slot, buttons.get("bonusowy_wygasly").getGuiItem(player, sounds.getError(), itemNames.get(slotsRewards.size() - 1)));
            gui.open(player);
            return;
        }

        gui.setItem(slot, ItemBuilder.from(buttons.get("bonusowy_zamkniety").getItem(itemNames.get(slotsRewards.size() - 1))).asGuiItem(event -> {
            UtilMessage.playSound(player, sounds.getSuccess());
            gui.updateItem(slot, buttons.get("bonusowy_otwarty").getGuiItem(player, sounds.getStep(), itemNames.get(slotsRewards.size() - 1)));
            calendarPlayer.addStreak();
        }));
        gui.open(player);
    }
}