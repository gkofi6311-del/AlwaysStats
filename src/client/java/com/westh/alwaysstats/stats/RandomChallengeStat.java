package com.westh.alwaysstats.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class RandomChallengeStat implements StatProvider {

    private static final KeyMapping.Category CATEGORY =
        KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath("alwaysstats", "alwaysstats"));

    private static final String[] CHALLENGES = {
        // ... (all your challenges stay exactly the same)
