package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Leikur;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.util.Random;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : XXXXXXXXXXXXXXX
 *
 *
 *****************************************************************************/
public class Leikbord extends Pane {

    private Leikur leikur = new Leikur();
    @FXML
    private Grafari fxGrafari;
    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();
    private boolean iGangi = true;

    public boolean isiGangi() {
        return iGangi;
    }

    public void setiGangi(boolean iGangi) {
        this.iGangi = iGangi;
        this.setDisable(!iGangi);
    }

    public Grafari getFxGrafari() {
        return fxGrafari;
    }

    public Leikbord() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leikbord-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            postLoadInitialization(); // Call after FXML is loaded
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void postLoadInitialization() {
        // This method is called immediately after the FXML is loaded
        // Suitable for any initialization that requires the FXML components to be loaded first
        if (fxGrafari != null) {
            Image image = new Image(getClass().getResourceAsStream("/hi/verkefni/vidmot/CSS/images/Grafari.png"));
            fxGrafari.setFill(new ImagePattern(image));
        }

        fxGrafari.setTranslateX(this.getPrefWidth() / 2 - fxGrafari.getWidth() / 2);
        fxGrafari.setTranslateY(this.getPrefHeight() / 2 - fxGrafari.getHeight() / 2);
    }

    public void reset(){
        if (this.isDisabled()){
            this.setDisable(false);
        }
        this.getChildren().removeIf(node -> node instanceof Gull);
        gullListi.clear();
        leikur.setStig(0);
        Platform.runLater(() ->{
            double centerX = this.getWidth() / 2 - fxGrafari.getBoundsInLocal().getWidth() / 2;
            double centerY = this.getHeight() / 2 - fxGrafari.getBoundsInLocal().getHeight() / 2;

            // Adjust translate properties instead of layout properties if you're using translations to move Grafari
            fxGrafari.setTranslateX(centerX);
            fxGrafari.setTranslateY(centerY);

        });
    }

    public void meiraGull() {
        Gull gull = new Gull();
        Image image = new Image(getClass().getResourceAsStream("/hi/verkefni/vidmot/CSS/images/Penge.png"));
        gull.setFill(new ImagePattern(image));

        Random rand = new Random();

        double maxX = this.getWidth() - gull.getRadius() * 2;
        double maxY = this.getHeight() - gull.getRadius() * 2;

        gull.setTranslateX(rand.nextDouble() * maxX + gull.getRadius());
        gull.setTranslateY(rand.nextDouble() * maxY + gull.getRadius());

        this.getChildren().add(gull);
        gullListi.add(gull);
    }

    public void arekstur() {
        Gull grafid = null;
        for(Gull gull : gullListi){
            if (gull.getBoundsInParent().intersects(fxGrafari.getBoundsInParent())){
                grafid = gull;
                break;
            }
        }
        if (grafid != null){
            this.getChildren().remove(grafid);
            gullListi.remove(grafid);
            int tempStig = leikur.getStig();
            leikur.setStig(++tempStig);
        }
    }

    public Leikur getLeikur() {
        return leikur;
    }

    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
    }

}
