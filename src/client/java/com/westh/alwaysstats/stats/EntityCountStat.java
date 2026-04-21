package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class EntityCountStat implements StatProvider {

    private static int totalCount  = 0;
    private static int mobCount    = 0;
    private static int animalCount = 0;
    private static int itemCount   = 0;
    private static int tickTimer   = 0;
    private static final int UPDATE_INTERVAL = 20;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (++tickTimer >= UPDATE_INTERVAL) {
                tickTimer = 0;
                if (client.level != null) {
                    int total = 0, mobs = 0, animals = 0, items = 0;
                    for (Entity e : client.level.entitiesForRendering()) {
                        total++;
                        if (e instanceof Monster)    mobs++;
                        if (e instanceof Animal)     animals++;
                        if (e instanceof ItemEntity) items++;
                    }
                    totalCount  = total;
                    mobCount    = mobs;
                    animalCount = animals;
                    itemCount   = items;
                } else {
                    totalCount = mobCount = animalCount = itemCount = 0;
                }
            }
        });
    }

    @Override
    public String getConfigKey() { return "entities"; }

    @Override
    public String getConfigName() { return "Entities"; }

    @Override
    public String getDisplayText(Minecraft client) {
        return getConfigName() + ": " + totalCount
             + "  Mobs: " + mobCount
             + "  Animals: " + animalCount
             + "  Items: " + itemCount;
    }
}
