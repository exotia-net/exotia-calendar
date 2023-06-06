package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class ConfigurationMessage extends OkaeriConfig {
    @Comment("No permission message")
    private CommandsNoPermission commandsNoPermission = new CommandsNoPermission();

    @Comment("Invalid usage message")
    private CommandsInvalid commandsInvalid = new CommandsInvalid();

    @Comment("Player Command")
    private CommandsPlayer commandsPlayer = new CommandsPlayer();

    @Comment("Reload Command")
    private CommandsReload commandsReload = new CommandsReload();

    @Comment("Calendar Command")
    private CommandsCalendar commandsCalendar = new CommandsCalendar();

    @Comment("Join Event")
    private EventsJoin eventsJoin = new EventsJoin();

    @Comment("Sounds")
    private Sounds sounds = new Sounds();

    @Getter
    public class CommandsNoPermission extends OkaeriConfig {
        private String failed = "鱂 Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do tej <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
    }

    @Getter
    public class CommandsInvalid extends OkaeriConfig {
        private String invalid = "鱂 Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
    }

    @Getter
    public class CommandsPlayer extends OkaeriConfig {
        private String offline = "鱂 Wybrany gracz jest <gradient:#4fa943:#9ec52f><bold>offline!</bold></gradient>";
        private String only = "鱂 Użycie dostępne tylko dla <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class CommandsReload extends OkaeriConfig {
        private String success = "鱂 Konfiguracja <gradient:#4fa943:#9ec52f><bold>przeładowana!</bold></gradient>";
        private String failed = "鱂 Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#4fa943:#9ec52f><bold>konfiguracji!</bold></gradient> Stworzono nowe <gradient:#4fa943:#9ec52f><bold>pliki konfiguracyjne!</bold></gradient>";
    }

    @Getter
    public class CommandsCalendar extends OkaeriConfig {
        private String obtain = "鱂 Odebrałeś/aś <gradient:#4fa943:#9ec52f><bold>nagrody dnia %value_1%!</bold></gradient> Dziękujemy za <gradient:#4fa943:#9ec52f><bold>aktywność!</bold></gradient>";
    }

    @Getter
    public class EventsJoin extends OkaeriConfig {
        private String obtainable = "鱂 <click:run_command:/dzienne>Pamiętaj o odebraniu <gradient:#4fa943:#9ec52f><bold>nagrody dnia %value_1%!</bold></gradient>\nKliknij na wiadomość, aby <gradient:#4fa943:#9ec52f><bold>ją odebrać!</bold></gradient></click>";
    }

    @Getter
    public class Sounds extends OkaeriConfig {
        private String success = "ui_toast_challenge_complete";
        private String activate = "block_note_block_pling";
        private String step = "block_note_block_xylophone";
        private String error = "block_note_block_bit";
        private String click = "ui_button_click";
    }
}
