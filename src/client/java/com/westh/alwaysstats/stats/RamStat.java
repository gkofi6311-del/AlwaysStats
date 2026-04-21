package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;

public class RamStat implements StatProvider {

    @Override
    public String getConfigKey() { return "ram"; }

    @Override
    public String getConfigName() { return "RAM"; }

    @Override
    public String getDisplayText(Minecraft client) {
        Runtime rt    = Runtime.getRuntime();
        long used     = (rt.totalMemory() - rt.freeMemory()) / 1_048_576L;
        long total    = rt.maxMemory() / 1_048_576L;
        return getConfigName() + ": " + used + " / " + total + " MB";
    }
}
