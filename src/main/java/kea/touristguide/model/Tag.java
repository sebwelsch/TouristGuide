package kea.touristguide.model;

public enum Tag {
    ART("Kunst"),
    MUSEUM("Museum"),
    CHILDFRIENDLY("Børnevenlig"),
    NATURE("Natur"),
    FREE("Gratis"),
    ENTERTAINMENT("Underholdning"),
    AMUSEMENTS("Forlystelser");

    private final String displayName;

    Tag(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
