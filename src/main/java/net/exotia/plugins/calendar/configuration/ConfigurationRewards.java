package net.exotia.plugins.calendar.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.calendar.calendar.rewards.Reward;

import java.util.Arrays;
import java.util.List;

@Getter
public class ConfigurationRewards extends OkaeriConfig {
    @Comment("Reward list")
    private List<List<Reward>> rewards = setupRewards();

    public List<List<Reward>> setupRewards() {
        return Arrays.asList(
            Arrays.asList(
                Reward.builder()
                    .rewardType("item")
                    .id("jedzenie_owoce_borowki")
                    .itemType("oraxen")
                    .displayName("Testowy")
                    .lore(Arrays.asList("Przedmiot", "Testowy"))
                    .amount(4)
                    .build(),
                Reward.builder()
                        .rewardType("item")
                        .id("jedzenie_nabial_ser")
                        .itemType("oraxen")
                        .displayName("Testowy")
                        .lore(Arrays.asList("Przedmiot", "Testowy"))
                        .amount(8)
                        .build()
            ),
            Arrays.asList(
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_owoce_borowki")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(4)
                            .build(),
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_nabial_ser")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(8)
                            .build()
            ),
            Arrays.asList(
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_owoce_borowki")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(4)
                            .build(),
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_nabial_ser")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(8)
                            .build()
            ),
            Arrays.asList(
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_owoce_borowki")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(4)
                            .build(),
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_nabial_ser")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(8)
                            .build()
            ),
            Arrays.asList(
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_owoce_borowki")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(4)
                            .build(),
                    Reward.builder()
                            .rewardType("item")
                            .id("jedzenie_nabial_ser")
                            .itemType("oraxen")
                            .displayName("Testowy")
                            .lore(Arrays.asList("Przedmiot", "Testowy"))
                            .amount(8)
                            .build()
            )
        );
    }
}
