package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class TimeOfDayStat implements StatProvider {

    @Override
    public String getConfigKey() { return "timeOfDay"; }

    @Override
    public String getConfigName() { return "Time of Day"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.level == null) return null;
        long ticks = client.level.getDayTime() % 24000;
        long hours = (ticks / 1000 + 6) % 24;
        long minutes = (ticks % 1000) * 60 / 1000;
        boolean canSleep = ticks >= 12542 && ticks <= 23460;
        String sleepIndicator = canSleep ? " *" : "";
        return String.format("Time: %02d:%02d%s", hours, minutes, sleepIndicator);
    }

    @Override
    public Component getDisplayComponent(Minecraft client) {
        String t = getDisplayText(client);
        return t != null ? Component.literal(t) : null;
    }
}
