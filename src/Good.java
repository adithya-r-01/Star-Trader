import com.sun.jdi.AbsentInformationException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class that describes a Good that will be sold in a Marketplace
 */
public abstract class Good {
    private static ArrayList<String> goodNames = initializeGoodNames();
    private final int basePrice;
    private int buyPrice;
    private int sellPrice;
    private Random rand; // provides random price variance
    private String name;
    private int minTechLevel;

    /**
     * Constructs a good
     *
     * @param basePrice    the base price, before the price is calculated
     * @param name         the name of the good
     * @param rand         an instance of Random
     * @param r            the Region in which the Good will be sold
     * @param minTechLevel the minimum tech for the Good
     */
    public Good(int basePrice, String name, Random rand,
                Region r, int minTechLevel) {
        int merchantSkill = Main.getPlayer().getMerchant();
        this.basePrice = basePrice;
        this.name = name;
        this.rand = rand;
        this.buyPrice = calculateBuyPrice(r, merchantSkill);
        this.sellPrice = calculateSellPrice(r, merchantSkill);
        this.minTechLevel = minTechLevel;
    }

    /**
     * Method that initializes the goodNames ArrayList with all the String
     * names of possible Goods.
     *
     * @return ArrayList with names of all possible goods
     */
    private static ArrayList<String> initializeGoodNames() {
        ArrayList<String> goodNames = new ArrayList<>();
        goodNames.add("Food");
        goodNames.add("Firearms");
        goodNames.add("Furs");
        goodNames.add("Games");
        goodNames.add("Medicine");
        goodNames.add("Narcotics");
        goodNames.add("Machines");
        goodNames.add("Ore");
        goodNames.add("Water");
        goodNames.add("Robots");
        return goodNames;
    }

    /**
     * getter for goodNames
     *
     * @return goodNames
     */
    public static ArrayList<String> getGoodNames() {
        return goodNames;
    }

    /**
     * Returns whether the good is available in a given region
     *
     * @param r            the region
     * @param minTechLevel the minimum tech level necessary for a good
     * @return availableInRegion
     */
    static boolean availableInRegion(Region r, int minTechLevel) {
        try {
            if (r.getTechLevel().getTechVal() >= minTechLevel) {
                return true;
            }
        } catch (AbsentInformationException | NullPointerException e) {
            e.printStackTrace();
            // Null pointer exception shouldn't happen because we shouldn't
            // pass in a null argument, but it's here just in case.
        }

        return false;
    }

    /**
     * getter for sellToPlayerPrice
     *
     * @return sellToPlayerPrice
     */
    public int getBuyPrice() {
        return buyPrice;
    }

    /**
     * Gets the buy from player price
     *
     * @return BuyFromPlayerPrice
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Gets the name of the good
     * Getter for name of good
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Calculates the sell to player price for the good
     *
     * @param region        the region in which the Good is being sold
     * @param merchantSkill the merchant skill of the Player
     * @return the sell to player price
     */
    public int calculateBuyPrice(Region region, int merchantSkill) {
        try {
            double price = basePrice * ((1.0 + minTechLevel)
                    / (region.getTechLevel().getTechVal() + 1));
            price += (rand.nextInt(10) / 10.0) * (basePrice / 2.0);
            price *= (1 + (48.0 / (merchantSkill + 32)));
            return (int) price;
        } catch (AbsentInformationException e) {
            return 0;
        }
    }

    /**
     * Calculates the buy from player price for the good
     *
     * @param region        the region which is buying the Good
     * @param merchantSkill the merchant skill of the Player
     * @return the buy from player price
     */
    public int calculateSellPrice(Region region, int merchantSkill) {
        try {
            double price = basePrice * ((1.0 + minTechLevel)
                    / (1 + region.getTechLevel().getTechVal()));
            price += (rand.nextInt(10) / 10.0) * (basePrice / 2.0);
            price = price * (1 + ((merchantSkill + 32.0) / 48));
            return (int) price;
        } catch (AbsentInformationException e) {
            return 0;
        }
    }


}
