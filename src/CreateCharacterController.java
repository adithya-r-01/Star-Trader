import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Class that controls the Character creation screen
 *
 * @author WilliamLeonard
 * @version M3
 */
public class CreateCharacterController {

    private Main mainApp;

    @FXML
    private TextField txtName;
    @FXML
    private ChoiceBox<Difficulty> chcboxDiff;
    @FXML
    private Spinner<Integer> spinPilot;
    @FXML
    private Spinner<Integer> spinFight;
    @FXML
    private Spinner<Integer> spinMerch;
    @FXML
    private Spinner<Integer> spinEngine;
    @FXML
    private Label lblPoints;

    private ObservableList<Difficulty> difficultyObservableList =
            FXCollections.observableArrayList(Difficulty.EASY, Difficulty.MEDIUM,
                    Difficulty.HARD);

    /**
     * Initializes the screen with base and default values for the different scene elements. It
     * also add listeners to the skill point spinners to handle constraining their values to the
     * maximum number of skill points.
     */
    @FXML
    public void initialize() {
        chcboxDiff.setItems(difficultyObservableList);
        chcboxDiff.setValue(Difficulty.EASY);
        SpinnerValueFactory<Integer> initial1 = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0);
        SpinnerValueFactory<Integer> initial2 = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0);
        SpinnerValueFactory<Integer> initial3 = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0);
        SpinnerValueFactory<Integer> initial4 = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0);
        spinPilot.setValueFactory(initial1);
        spinFight.setValueFactory(initial2);
        spinMerch.setValueFactory(initial3);
        spinEngine.setValueFactory(initial4);
        diffChange();
        spinPilot.valueProperty().addListener((obs, oldValue, newValue) -> pilotChange());
        spinFight.valueProperty().addListener((obs, oldValue, newValue) -> fightChange());
        spinMerch.valueProperty().addListener((obs, oldValue, newValue) -> merchChange());
        spinEngine.valueProperty().addListener((obs, oldValue, newValue) -> engineChange());
        chcboxDiff.valueProperty().addListener((obs, oldValue, newValue) -> diffChange());
    }

    /**
     * Handles the functionality when the create button is pressed by checking for valid inputs
     * and saving them to the current Player instance.
     */
    @FXML
    public void handleCreatePlayer() {
        try {
            Main.getPlayer().setName(txtName.getText());
            Main.getPlayer().setDiff(chcboxDiff.getValue());
            Main.getPlayer().setSkills(spinPilot.getValue(), spinFight.getValue(),
                    spinMerch.getValue(), spinEngine.getValue());
            mainApp.initViewPlayer();
        } catch (IllegalArgumentException e) {
            txtName.clear();
            txtName.setPromptText("Please Enter a Character Name");
        }

    }

    /**
     * Handles changing the maximum value for the Spinners when the Engineer skill points value
     * is changed.
     */
    private void engineChange() {
        int fightMax = spinPilot.getValue() + spinMerch.getValue() + spinEngine.getValue();
        int merchMax = spinPilot.getValue() + spinFight.getValue() + spinEngine.getValue();
        int pilotMax = spinEngine.getValue() + spinMerch.getValue() + spinFight.getValue();
        spinFight.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - fightMax, spinFight.getValue()));
        spinMerch.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - merchMax, spinMerch.getValue()));
        spinPilot.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - pilotMax, spinPilot.getValue()));
    }

    /**
     * Handles changing the maximum value for the Spinners when the Merchant skill points value
     * is changed.
     */
    private void merchChange() {
        int fightMax = spinPilot.getValue() + spinMerch.getValue() + spinEngine.getValue();
        int pilotMax = spinEngine.getValue() + spinMerch.getValue() + spinFight.getValue();
        int engineMax = spinPilot.getValue() + spinMerch.getValue() + spinFight.getValue();
        spinFight.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - fightMax, spinFight.getValue()));
        spinPilot.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - pilotMax, spinPilot.getValue()));
        spinEngine.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - engineMax, spinEngine.getValue()));
    }

    /**
     * Handles changing the maximum value for the Spinners when the Fighter skill points value
     * is changed.
     */
    private void fightChange() {
        int merchMax = spinPilot.getValue() + spinFight.getValue() + spinEngine.getValue();
        int pilotMax = spinEngine.getValue() + spinMerch.getValue() + spinFight.getValue();
        int engineMax = spinPilot.getValue() + spinMerch.getValue() + spinFight.getValue();
        spinPilot.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - pilotMax, spinPilot.getValue()));
        spinMerch.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - merchMax, spinMerch.getValue()));
        spinEngine.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - engineMax, spinEngine.getValue()));
    }

    /**
     * Handles resetting the Spinners and updating their maximum value when the game difficulty
     * is changed
     */
    private void diffChange() {
        lblPoints.setText("Skill Points - Max: " + chcboxDiff.getValue().getSkillPoints()
                + " Points");
        int pilotMax = spinEngine.getValue() + spinMerch.getValue() + spinFight.getValue();
        int fightMax = spinPilot.getValue() + spinMerch.getValue() + spinEngine.getValue();
        int merchMax = spinPilot.getValue() + spinFight.getValue() + spinEngine.getValue();
        int engineMax = spinPilot.getValue() + spinMerch.getValue() + spinFight.getValue();
        spinPilot.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - pilotMax));
        spinFight.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - fightMax));
        spinMerch.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - merchMax));
        spinEngine.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - engineMax));
    }

    /**
     * Handles changing the maximum value for the Spinners when the Pilot skill points value
     * is changed.
     */
    private void pilotChange() {
        int fightMax = spinPilot.getValue() + spinMerch.getValue() + spinEngine.getValue();
        int merchMax = spinPilot.getValue() + spinFight.getValue() + spinEngine.getValue();
        int engineMax = spinPilot.getValue() + spinMerch.getValue() + spinFight.getValue();
        spinFight.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - fightMax, spinFight.getValue()));
        spinMerch.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - merchMax, spinMerch.getValue()));
        spinEngine.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                chcboxDiff.getValue().getSkillPoints() - engineMax, spinEngine.getValue()));
    }

    /**
     * Setter for the mainApp variable which allows for the controller to call methods from the
     * Main class
     *
     * @param mainApp New mainApp variable to be set
     */
    void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
