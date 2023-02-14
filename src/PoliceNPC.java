import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that describes a PoliceNPC
 */
public class PoliceNPC extends NPC {
    private static PoliceNPC policeNPC;
    private static String stolenGood;

    private PoliceNPC() {
        super();
    }

    /**
     * Method used to get the instance of PoliceNPC. If one has not been
     * instantiated yet, this method will call the private constructor.
     *
     * @return the instance of PoliceNPC
     */
    public static PoliceNPC getPoliceNPC() {
        if (policeNPC == null) {
            policeNPC = new PoliceNPC();
        }
        return policeNPC;
    }

    /**
     * Method that begins an interaction with the player
     *
     * @param player the player to interact with
     * @throws IllegalStateException when player inventory is empty
     */
    public void startInteraction(Player player) throws IllegalStateException {
        ArrayList<String> goodNames = Good.getGoodNames();
        Inventory inventory = player.getShip().getInventory();
        if (inventory.isEmpty()) {
            throw new IllegalStateException("Cannot interact with a PoliceNPC"
                    + " when inventory is empty.");
        } else {
            Collections.shuffle(goodNames);
            for (String name : goodNames) {
                if (inventory.get(name) != 0) {
                    stolenGood = name;
                    return;
                }
            }
        }

    }

    public void forfeitItems(Inventory inventory) {

        if (stolenGood == null) { // startInteraction never called
            return;
        }
        try {
            inventory.remove(stolenGood);
        } catch (IllegalStateException e) { // shouldn't happen
            e.printStackTrace();
        }

    }

    /**
     * Method called when the player attempts to flee from the PoliceNPC.
     * Fleeing successfully should result in the player canceling their
     * travel to the new region, but still spending the fuel associated with
     * it. Fleeing unsuccessfully results in the stolen good being forfeited,
     * the ship health being damaged, and a fine levied upon the player. The
     * player then travels back to the original region, spending the fuel
     * regardless.
     *
     * @param player the player attempting to flee
     * @return whether the fleeing is successful or not
     */
    public boolean flee(Player player) {
        if (!rollPilot(player)) {
            Ship ship = player.getShip();
            forfeitItems(ship.getInventory());
            damageHealth(ship);
            player.setCredits((int) (player.getCredits() * 0.9)); // fine
            return false;
        } else {
            return true;
        }
    }

    private void damageHealth(Ship ship) {
        ship.setHealth((int) (ship.getHealth() * 0.9)); // damage health
    }

    /**
     * Method for attempting to fight the PoliceNPC. If fighting is
     * successful, the player garners no penalties and can travel to their
     * intended region. If not, their "stolen" item is confiscated, their
     * ship is damaged, and the travel is aborted.
     *
     * @param player the player fighting the PoliceNPC
     * @return whether fighting/traveling is successful or not
     */
    public boolean fight(Player player) {
        Ship ship = player.getShip();
        boolean successful = rollFighter(player);
        if (!successful) {
            forfeitItems(ship.getInventory());
            damageHealth(ship);
        }
        return successful;
    }

    public String getStolenGood() {
        return stolenGood;
    }
}
