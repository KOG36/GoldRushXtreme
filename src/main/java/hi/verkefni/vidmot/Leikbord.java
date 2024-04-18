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
import java.util.Objects;
import java.util.Random;

import static hi.verkefni.vinnsla.Leikur.getGrafaraURL;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : Klasi sem skilgreinir Leikborð.
 *
 *
 *****************************************************************************/
public class Leikbord extends Pane {

    public Leikur leikur = new Leikur();
    @FXML
    private Grafari fxGrafari;
    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();

    /**
     * Smiður fyrir Leikbord.
     */
    public Leikbord() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leikbord-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            postLoadInitialization();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get-er fyrir fxGrafari.
     * @return Grafari - Skilar grafara.
     */
    public Grafari getFxGrafari() {
        return fxGrafari;
    }

    /**
     * Initialize fall fyrir Leikbord.
     */
    private void postLoadInitialization() {
        if (fxGrafari != null) {
            int index = Leikur.grafaraValProperty().get() - 1;
            Image image = new Image(getClass().getResourceAsStream(getGrafaraURL(index)));
            fxGrafari.setFill(new ImagePattern(image));
        }

        fxGrafari.setTranslateX(this.getPrefWidth() / 2 - fxGrafari.getWidth() / 2);
        fxGrafari.setTranslateY(this.getPrefHeight() / 2 - fxGrafari.getHeight() / 2);
    }

    /**
     * Fall sem sér um að núllstilla leikborð þegar nýr leikur er byrjaður.
     */
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


            fxGrafari.setTranslateX(centerX);
            fxGrafari.setTranslateY(centerY);

        });
    }

    /**
     * Fall sem birtir gull á leikborði.
     */
    public void meiraGull() {
        Gull gull = new Gull();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/hi/verkefni/vidmot/CSS/images/Penge.png")));
        gull.setFill(new ImagePattern(image));

        Random rand = new Random();

        double maxX = this.getWidth() - gull.getRadius() * 2;
        double maxY = this.getHeight() - gull.getRadius() * 2;

        gull.setTranslateX(rand.nextDouble() * maxX + gull.getRadius());
        gull.setTranslateY(rand.nextDouble() * maxY + gull.getRadius());

        this.getChildren().add(gull);
        gullListi.add(gull);
    }

    /**
     * Fall sem sér um að taka í burtu gull og bæta við stigum þegar grafari snertir gull.
     */
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

    /**
     * Get-er fyrir leikur.
     * @return Leikur - Skilar leik.
     */
    public Leikur getLeikur() {
        return leikur;
    }
}