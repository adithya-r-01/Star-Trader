import com.sun.jdi.AbsentInformationException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegionViewController {

    private Main mainApp;

    @FXML
    private Label lblRegionName;
    @FXML
    private Label lblRegionDescription;
    @FXML
    private Label lblRegionTechLevel;
    @FXML
    private Label lblRegionCoordinates;
    @FXML
    private Label lblHealth;
    @FXML
    private Label lblFuel;
    @FXML
    private Label lblInventory;

    private String regionN = Main.getPlayer().getRegion().getName();
    private String regionDesc = Main.getPlayer().getRegion().getDescription();
    private int xCoor = Main.getPlayer().getRegion().getX();
    private int yCoor = Main.getPlayer().getRegion().getY();

    public void initialize() {
        try {
            Ship ship = Main.getPlayer().getShip();
            Inventory inventory = ship.getInventory();
            lblRegionName.setText("Region - " + regionN);
            lblRegionDescription.setText(regionDesc);
            lblRegionTechLevel.setText(
                    Main.getPlayer().getRegion().getTechLevel().getStr());
            lblRegionCoordinates.setText("X: " + xCoor + " Y: " + yCoor);
            lblHealth.setText(ship.getHealth() + " HP");
            lblFuel.setText(ship.getCurrFuel() + " Units");
            lblInventory.setText("Inventory Slots: " + inventory.getCount() + "/"
                    + inventory.getCapacity());
        } catch (AbsentInformationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleMarketplace() {
        mainApp.initMarketplace();
    }

    void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
