import com.sun.jdi.AbsentInformationException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class TravelPageController {

    private Main mainApp;
    private Region region;

    @FXML
    private Label lblName;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblTech;
    @FXML
    private Label lblCoord;
    @FXML
    private Label lblDist;

    @FXML
    public void handleTravel() {
        Random rand = new Random();
        int npcSelect = rand.nextInt(30);
        String npcName = setNPCName(npcSelect);
        if (npcName != null) {
            mainApp.initNPCPage(region, npcName);
        } else {
            region.setVisited(true);
            Main.getPlayer().travel(region, false);
            mainApp.initRegionPage();
        }
    }

    private String setNPCName(int npcSelect) {
        String npcName;
        boolean forceBandit = false; // used for testing purposes only
        boolean forcePolice = false;
        boolean forceTrader = false;
        if (forceBandit) {
            npcName = "Bandit";
        } else if (forcePolice) {
            npcName = "Police";
        } else if (forceTrader) {
            npcName = "Trader";
        } else {
            npcName = determineNPCEncounter(npcSelect);
        }
        return npcName;
    }

    private String determineNPCEncounter(int npcSelect) {
        Player player = Main.getPlayer();
        Inventory inventory = player.getShip().getInventory();
        Difficulty diff = player.getDiff();
        boolean hasItems = !inventory.isEmpty();
        boolean notFull = !inventory.isFull();

        if (diff.equals(Difficulty.EASY)) {
            if (npcSelect >= 0 && npcSelect <= 2) {
                return "Bandit";
            } else if ((npcSelect >= 3 && npcSelect <= 5)
                    && hasItems) {
                return "Police";
            } else if (npcSelect >= 6 && npcSelect <= 8
                    && notFull) {
                return "Trader";
            }
        } else if (diff.equals(Difficulty.MEDIUM)) {
            if (npcSelect >= 0 && npcSelect <= 4) {
                return "Bandit";
            } else if ((npcSelect >= 5 && npcSelect <= 9)
                    && hasItems) {
                return "Police";
            } else if (npcSelect >= 10 && npcSelect <= 12
                    && notFull) {
                return "Trader";
            }
        } else {
            if (npcSelect >= 0 && npcSelect <= 6) {
                return "Bandit";
            } else if ((npcSelect >= 7 && npcSelect <= 13)
                    && hasItems) {
                return "Police";
            } else if (npcSelect >= 14 && npcSelect <= 16
                    && notFull) {
                return "Trader";
            }
        }
        return null;
    }

    public void showInfo() {
        lblName.setText(region.getName());
        lblDesc.setText(region.getDescription());
        try {
            lblTech.setText(region.getTechLevel().getStr());
        } catch (AbsentInformationException as) {
            lblTech.setText("Unknown");
        }

        lblCoord.setText("X: " + region.getX() + ", Y: " + region.getY());
        lblDist.setText(Region.distance(Main.getPlayer().getRegion(),
                region) + " Light-Minutes");
    }

    void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
