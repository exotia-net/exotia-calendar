package net.exotia.plugins.calendar.configuration.section;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import net.exotia.plugins.calendar.calendar.gui.GuiButton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public class SectionCalendar extends OkaeriConfig {
    private String title = "✟ꡝ";
    private int size = 2;
    private List<Integer> slotsEmpty = Arrays.asList(0, 6, 8, 9, 10, 16, 17);
    private List<Integer> slotsRewards = Arrays.asList(1, 2, 3, 4, 5, 7);
    private List<Integer> slotsStreak = Arrays.asList(10, 11, 12, 13, 14);
    private HashMap<String, GuiButton> buttons = setupButtons();

    private HashMap<String, GuiButton> setupButtons() {
        HashMap<String, GuiButton> buttons = new HashMap<>();
        buttons.put("basic_not_active", GuiButton.builder()
                .id("button_gift_not_active")
                .type("oraxen")
                .displayName("Nagrody <gradient:#4fa943:#9ec52f><bold>dnia %value_1%!</bold></gradient>")
                .lore(Arrays.asList("<gradient:#4fa943:#9ec52f><bold>Niedostępne</bold></gradient>", "Zaloguj się przez", "następne dni,", "aby je uzyskać", "<gradient:#4fa943:#9ec52f><bold>Zawiera:</bold></gradient>"))
                .build()
        );
        buttons.put("basic_opened", GuiButton.builder()
                .id("button_gift_basic_opened")
                .type("oraxen")
                .displayName("Nagrody <gradient:#4fa943:#9ec52f><bold>dnia %value_1%!</bold></gradient>")
                .lore(Arrays.asList("<gradient:#4fa943:#9ec52f><bold>Odebrane</bold></gradient>", "Odebrałeś/aś już", "te nagrody!", "<gradient:#4fa943:#9ec52f><bold>Zawiera:</bold></gradient>"))
                .build()
        );
        buttons.put("basic_closed", GuiButton.builder()
                .id("button_gift_basic_closed")
                .type("oraxen")
                .displayName("Nagrody <gradient:#4fa943:#9ec52f><bold>dnia %value_1%!</bold></gradient>")
                .lore(Arrays.asList("<gradient:#4fa943:#9ec52f><bold>Do odebrania</bold></gradient>", "Kliknij, aby odebrać", "te nagrody!", "<gradient:#4fa943:#9ec52f><bold>Zawiera:</bold></gradient>"))
                .build()
        );
        buttons.put("bonus_not_active", GuiButton.builder()
                .id("button_gift_not_active")
                .type("oraxen")
                .displayName("Nagrody <gradient:#7776ff:#ae2cf1><bold>bonusowe!</bold></gradient>")
                .lore(Arrays.asList("<gradient:#4fa943:#9ec52f><bold>Niedostępne</bold></gradient>", "Loguj się przez", "cały tydzień", "aby je uzyskać", "<gradient:#4fa943:#9ec52f><bold>Zawiera:</bold></gradient>"))
                .build()
        );
        buttons.put("bonus_opened", GuiButton.builder()
                .id("button_gift_bonus_opened")
                .type("oraxen")
                .displayName("Nagrody <gradient:#7776ff:#ae2cf1><bold>bonusowe!</bold></gradient>")
                .lore(Arrays.asList("<gradient:#4fa943:#9ec52f><bold>Odebrane</bold></gradient>", "Odebrałeś/aś już", "te nagrody!", "<gradient:#4fa943:#9ec52f><bold>Zawiera:</bold></gradient>"))
                .build()
        );
        buttons.put("bonus_closed", GuiButton.builder()
                .id("button_gift_bonus_closed")
                .type("oraxen")
                .displayName("Nagrody <gradient:#7776ff:#ae2cf1><bold>bonusowe!</bold></gradient>")
                .lore(Arrays.asList("<gradient:#4fa943:#9ec52f><bold>Do odebrania</bold></gradient>", "Kliknij, aby odebrać", "te nagrody!", "<gradient:#4fa943:#9ec52f><bold>Zawiera:</bold></gradient>"))
                .build()
        );
        buttons.put("streak_not_active", GuiButton.builder()
                .id("button_gift_streak_not_active")
                .type("oraxen")
                .displayName("<gradient:#4fa943:#9ec52f><bold>%value_1% dni</bold></gradient> z rzędu!")
                .lore(Arrays.asList("Loguj się codziennie", "na serwer, aby uzyskać", "<gradient:#7776ff:#ae2cf1><bold>bonusowe nagrody!</bold></gradient>"))
                .build()
        );
        buttons.put("streak_active", GuiButton.builder()
                .id("button_gift_streak_active")
                .type("oraxen")
                .displayName("<gradient:#4fa943:#9ec52f><bold>%value_1% dni</bold></gradient> z rzędu!")
                .lore(Arrays.asList("Loguj się codziennie", "na serwer, aby uzyskać", "<gradient:#7776ff:#ae2cf1><bold>bonusowe nagrody!</bold></gradient>"))
                .build()
        );
        return buttons;
    }
}
