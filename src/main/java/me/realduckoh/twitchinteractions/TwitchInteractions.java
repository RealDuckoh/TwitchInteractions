package me.realduckoh.twitchinteractions;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import me.realduckoh.twitchinteractions.rewards.ExpEvent;
import me.realduckoh.twitchinteractions.rewards.ItemEvent;
import me.realduckoh.twitchinteractions.rewards.GameEvent;
import me.realduckoh.twitchinteractions.rewards.PotionEvent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class TwitchInteractions extends JavaPlugin {
    private static GameEvent[] rewards = new GameEvent[]{new ExpEvent(0.188),

            new ItemEvent(0.15, new ItemStack(Material.IRON_INGOT, 4)),
            new ItemEvent(0.15, new ItemStack(Material.GOLD_INGOT, 4)),
            new ItemEvent(0.01, new ItemStack(Material.DIAMOND, 1)),
            new ItemEvent(0.001, new ItemStack(Material.ANCIENT_DEBRIS, 4)),
            new ItemEvent(0.001, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1)),

            new PotionEvent(0.1, new PotionEffect(PotionEffectType.ABSORPTION, 1200, 0)),
            new PotionEvent(0.1, new PotionEffect(PotionEffectType.REGENERATION, 600, 0)),
            new PotionEvent(0.1, new PotionEffect(PotionEffectType.SPEED, 1200, 0)),
            new PotionEvent(0.1, new PotionEffect(PotionEffectType.LUCK, 1200, 1)),
            new PotionEvent(0.1, new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 0)),

    };
    private static String clientID = "w0imxiuaypi698384yv6rwly9ghawh";
    private static String clientSecret = "b77jpll97huu8m6xdj5r7rdv0tcp3c";
    private static String OAuthToken = "oauth:6uzqtr54cb7pd9l16p9a704e9d61l9";
    @Override
    public void onEnable() {
        // Plugin startup logic
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withClientId(clientID)
                .withClientSecret(clientSecret)
                .withEnableTMI(true)
                .withEnableChat(true)
                .withEnableHelix(true)
                .withChatAccount(new OAuth2Credential("twitch", OAuthToken))
                .build();
        twitchClient.getChat().joinChannel("lWantUrCookie");
        twitchClient.getChat().getEventManager().registerEventHandler(new SimpleEventHandler());
        RewardCommand rewardCommand = new RewardCommand(rewards);
        twitchClient.getChat().getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(rewardCommand);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
