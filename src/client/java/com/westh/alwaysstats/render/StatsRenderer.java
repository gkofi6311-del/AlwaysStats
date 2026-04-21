package com.westh.alwaysstats.render;

import com.westh.alwaysstats.stats.MinecraftDayStat;
import com.westh.alwaysstats.stats.StatProvider;
import com.westh.alwaysstats.stats.TimeOfDayStat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class StatsRenderer {
    private final List<StatProvider> stats = new ArrayList<>();
    private final MinecraftClient client = MinecraftClient.getInstance();

    public StatsRenderer() {
        stats.add(new MinecraftDayStat());
        stats.add(new TimeOfDayStat());
        // add your other stats here
    }

    public void render(MatrixStack matrices) {
        if (client.player == null || client.world == null) return;
        
        TextRenderer textRenderer = client.textRenderer;
        int y = 4;
        int x = 4;

        for (StatProvider stat : stats) {
            String text = stat.getLabel() + ": " + stat.getValue();
            textRenderer.drawWithShadow(matrices, text, x, y, stat.getColor());
            y += 10;
        }
    }
}
