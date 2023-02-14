import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;


public class GameOverScreenController {

    @FXML
    private Label text1;

    private Main mainApp;
    private Timer timer = new Timer();
    private int count = 0;

    public void initialize() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> update());
            }
        }, 3000, 4000);
    }

    public void update() {
        if (count == 0) {
            text1.setText("");
        }
        if (count == 1) {
            text1.setText("You float away into space...");
        }
        if (count == 2) {
            text1.setText("");
        }
        if (count == 3) {
            timer.cancel();
            mainApp.initCreditsPage();
        }
        count++;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
