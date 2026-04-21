package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;

public class OnFireWetStat implements StatProvider {

    @Override
    public String getConfigKey() { return "onFireWet"; }

    @Override
    public String getConfigName() { return "Status"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.player == null) return null;
        if (client.player.isInLava())              return getConfigName() + ": §cIn Lava 🔥";
        if (client.player.isOnFire())              return getConfigName() + ": §cOn Fire 🔥";
        if (client.player.isInWaterOrRain())       return getConfigName() + ": §9Wet 💧";
        return getConfigName() + ": §aOK";
    }
}
