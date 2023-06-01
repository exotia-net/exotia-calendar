package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.calendar.gui.GuiAction;
import net.exotia.plugins.calendar.gui.GuiButton;

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

    private HashMap<Integer, GuiButton> setupCalendarButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(1, GuiButton.builder()
                .slots(List.of(1))
                .oraxenID("wymagany_pusty")
                .displayName("Nagroda za <gradient:#4fa943:#9ec52f><bold>dzień 1!</bold></gradient>")
                .lore(List.of("<gray>Zaloguj się już jutro", "<gray>po kolejną nagrodę!"))
                .action(GuiAction.builder().name("give_item").value("apple").build())
                .build()
        );
        buttons.put(2, GuiButton.builder()
                .slots(List.of(2))
                .oraxenID("wymagany_pusty")
                .displayName("Nagroda za <gradient:#4fa943:#9ec52f><bold>dzień 2!</bold></gradient>")
                .lore(List.of("<gray>Zaloguj się już jutro", "<gray>po kolejną nagrodę!"))
                .action(GuiAction.builder().name("give_item").value("carrot").build())
                .build()
        );
        buttons.put(3, GuiButton.builder()
                .slots(List.of(3))
                .oraxenID("wymagany_pusty")
                .displayName("Nagroda za <gradient:#4fa943:#9ec52f><bold>dzień 3!</bold></gradient>")
                .lore(List.of("<gray>Zaloguj się już jutro", "<gray>po kolejną nagrodę!"))
                .action(GuiAction.builder().name("give_item").value("potato").build())
                .build()
        );
        buttons.put(4, GuiButton.builder()
                .slots(List.of(4))
                .oraxenID("wymagany_pusty")
                .displayName("Nagroda za <gradient:#4fa943:#9ec52f><bold>dzień 4!</bold></gradient>")
                .lore(List.of("<gray>Zaloguj się już jutro", "<gray>po kolejną nagrodę!"))
                .action(GuiAction.builder().name("give_item").value("beetroot").build())
                .build()
        );
        buttons.put(5, GuiButton.builder()
                .slots(List.of(5))
                .oraxenID("wymagany_pusty")
                .displayName("Nagroda za <gradient:#4fa943:#9ec52f><bold>dzień 5!</bold></gradient>")
                .lore(List.of("<gray>Pamiętaj o odebraniu", "<gray>bonusu!"))
                .action(GuiAction.builder().name("give_item").value("cookie").build())
                .build()
        );
        buttons.put(7, GuiButton.builder()
                .slots(List.of(7))
                .oraxenID("wymagany_pusty")
                .displayName("Nagroda <gradient:#4fa943:#9ec52f><bold>bonusowa!</bold></gradient>")
                .lore(List.of("<gray>Widzimy się w następnym", "<gray>tygodniu!"))
                .action(GuiAction.builder().name("give_item").value("diamond").build())
                .build()
        );
        return buttons;
    }

    @Getter
    public static class GuiConfiguration extends OkaeriConfig {
        private String title;
        private int size;
        private List<Integer> slotsEmpty;
        private int itemsPerPage;
        private GuiButton buttonNext;
        private GuiButton buttonPrevious;
        private HashMap<Integer, GuiButton> buttons;
    }

    @Getter
    public class GuiCalendar extends GuiConfiguration {
        private String title = "✟ꡝ";
        private int size = 2;
        private List<Integer> slotsEmpty = Arrays.asList(0, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        private HashMap<Integer, GuiButton> buttons = setupCalendarButtons();
    }
}
