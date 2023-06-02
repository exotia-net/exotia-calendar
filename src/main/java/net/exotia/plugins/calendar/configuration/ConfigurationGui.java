package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationGui extends OkaeriConfig {
    @Comment("GUIs")
    private HashMap<String, GuiConfiguration> guis = setupGuis();

    private HashMap<String, GuiConfiguration> setupGuis() {
        HashMap<String, GuiConfiguration> guis = new HashMap<>();
        guis.put("calendar", new GuiCalendar());
        return guis;
    }

    @Getter
    public static class GuiConfiguration extends OkaeriConfig {
        private String title;
        private int size;
        private List<Integer> slotsEmpty;
        private List<Integer> slotsRewards;
    }

    @Getter
    public class GuiCalendar extends GuiConfiguration {
        private String title = "✟ꡝ";
        private int size = 2;
        private List<Integer> slotsEmpty = Arrays.asList(0, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        private List<Integer> slotsRewards = Arrays.asList(1, 2, 3, 4, 5, 7);
    }
}
