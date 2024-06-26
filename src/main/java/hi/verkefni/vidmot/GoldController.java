package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.HighScore;
import hi.verkefni.vinnsla.Klukka;
import hi.verkefni.vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson og Róbert A. Jack
 * T-póstur: kog36@hi.is og ral9@hi.is
 * <p>
 * Lýsing : Controller fyrir Gold Rush. Forritið er leikur þar sem gullgrafari safnar gulli á leikborði.
 * Leikmaður fær stig fyrir hvert gull sem hann safnar.
 *
 *
 *****************************************************************************/
public class GoldController implements Initializable {
    public Leikbord fxLeikbord;
    public Label fxStig;
    public Label fxTimi;
    private int difficulty;
    public MenuBar menuStyring;
    private final HashMap<KeyCode, Stefna> map= new HashMap<>();
    private int[] lengd = {30, 20, 10};
    private Klukka klukka;
    private Timeline timer;
    private Timeline gull;
    @FXML
    public MenuController menuStyringController;
    private MediaPlayer mediaPlayer;

    /**
     * Set-er fyrir difficulty.
     * @param difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Fall sem sér um að geyma hvaða örvatakka er verið að ýta á.
     */
    public void orvatakkar() {
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);

        HashSet<KeyCode> yttirTakkar = new HashSet<>();

        fxLeikbord.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            if (map.containsKey(key)) {
                yttirTakkar.add(key);
                breytaUmAtt(yttirTakkar);
                event.consume();
            }
        });

        fxLeikbord.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            if (map.containsKey(key)) {
                yttirTakkar.remove(key);
                breytaUmAtt(yttirTakkar);
                event.consume();
            }
        });
    }

    /**
     * Fall sem sér um að geyma stefnu grafaranns og færa hann í þá átt.
     * @param yttirTakkar
     */
    private void breytaUmAtt(Set<KeyCode> yttirTakkar) {
        int nyStefna= 0;
        if(!yttirTakkar.isEmpty()) {
            if (yttirTakkar.contains(KeyCode.UP) && yttirTakkar.contains(KeyCode.RIGHT)) {
                nyStefna = Stefna.NA.getGradur();
            } else if (yttirTakkar.contains(KeyCode.DOWN) && yttirTakkar.contains(KeyCode.RIGHT)) {
                nyStefna = Stefna.SA.getGradur();
            } else if (yttirTakkar.contains(KeyCode.DOWN) && yttirTakkar.contains(KeyCode.LEFT)) {
                nyStefna = Stefna.SW.getGradur();
            } else if (yttirTakkar.contains(KeyCode.UP) && yttirTakkar.contains(KeyCode.LEFT)) {
                nyStefna = Stefna.NW.getGradur();
            } else if (yttirTakkar.contains(KeyCode.UP)) {
                nyStefna = Stefna.UPP.getGradur();
            } else if (yttirTakkar.contains(KeyCode.DOWN)) {
                nyStefna = Stefna.NIDUR.getGradur();
            } else if (yttirTakkar.contains(KeyCode.RIGHT)) {
                nyStefna = Stefna.HAEGRI.getGradur();
            } else if (yttirTakkar.contains(KeyCode.LEFT)) {
                nyStefna = Stefna.VINSTRI.getGradur();
            }

            fxLeikbord.getFxGrafari().setStefna(nyStefna);
            fxLeikbord.getFxGrafari().afram();
        }
    }

    /**
     * Fall sem stoppar leikinn og skiptir yfir í endaskjáinn.
     * @throws IOException
     */
    public void leikLokid() throws IOException {
        gull.stop();
        HighScore.setHighScore(fxLeikbord.getLeikur().getStig());
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
        ViewSwitcher.switchTo(View.ENDASKJAR);
    }

    /**
     * Fall sem sér um að halda utan um niðurtalningu og endurræsingu klukkunnar.
     */
    public void stillaKlukku() {
        if (timer != null) {
            timer.stop();
            timer = null;
    }

        klukka = new Klukka(lengd[difficulty - 1]);

        fxTimi.textProperty().bind(klukka.timiProperty().asString());

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> klukka.tic()));
        timer.setCycleCount(Timeline.INDEFINITE);
        klukka.timiProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() == 0) {
                timer.stop();
                try {
                    leikLokid();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        timer.play();
    }

    /**
     * Fall sem sér um að hefja nýjanleik.
     */
    public void onNyrLeikur(){
        fxLeikbord.reset();
        stillaKlukku();
        hefjaLeik();
        hefjaTonlist();
        HighScore.setHighScore(0);
    }

    /**
     * Fall sem sér um að hefja nýjan leik.
     * Athugar hvort það sé verið að búa til gull og hættir því ef svo er.
     * Byrjar að búa til gull á 1.5 sekúnda fresti
     */
    public void hefjaLeik(){
        if (gull != null){
            gull.stop();
        }

        KeyFrame k = new KeyFrame(Duration.seconds(1.5), event -> fxLeikbord.meiraGull());
        gull = new Timeline(k);
        gull.setCycleCount(Timeline.INDEFINITE);
        gull.play();
    }

    /**
     * Fallið athugar hvaða lag var valið á forsíðu og spilar það.
     * Ef ekkert lag var valið er ekkert spilað og ef tónlist var núþegar í gangi þá hættir spilunin fyrst
     * áður en ný spilun er hafin.
     */
    public void hefjaTonlist(){
        String validLag = Leikur.getValidLag();
        if (validLag == null || validLag.isEmpty() || validLag.equals("None")) {
            if (mediaPlayer != null) {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.stop();
                }
                mediaPlayer.dispose();
                mediaPlayer = null;
            }
            return;
        }
        URL resource = getClass().getResource(validLag);
        if (resource != null) {
            try {
                if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                }
                String lag = resource.toExternalForm();
                Media sound = new Media(lag);
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.setVolume(0.30);
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Error initializing media player: " + e.getMessage());
            }
        } else {
            System.out.println("Resource not found: " + validLag);
        }
    }

    /**
     * Initialize fall fyrir GoldController.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuStyringController.setGoldController(this);

        setDifficulty(Leikur.getDifficulty());

        fxStig.textProperty().bind(fxLeikbord.getLeikur().stigProperty().asString());
        orvatakkar();
        stillaKlukku();
        hefjaLeik();
        hefjaTonlist();

        fxLeikbord.setFocusTraversable(true);
        Platform.runLater(() -> fxLeikbord.requestFocus());
    }
}