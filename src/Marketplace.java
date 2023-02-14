/**
 * Class that describes a Marketplace within a Region
 */
public class Marketplace {
    private Furs furs;
    private Ore ore;
    private Food food;
    private Firearms firearms;
    private Games games;
    private Machines machines;
    private Medicine medicine;
    private Narcotics narcotics;
    private Water water;
    private Robots robots;

    /**
     * Constructs a new Marketplace
     *
     * @param r the region in which the marketplace will be in
     */
    public Marketplace(Region r) {
        furs = new Furs(r);
        ore = new Ore(r);
        food = new Food(r);
        firearms = new Firearms(r);
        games = new Games(r);
        machines = new Machines(r);
        medicine = new Medicine(r);
        narcotics = new Narcotics(r);
        water = new Water(r);
        robots = new Robots(r);
    }

    /**
     * Takes in a good, and sells it to the player if they have sufficient
     * credits.
     *
     * @param good the good that is being bought
     * @param p    the player that is buying
     */
    public void buyGood(Good good, Player p) {
        int credits = p.getCredits();
        Inventory inventory = p.getShip().getInventory();

        if (good.getBuyPrice() < credits) { // enough money for the
            // player to buy
            try {
                inventory.add(good.getName());
                p.spend(good.getBuyPrice());
            } catch (InventoryFullException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Takes in a good, and buys it from the player if the player has that
     * good in their inventory.
     *
     * @param good the good being sold
     * @param p    the player that is selling
     */
    public void sellGood(Good good, Player p) {
        Inventory inventory =
                p.getShip().getInventory();
        int credits = p.getCredits();

        try {
            inventory.remove(good.getName());
            p.earn(good.getSellPrice());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void refuelShip() {
        int fuelCost = (int) ((300 - Main.getPlayer().getShip().getCurrFuel()) * 0.25);
        if (Main.getPlayer().getCredits() >= fuelCost) {
            Main.getPlayer().getShip().refuel(300.0 - Main.getPlayer().getShip().getCurrFuel());
            Main.getPlayer().setCredits(Main.getPlayer().getCredits() - fuelCost);
        } else {
            Main.getPlayer().getShip().refuel(Main.getPlayer().getCredits() * 4);
            Main.getPlayer().setCredits(0);
        }
    }

    public void repairShip() {
        int repairCost = getRepairPrice();
        if (Main.getPlayer().getCredits() >= repairCost) {
            Main.getPlayer().getShip().repair(100 - Main.getPlayer().getShip().getHealth());
            Main.getPlayer().setCredits(Main.getPlayer().getCredits() - repairCost);
        } else {
            int healthAdd = (int) (Main.getPlayer().getCredits() / (1 + (48.0
                    / (Main.getPlayer().getEngineer() + 32))));
            Main.getPlayer().getShip().repair(healthAdd);
            Main.getPlayer().setCredits(0);
        }
    }

    public int getRepairPrice() {
        double price = (100 - Main.getPlayer().getShip().getHealth());
        price *= (1 + (48.0 / (Main.getPlayer().getEngineer() + 32)));
        return (int) price;
    }

    /**
     * Gets furs
     *
     * @return furs
     */
    public Furs getFurs() {
        return furs;
    }

    /**
     * Gets ore
     *
     * @return ore
     */
    public Ore getOre() {
        return ore;
    }

    /**
     * Gets food
     *
     * @return food
     */
    public Food getFood() {
        return food;
    }

    /**
     * Gets firearms
     *
     * @return firearms
     */
    public Firearms getFirearms() {
        return firearms;
    }

    /**
     * Gets games
     *
     * @return games
     */
    public Games getGames() {
        return games;
    }

    /**
     * Gets machines
     *
     * @return machines
     */
    public Machines getMachines() {
        return machines;
    }

    /**
     * Gets medicine
     *
     * @return medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * Gets narcotics
     *
     * @return narcotics
     */
    public Narcotics getNarcotics() {
        return narcotics;
    }

    /**
     * Gets water
     *
     * @return robots
     */
    public Water getWater() {
        return water;
    }

    /**
     * Gets robots
     *
     * @return robots
     */
    public Robots getRobots() {
        return robots;
    }
}
