package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.calendar.gui.GuiTemplate;
import net.exotia.plugins.calendar.gui.GuiButton;

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
        private String title = "✟ꡝ";
        private int size = 2;
        private List<Integer> slotsEmpty = Arrays.asList(0, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        private List<Integer> slotsRewards = Arrays.asList(1, 2, 3, 4, 5, 7);
        private HashMap<String, GuiButton> buttons = setupButtons();
    }

    private HashMap<String, GuiButton> setupButtons() {
        HashMap<String, GuiButton> buttons = new HashMap<>();
        buttons.put("wygasly", GuiButton.builder()
            .id("nagrody_prezenty_wygasly")
            .type("oraxen")
            .build()
        );
        buttons.put("zwykly_otwarty", GuiButton.builder()
            .id("nagrody_prezenty_zwykly_otwarty")
            .type("oraxen")
            .build()
        );
        buttons.put("zwykly_zamkniety", GuiButton.builder()
            .id("nagrody_prezenty_zwykly_zamkniety")
            .type("oraxen")
            .build()
        );
        buttons.put("bonusowy_otwarty", GuiButton.builder()
            .id("nagrody_prezenty_bonusowy_otwarty")
            .type("oraxen")
            .build()
        );
        buttons.put("bonusowy_zamkniety", GuiButton.builder()
            .id("nagrody_prezenty_bonusowy_zamkniety")
            .type("oraxen")
            .build()
        );
        return buttons;
    }
}
