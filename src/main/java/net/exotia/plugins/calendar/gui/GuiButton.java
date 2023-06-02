package net.exotia.plugins.calendar.gui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GuiButton {
    private List<Integer> slots;
}
