/**
 * Class that describes a Ship. A Player always has one Ship.
 */
public class Ship {

    private String name;
    private double fuelCap;
    private double currFuel;
    private int health;
    private Inventory inventory;

    /**
     * Constructs a new Ship
     *
     * @param name         the name of the ship
     * @param inventoryCap the capacity of the ship's inventory
     * @param fuelCap      the maximum fuel it can have
     * @param health       the health of the ship
     */
    public Ship(String name, int inventoryCap, double fuelCap, int health) {
        this.name = name;
        this.fuelCap = fuelCap;
        this.currFuel = fuelCap;
        this.health = health;
        inventory = new Inventory(inventoryCap);
    }

    /**
     * Constructs a new basic ship, this is the "default" ship
     */
    public Ship() {
        this("The Basic", 20, 300, 100);
    }

    public Region travel(Region curr, Region next, boolean abortTravel) {
        double fuelToTravel = (Region.distance(curr, next) * (1 + (1
                - ((Main.getPlayer().getPilot()) / 17.0))));
        if (currFuel - fuelToTravel <= 0) {
            System.out.println("Cannot travel due to insufficient fuel");
            return curr;
        }
        currFuel -= fuelToTravel;
        if (abortTravel) {
            return curr;
        }
        return next;
    }

    public void refuel(double fuelAdd) {
        currFuel += fuelAdd;
    }

    public void repair(int healthAdd) {
        health += healthAdd;
    }

    /**
     * Gets the inventory
     *
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }


    public double maxTravelDistance() {
        return currFuel / (1 + (1 - ((Main.getPlayer().getPilot()) / 17.0)));
    }

    /**
     * Getter for health
     *
     * @return the current health of the ship
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter for health
     *
     * @param health set the desired health value of the ship
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public double getCurrFuel() {
        return currFuel;
    }
}
