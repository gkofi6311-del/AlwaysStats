package com.westh.alwaysstats.stats;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RandomChallengeStat implements StatProvider {
    
    public static final KeyBinding CHALLENGE_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.alwaysstats.random_challenge",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.categories.alwaysstats" // This String is correct HERE
    ));

    @Override
    public String getLabel() {
        return "Challenge";
    }

    @Override
    public String getValue() {
        return "Press " + CHALLENGE_KEY.getBoundKeyLocalizedText().getString();
    }

    @Override
    public int getColor() {
        return 0xFF5555; // red
    }
    
    // If you had a method that took Category instead of String, delete that line.
    // The "key.categories.alwaysstats" string only belongs in the KeyBinding constructor above.
}
