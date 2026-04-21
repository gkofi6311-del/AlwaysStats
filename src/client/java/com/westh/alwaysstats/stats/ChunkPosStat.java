package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ChunkPosStat implements StatProvider {

    @Override
    public String getConfigKey() {
        return "chunkPos";
    }

    @Override
    public String getConfigName() {
        return "Chunk Position";
    }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.player == null) return null;
        int chunkX = (int) Math.floor(client.player.getX()) >> 4;
        int chunkZ = (int) Math.floor(client.player.getZ()) >> 4;
        return "Chunk: " + chunkX + ", " + chunkZ;
    }

    @Override
    public Component getDisplayComponent(Minecraft client) {
        String text = getDisplayText(client);
        return text != null ? Component.literal(text) : null;
    }
}
