package me.realduckoh.twitchinteractions.rewards;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class GameEvent implements Comparable<GameEvent> {
    double chance;
    public GameEvent(double chance) {
        this.chance = chance;
    }
    public abstract void give(Player player);
    public double getProbability() {
        return chance;
    }

    @Override
    public int compareTo(@NotNull GameEvent o) {
        return Double.compare(this.getProbability(), o.getProbability());
    }

}
