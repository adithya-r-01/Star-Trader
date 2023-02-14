public class CreditsPageController {

    private Main mainApp;

    public void handleNewGame() {
        mainApp.newGame();
    }

    public void handleExit() {
        mainApp.exit();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
