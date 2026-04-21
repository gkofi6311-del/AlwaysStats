package com.westh.alwaysstats.stats;

import com.westh.alwaysstats.config.StatsConfig;
import net.minecraft.client.MinecraftClient;

public class ChunkPosStat implements StatProvider {

    @Override
    public String getId() { return "chunk_pos"; }

    @Override
    public String getLabel() { return "Chunk"; }

    @Override
    public String getValue(MinecraftClient client) {
        if (client.player == null) return "N/A";

        // Which chunk in the world
        int chunkX = client.player.getChunkPos().x;
        int chunkZ = client.player.getChunkPos().z;

        // Position inside that chunk (0-15)
        int inChunkX = client.player.getBlockX() & 15;
        int inChunkZ = client.player.getBlockZ() & 15;

        return chunkX + ", " + chunkZ + " (pos: " + inChunkX + ", " + inChunkZ + ")";
    }

    @Override
    public boolean isEnabled() { return StatsConfig.getInstance().enabledStats.getOrDefault("chunk_pos", true); }
}

