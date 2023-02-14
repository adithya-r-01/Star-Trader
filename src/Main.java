import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Main class used to launch the game and load different screens
 *
 * @author WilliamLeonard
 * @version M5
 */
public class Main extends Application {

    private static Player player;
    private static MediaPlayer mp = new MediaPlayer(new Media(
            new File("src/media/NazcaFULL.mp3").toURI().toString()));
    private Stage primaryStage;
    private Stage secondaryStage;

    /**
     * Main method that launches the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Getter for the current Player instance
     *
     * @return Current instance of Player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Starts the application and sets the window and title
     *
     * @param primaryStage main window of the application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Space Trader");
            secondaryStage = new Stage();
            secondaryStage.setTitle("Space Trader");
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.initOwner(primaryStage);
            initRootLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and loads the welcome screen to start the game
     */
    public void initRootLayout() {
        try {
            player = new Player();
            VBox rootLayout;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();
            Scene welcomeScreen = new Scene(rootLayout);
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.setScene(welcomeScreen);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and loads the screen used to create your player and set player stats
     */
    public void initCreatePlayer() {
        try {
            BorderPane createLayout;
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("CreateCharacterScreen.fxml"));
            createLayout = loader.load();
            Scene createCharacterScreen = new Scene(createLayout);
            CreateCharacterController controller2 = loader.getController();
            controller2.setMainApp(this);
            primaryStage.setScene(createCharacterScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and loads the screen to display the player information that has been inputted and
     * saved
     */
    public void initViewPlayer() {
        try {
            VBox displayPlayerLayout;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("DisplayPlayerLayout.fxml"));
            displayPlayerLayout = loader.load();
            Scene displayPlayerScreen = new Scene(displayPlayerLayout);
            DisplayPlayerController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.setScene(displayPlayerScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRegionMap() {
        try {
            BorderPane regionMapLayout;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegionMap.fxml"));
            regionMapLayout = loader.load();
            Scene regionMapScreen = new Scene(regionMapLayout);
            RegionMapController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.setScene(regionMapScreen);
            mp.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void regionDialog(Region region) {
        if (!region.equals(player.getRegion())) {
            initTravelPage(region);
        } else {
            initRegionPage();
        }
    }

    public void initTravelPage(Region r) {
        try {
            VBox travelPage;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegionTravelPage.fxml"));
            travelPage = loader.load();
            Scene travelScene = new Scene(travelPage);
            TravelPageController controller = loader.getController();
            controller.setRegion(r);
            controller.setMainApp(this);
            controller.showInfo();
            secondaryStage.setScene(travelScene);
            secondaryStage.setOnCloseRequest(e -> initRegionMap());
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRegionPage() {
        try {
            AnchorPane regionPage;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegionView.fxml"));
            regionPage = loader.load();
            Scene regionScene = new Scene(regionPage);
            RegionViewController controller = loader.getController();
            controller.setMainApp(this);
            secondaryStage.setScene(regionScene);
            secondaryStage.centerOnScreen();
            secondaryStage.setOnCloseRequest(e -> initRegionMap());
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMarketplace() {
        try {
            TabPane marketplacePage;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MarketplaceView.fxml"));
            marketplacePage = loader.load();
            MarketplaceViewController controller = loader.getController();
            Scene marketplaceScene = new Scene(marketplacePage);
            controller.setMainApp(this);
            secondaryStage.setScene(marketplaceScene);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initNPCPage(Region travelTo, String npcName) {
        try {
            AnchorPane npcPage;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("NPCPage.fxml"));
            npcPage = loader.load();
            Scene npcScene = new Scene(npcPage);
            NPCPageController controller = loader.getController();
            controller.setTravelTo(travelTo);
            controller.setNpcName(npcName);
            controller.setMainApp(this);
            controller.setupPage();
            secondaryStage.setScene(npcScene);
            secondaryStage.centerOnScreen();
            secondaryStage.setOnCloseRequest(Event::consume);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initGameOverScreen() {
        try {
            AnchorPane gameOverScreen;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("GameOverScreen.fxml"));
            gameOverScreen = loader.load();
            Scene gameOverScene = new Scene(gameOverScreen);
            GameOverScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.initialize();
            secondaryStage.close();
            primaryStage.setScene(gameOverScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initCreditsPage() {
        try {
            AnchorPane creditsPage;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("CreditsPage.fxml"));
            creditsPage = loader.load();
            Scene creditsScene = new Scene(creditsPage);
            CreditsPageController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.setScene(creditsScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initGameWinScreen() {
        try {
            AnchorPane gameWinScreen;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("GameWinScreen.fxml"));
            gameWinScreen = loader.load();
            Scene gameWinScene = new Scene(gameWinScreen);
            GameWinScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.initialize();
            secondaryStage.close();
            primaryStage.setScene(gameWinScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        primaryStage.close();
    }

    public void newGame() {
        mp.stop();
        initCreatePlayer();
    }
}
