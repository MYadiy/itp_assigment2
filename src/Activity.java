public enum Activity {
    ROCK_CLIMBING("Rock Climbing"),
    HIKING("Hiking"),
    BUSH_WALKING("Bush Walking"),
    CAVING("Caving"),
    MOUNTAIN_BIKING("Mountain Biking"),
    PADDLING("paddling"),
    ALL("All");

    private final String displayName;

   
    Activity(String displayName) {
        this.displayName = displayName;
    }

    
    public String getDisplayName() {
        return displayName;
    }

    
    public static Activity fromDisplayName(String displayName) {
        for (Activity activity : Activity.values()) {
            if (activity.getDisplayName().equalsIgnoreCase(displayName)) {
                return activity;
            }
        }
        throw new IllegalArgumentException("Unknown activity: " + displayName);
    }

    public static void displayAllActivities() {
        for (Activity activity : Activity.values()) {
            System.out.println(activity.ordinal() + 1 + ". " + activity.getDisplayName());
        }
    }
}
