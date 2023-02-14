import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class MarketplaceViewController {

    private final Player player = Main.getPlayer();
    private final Region curr = player.getRegion();
    private final Ship ship = player.getShip();
    private final Inventory inventory = ship.getInventory();
    private final Marketplace marketplace = curr.getMarketplace();
    private Main mainApp;
    @FXML
    private Label lblCredits1;
    @FXML
    private Label lblCredits2;
    @FXML
    private Label lblInventory1;
    @FXML
    private Label lblInventory2;
    @FXML
    private Button btnBuyWater;
    @FXML
    private Button btnBuyFur;
    @FXML
    private Button btnBuyFood;
    @FXML
    private Button btnBuyOre;
    @FXML
    private Button btnBuyGames;
    @FXML
    private Button btnBuyFirearms;
    @FXML
    private Button btnBuyMedicine;
    @FXML
    private Button btnBuyMachines;
    @FXML
    private Button btnBuyNarcotics;
    @FXML
    private Button btnBuyRobots;
    @FXML
    private Label lblNumWater1;
    @FXML
    private Label lblNumFur1;
    @FXML
    private Label lblNumFood1;
    @FXML
    private Label lblNumOre1;
    @FXML
    private Label lblNumGames1;
    @FXML
    private Label lblNumFirearms1;
    @FXML
    private Label lblNumMedicine1;
    @FXML
    private Label lblNumMachines1;
    @FXML
    private Label lblNumNarcotics1;
    @FXML
    private Label lblNumRobots1;
    @FXML
    private Label lblBuyWaterPrice;
    @FXML
    private Label lblBuyFurPrice;
    @FXML
    private Label lblBuyFoodPrice;
    @FXML
    private Label lblBuyOrePrice;
    @FXML
    private Label lblBuyGamesPrice;
    @FXML
    private Label lblBuyFirearmsPrice;
    @FXML
    private Label lblBuyMedicinePrice;
    @FXML
    private Label lblBuyMachinesPrice;
    @FXML
    private Label lblBuyNarcoticsPrice;
    @FXML
    private Label lblBuyRobotsPrice;
    @FXML
    private Button btnSellWater;
    @FXML
    private Button btnSellFur;
    @FXML
    private Button btnSellFood;
    @FXML
    private Button btnSellOre;
    @FXML
    private Button btnSellGames;
    @FXML
    private Button btnSellFirearms;
    @FXML
    private Button btnSellMedicine;
    @FXML
    private Button btnSellMachines;
    @FXML
    private Button btnSellNarcotics;
    @FXML
    private Button btnSellRobots;
    @FXML
    private Label lblNumWater2;
    @FXML
    private Label lblNumFur2;
    @FXML
    private Label lblNumFood2;
    @FXML
    private Label lblNumOre2;
    @FXML
    private Label lblNumGames2;
    @FXML
    private Label lblNumFirearms2;
    @FXML
    private Label lblNumMedicine2;
    @FXML
    private Label lblNumMachines2;
    @FXML
    private Label lblNumNarcotics2;
    @FXML
    private Label lblNumRobots2;
    @FXML
    private Label lblSellWaterPrice;
    @FXML
    private Label lblSellFurPrice;
    @FXML
    private Label lblSellFoodPrice;
    @FXML
    private Label lblSellOrePrice;
    @FXML
    private Label lblSellGamesPrice;
    @FXML
    private Label lblSellFirearmsPrice;
    @FXML
    private Label lblSellMedicinePrice;
    @FXML
    private Label lblSellMachinesPrice;
    @FXML
    private Label lblSellNarcoticsPrice;
    @FXML
    private Label lblSellRobotsPrice;
    @FXML
    private Label lblFuelRemain;
    @FXML
    private Label lblFuelCost;
    @FXML
    private Button btnRefuel;
    @FXML
    private Label lblHealthRemain;
    @FXML
    private Label lblRepairCost;
    @FXML
    private Button btnRepair;
    @FXML
    private Label lblCredits3;
    @FXML
    private Label lblInventory3;
    @FXML
    private Tab tabWin;
    @FXML
    private Button btnBuyWin;

    public void initialize() {
        resetButtons();
        int slots = inventory.getCount();
        int cap = inventory.getCapacity();

        lblCredits1.setText("Cash: " + Main.getPlayer().getCredits() + " cr.");
        lblCredits2.setText("Cash: " + Main.getPlayer().getCredits() + " cr.");
        lblCredits3.setText("Cash: " + Main.getPlayer().getCredits() + " cr.");
        lblInventory1.setText("Inventory Slots: " + slots + "/"
                + cap);
        lblInventory2.setText("Inventory Slots: " + slots + "/"
                + cap);
        lblInventory3.setText("Inventory Slots: " + slots + "/"
                + cap);
        inventoryBar();
        tabWin.setDisable(!curr.isWinItem());

        if (!Water.availableInRegion(curr)) {
            notAvailableSetter(btnBuyWater, btnSellWater,
                    lblBuyWaterPrice, lblSellWaterPrice);
        } else {
            lblBuyWaterPrice.setText(
                    marketplace.getWater().getBuyPrice() + " cr.");
            lblSellWaterPrice.setText(
                    marketplace.getWater().getSellPrice() + " cr.");
            if (!inventory.contains("Water")) {
                btnSellWater.setDisable(true);
            }
        }
        if (!Furs.availableInRegion(curr)) {
            notAvailableSetter(btnBuyFur, btnSellFur,
                    lblBuyFurPrice, lblSellFurPrice);
        } else {
            lblBuyFurPrice.setText(marketplace.getFurs().getBuyPrice() + " cr.");
            lblSellFurPrice.setText(
                    marketplace.getFurs().getSellPrice() + " cr.");
            if (!inventory.contains("Furs")) {
                btnSellFur.setDisable(true);
            }
        }
        if (!Food.availableInRegion(curr)) {
            notAvailableSetter(btnBuyFood, btnSellFood,
                    lblBuyFoodPrice, lblSellFoodPrice);
        } else {
            lblBuyFoodPrice.setText(
                    marketplace.getFood().getBuyPrice() + " cr.");
            lblSellFoodPrice.setText(
                    marketplace.getFood().getSellPrice() + " cr.");
            if (!inventory.contains("Food")) {
                btnSellFood.setDisable(true);
            }
        }
        if (!Ore.availableInRegion(curr)) {
            notAvailableSetter(btnBuyOre, btnSellOre,
                    lblBuyOrePrice, lblSellOrePrice);
        } else {
            lblBuyOrePrice.setText(marketplace.getOre().getBuyPrice() + " cr.");
            lblSellOrePrice.setText(
                    marketplace.getOre().getSellPrice() + " cr.");
            if (!inventory.contains("Ore")) {
                btnSellOre.setDisable(true);
            }
        }
        if (!Games.availableInRegion(curr)) {
            notAvailableSetter(btnBuyGames, btnSellGames,
                    lblBuyGamesPrice, lblSellGamesPrice);
        } else {
            lblBuyGamesPrice.setText(
                    marketplace.getGames().getBuyPrice() + " cr.");
            lblSellGamesPrice.setText(
                    marketplace.getGames().getSellPrice() + " cr.");
            if (!inventory.contains("Games")) {
                btnSellGames.setDisable(true);
            }
        }
        if (!Firearms.availableInRegion(curr)) {
            notAvailableSetter(btnBuyFirearms, btnSellFirearms,
                    lblBuyFirearmsPrice, lblSellFirearmsPrice);
        } else {
            lblBuyFirearmsPrice.setText(
                    marketplace.getFirearms().getBuyPrice() + " cr.");
            lblSellFirearmsPrice.setText(
                    marketplace.getFirearms().getSellPrice() + " cr.");
            if (!inventory.contains("Firearms")) {
                btnSellFirearms.setDisable(true);
            }
        }
        if (!Medicine.availableInRegion(curr)) {
            notAvailableSetter(btnBuyMedicine, btnSellMedicine,
                    lblBuyMedicinePrice, lblSellMedicinePrice);
        } else {
            lblBuyMedicinePrice.setText(
                    marketplace.getMedicine().getBuyPrice() + " cr.");
            lblSellMedicinePrice.setText(
                    marketplace.getMedicine().getSellPrice() + " cr.");
            if (!inventory.contains("Medicine")) {
                btnSellMedicine.setDisable(true);
            }
        }
        if (!Machines.availableInRegion(curr)) {
            notAvailableSetter(btnBuyMachines, btnSellMachines,
                    lblBuyMachinesPrice, lblSellMachinesPrice);
        } else {
            lblBuyMachinesPrice.setText(
                    marketplace.getMachines().getBuyPrice() + " cr.");
            lblSellMachinesPrice.setText(
                    marketplace.getMachines().getSellPrice() + " cr.");
            if (!inventory.contains("Machines")) {
                btnSellMachines.setDisable(true);
            }
        }
        if (!Narcotics.availableInRegion(curr)) {
            notAvailableSetter(btnBuyNarcotics, btnSellNarcotics,
                    lblBuyNarcoticsPrice, lblSellNarcoticsPrice);
        } else {
            lblBuyNarcoticsPrice.setText(
                    marketplace.getNarcotics().getBuyPrice() + " cr.");
            lblSellNarcoticsPrice.setText(
                    marketplace.getNarcotics().getSellPrice() + " cr.");
            if (!inventory.contains("Narcotics")) {
                btnSellNarcotics.setDisable(true);
            }
        }
        if (!Robots.availableInRegion(curr)) {
            notAvailableSetter(btnBuyRobots, btnSellRobots,
                    lblBuyRobotsPrice, lblSellRobotsPrice);
        } else {
            lblBuyRobotsPrice.setText(
                    marketplace.getRobots().getBuyPrice() + " cr.");
            lblSellRobotsPrice.setText(
                    marketplace.getRobots().getSellPrice() + " cr.");
            if (!inventory.contains("Robots")) {
                btnSellRobots.setDisable(true);
            }
        }
        createShipyard();
        checkCredits();
        checkInventoryCap();
        setInventoryValues();
    }

    private void notAvailableSetter(Button buy, Button sell, Label buyPrice, Label sellPrice) {
        buy.setDisable(true);
        sell.setDisable(true);
        buyPrice.setText("Not Sold");
        sellPrice.setText("Not Sold");
    }

    private void createShipyard() {
        lblFuelRemain.setText("Fuel Remaining: " + ship.getCurrFuel() + " / 300.0");
        lblFuelCost.setText("Refuel Cost: " + (int) ((300 - ship.getCurrFuel()) * 0.25) + " cr.");
        lblHealthRemain.setText("Health Remaining: " + ship.getHealth() + " / 100");
        lblRepairCost.setText("Cost: " + marketplace.getRepairPrice() + " cr.");
        if (Main.getPlayer().getCredits() == 0) {
            btnRefuel.setDisable(true);
            btnRepair.setDisable(true);
        } else {
            btnRefuel.setDisable(false);
            btnRepair.setDisable(false);
        }
    }

    private void checkCredits() {
        int credits = Main.getPlayer().getCredits();
        if (marketplace.getWater().getBuyPrice() > credits) {
            btnBuyWater.setDisable(true);
        }
        if (marketplace.getFurs().getBuyPrice() > credits) {
            btnBuyFur.setDisable(true);
        }
        if (marketplace.getFood().getBuyPrice() > credits) {
            btnBuyFood.setDisable(true);
        }
        if (marketplace.getOre().getBuyPrice() > credits) {
            btnBuyOre.setDisable(true);
        }
        if (marketplace.getGames().getBuyPrice() > credits) {
            btnBuyGames.setDisable(true);
        }
        if (marketplace.getFirearms().getBuyPrice() > credits) {
            btnBuyFirearms.setDisable(true);
        }
        if (marketplace.getMedicine().getBuyPrice() > credits) {
            btnBuyMedicine.setDisable(true);
        }
        if (marketplace.getMachines().getBuyPrice() > credits) {
            btnBuyMachines.setDisable(true);
        }
        if (marketplace.getNarcotics().getBuyPrice() > credits) {
            btnBuyNarcotics.setDisable(true);
        }
        if (marketplace.getRobots().getBuyPrice() > credits) {
            btnBuyRobots.setDisable(true);
        }
        if (10000 > credits) {
            btnBuyWin.setDisable(true);
        }
    }

    private void inventoryBar() {
        int count = inventory.getCount();
        int cap = inventory.getCapacity();
        lblCredits1.setText("Cash: " + Main.getPlayer().getCredits() + " cr.");
        lblCredits2.setText("Cash: " + Main.getPlayer().getCredits() + " cr.");
        lblCredits3.setText("Cash: " + Main.getPlayer().getCredits() + " cr.");
        lblInventory1.setText("Inventory Slots: " + count + "/"
            + cap);
        lblInventory2.setText("Inventory Slots: " + count + "/"
            + cap);
        lblInventory3.setText("Inventory Slots: " + count + "/"
            + cap);
    }

    private void resetButtons() {
        btnBuyWater.setDisable(false);
        btnSellWater.setDisable(false);
        btnBuyFur.setDisable(false);
        btnSellFur.setDisable(false);
        btnBuyFood.setDisable(false);
        btnSellFood.setDisable(false);
        btnBuyOre.setDisable(false);
        btnSellOre.setDisable(false);
        btnBuyGames.setDisable(false);
        btnSellGames.setDisable(false);
        btnBuyFirearms.setDisable(false);
        btnSellFirearms.setDisable(false);
        btnBuyMedicine.setDisable(false);
        btnSellMedicine.setDisable(false);
        btnBuyMachines.setDisable(false);
        btnSellMachines.setDisable(false);
        btnBuyNarcotics.setDisable(false);
        btnSellNarcotics.setDisable(false);
        btnBuyRobots.setDisable(false);
        btnSellRobots.setDisable(false);
        btnBuyWin.setDisable(false);
    }

    private void checkInventoryCap() {
        if (inventory.isFull()) {
            btnBuyWater.setDisable(true);
            btnBuyFur.setDisable(true);
            btnBuyFood.setDisable(true);
            btnBuyOre.setDisable(true);
            btnBuyGames.setDisable(true);
            btnBuyFirearms.setDisable(true);
            btnBuyMedicine.setDisable(true);
            btnBuyMachines.setDisable(true);
            btnBuyNarcotics.setDisable(true);
            btnBuyRobots.setDisable(true);
        }
    }

    private void setInventoryValues() {
        lblNumWater1.setText(String.valueOf(inventory.get("Water")));
        lblNumWater2.setText(String.valueOf(inventory.get("Water")));
        lblNumFur1.setText(String.valueOf(inventory.get("Furs")));
        lblNumFur2.setText(String.valueOf(inventory.get("Furs")));
        lblNumFood1.setText(String.valueOf(inventory.get("Food")));
        lblNumFood2.setText(String.valueOf(inventory.get("Food")));
        lblNumOre1.setText(String.valueOf(inventory.get("Ore")));
        lblNumOre2.setText(String.valueOf(inventory.get("Ore")));
        lblNumGames1.setText(String.valueOf(inventory.get("Games")));
        lblNumGames2.setText(String.valueOf(inventory.get("Games")));
        lblNumFirearms1.setText(String.valueOf(inventory.get("Firearms")));
        lblNumFirearms2.setText(String.valueOf(inventory.get("Firearms")));
        lblNumMedicine1.setText(String.valueOf(inventory.get("Medicine")));
        lblNumMedicine2.setText(String.valueOf(inventory.get("Medicine")));
        lblNumMachines1.setText(String.valueOf(inventory.get("Machines")));
        lblNumMachines2.setText(String.valueOf(inventory.get("Machines")));
        lblNumNarcotics1.setText(String.valueOf(inventory.get("Narcotics")));
        lblNumNarcotics2.setText(String.valueOf(inventory.get("Narcotics")));
        lblNumRobots1.setText(String.valueOf(inventory.get("Robots")));
        lblNumRobots2.setText(String.valueOf(inventory.get("Robots")));
    }

    @FXML
    public void handleBuyWinItem() {
        mainApp.initGameWinScreen();
    }

    @FXML
    public void handleRefuelShip() {
        marketplace.refuelShip();
        initialize();
    }

    @FXML
    public void handleRepairShip() {
        marketplace.repairShip();
        initialize();
    }

    @FXML
    public void handleBuyWater() {
        marketplace.buyGood(marketplace.getWater(), player);
        initialize();
    }

    @FXML
    public void handleBuyFurs() {
        marketplace.buyGood(marketplace.getFurs(), player);
        initialize();
    }

    @FXML
    public void handleBuyFood() {
        marketplace.buyGood(marketplace.getFood(), player);
        initialize();
    }

    @FXML
    public void handleBuyOre() {
        marketplace.buyGood(marketplace.getOre(), player);
        initialize();
    }

    @FXML
    public void handleBuyGames() {
        marketplace.buyGood(marketplace.getGames(), player);
        initialize();
    }

    @FXML
    public void handleBuyFirearms() {
        marketplace.buyGood(marketplace.getFirearms(), player);
        initialize();
    }

    @FXML
    public void handleBuyMedicine() {
        marketplace.buyGood(marketplace.getMedicine(), player);
        initialize();
    }

    @FXML
    public void handleBuyMachines() {
        marketplace.buyGood(marketplace.getMachines(), player);
        initialize();
    }

    @FXML
    public void handleBuyNarcotics() {
        marketplace.buyGood(marketplace.getNarcotics(), player);
        initialize();
    }

    @FXML
    public void handleBuyRobots() {
        marketplace.buyGood(marketplace.getRobots(), player);
        initialize();
    }

    @FXML
    public void handleSellWater() {
        marketplace.sellGood(marketplace.getWater(), player);
        initialize();
    }

    @FXML
    public void handleSellFurs() {
        marketplace.sellGood(marketplace.getFurs(), player);
        initialize();
    }

    @FXML
    public void handleSellFood() {
        marketplace.sellGood(marketplace.getFood(), player);
        initialize();
    }

    @FXML
    public void handleSellOre() {
        marketplace.sellGood(marketplace.getOre(), player);
        initialize();
    }

    @FXML
    public void handleSellGames() {
        marketplace.sellGood(marketplace.getGames(), player);
        initialize();
    }

    @FXML
    public void handleSellFirearms() {
        marketplace.sellGood(marketplace.getFirearms(), player);
        initialize();
    }

    @FXML
    public void handleSellMedicine() {
        marketplace.sellGood(marketplace.getMedicine(), player);
        initialize();
    }

    @FXML
    public void handleSellMachines() {
        marketplace.sellGood(marketplace.getMachines(), player);
        initialize();
    }

    @FXML
    public void handleSellNarcotics() {
        marketplace.sellGood(marketplace.getNarcotics(), player);
        initialize();
    }

    @FXML
    public void handleSellRobots() {
        marketplace.sellGood(marketplace.getRobots(), player);
        initialize();
    }

    @FXML
    public void handleBack() {
        mainApp.initRegionPage();
    }

    void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
