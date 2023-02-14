import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class RegionMapController {

    private Main mainApp;

    @FXML
    private AnchorPane mapPane;

    public void initialize() {
        Region currRegion = Main.getPlayer().getRegion();
        Ship ship = Main.getPlayer().getShip();
        Arc travelLimit = setupTravelArc(currRegion, ship);
        mapPane.getChildren().add(travelLimit);
        setupRegionLinks(currRegion, ship);
    }

    private void setupRegionLinks(Region currRegion, Ship ship) {
        Region[] regions = Region.getRegions();
        for (Region e : regions) {
            Circle point = new Circle(2);
            Hyperlink link = new Hyperlink(e.getName(), point);
            link.setFocusTraversable(false);
            if (e.equals(currRegion)) {
                link.setTextFill(Color.BLACK);
            }
            link.setOnAction(a -> {
                link.setVisited(false);
                mainApp.regionDialog(e);
            });
            link.setLayoutX(e.getX());
            link.setLayoutY(e.getY());
            mapPane.getChildren().add(link);
            if (Region.distance(currRegion, e) > ship.maxTravelDistance()) {
                mapPane.getChildren().get(mapPane.getChildren().indexOf(link)).setDisable(true);
            }
        }
    }

    private Arc setupTravelArc(Region currRegion, Ship ship) {
        double start;
        double add;
        Arc travelLimit;
        if (currRegion.getY() > ship.maxTravelDistance() && ship.maxTravelDistance()
                < (354.4 - currRegion.getY())) {
            if (ship.maxTravelDistance() < (640 - currRegion.getX())) {
                travelLimit = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                        ship.maxTravelDistance(), ship.maxTravelDistance(), 0, 360);
            } else {
                start = Math.toDegrees(Math.acos((640.0 - currRegion.getX())
                        / ship.maxTravelDistance())) + 4;
                travelLimit = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                        ship.maxTravelDistance(), ship.maxTravelDistance(), start,
                        360 - (2 * start));
            }
        } else if (currRegion.getY() >= 354.4 - ship.maxTravelDistance()) {
            if (currRegion.getY() <= ship.maxTravelDistance()) {
                start = Math.toDegrees(Math.asin((double) currRegion.getY()
                        / ship.maxTravelDistance())) + 5;
                add = 2 * (90 - start);
                double start2 = Math.toDegrees(Math.acos((354.4 - currRegion.getY())
                        / ship.maxTravelDistance())) + 5;
                double add2 = 2 * start2;
                if (currRegion.getX() >= 640 - ship.maxTravelDistance()) {
                    double start1 = Math.toDegrees(Math.acos((640.0 - currRegion.getX())
                            / ship.maxTravelDistance())) + 3;
                    double stop1 = Math.toDegrees(Math.asin((double) currRegion.getY()
                            / ship.maxTravelDistance())) + 1;
                    double arcLength1 = stop1 - start1;
                    double start3 = Math.toDegrees(Math.acos((354.4 - (double) currRegion.getY())
                            / ship.maxTravelDistance())) + 4;
                    double arcLength3 = start3 - start3;
                    if (Math.hypot(currRegion.getY(), (640 - currRegion.getX()))
                            > ship.maxTravelDistance()) {
                        Arc travelLimit1 = new Arc(currRegion.getX() + 6,
                                currRegion.getY() + 12, ship.maxTravelDistance(),
                                ship.maxTravelDistance(), start1, arcLength1);
                        travelLimit1.setFill(Color.TRANSPARENT);
                        travelLimit1.setStroke(Color.BLACK);
                        mapPane.getChildren().add(travelLimit1);
                    }
                    if (Math.hypot(354.4 - currRegion.getY(), 640 - currRegion.getX())
                            > ship.maxTravelDistance()) {
                        Arc travelLimit3 = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                                ship.maxTravelDistance(), ship.maxTravelDistance(), start3 - 90,
                                arcLength3);
                        travelLimit3.setFill(Color.TRANSPARENT);
                        travelLimit3.setStroke(Color.BLACK);
                        mapPane.getChildren().add(travelLimit3);
                    }
                } else {
                    Arc travelLimit2 = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                            ship.maxTravelDistance(), ship.maxTravelDistance(), start2 - 90,
                            180 - ((add / 2) + (add2 / 2)));
                    travelLimit2.setFill(Color.TRANSPARENT);
                    travelLimit2.setStroke(Color.BLACK);
                    mapPane.getChildren().add(travelLimit2);
                }
                travelLimit = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                        ship.maxTravelDistance(), ship.maxTravelDistance(), start + add,
                        180 - ((add / 2) + (add2 / 2)));
            } else {
                if (currRegion.getX() >= 640 - ship.maxTravelDistance()) {
                    start = Math.toDegrees(Math.acos((640.0 - currRegion.getX())
                            / ship.maxTravelDistance())) + 4;
                    double extra = Math.toDegrees(Math.acos((354.4 - currRegion.getY())
                            / ship.maxTravelDistance())) + 3;
                    travelLimit = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                            ship.maxTravelDistance(), ship.maxTravelDistance(), start,
                            270 - start - extra);
                } else {
                    start = Math.toDegrees(Math.acos((354.4 - currRegion.getY())
                            / ship.maxTravelDistance())) + 2.5;
                    add = 2 * start;
                    travelLimit = new Arc(currRegion.getX() + 6,
                            currRegion.getY() + 12, ship.maxTravelDistance(),
                            ship.maxTravelDistance(), start - 90, 360 - add);
                }
            }
        } else {
            start = Math.toDegrees(Math.asin((double) currRegion.getY()
                    / ship.maxTravelDistance())) + 8;
            if (currRegion.getX() >= 640 - ship.maxTravelDistance()) {
                double extra = Math.toDegrees(Math.acos((640.0 - currRegion.getX())
                        / ship.maxTravelDistance())) + 4;
                double arcLength = 270 - (90 - start) - extra;
                travelLimit = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                        ship.maxTravelDistance(), ship.maxTravelDistance(), 180 - start, arcLength);
            } else {
                add = 2 * (90 - start);
                travelLimit = new Arc(currRegion.getX() + 6, currRegion.getY() + 12,
                        ship.maxTravelDistance(), ship.maxTravelDistance(), start + add, 360 - add);
            }
        }
        travelLimit.setFill(Color.TRANSPARENT);
        travelLimit.setStroke(Color.BLACK);
        return travelLimit;
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
