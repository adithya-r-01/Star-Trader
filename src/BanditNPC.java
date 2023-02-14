import java.util.Random;

/**
 * Class that describes a BanditNPC
 *
 * @author Adithya Ramanujam
 * @version 1.0
 */
public class BanditNPC extends NPC {

    private static BanditNPC banditNPC;


    private int payAmount;
    private int currCredits;
    private Random rand;
    private int shipHealth;
    private int healthLoss;

    /**
     * Private constructor to create a new BanditNPC
     */
    private BanditNPC() {
        super();
        rand = new Random();
    }

    /**
     * Method to get the BanditNPC if it exists otherwise one is created
     *
     * @return the banditNPC
     */
    public static BanditNPC getBanditNPC() {
        if (banditNPC == null) {
            banditNPC = new BanditNPC();
        }
        return banditNPC;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public int getHealthLoss() {
        return healthLoss;
    }

    public void startInteraction(Player player) {
        currCredits = player.getCredits();
        payAmount =
                (currCredits - 3 * (currCredits / 4)) + rand.nextInt(currCredits / 2);
        shipHealth = player.getShip().getHealth();
        healthLoss = (shipHealth - 4 * (shipHealth / 5) + rand.nextInt(
                shipHealth / 5));
    }

    /**
     * Method to pay the bandit's randomized demand, if that does not work
     * then the bandit will clear the player's
     * inventory, and finally if the inventory is empty then the player loses
     * credit and ship health.
     *
     * @param player the player that is paying
     * @return Whether the player paid the trader or not
     */
    public boolean pay(Player player) {
        Inventory inventory = player.getShip().getInventory();

        if (payAmount < currCredits) {
            player.setCredits(currCredits - payAmount);
            return true;
        } else {
            if (!inventory.isEmpty()) {
                inventory.clear();
            } else {
                damageHealth(player.getShip());
            }
        }
        return false;
    }

    /**
     * Method called when the player is attempting to flee the bandit. If the
     * attempt is successful then the player
     * would still lose the fuel and return the previous region. Otherwise the
     * player would lose health and credits.
     *
     * @param player the player that is fleeing
     * @return whether the flee attempt was successful or not.
     */
    public boolean flee(Player player) {
        if (super.rollPilot(player)) {
            return true;
        } else {
            player.setCredits((int) (player.getCredits() * 0.4));
            damageHealth(player.getShip());
        }
        return false;
    }

    /**
     * Method that is called when the player attempts to fight the bandit.
     * If it is successful then the player would receive some of the bandits
     * credits. Otherwise the player's ship will lose health.
     *
     * @param player the player that is fighting
     * @return whether the fight attempt is successful
     */
    public boolean fight(Player player) {
        if (super.rollFighter(player)) {
            Main.getPlayer().setCredits(currCredits + payAmount);
            return true;
        } else {
            Main.getPlayer().setCredits((int) (player.getCredits() * 0.4));
            damageHealth(player.getShip());
        }
        return false;
    }

    private void damageHealth(Ship ship) {
        ship.setHealth(shipHealth - healthLoss);
    }
}
