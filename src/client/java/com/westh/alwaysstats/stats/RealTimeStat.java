package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RealTimeStat implements StatProvider {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String getConfigKey() { return "realTime"; }

    @Override
    public String getConfigName() { return "Time"; }

    @Override
    public String getDisplayText(Minecraft client) {
        return getConfigName() + ": " + LocalTime.now().format(FMT);
    }
}
