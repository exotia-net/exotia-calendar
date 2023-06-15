package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.calendar.configuration.section.SectionCalendar;

@Getter
public class ConfigurationGui extends OkaeriConfig {
    @Comment("Calendar GUI")
    private SectionCalendar guiCalendar = new SectionCalendar();
}
