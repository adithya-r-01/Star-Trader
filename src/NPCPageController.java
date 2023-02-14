import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class NPCPageController {

    private final Player player = Main.getPlayer();
    private final PoliceNPC police = PoliceNPC.getPoliceNPC();
    private final TraderNPC trader = TraderNPC.getTraderNPC();
    private final BanditNPC bandit = BanditNPC.getBanditNPC();
    @FXML
    private Label npcType;
    @FXML
    private Button action1;
    @FXML
    private Button action2;
    @FXML
    private Button action3;
    @FXML
    private Button action4;
    private Main mainApp;
    private Region travelTo;
    private String npcName;
    private boolean travel;

    void setupPage() {
        if (npcName.equals("Bandit")) {
            setupBandit();
        } else if (npcName.equals("Police")) {
            setupPolice();
        } else if (npcName.equals("Trader")) {
            setupTrader();
        }
    }

    /**
     * Method used to setup labels and buttons when the player encounters a
     * TraderNPC
     */
    private void setupTrader() {
        trader.startInteraction(player);
        npcType.setText("You have encountered a trader! He would like to sell "
                + trader.getTraderInventory() + " " + trader.getTradeGood()
                + " for "
                + trader.getPrice() + " cr.");
        action1.setText("Buy");
        action1.setOnAction(e -> traderBuy(trader));
        action2.setText("Ignore");
        action2.setOnAction(e -> {
            travel = true;
            finishEncounter();
        });
        action3.setText("Rob");
        action3.setOnAction(e -> traderRob(trader));
        action4.setText("Negotiate");
        action4.setOnAction(e -> traderNegotiate(trader));
    }

    /**
     * Method used to set up the buttons and labeling for when the player
     * encounters a BanditNPC
     */
    private void setupBandit() {
        bandit.startInteraction(player);
        npcType.setText(String.format("You have encountered a bandit! They "
                + "request %d credits!", bandit.getPayAmount()));
        action1.setText("Pay");
        action1.setOnAction(e -> banditPay());
        action2.setText("Flee");
        action2.setOnAction(e -> banditFlee(player));
        action3.setText("Fight");
        action3.setOnAction(e -> banditFight(player));
        action4.setText("");
        action4.setDisable(true);
    }

    /**
     * Method used to set up the buttons when the interaction is with a
     * PoliceNPC
     */
    private void setupPolice() {
        police.startInteraction(player);
        npcType.setText(String.format("You have encountered the police! They "
                + "demand your stolen %s!", police.getStolenGood()));

        action1.setText("Forfeit Goods");
        action1.setOnAction(e -> policeForfeit(player.getShip().getInventory()));

        action2.setText("Flee");
        action2.setOnAction(e -> policeFlee(player));

        action3.setText("Fight");
        action3.setOnAction(e -> policeFight(player));

        action4.setText("");
        action4.setDisable(true);
    }

    /**
     * Method called when the user chooses to fight during a police encounter
     *
     * @param player the player
     */
    private void policeFight(Player player) {
        boolean success = police.fight(player);
        if (success) {
            npcType.setText("You successfully fought off the police!");
        } else {
            if (player.getShip().getHealth() <= 0) {
                mainApp.initGameOverScreen();
            }
            npcType.setText(String.format("You have failed to fight off the "
                            + "police! You've lost one %s and your ship health is now %d",
                    police.getStolenGood(), player.getShip().getHealth()));
        }
        travel = success;
        allowTravel();
    }

    /**
     * Method called when the player chooses to flee during a police encounter
     *
     * @param player the player
     */
    private void policeFlee(Player player) {
        boolean success = police.flee(player);
        if (success) {
            npcType.setText("You have successfully fled back to your "
                    + "original region!");
        } else {
            if (player.getShip().getHealth() <= 0) {
                mainApp.initGameOverScreen();
            }
            npcType.setText(String.format("You have failed to flee. Your "
                            + "ship now has %d health and 1 %s has been confiscated.",
                    player.getShip().getHealth(), police.getStolenGood()));
        }
        travel = false;
        allowTravel();
    }

    /**
     * Method called when the user chooses to forfeit their items during a
     * police encounter
     *
     * @param inventory the player's inventory
     */
    private void policeForfeit(Inventory inventory) {
        police.forfeitItems(inventory);
        npcType.setText(String.format("You have forfeited 1 %s!",
                police.getStolenGood()));
        travel = true;
        allowTravel();
    }

    /**
     * Method that handles traveling logic when the button to travel is clicked
     */
    private void finishEncounter() {
        if (travel) {
            travelTo.setVisited(true);
            player.travel(travelTo, false);
        } else {
            player.travel(travelTo, true);
        }
        mainApp.initRegionPage();
    }

    /**
     * Method that changes the UI to allow for traveling at the conclusion of
     * the NPC encounter
     */
    private void allowTravel() {
        action1.setOnAction(e -> finishEncounter());
        action1.setText("Travel");
        action2.setText("");
        action2.setDisable(true);
        action3.setText("");
        action3.setDisable(true);
        action4.setText("");
        action4.setDisable(true);
    }

    private void traderBuy(TraderNPC trader) {
        trader.buyGoods(player);
        npcType.setText(
                "You have purchased " + trader.getTraderInventory() + " "
                        + trader.getTradeGood() + " for " + trader.getPrice() + " cr.");
        allowTravel();
        travel = true;
    }

    private void traderRob(TraderNPC trader) {
        boolean rob = trader.robTrader(player);
        allowTravel();
        travel = true;
        if (rob) {
            npcType.setText(
                    "You have successfully robbed the trader! You have now added "
                            + trader.getTraderInventory() + " " + trader.getTradeGood()
                            + " to your inventory");
        } else {
            if (player.getShip().getHealth() <= 0) {
                mainApp.initGameOverScreen();
            }
            npcType.setText(
                    "You have unsuccessfully robbed the trader. Your ship has taken damage "
                            + "and is now at " + player.getShip().getHealth()
                            + " health!");
        }
    }

    private void traderNegotiate(TraderNPC trader) {
        boolean negotiate = trader.negotiate(player);
        if (negotiate) {
            npcType.setText("Negotiation Successful! He would now like to sell "
                    + trader.getTraderInventory() + " " + trader.getTradeGood()
                    + " for "
                    + trader.getPrice() + " cr.");
        } else {
            npcType.setText("Negotiation Failed! He would now like to sell "
                    + trader.getTraderInventory() + " " + trader.getTradeGood()
                    + " for "
                    + trader.getPrice() + " cr.");
        }
        action4.setDisable(true);
    }


    /**
     * A method that controls paying the bandit.
     */
    private void banditPay() {
        boolean success = bandit.pay(player);
        if (success) {
            npcType.setText(String.format("You have successfully paid off the "
                    + "bandit and "
                    + "now have %d credits!", player.getCredits()));
        } else {
            npcType.setText("You didn't have enough credits to pay the "
                    + "bandit!");
        }
        travel = true;
        allowTravel();
    }

    /**
     * A method that controls fleeing the bandit
     *
     * @param player the player that is fleeing
     */
    private void banditFlee(Player player) {
        boolean success = bandit.flee(player);
        if (success) {
            npcType.setText("You have fled the bandit!");
        } else {
            if (player.getShip().getHealth() <= 0) {
                mainApp.initGameOverScreen();
            }
            npcType.setText(String.format("Your flee attempt was "
                            + "unsuccessful, your ship health is %d.",
                    player.getShip().getHealth()));
        }
        travel = false;
        allowTravel();
    }

    private void banditFight(Player player) {
        boolean success = bandit.fight(player);
        if (success) {
            npcType.setText("You have fought off the bandit!");
        } else {
            if (player.getShip().getHealth() <= 0) {
                mainApp.initGameOverScreen();
            }
            npcType.setText(
                    String.format("Your fight attempt was unsuccessful, "
                            + "your ship health is %d.", player.getShip().getHealth()));
        }
        travel = true;
        allowTravel();
    }


    void setTravelTo(Region travelTo) {
        this.travelTo = travelTo;
    }

    void setNpcName(String npcName) {
        this.npcName = npcName;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
