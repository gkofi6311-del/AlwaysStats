package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;

public class MoodStat implements StatProvider {
    private static final String[] MOODS = {
        "😊 Feeling good!", "😐 Just vibing", "😢 A little sad",
        "😤 Ready to fight", "😴 Getting sleepy", "🤩 Absolutely loving it",
        "😬 A bit nervous", "🥳 Party time!", "🧐 Deep in thought",
        "😅 That was close!", "😎 Cool as ever", "🤔 Thinking..."
    };

    @Override
    public String getConfigKey() { return "mood"; }

    @Override
    public String getConfigName() { return "Mood"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.level == null) return null;
        long day = client.level.getDayTime() / 24000L;
        return getConfigName() + ": " + MOODS[(int)(Math.abs(day) % MOODS.length)];
    }
}
