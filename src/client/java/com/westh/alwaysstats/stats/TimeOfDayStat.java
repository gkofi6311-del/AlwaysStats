package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;

public class MinecraftDayStat implements StatProvider {

    @Override
    public String getConfigKey() { return "mcDay"; }

    @Override
    public String getConfigName() { return "Day"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.level == null) return null;
        long day = (client.level.getDayTime() / 24000L) + 1;
        return getConfigName() + ": " + day;
    }
}
