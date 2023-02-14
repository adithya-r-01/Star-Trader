import javafx.fxml.FXML;

/**
 * Class that controls the welcome screen for the game
 *
 * @author WilliamLeonard
 * @version M3
 */
public class RootLayoutController {

    private Main mainApp;

    /**
     * Setter for the mainApp variable
     *
     * @param mainApp New instance of Main class
     */
    void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handles the action for when the start button is pressed.
     */
    @FXML
    public void handleStart() {
        mainApp.initCreatePlayer();
    }

}
