package com.westh.alwaysstats.stats;
        "Catch 5 fish",
        "Breed 2 cows",
        "Build a castle wall",
        "Kill 10 zombies with a sword",
        "Find a village",
        "Craft a bookshelf",
        "Tame a wolf",
        "Eat only bread for today",
        "Collect 1 of every block in the game"
    };

    private static final Set<Integer> completedChallenges = new HashSet<>();

    private static KeyMapping completeKey;
    private static boolean registered = false;

    public RandomChallengeStat() {
        if (!registered) {
            registered = true;

            completeKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.alwaysstats.complete_challenge",
                GLFW.GLFW_KEY_V,
                KeyMapping.Category.create("key.categories.alwaysstats")
            ));

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
            return "§a✔ " + CHALLENGES[index];
        } else {
            return "§f☐ " + CHALLENGES[index];
        }
    }

    @Override
    public Component getDisplayComponent(Minecraft client) {
        String text = getDisplayText(client);
        return text != null ? Component.literal(text) : null;
    }
}
