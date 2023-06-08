package net.exotia.plugins.calendar.calendar.rewards;

import net.exotia.bridge.api.user.ApiEconomyService;
import net.exotia.plugins.calendar.utils.UtilItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum RewardType {
    ITEM {
        @Override
        public void rewardPlayer(Player player, Reward reward, ApiEconomyService economyService) {
            ItemStack item = UtilItem.getItem(reward.getId(), reward.getItemType(), reward.getDisplayName(), reward.getLore(), reward.getAmount());
            player.getInventory().addItem(item);
        }
    },
    CURRENCY {
        @Override
        public void rewardPlayer(Player player, Reward reward, ApiEconomyService economyService) {
            economyService.give(player.getUniqueId(), reward.getAmount());
            economyService.save(player.getUniqueId());
        }
    },
    LEVEL {
        @Override
        public void rewardPlayer(Player player, Reward reward, ApiEconomyService economyService) {
            player.setLevel(player.getLevel() + reward.getAmount());
        }
    };

    public abstract void rewardPlayer(Player player, Reward reward, ApiEconomyService economyService);
}
