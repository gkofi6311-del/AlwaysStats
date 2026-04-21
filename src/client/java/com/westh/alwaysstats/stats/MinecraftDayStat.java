package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class MinecraftDayStat implements StatProvider {

    @Override
    public String getConfigKey() { return "mcDay"; }

    @Override
    public String getConfigName() { return "Minecraft Day"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.level == null) return null;
        long day = client.level.getDayTime() / 24000L;
        return "Day: " + day;
    }

    @Override
    public Component getDisplayComponent(Minecraft client) {
        String t = getDisplayText(client);
        return t != null ? Component.literal(t) : null;
    }
}
