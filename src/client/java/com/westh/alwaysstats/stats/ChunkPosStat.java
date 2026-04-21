package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;

public class ChunkPosStat implements StatProvider {

    @Override
    public String getConfigKey() { return "chunkPos"; }

    @Override
    public String getConfigName() { return "Chunk Position"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.player == null) return null;

        int chunkX    = client.player.chunkPosition().x;
        int chunkZ    = client.player.chunkPosition().z;
        int inChunkX  = client.player.getBlockX() & 15;
        int inChunkZ  = client.player.getBlockZ() & 15;

        return getConfigName() + ": " + chunkX + ", " + chunkZ
             + " (pos: " + inChunkX + ", " + inChunkZ + ")";
    }
}
