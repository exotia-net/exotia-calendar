package net.exotia.plugins.calendar.calendar.rewards;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

@Builder
@Getter
public class Reward extends OkaeriConfig {
    private String rewardType;
    private String id;
    private String itemType;
    private String displayName;
    private List<String> lore;
    private int amount;

    public void rewardPlayer(Player player) {
        RewardType.valueOf(rewardType.toUpperCase()).rewardPlayer(player, this);
    }
}
