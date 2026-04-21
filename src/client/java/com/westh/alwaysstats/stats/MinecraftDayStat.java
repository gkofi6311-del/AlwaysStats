package com.westh.alwaysstats.stats;

import net.minecraft.client.MinecraftClient;

public class MinecraftDayStat implements StatProvider {
    
    @Override
    public String getLabel() {
        return "Day";
    }

    @Override
    public String getValue() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return "N/A";
        return String.valueOf(client.world.getTimeOfDay() / 24000L);
    }
}
