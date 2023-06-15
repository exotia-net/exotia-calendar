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
                .id("food_fruit_kiwi")
                .itemType("oraxen")
                .displayName("Kiwi dnia pierwszego")
                .amount(1)
                .build()
            ));
        rewards.put(1, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("food_fruit_strawberry")
                .itemType("oraxen")
                .displayName("Truskawka dnia drugiego")
                .amount(1)
                .build()
        ));
        rewards.put(2, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("food_fruit_bilberry")
                .itemType("oraxen")
                .displayName("Borówki dnia trzeciego")
                .amount(1)
                .build()
        ));
        rewards.put(3, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("food_fruit_cherry")
                .itemType("oraxen")
                .displayName("Wiśnia dnia czwartego")
                .amount(1)
                .build()
        ));
        rewards.put(4, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("food_fruit_banana")
                .itemType("oraxen")
                .displayName("Banan dnia piątego")
                .amount(1)
                .build()
        ));
        rewards.put(5, Arrays.asList(
            Reward.builder()
                .rewardType("item")
                .id("food_dairy_cheese")
                .itemType("oraxen")
                .displayName("Bonusowy Ser")
                .amount(1)
                .build()
        ));
        return rewards;
    }
}
