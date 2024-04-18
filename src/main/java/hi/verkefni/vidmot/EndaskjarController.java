package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.HighScore;
import hi.verkefni.vinnsla.Leikur;
import hi.verkefni.vinnsla.StigaListi;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Nafn: Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 *
 * Contrloller fyrir endasíðunna.
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
                case UP, DOWN, LEFT, RIGHT -> event.consume(); // This stops the event from propagating further
                default -> {
                }
            }
        });
        fxHighScoreListi.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP, DOWN, LEFT, RIGHT -> event.consume(); // This stops the event from propagating further
                default -> {
                }
            }
        });

        fxSpilaAftur.setFocusTraversable(true);
        Platform.runLater(() -> fxSpilaAftur.requestFocus());
    }

    public void fxOnSpilaAfturTakki(ActionEvent event) throws IOException {
        ViewSwitcher.switchTo(View.LEIKUR);
    }

    public void fxOnForsidaTakki(ActionEvent event) throws IOException {
        ViewSwitcher.switchTo(View.FORSIDA);
    }

    public void fxOnHaettaTakki(ActionEvent event) {
        System.exit(0);
    }


    public void fxOnVistaStig(ActionEvent event) {
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
