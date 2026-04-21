package com.westh.alwaysstats.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class RandomChallengeStat implements StatDisplay {

    private static final String[] CHALLENGES = new String[300];

    static {
        String[] verbs = {
                "Catch", "Breed", "Kill", "Mine", "Craft", "Find", "Build", "Tame", "Explore", "Trade"
        };

        String[] targets = {
                "5 fish", "2 cows", "10 zombies", "20 stone", "a bookshelf",
                "a village", "a castle wall", "a wolf", "a horse", "a desert temple",
                "a nether fortress", "a creeper", "iron ore", "diamonds", "a full armor set"
        };

        for (int i = 0; i < 300; i++) {
            String v = verbs[i % verbs.length];
            String t = targets[(i * 3) % targets.length];
            CHALLENGES[i] = v + " " + t + " #" + (i + 1);
        }
    }

    private static final Set<Integer> completedChallenges = new HashSet<>();

    private static KeyMapping completeKey;
    private static boolean registered = false;

    public RandomChallengeStat() {
        if (registered) return;
        registered = true;

        completeKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.alwaysstats.complete_challenge",
                GLFW.GLFW_KEY_V,
                "key.categories.alwaysstats"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level == null) return;

            while (completeKey.consumeClick()) {
                int index = getCurrentIndex(client);

                if (completedChallenges.contains(index)) {
                    completedChallenges.remove(index);
                } else {
                    completedChallenges.add(index);
                }
            }
        });
    }

    private static int getCurrentIndex(Minecraft client) {
        long day = client.level.getDayTime() / 24000L;
        return (int)(day % CHALLENGES.length);
    }

    @Override
    public String getConfigKey() {
        return "randomChallenge";
    }

    @Override
    public String getConfigName() {
        return "Random Challenge";
    }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.level == null) return null;

        int index = getCurrentIndex(client);
        boolean done = completedChallenges.contains(index);

        return (done ? "§a✔ " : "§f☐ ") + CHALLENGES[index];
    }

    @Override
    public Component getDisplayComponent(Minecraft client) {
        String text = getDisplayText(client);
        return text != null ? Component.literal(text) : null;
    }
}
