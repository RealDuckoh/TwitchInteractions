package me.realduckoh.twitchinteractions.rewards;

import org.bukkit.entity.Player;

public class ExpEvent extends GameEvent {
    public ExpEvent(double chance) {
        super(chance);
    }

    @Override
    public void give(Player player) {
        player.giveExp(50);

    }
}
