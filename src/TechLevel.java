/**
 * Enum describing the Tech Level of a Region. Each TechLevel has an
 * associated number, where a higher number is more advanced.
 */
public enum TechLevel {
    PRE_AG(0, "Pre-Agricultural"), AG(1, "Agricultural"), MEDIEVAL(2, "Medieval"),
    RENAISSANCE(3, "Renaissance"), EARLY_INDUSTRIAL(4, "Early Industrial"),
    INDUSTRIAL(5, "Industrial"), POST_INDUSTRIAL(6, "Post-Industrial"), HI_TECH(7, "Hi-Tech");

    private int techVal;
    private String str;

    /**
     * Constructs a TechLevel enum
     *
     * @param techVal the integer value describing the TechLevel
     * @param str     the formatted string describing the TechLevel
     */
    TechLevel(int techVal, String str) {
        this.techVal = techVal;
        this.str = str;
    }

    /**
     * Gets techVal
     *
     * @return techVal
     */
    public int getTechVal() {
        return techVal;
    }

    public String getStr() {
        return str;
    }
}
