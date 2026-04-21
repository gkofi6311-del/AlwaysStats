private static final String[] CHALLENGES = new String[300];

static {
    String[] verbs = {
        "Catch", "Breed", "Kill", "Mine", "Craft", "Find", "Build", "Tame", "Explore", "Trade"
    };

    String[] targets = {
        "5 fish", "2 cows", "10 zombies", "20 stone", "a bookshelf",
        "a village", "a castle wall", "a wolf", "a horse", "a desert temple",
        "a nether fortress", "a creeper", "iron ore", "diamonds", "a full armor set"
    };

    for (int i = 0; i < 300; i++) {
        String v = verbs[i % verbs.length];
        String t = targets[(i * 3) % targets.length];
        CHALLENGES[i] = v + " " + t + " #" + (i + 1);
    }
}
