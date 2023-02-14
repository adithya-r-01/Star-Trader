import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TraderNPC extends NPC {

    private static TraderNPC traderNPC;
    private int traderInventory;
    private String tradeGood;
    private int price;
    private Random rand;

    private TraderNPC() {
        super();
        rand = new Random();
    }

    public static TraderNPC getTraderNPC() {
        if (traderNPC == null) {
            return new TraderNPC();
        }
        return traderNPC;
    }

    public void startInteraction(Player player) {
        Inventory inventory = player.getShip().getInventory();
        ArrayList<String> goodNames = Good.getGoodNames();
        Collections.shuffle(goodNames);
        tradeGood = goodNames.get(0);
        traderInventory =
                rand.nextInt(inventory.getCapacity()
                        - inventory.getCount()) + 1;
        price = 100;

    }

    public boolean robTrader(Player player) {
        boolean success = rollFighter(player);
        if (!success) {
            damageHealth(player.getShip());
        } else {
            player.getShip().getInventory().add(tradeGood, traderInventory);
        }
        return success;
    }

    public boolean negotiate(Player player) {
        boolean success = rollMerchant(player);
        if (success) {
            price *= ((double) rand.nextInt(5) / 10) + 0.25;
        } else {
            price *= 1 + (((double) rand.nextInt(3) + 1) / 10);
        }
        return success;
    }

    public void buyGoods(Player player) {
        player.getShip().getInventory().add(tradeGood, traderInventory);
        player.setCredits(player.getCredits() - price);
    }

    private void damageHealth(Ship ship) {
        ship.setHealth((int) (ship.getHealth() * 0.9)); // damage health
    }

    public int getPrice() {
        return price;
    }

    public int getTraderInventory() {
        return traderInventory;
    }

    public String getTradeGood() {
        return tradeGood;
    }
}
