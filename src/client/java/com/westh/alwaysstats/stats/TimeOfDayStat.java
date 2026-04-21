package com.westh.alwaysstats.stats;

import net.minecraft.client.MinecraftClient;

public class TimeOfDayStat implements StatProvider {
    
    @Override
    public String getLabel() {
        return "Time";
    }

    @Override
    public String getValue() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return "N/A";
        
        long timeOfDay = client.world.getTimeOfDay() % 24000;
        long hours = (timeOfDay / 1000 + 6) % 24; // +6 because MC day starts at 6am
        long minutes = (timeOfDay % 1000) * 60 / 1000;
        
        return String.format("%02d:%02d", hours, minutes);
    }
}
