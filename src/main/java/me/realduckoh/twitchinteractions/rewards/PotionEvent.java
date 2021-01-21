package me.realduckoh.twitchinteractions.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PotionEvent extends GameEvent {

    PotionEffect effect;

    public PotionEvent(double chance, PotionEffect effect) {
        super(chance);
        this.effect = effect;
    }

    @Override
    public void give(Player player) {
        player.addPotionEffect(effect);

    }
}
