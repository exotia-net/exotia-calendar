package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.calendar.calendar.gui.GuiTemplate;
import net.exotia.plugins.calendar.calendar.gui.GuiButton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationGui extends OkaeriConfig {
    @Comment("GUIs")
    private HashMap<String, GuiTemplate> guis = setupGuis();

    private HashMap<String, GuiTemplate> setupGuis() {
        HashMap<String, GuiTemplate> guis = new HashMap<>();
        guis.put("calendar", new GuiCalendar());
        return guis;
    }

    @Getter
    public class GuiCalendar extends GuiTemplate {
        private final String title = "✟ꡝ";
        private final String alternateTitle = "✟ꡞ";
        private final int size = 2;
        private final List<Integer> slotsEmpty = Arrays.asList(0, 6, 8, 9, 10, 16, 17);
        private final List<Integer> slotsRewards = Arrays.asList(1, 2, 3, 4, 5, 7);
        private final List<Integer> slotsStreak = Arrays.asList(10, 11, 12, 13, 14);
        private final HashMap<String, GuiButton> buttons = setupButtons();
    }

    private HashMap<String, GuiButton> setupButtons() {
        HashMap<String, GuiButton> buttons = new HashMap<>();
        buttons.put("wygasly", GuiButton.builder()
            .id("nagrody_prezenty_wygasly")
            .type("oraxen")
            .displayName("Nagrody <gradient:#4fa943:#9ec52f><bold>dnia %value_1%!</bold></gradient>")
            .lore(Arrays.asList("%value_1%"))
            .build()
        );
        buttons.put("zwykly_otwarty", GuiButton.builder()
            .id("nagrody_prezenty_zwykly_otwarty")
            .type("oraxen")
            .displayName("Nagrody <gradient:#4fa943:#9ec52f><bold>dnia %value_1%!</bold></gradient>")
            .lore(Arrays.asList("%value_1%"))
            .build()
        );
        buttons.put("zwykly_zamkniety", GuiButton.builder()
            .id("nagrody_prezenty_zwykly_zamkniety")
            .type("oraxen")
            .displayName("Nagrody <gradient:#4fa943:#9ec52f><bold>dnia %value_1%!</bold></gradient>")
            .lore(Arrays.asList("%value_1%"))
            .build()
        );
        buttons.put("bonusowy_otwarty", GuiButton.builder()
            .id("nagrody_prezenty_bonusowy_otwarty")
            .type("oraxen")
            .displayName("Nagrody <gradient:#7776ff:#ae2cf1><bold>bonusowe!</bold></gradient>")
            .lore(Arrays.asList("%value_1%"))
            .build()
        );
        buttons.put("bonusowy_zamkniety", GuiButton.builder()
            .id("nagrody_prezenty_bonusowy_zamkniety")
            .type("oraxen")
            .displayName("Nagrody <gradient:#7776ff:#ae2cf1><bold>bonusowe!</bold></gradient>")
            .lore(Arrays.asList("%value_1%"))
            .build()
        );
        buttons.put("streak_wygasly", GuiButton.builder()
            .id("nagrody_prezenty_streak_wygasly")
            .type("oraxen")
            .displayName("<gradient:#4fa943:#9ec52f><bold>%value_1% dni</bold></gradient> z rzędu!")
            .lore(Arrays.asList("Loguj się codziennie", "na serwer, aby uzyskać", "<gradient:#7776ff:#ae2cf1><bold>bonusowe nagrody!</bold></gradient>"))
            .build()
        );
        buttons.put("streak_aktywny", GuiButton.builder()
            .id("nagrody_prezenty_streak_aktywny")
            .type("oraxen")
            .displayName("<gradient:#4fa943:#9ec52f><bold>%value_1% dni</bold></gradient> z rzędu!")
            .lore(Arrays.asList("Loguj się codziennie", "na serwer, aby uzyskać", "<gradient:#7776ff:#ae2cf1><bold>bonusowe nagrody!</bold></gradient>"))
            .build()
        );
        return buttons;
    }
}
