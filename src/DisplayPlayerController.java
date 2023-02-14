import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Class that controls the Character Sheet screen
 *
 * @author WilliamLeonard
 * @version M3
 */
public class DisplayPlayerController {

    private Main mainApp;

    @FXML
    private Label lblName;
    @FXML
    private Label lblDiff;
    @FXML
    private Label lblPilot;
    @FXML
    private Label lblFight;
    @FXML
    private Label lblMerch;
    @FXML
    private Label lblEngin;
    @FXML
    private Label lblCredits;
    private Player player = Main.getPlayer();

    /**
     * Initializes the Character Sheet screen with the information inputted into the previous
     * screen
     */
    public void initialize() {
        lblName.setText(player.getName());
        lblDiff.setText(player.getDiff().toString().substring(0, 1)
                + player.getDiff().toString().substring(1).toLowerCase());
        lblPilot.setText(player.getPilot() + " Points");
        lblFight.setText(player.getFighter() + " Points");
        lblMerch.setText(player.getMerchant() + " Points");
        lblEngin.setText(player.getEngineer() + " Points");
        lblCredits.setText(String.valueOf(player.getCredits()));
    }

    @FXML
    public void handleBeginGame() {
        Region.populateRegions();
        player.setRegion(Region.getRegions()[0]);
        player.getRegion().setVisited(true);
        mainApp.initRegionMap();
    }

    /**
     * Setter for the mainApp variable
     *
     * @param mainApp New instance of Main class
     */
    void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
