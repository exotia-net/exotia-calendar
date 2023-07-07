package net.exotia.plugins.calendar.calendar.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.bridge.api.entities.CalendarUser;
import net.exotia.plugins.calendar.calendar.ServiceCalendar;
import net.exotia.plugins.calendar.calendar.rewards.Reward;
import net.exotia.plugins.calendar.configuration.ConfigurationGui;
import net.exotia.plugins.calendar.configuration.ConfigurationMessage;
import net.exotia.plugins.calendar.configuration.ConfigurationRewards;
import net.exotia.plugins.calendar.configuration.section.SectionCalendar;
import net.exotia.plugins.calendar.utils.UtilMessage;
import org.bukkit.Material;
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

    public void setupGui(BaseGui gui, List<Integer> emptySlots) {
        GuiItem filling = ItemBuilder.from(Material.AIR).asGuiItem();
        gui.setItem(emptySlots, filling);
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> UtilMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
    }

    public void open(Player player) {
        ConfigurationMessage.Sounds sounds = configurationMessage.getSounds();
        SectionCalendar guiConfiguration = configurationGui.getGuiCalendar();
        Gui gui = Gui.gui().title(UtilMessage.getComponent("<white>" + guiConfiguration.getTitle())).rows(guiConfiguration.getSize()).create();
        CalendarUser calendarUser = ServiceCalendar.getCalendar(player);
        HashMap<String, GuiButton> buttons = guiConfiguration.getButtons();
        HashMap<Integer, List<Reward>> rewards = configurationRewards.getRewards();
        List<Integer> slotsRewards = guiConfiguration.getSlotsRewards();
        List<Integer> slotsStreak = guiConfiguration.getSlotsStreak();
        int streakDays = calendarUser.getStreakDays();

        if (calendarUser.getStep() == slotsRewards.size() && calendarUser.getNotObtainedRewards().isEmpty()) {
            UtilMessage.playSound(player, sounds.getActivate());
            UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getFinished());
            return;
        }

        setupGui(gui, guiConfiguration.getSlotsEmpty());

        HashMap<Integer, List<String>> itemNames = new HashMap<>();

        for (int index : rewards.keySet()) {
            List<String> itemList = rewards.get(index).stream()
                    .map(reward -> "- " + reward.getDisplayName())
                    .collect(Collectors.toList());
            itemNames.put(index, itemList);
        }

        for (int i = 0; i < slotsRewards.size() - 1; i++) gui.setItem(slotsRewards.get(i), buttons.get("basic_not_active").getGuiItem(player, sounds.getError(), itemNames.get(i), String.valueOf(i + 1)));
        for (int i = 0; i <= calendarUser.getStep() - 1; i++) gui.setItem(slotsRewards.get(i), buttons.get("basic_opened").getGuiItem(player, sounds.getStep(), itemNames.get(i), String.valueOf(i + 1)));

        slotsStreak.forEach(slot -> gui.setItem(slot, buttons.get("streak_not_active").getGuiItem(player, sounds.getStep(), new ArrayList<>(), String.valueOf(streakDays))));
        for (int i = 0; i < streakDays; i++) gui.setItem(slotsStreak.get(i), buttons.get("streak_active").getGuiItem(player, sounds.getStep(), new ArrayList<>(), String.valueOf(streakDays)));

        if (calendarUser.getNotObtainedRewards() != null) {
            for (int i = 0; i < calendarUser.getNotObtainedRewards().size(); i++) {
                int index = i;
                int slot = calendarUser.getNotObtainedRewards().get(index);
                gui.setItem(slotsRewards.get(slot), ItemBuilder.from(buttons.get("basic_closed").getItem(itemNames.get(index), String.valueOf(slot + 1))).asGuiItem(event -> {
                    UtilMessage.playSound(player, sounds.getActivate());
                    UtilMessage.sendMessage(player, configurationMessage.getCommandsCalendar().getObtain(), String.valueOf(slot + 1));
                    for (Reward reward : rewards.get(index)) reward.rewardPlayer(player);
                    gui.updateItem(slotsRewards.get(slot), buttons.get("basic_opened").getGuiItem(player, sounds.getStep(), itemNames.get(index), String.valueOf(index + 1)));
                    calendarUser.removeNotObtained(index);
                    ServiceCalendar.saveCalendar(player);
                }));
            }
        }

        int slot = slotsRewards.get(slotsRewards.size() - 1);

        if (streakDays > slotsStreak.size()) {
            gui.setItem(slot, buttons.get("bonus_opened").getGuiItem(player, sounds.getStep(), itemNames.get(slotsRewards.size() - 1)));
            return;
        }

        if (streakDays != slotsStreak.size()) {
            gui.setItem(slot, buttons.get("bonus_not_active").getGuiItem(player, sounds.getError(), itemNames.get(slotsRewards.size() - 1)));
            gui.open(player);
            return;
        }

        gui.setItem(slot, ItemBuilder.from(buttons.get("bonus_closed").getItem(itemNames.get(slotsRewards.size() - 1))).asGuiItem(event -> {
            UtilMessage.playSound(player, sounds.getSuccess());
            gui.updateItem(slot, buttons.get("bonus_opened").getGuiItem(player, sounds.getStep(), itemNames.get(slotsRewards.size() - 1)));
            calendarUser.addStreak();
            ServiceCalendar.saveCalendar(player);
        }));

        gui.open(player);
    }
}