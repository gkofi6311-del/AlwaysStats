package com.westh.alwaysstats.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;

public class EntityCountStat implements StatProvider {

    // Cached counts - only updated once per second, not every render frame
    private static int cachedTotal   = 0;
    private static int cachedMobs    = 0;
    private static int cachedAnimals = 0;
    private static int cachedItems   = 0;

    private static int tickTimer = 0;
    private static final int UPDATE_INTERVAL = 20; // ticks (= 1 second)

    // Guard so the tick listener is only registered once, even if the class
    // is constructed multiple times (e.g. after a config reload)
    private static boolean registered = false;

    public EntityCountStat() {
        if (!registered) {
            registered = true;
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                tickTimer++;
                if (tickTimer >= UPDATE_INTERVAL) {
                    tickTimer = 0;
                    if (client.level == null) {
                        cachedTotal = cachedMobs = cachedAnimals = cachedItems = 0;
                        return;
                    }
                    int total = 0, mobs = 0, animals = 0, items = 0;
                    for (Entity e : client.level.entitiesForRendering()) {
                        total++;
                        if (e instanceof Monster)   mobs++;
                        if (e instanceof Animal)    animals++;
                        if (e instanceof ItemEntity) items++;
                    }
                    cachedTotal   = total;
                    cachedMobs    = mobs;
                    cachedAnimals = animals;
                    cachedItems   = items;
                }
            });
        }
    }

    @Override
    public String getConfigKey() {
        return "entities";
    }

    @Override
    public String getConfigName() {
        return "Entity Count";
    }

    @Override
    public String getDisplayText(Minecraft client) {
        return "Entities: " + cachedTotal
             + "  Mobs: "    + cachedMobs
             + "  Animals: " + cachedAnimals
             + "  Items: "   + cachedItems;
    }
}
