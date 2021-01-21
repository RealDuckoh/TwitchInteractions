package me.realduckoh.twitchinteractions.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemEvent extends GameEvent {

    ItemStack item;

    public ItemEvent(double chance, ItemStack item) {
        super(chance);
        this.item = item;
    }

    @Override
    public void give(Player player) {
        player.getInventory().addItem(item);

    }
}
