package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Klukka;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GoldController implements Initializable {
    public Leikbord fxLeikbord;
    public Label fxStig;
    public Label fxTimi;
    private int difficulty = 1;
    public MenuBar menuStyring;
    private final HashMap<KeyCode, Stefna> map= new HashMap<>();
    private int[] lengd = {30, 20, 10};
    private Klukka klukka;
    private Timeline timer;
    private Timeline gull;
    @FXML
    public MenuController menuStyringController;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void orvatakkar(){
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.W, Stefna.UPP);
        map.put(KeyCode.S, Stefna.NIDUR);
        map.put(KeyCode.A, Stefna.VINSTRI);
        map.put(KeyCode.D, Stefna.HAEGRI);
        fxLeikbord.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode key = event.getCode();
            if(map.containsKey(key)){
                int newStefna = map.get(key).getGradur();
                fxLeikbord.getFxGrafari().setStefna(newStefna);
                fxLeikbord.getFxGrafari().afram();
                event.consume();
            }
                });
    }
    public void leikLokid(){
        fxLeikbord.setiGangi(false);
        gull.stop();
    }
    public void stillaKlukku() {
        if (timer != null) {
            timer.stop();
            timer = null;
    }

        klukka = new Klukka(lengd[difficulty - 1]);

        // Bind the label to the Klukka's time property as before
        fxTimi.textProperty().bind(klukka.timiProperty().asString());

        // Create a new timeline
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> klukka.tic()));
        timer.setCycleCount(Timeline.INDEFINITE);
        klukka.timiProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() == 0) {
                timer.stop();
                leikLokid();

            }
        });

        // Start the new timeline
        timer.play();
    }

    public void onNyrLeikur(){
        fxLeikbord.reset();
        stillaKlukku();
        hefjaLeik();
    }
    public void hefjaLeik(){
        if (gull != null){
            gull.stop();
        }

        KeyFrame k = new KeyFrame(Duration.seconds(1.5), event -> fxLeikbord.meiraGull());
        gull = new Timeline(k);
        gull.setCycleCount(Timeline.INDEFINITE);
        gull.play();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuStyringController.setGoldController(this);

        fxStig.textProperty().bind(fxLeikbord.getLeikur().stigProperty().asString());
        orvatakkar();
        stillaKlukku();
        hefjaLeik();


        fxLeikbord.setFocusTraversable(true);
        Platform.runLater(() -> fxLeikbord.requestFocus());
    }
}