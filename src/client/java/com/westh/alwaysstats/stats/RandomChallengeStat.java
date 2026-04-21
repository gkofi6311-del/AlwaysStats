package com.westh.alwaysstats.stats;

import net.minecraft.client.Minecraft;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class RandomChallengeStat implements StatProvider {

    private static final String[] CHALLENGES = {
        "Mine 64 iron ore", "Craft a diamond sword", "Kill 10 zombies", "Catch 5 fish",
        "Sleep in a bed", "Build a 10-block tower", "Craft a full set of iron armor",
        "Find a village", "Trade with a villager", "Kill a creeper without taking damage",
        "Grow and harvest wheat", "Tame a wolf", "Ride a horse", "Brew a potion",
        "Kill the Ender Dragon", "Find a stronghold", "Craft an enchantment table",
        "Enchant a sword", "Kill 5 skeletons", "Collect 32 sand blocks",
        "Build a nether portal", "Go to the Nether", "Kill a blaze", "Collect 10 glowstone",
        "Find a nether fortress", "Kill a wither skeleton", "Collect 5 nether quartz",
        "Build a house with a roof", "Place a painting", "Make a bookshelf",
        "Craft a compass", "Craft a clock", "Find a dungeon", "Break a spawner",
        "Collect 64 wood logs", "Craft a bow and 32 arrows", "Kill a spider jockey",
        "Find an ocean monument", "Kill an elder guardian", "Collect sponge",
        "Find a woodland mansion", "Kill an evoker", "Collect a totem of undying",
        "Find a shipwreck", "Find a buried treasure", "Collect a heart of the sea",
        "Find an underwater ruin", "Kill a drowned", "Collect a trident",
        "Find a desert temple", "Find a jungle temple", "Find an igloo",
        "Find a pillager outpost", "Kill a pillager captain", "Start a raid",
        "Win a raid", "Collect 10 emeralds", "Max out your hunger bar",
        "Collect 64 cobblestone", "Make a stone pickaxe", "Make an iron axe",
        "Collect 10 coal", "Smelt 32 iron ingots", "Cook 10 fish",
        "Make a cake", "Make a pumpkin pie", "Collect 5 melon slices",
        "Grow a pumpkin", "Grow a melon", "Collect sugar cane",
        "Make paper", "Make a book", "Make a bookshelf",
        "Find a cave", "Mine to y=0", "Find diamonds",
        "Collect 5 diamonds", "Craft diamond boots", "Craft a diamond pickaxe",
        "Collect obsidian", "Craft a diamond chestplate", "Enchant a piece of armor",
        "Get a Fortune pickaxe", "Get a Silk Touch pickaxe", "Get a Sharpness sword",
        "Get a Power bow", "Kill a mob with a bow from 50+ blocks away", "Tame a cat",
        "Get a parrot to sit on your shoulder", "Breed two cows", "Breed two pigs",
        "Breed two chickens", "Collect 32 feathers", "Collect 16 leather",
        "Craft a saddle... wait, find one", "Ride a pig", "Ride a strider",
        "Go to the End", "Collect an ender pearl", "Kill an enderman",
        "Collect 12 ender pearls", "Activate the end portal", "Kill the dragon",
        "Collect a dragon egg", "Go to the End cities", "Find an elytra",
        "Fly with elytra", "Use fireworks with elytra", "Collect shulker shells",
        "Make a shulker box", "Collect 5 chorus fruit", "Eat chorus fruit",
        "Teleport using chorus fruit", "Collect end stone", "Collect purpur blocks",
        "Build something in the End", "Kill the Wither", "Collect a nether star",
        "Build a beacon", "Activate a beacon", "Get a full beacon effect",
        "Collect ancient debris", "Smelt netherite scrap", "Craft netherite ingot",
        "Upgrade a diamond tool to netherite", "Find bastion remnant", "Kill a piglin brute",
        "Barter with a piglin", "Collect crying obsidian", "Craft respawn anchor",
        "Kill a hoglin", "Collect warped fungus", "Collect crimson fungus",
        "Build in the Nether", "Collect soul sand", "Make a soul torch",
        "Collect a magma block", "Collect fire charge", "Shoot a fireball",
        "Collect 10 gold ingots", "Craft golden armor", "Wear a pumpkin on your head",
        "Collect a jack o lantern", "Collect 5 lily pads", "Collect a nautilus shell",
        "Craft a conduit", "Place a conduit underwater", "Collect dried kelp",
        "Eat dried kelp", "Build an underwater base", "Collect prismarine",
        "Collect sea lanterns", "Collect 10 clay", "Craft terracotta",
        "Dye terracotta", "Craft glazed terracotta", "Collect concrete powder",
        "Make concrete", "Collect all 16 colors of wool", "Make a banner",
        "Put a pattern on a banner", "Collect a map", "Fill in a map",
        "Make a cartography table", "Zoom out a map", "Collect a lodestone",
        "Use a lodestone compass", "Collect amethyst", "Craft spyglass",
        "Use a spyglass", "Collect tuff", "Collect calcite",
        "Find a lush cave", "Find a dripstone cave", "Collect pointed dripstone",
        "Collect moss blocks", "Grow azalea tree", "Collect glow berries",
        "Eat glow berries", "Find an axolotl", "Collect an axolotl in a bucket",
        "Find a glow squid", "Collect a glow ink sac", "Make a glow item frame",
        "Make a glow sign", "Find a goat", "Get hit by a goat",
        "Collect a goat horn", "Find a frog", "Collect frogspawn",
        "Collect froglight", "Find an ancient city", "Collect sculk",
        "Collect sculk catalyst", "Collect sculk shrieker", "Avoid a warden",
        "Kill a warden", "Collect a swift sneak enchantment", "Find a mangrove swamp",
        "Collect mangrove logs", "Collect mud", "Make packed mud",
        "Make mud bricks", "Collect a tadpole in a bucket", "Find a cherry grove",
        "Collect cherry logs", "Collect pink petals", "Find a trail ruins",
        "Collect a pottery shard", "Make a decorated pot", "Find a suspicious block",
        "Brush a suspicious block", "Find a camel", "Ride a camel",
        "Find bamboo jungle", "Collect bamboo", "Make bamboo planks",
        "Make a bamboo raft", "Collect a chiseled bookshelf", "Place 6 books in bookshelf",
        "Craft an armadillo scute", "Craft wolf armor", "Put armor on a wolf",
        "Find a vault", "Open a vault with a trial key", "Kill a breeze",
        "Collect a wind charge", "Use a wind charge", "Find a trial chamber",
        "Complete a trial chamber", "Collect a heavy core", "Craft a mace",
        "Kill a mob with a mace smash attack", "Collect 64 gravel", "Make flint and steel",
        "Start a fire", "Burn down a tree (oops)", "Collect charcoal",
        "Make 10 torches", "Light up a cave", "Make a campfire",
        "Cook food on a campfire", "Make a soul campfire", "Make a lantern",
        "Make a soul lantern", "Collect 10 string", "Make a fishing rod",
        "Fish for treasure", "Get a nametag from fishing", "Name a mob",
        "Name a sheep Jeb_", "Name a vindicator Johnny", "Collect a music disc",
        "Play a music disc", "Collect all common music discs", "Kill a charged creeper",
        "Get a mob head from charged creeper", "Collect a zombie head", "Wear a mob head",
        "Collect a skeleton skull", "Make note block play with mob head",
        "Collect 10 bones", "Make bone meal", "Grow flowers with bone meal",
        "Collect 5 different flowers", "Make a flower pot", "Plant a flower in a pot",
        "Collect a fern", "Collect dead bush", "Collect a cactus",
        "Build a cactus farm", "Collect 32 sand", "Make glass",
        "Make a glass bottle", "Make a glass pane", "Build a glass tower",
        "Collect red sand", "Make red sandstone", "Find mesa biome",
        "Collect terracotta from mesa", "Find a badlands mineshaft", "Collect gold from badlands",
        "Find a frozen ocean", "Find a mushroom island", "Collect mycelium",
        "Collect a mushroom", "Make mushroom stew", "Collect both mushroom types",
    };

    private static KeyMapping completeKey;
    private static boolean completed    = false;
    private static long    currentDay   = -1;
    private static String  challenge    = CHALLENGES[0];

    public static void register() {
        completeKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.alwaysstats.complete_challenge",
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_V,
            "key.categories.alwaysstats"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level == null) return;
            long day = client.level.getDayTime() / 24000L;
            if (day != currentDay) {
                currentDay = day;
                completed  = false;
                challenge  = CHALLENGES[(int)(Math.abs(day) % CHALLENGES.length)];
            }
            while (completeKey.consumeClick()) {
                completed = !completed;
            }
        });
    }

    @Override
    public String getConfigKey() { return "randomChallenge"; }

    @Override
    public String getConfigName() { return "Challenge"; }

    @Override
    public String getDisplayText(Minecraft client) {
        if (completed) {
            return "§a✔ " + challenge;
        } else {
            return "§f☐ " + challenge;
        }
    }
}
