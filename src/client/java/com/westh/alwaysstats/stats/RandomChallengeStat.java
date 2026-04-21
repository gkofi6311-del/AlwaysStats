package com.westh.alwaysstats.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class RandomChallengeStat implements StatProvider {

    private static final String[] CHALLENGES = {
        // Farming & Food
        "Mine 64 iron ore",
        "Catch 5 fish",
        "Breed 2 cows",
        "Make a cake",
        "Grow a pumpkin",
        "Collect 64 melon slices",
        "Plant a tree farm with 10 trees",
        "Build a 10x10 farm",
        "Cook 32 chicken",
        "Collect 64 bamboo",
        "Make mushroom stew",
        "Breed every farmable animal",
        "Cook every type of food",
        "Collect 64 carrots",
        "Collect 64 potatoes",
        "Grow 64 flowers",
        "Make a composter",
        "Collect all 16 colors of wool",
        "Make a bone meal farm",
        "Harvest 64 wheat",
        "Make bread 16 times",
        "Collect 32 eggs",
        "Make a pumpkin pie",
        "Find and eat a golden apple",
        "Collect 10 honey bottles",
        "Find a beehive",
        "Make a bee farm",
        "Collect 64 sugar cane",
        "Make 16 sugar",
        "Brew a fermented spider eye",

        // Building
        "Build a house out of dirt",
        "Build a medieval tower",
        "Build a modern house",
        "Build a treehouse",
        "Build an underwater base",
        "Build a mountain base",
        "Build a desert pyramid",
        "Build a jungle temple",
        "Build a viking longhouse",
        "Build a Japanese pagoda",
        "Build a barn with animals inside",
        "Build a blacksmith shop",
        "Build a library",
        "Build a market stall",
        "Build a fountain",
        "Build a statue of a creeper",
        "Build a pixel art of a face",
        "Build a maze",
        "Build a working clock tower",
        "Build an arena for mob fights",
        "Build a lighthouse on the ocean",
        "Build a castle wall",
        "Build a sky base",
        "Build a sky bridge",
        "Build entirely underground for a day",
        "Build a dock",
        "Build a stable for 3 horses",
        "Build a windmill",
        "Build a secret room in your base",
        "Build a bridge over lava",
        "Build a nether base",
        "Build a hidden staircase",
        "Build a working elevator",
        "Build a minecart railway",
        "Build a mob farm",
        "Build a gold farm",
        "Build an iron golem",
        "Build a snow golem",
        "Build a beacon",
        "Build a conduit",
        "Build a glass dome",
        "Build a pyramid out of sand",
        "Build a floating island base",
        "Build a cave base",
        "Build a ship",
        "Build a bridge 100 blocks long",
        "Build a wall around your base",
        "Build a moat around your base",
        "Build a castle with a drawbridge",
        "Build a roller coaster",

        // Combat
        "Kill 10 zombies with a sword",
        "Kill a creeper without taking damage",
        "Kill an enderman",
        "Kill a ghast",
        "Kill a witch",
        "Kill a drowned",
        "Kill a phantom",
        "Kill a ravager",
        "Kill an evoker",
        "Kill 5 pillagers",
        "Kill a hoglin",
        "Kill a piglin brute",
        "Kill a wither skeleton",
        "Kill a blaze",
        "Kill an elder guardian",
        "Kill the Ender Dragon",
        "Kill the Wither",
        "Kill 10 mobs using only a bow",
        "Snipe a creeper from 50 blocks",
        "Kill a mob with a firework rocket",
        "Kill 3 mobs with one arrow",
        "Kill a mob in mid-air with an arrow",
        "Kill a mob by knocking it into lava",
        "Survive a raid",
        "Survive 5 minutes on fire",
        "Kill a boss using only potions",
        "Kill the Ender Dragon without armor",
        "Kill 20 skeletons",
        "Kill 20 spiders",
        "Kill 10 husks",

        // Exploration
        "Find a desert temple",
        "Find a shipwreck",
        "Find a jungle temple",
        "Find a stronghold",
        "Find a village",
        "Find a nether fortress",
        "Find a bastion remnant",
        "Find a ruined portal",
        "Find an ocean monument",
        "Find a woodland mansion",
        "Find a pillager outpost",
        "Find a coral reef",
        "Find an igloo",
        "Find a panda",
        "Find all nether biomes",
        "Find an end city",
        "Swim across an ocean",
        "Travel 1000 blocks by minecart",
        "Travel 2000 blocks in the nether",
        "Fly 1000 blocks with elytra",
        "Ride a horse for 500 blocks",
        "Ride a strider across lava",
        "Ride a pig for 100 blocks",
        "Find a treasure chest",
        "Explore 5 different biomes in one day",
        "Travel to Y level 0",
        "Reach the top of a mountain",
        "Find a mushroom island",
        "Find a frozen ocean",
        "Find a badlands biome",

        // Crafting & Collecting
        "Craft a full set of leather armor",
        "Craft a bookshelf",
        "Craft enchanting table",
        "Make a brewing stand",
        "Make a lead",
        "Make a map",
        "Make stained glass",
        "Make a campfire",
        "Make a smoker",
        "Make a blast furnace",
        "Make a scaffolding tower",
        "Make 10 TNT",
        "Make a piston door",
        "Make a minecart railway",
        "Make a crossbow",
        "Make a grindstone",
        "Make an anvil",
        "Make a respawn anchor",
        "Make a full set of netherite armor",
        "Make a netherite ingot",
        "Make a beacon",
        "Make a conduit",
        "Make every type of sword",
        "Make every type of pickaxe",
        "Make every type of axe",
        "Make every type of boat",
        "Make every type of potion",
        "Make 10 fire charges",
        "Make a full set of crimson furniture",
        "Make a shulker box",
        "Collect 64 iron ore",
        "Collect 64 sand",
        "Collect 64 gravel",
        "Collect 64 clay",
        "Collect 64 gunpowder",
        "Collect 64 bones",
        "Collect 64 quartz",
        "Collect 64 soul sand",
        "Collect 64 obsidian",
        "Collect 64 end stone",
        "Collect 64 blackstone",
        "Collect 32 diamonds",
        "Collect every type of wood",
        "Collect all 16 colors of wool",
        "Collect every dye",
        "Collect every music disc",
        "Collect every banner pattern",
        "Collect every type of mushroom",
        "Collect warped wood",
        "Collect crimson wood",
        "Collect 10 heart of the sea",
        "Collect 10 nautilus shells",
        "Collect shulker shells",
        "Collect gilded blackstone",
        "Collect every type of fish",
        "Collect every type of ore",
        "Mine ancient debris",
        "Smelt 64 iron ingots",
        "Smelt 64 gold ingots",
        "Collect 64 redstone dust",

        // Enchanting & Potions
        "Get a full set of max enchanted armor",
        "Get a sharpness V sword",
        "Get a Power V bow",
        "Enchant every tool to max",
        "Make a mending book",
        "Fix an item using mending",
        "Disenchant 5 items",
        "Rename every tool you own",
        "Make a fire resistance potion",
        "Swim in lava with fire resistance",
        "Throw a splash potion at an enemy",
        "Make a lingering potion",
        "Brew 10 strength potions",
        "Kill a mob with poison potion",
        "Make every type of arrow",
        "Brew a slow falling potion",
        "Brew a night vision potion",
        "Brew an invisibility potion",
        "Brew a speed potion",
        "Brew a jump boost potion",

        // Redstone
        "Build a redstone door",
        "Build a redstone trap",
        "Build a working elevator with redstone",
        "Make a note block song",
        "Build a TNT cannon",
        "Build a redstone clock",
        "Build a hidden door",
        "Build a drawbridge with redstone",
        "Build a sorting system",
        "Build an auto farm",

        // Mobs & Animals
        "Tame a wolf",
        "Tame a cat",
        "Breed 5 different animals",
        "Name 5 mobs with name tags",
        "Name a sheep Jeb_",
        "Name a vindicator Johnny",
        "Trade with 5 villagers",
        "Barter with piglins 10 times",
        "Get a totem of undying",
        "Find a strider",
        "Find every passive mob",
        "Find a mooshroom",
        "Find an axolotl",
        "Find a glow squid",
        "Find a goat",

        // Challenges & Restrictions
        "Eat only bread for today",
        "Never sprint for an entire day",
        "Only eat carrots today",
        "Mine only with stone tools today",
        "Survive without a bed for 3 nights",
        "Only eat meat today",
        "Only use wooden tools for a day",
        "Never go below Y level 0 today",
        "Stay above Y level 100 all day",
        "Never use a crafting table today",
        "Only travel by boat today",
        "Never attack first today",
        "Only build with one material today",
        "Eat only fish today",
        "Play the entire day in the nether",

        // Fun & Miscellaneous
        "Blow up a hill with TNT",
        "Set 5 things on fire with fire charges",
        "Design a custom shield",
        "Build a flag for your base",
        "Collect every banner pattern",
        "Make a pixel art build",
        "Fill a shulker box with diamonds",
        "Get elytra",
        "Sleep in a bed far from spawn",
        "Find and activate an end portal",
        "Sleep 10 nights in a row",
        "Survive a raid",
        "Die 0 times today",
        "Get every achievement in one day",
        "Make a working mob zoo",
        "Build a village from scratch",
        "Make a full netherite beacon",
        "Collect 1000 blocks of any material",
        "Name every animal in your farm",
        "Make the tallest tower possible",
        "Dig straight down to bedrock",
        "Fill a chest with only diamonds",
        "Build a house using only glass",
        "Make a rainbow build with all 16 colors",
        "Build a replica of your real home",
        "Create an automatic chicken farm",
        "Build a working rollercoaster",
        "Make a underwater tunnel 100 blocks long",
        "Build a fully lit cave base",
        "Collect 1 of every block in the game",
    };

    // Tracks which challenge indices have been completed
    private static final Set<Integer> completedChallenges = new HashSet<>();

    private static KeyMapping completeKey;
    private static boolean registered = false;

    public RandomChallengeStat() {
        if (!registered) {
            registered = true;

            // Register the keybind: press V to mark challenge complete/incomplete
            completeKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.alwaysstats.complete_challenge",
                GLFW.GLFW_KEY_V,
                "key.categories.alwaysstats"
            ));

            // Check for key press each tick
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                if (client.level == null) return;
                while (completeKey.consumeClick()) {
                    int index = getCurrentIndex(client);
                    if (completedChallenges.contains(index)) {
                        completedChallenges.remove(index);
                    } else {
                        completedChallenges.add(index);
                    }
                }
            });
        }
    }

    private static int getCurrentIndex(Minecraft client) {
        long day = client.level.getDayTime() / 24000L;
        return (int)(day % CHALLENGES.length);
    }

    @Override
    public String getConfigKey() {
        return "randomChallenge";
    }

    @Override
    public String getConfigName() {
        return "Random Challenge";
    }

    @Override
    public String getDisplayText(Minecraft client) {
        if (client.level == null) return null;
        int index = getCurrentIndex(client);
        boolean done = completedChallenges.contains(index);
        if (done) {
            return "§a✔ " + CHALLENGES[index]; // green with checkmark
        } else {
            return "§f☐ " + CHALLENGES[index]; // white with empty box
        }
    }

    @Override
    public Component getDisplayComponent(Minecraft client) {
        String text = getDisplayText(client);
        return text != null ? Component.literal(text) : null;
    }
}
