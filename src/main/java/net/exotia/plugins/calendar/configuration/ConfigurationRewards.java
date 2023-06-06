package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.calendar.calendar.rewards.Reward;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationRewards extends OkaeriConfig {
    @Comment("Reward list")
    private HashMap<Integer, List<Reward>> rewards = setupRewards();

    public HashMap<Integer, List<Reward>> setupRewards() {
        HashMap<Integer, List<Reward>> rewards = new HashMap<>();
        rewards.put(0, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("jedzenie_owoce_kiwi")
                .itemType("oraxen")
                .amount(1)
                .build()
            ));
        rewards.put(1, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("jedzenie_owoce_truskawka")
                .itemType("oraxen")
                .amount(1)
                .build()
        ));
        rewards.put(2, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("jedzenie_owoce_borowki")
                .itemType("oraxen")
                .amount(1)
                .build()
        ));
        rewards.put(3, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("jedzenie_owoce_wisnia")
                .itemType("oraxen")
                .amount(1)
                .build()
        ));
        rewards.put(4, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("jedzenie_owoce_banan")
                .itemType("oraxen")
                .displayName("Testowy")
                .lore(Arrays.asList("Przedmiot", "Testowy"))
                .amount(1)
                .build()
        ));
        rewards.put(5, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("jedzenie_nabial_ser")
                .itemType("oraxen")
                .displayName("Testowy")
                .lore(Arrays.asList("Przedmiot", "Testowy"))
                .amount(1)
                .build()
        ));
        return rewards;
    }
}
