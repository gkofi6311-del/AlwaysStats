package com.westh.alwaysstats.render;

import com.westh.alwaysstats.config.FontSize;
import com.westh.alwaysstats.config.ScreenCorner;
import com.westh.alwaysstats.config.StatsConfig;
import com.westh.alwaysstats.stats.BiomeStat;
import com.westh.alwaysstats.stats.CoordStat;
import com.westh.alwaysstats.stats.DirectionStat;
import com.westh.alwaysstats.stats.EntityCountStat;
import com.westh.alwaysstats.stats.FpsStat;
import com.westh.alwaysstats.stats.LightLevelStat;
import com.westh.alwaysstats.stats.MinecraftDayStat;
import com.westh.alwaysstats.stats.StatProvider;
import com.westh.alwaysstats.stats.TargetStat;
import com.westh.alwaysstats.stats.TimeOfDayStat;
import com.westh.alwaysstats.stats.LastDeathStat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class StatsRenderer {
    public static final int BASE_LINE_HEIGHT = 11;
    public static final int PADDING = 2;
    public static final int MARGIN = 5;
    private static final int BACKGROUND_COLOR = 0x90000000;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    public record StatBounds(String key, int y, int height) {}
    public record PreviewResult(int width, int height, List<StatBounds> statBounds) {}

    private static final List<StatProvider> ALL_STATS = List.of(
        new FpsStat(),
        new BiomeStat(),
        new CoordStat(),
        new DirectionStat(),
        new LightLevelStat(),
        new TargetStat(),
        new TimeOfDayStat(),
        new LastDeathStat(),
        new EntityCountStat(),
        new MinecraftDayStat()
    );

    public static List<StatProvider> getAllStats() {
        return ALL_STATS;
    }

    private static StatProvider getStatByKey(String key) {
        for (StatProvider stat : ALL_STATS) {
            if (stat.getConfigKey().equals(key)) {
                return stat;
            }
        }
        return null;
    }

    public static void render(GuiGraphics guiGraphics, Minecraft client) {
        StatsConfig config = StatsConfig.get();
        List<Component> lines = getDisplayLines(client);

        if (lines.isEmpty()) {
            return;
        }

        float scale = config.corner == ScreenCorner.CUSTOM ? config.customScale : config.fontSize.getScale();
        int lineHeight = Math.round(BASE_LINE_HEIGHT * scale);

        int maxWidth = 0;
        for (Component line : lines) {
            maxWidth = Math.max(maxWidth, Math.round(client.font.width(line) * scale));
        }

        int boxWidth = maxWidth + (PADDING * 2);
        int boxHeight = (lines.size() * lineHeight) + PADDING;

        int screenWidth = client.getWindow().getGuiScaledWidth();
        int screenHeight = client.getWindow().getGuiScaledHeight();

        ScreenCorner corner = config.corner;
        int x, y;

        switch (corner) {
            case TOP_RIGHT -> {
                x = screenWidth - maxWidth - MARGIN;
                y = MARGIN;
            }
            case BOTTOM_LEFT -> {
                x = MARGIN;
                y = screenHeight - boxHeight - MARGIN + PADDING;
            }
            case BOTTOM_RIGHT -> {
                x = screenWidth - maxWidth - MARGIN;
                y = screenHeight - boxHeight - MARGIN + PADDING;
            }
            case CUSTOM -> {
                x = config.customX;
                y = config.customY;
            }
            default -> {
                x = MARGIN;
                y = MARGIN;
            }
        }

        int boxX = x - PADDING;
        int boxY = y - PADDING;

        if (config.showBackground) {
            guiGraphics.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, BACKGROUND_COLOR);
        }

        boolean rightAligned = config.alignRight;

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(x, y);
        guiGraphics.pose().scale(scale, scale);

        int unscaledMaxWidth = Math.round(maxWidth / scale);

        int currentY = 0;
        for (Component line : lines) {
            int textX = 0;
            if (rightAligned) {
                int lineWidth = client.font.width(line);
                textX = unscaledMaxWidth - lineWidth;
            }
            guiGraphics.drawString(client.font, line, textX, currentY, TEXT_COLOR);
            currentY += BASE_LINE_HEIGHT;
        }

        guiGraphics.pose().popMatrix();
    }

    public static int[] calculatePosition(Minecraft client, ScreenCorner corner) {
        StatsConfig config = StatsConfig.get();
        float scale = config.fontSize.getScale();

        List<Component> lines = getDisplayLines(client);
        if (lines.isEmpty()) {
            return new int[]{MARGIN, MARGIN};
        }

        int maxWidth = 0;
        for (Component line : lines) {
            maxWidth = Math.max(maxWidth, Math.round(client.font.width(line) * scale));
        }

        int lineHeight = Math.round(BASE_LINE_HEIGHT * scale);
        int boxHeight = (lines.size() * lineHeight) + PADDING;

        int screenWidth = client.getWindow().getGuiScaledWidth();
        int screenHeight = client.getWindow().getGuiScaledHeight();

        int x, y;
        switch (corner) {
            case TOP_RIGHT -> {
                x = screenWidth - maxWidth - MARGIN;
                y = MARGIN;
            }
            case BOTTOM_LEFT -> {
                x = MARGIN;
                y = screenHeight - boxHeight - MARGIN + PADDING;
            }
            case BOTTOM_RIGHT -> {
                x = screenWidth - maxWidth - MARGIN;
                y = screenHeight - boxHeight - MARGIN + PADDING;
            }
            case CUSTOM -> {
                x = config.customX;
                y = config.customY;
            }
            default -> {
                x = MARGIN;
                y = MARGIN;
            }
        }
        return new int[]{x, y};
    }

    public static PreviewResult renderPreview(GuiGraphics guiGraphics, Minecraft client, int x, int y, float scale) {
        return renderPreview(guiGraphics, client, x, y, scale, null);
    }

    public static PreviewResult renderPreview(GuiGraphics guiGraphics, Minecraft client, int x, int y, float scale, String highlightedKey) {
        List<Component> lines = getDisplayLines(client);
        List<String> statKeys = getEnabledStatKeys(client);

        if (lines.isEmpty()) {
            return new PreviewResult(0, 0, List.of());
        }

        StatsConfig config = StatsConfig.get();
        int lineHeight = Math.round(BASE_LINE_HEIGHT * scale);

        int maxWidth = 0;
        for (Component line : lines) {
            maxWidth = Math.max(maxWidth, Math.round(client.font.width(line) * scale));
        }

        int boxWidth = maxWidth + (PADDING * 2);
        int boxHeight = (lines.size() * lineHeight) + PADDING;

        int boxX = x - PADDING;
        int boxY = y - PADDING;

        if (config.showBackground) {
            guiGraphics.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, BACKGROUND_COLOR);
        }

        guiGraphics.fill(boxX, boxY, boxX + boxWidth, boxY + 1, 0xFFFFFFFF);
        guiGraphics.fill(boxX, boxY + boxHeight - 1, boxX + boxWidth, boxY + boxHeight, 0xFFFFFFFF);
        guiGraphics.fill(boxX, boxY, boxX + 1, boxY + boxHeight, 0xFFFFFFFF);
        guiGraphics.fill(boxX + boxWidth - 1, boxY, boxX + boxWidth, boxY + boxHeight, 0xFFFFFFFF);

        int handleSize = 6;
        int handleX = boxX + boxWidth - handleSize;
        int handleY = boxY + boxHeight - handleSize;
        guiGraphics.fill(handleX, handleY, boxX + boxWidth, boxY + boxHeight, 0xFFFFFF00);

        boolean rightAligned = config.alignRight;
        int unscaledMaxWidth = Math.round(maxWidth / scale);

        List<StatBounds> statBounds = new ArrayList<>();
        int currentYOffset = 0;
        for (int i = 0; i < lines.size(); i++) {
            String key = statKeys.get(i);
            int statY = boxY + PADDING + currentYOffset;
            statBounds.add(new StatBounds(key, statY, lineHeight));
            currentYOffset += lineHeight;
        }

        if (highlightedKey != null) {
            for (StatBounds bounds : statBounds) {
                if (bounds.key().equals(highlightedKey)) {
                    guiGraphics.fill(boxX + 1, bounds.y(), boxX + boxWidth - 1, bounds.y() + bounds.height(), 0x40FFFFFF);
                    break;
                }
            }
        }

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(x, y);
        guiGraphics.pose().scale(scale, scale);

        int currentY = 0;
        for (int i = 0; i < lines.size(); i++) {
            Component line = lines.get(i);
            String key = statKeys.get(i);
            int textX = 0;
            if (rightAligned) {
                int lineWidth = client.font.width(line);
                textX = unscaledMaxWidth - lineWidth;
            }
            int color = (highlightedKey != null && key.equals(highlightedKey)) ? 0xFFFFFF55 : TEXT_COLOR;
            guiGraphics.drawString(client.font, line, textX, currentY, color);
            currentY += BASE_LINE_HEIGHT;
        }

        guiGraphics.pose().popMatrix();

        return new PreviewResult(boxWidth, boxHeight, statBounds);
    }

    private static List<Component> getDisplayLines(Minecraft client) {
        List<Component> lines = new ArrayList<>();
        StatsConfig config = StatsConfig.get();

        for (String key : config.statOrder) {
            if (!config.enabledStats.contains(key)) {
                continue;
            }
            StatProvider provider = getStatByKey(key);
            if (provider != null) {
                Component component = provider.getDisplayComponent(client);
                if (component != null) {
                    lines.add(component);
                }
            }
        }

        return lines;
    }

    private static List<String> getEnabledStatKeys(Minecraft client) {
        List<String> keys = new ArrayList<>();
        StatsConfig config = StatsConfig.get();

        for (String key : config.statOrder) {
            if (!config.enabledStats.contains(key)) {
                continue;
            }
            StatProvider provider = getStatByKey(key);
            if (provider != null) {
                Component component = provider.getDisplayComponent(client);
                if (component != null) {
                    keys.add(key);
                }
            }
        }

        return keys;
    }
}
