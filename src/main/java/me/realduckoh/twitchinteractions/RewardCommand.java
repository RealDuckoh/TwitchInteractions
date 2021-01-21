package me.realduckoh.twitchinteractions;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import me.realduckoh.twitchinteractions.rewards.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RewardCommand implements EventListener {
    List<Double> probabilities = new ArrayList<>();
    List<GameEvent> rewards = new ArrayList<>();
    public RewardCommand(GameEvent... rewards){
        double cumulativeProbability = 0;
        Collections.addAll(this.rewards, rewards);
        Collections.sort(this.rewards);
        for (GameEvent reward : this.rewards) {
            cumulativeProbability += reward.getProbability();
            probabilities.add(cumulativeProbability);
        }
    }

    @EventSubscriber
    public void rewardCommand(ChannelMessageEvent event){
        String[] args = event.getMessage().split(" ");
        if (args[0].equals("!reward")) {
            String senderChannelName = event.getUser().getName();
            String channelName = event.getChannel().getName();
            if (args.length == 1) {
                event.getTwitchChat().sendMessage(channelName,"Please enter a user to reward.");
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null) event.getTwitchChat().sendMessage(channelName,"The user \"" + args[1] + "\" does not exist" );
                    double random = ThreadLocalRandom.current().nextDouble();
                    for (int i = 0; i < probabilities.size(); i++) {
                        if (random <= probabilities.get(i)) {
                            rewards.get(i).give(player);
                            break;
                        }
                    }
                    event.getTwitchChat().sendMessage(channelName,"A reward was sent by " + senderChannelName + " to "  + args[1]);
                }
            }.runTask(JavaPlugin.getPlugin(TwitchInteractions.class));

        }
    }


}
