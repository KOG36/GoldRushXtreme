package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.HighScore;
import hi.verkefni.vinnsla.Leikur;
import hi.verkefni.vinnsla.StigaListi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    public void fxSmelltATexField(MouseEvent mouseEvent) {
        nafnLeikmanns.setText("");
    }
}
