package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;

public class PingStat implements StatProvider {

    @Override
    public String getConfigKey() { return "ping"; }

    @Override
    public String getConfigName() { return "Ping"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.player == null || client.getConnection() == null) return null;
        PlayerInfo info = client.getConnection().getPlayerInfo(client.player.getUUID());
        if (info == null) return null;
        return getConfigName() + ": " + info.getLatency() + " ms";
    }
}
