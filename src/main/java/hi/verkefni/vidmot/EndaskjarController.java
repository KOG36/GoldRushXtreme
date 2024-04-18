package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.HighScore;
import hi.verkefni.vinnsla.Leikur;
import hi.verkefni.vinnsla.StigaListi;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Nöfn: Róbert A. Jack,
 *       Kjartan Ólafur Gunnarsson
 * Tölvupóstur: ral9@hi.is,
 *              kog36@hi.is
 *
 * Controller fyrir endasíðunna.
 *
 */
public class EndaskjarController {

    public Button fxVistaTakki;
    public Label fxEStig;
    public Button fxSpilaAftur;
    @FXML
    private ListView fxHighScoreListi;
    @FXML
    private TextField nafnLeikmanns;
    @FXML
    private Label fxLokaStig;
    public String vistadNafn;
    public String vistadStig;
    private StigaListi stigaListi;
    private String[] eStig = {"Létt", "Miðlungs", "Erfitt"};



    public void initialize(){
        fxLokaStig.setText(HighScore.getHighScore() + "");
        fxEStig.setText(eStig[Leikur.getDifficulty()-1]);
        stigaListi = new StigaListi();
        fxHighScoreListi.setItems(stigaListi.getOllNofnOgStig(Leikur.getDifficulty()));

        nafnLeikmanns.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP, DOWN, LEFT, RIGHT -> event.consume();
                default -> {
                }
            }
        });
        fxHighScoreListi.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP, DOWN, LEFT, RIGHT -> event.consume();
                default -> {
                }
            }
        });

        fxSpilaAftur.setFocusTraversable(true);
        Platform.runLater(() -> fxSpilaAftur.requestFocus());
    }

    /**
     * Fall sem gerir manni kleyft að spila aftur með því að færa mann aftur yfir á leikborðið
     * @throws IOException
     */
    public void fxOnSpilaAfturTakki() throws IOException {
        ViewSwitcher.switchTo(View.LEIKUR);
    }

    /**
     * Fall sem færir mann aftur á forsíðu.
     * @throws IOException
     */
    public void fxOnForsidaTakki() throws IOException {
        ViewSwitcher.switchTo(View.FORSIDA);
    }

    /**
     * Fall sem lokar forriti
     */
    public void fxOnHaettaTakki() {
        System.exit(0);
    }

    /**
     * Fallið tekur Nafn leikmanns úr viðeigandi textField, stig leikmannsins og erfiðleikastigið og vistar á .txt skrá
     * sem heldur utan um stig.
     * Síðan kallar fallið á fall sem uppfærir sýnilegan highScore lista til að viðmótið innihaldi rétt stig.
     *
     * Ef ekkert nafn er skrifað í reitinn er látið leikmann vita að það þurfi að skrifa nafn.
     */
    public void fxOnVistaStig() {
        if(!nafnLeikmanns.getText().isEmpty()) {
            vistadNafn = nafnLeikmanns.getText();
            vistadStig = fxLokaStig.getText();
            int stig = Integer.parseInt(fxLokaStig.getText());
            int eStig = Leikur.getDifficulty();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/hi/verkefni/vidmot/CSS/stigalisti.txt", true))) {
                writer.write(vistadNafn + "," + vistadStig + "," + eStig);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fxHighScoreListi.setItems(stigaListi.getOllNofnOgStig(Leikur.getDifficulty()));
            fxVistaTakki.setDisable(true);
        }
        else {
            nafnLeikmanns.setPromptText("Það þarf að skrifa nafn");
        }

    }


}
