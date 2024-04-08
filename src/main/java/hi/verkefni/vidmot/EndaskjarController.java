package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.HighScore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


public class EndaskjarController {

    @FXML
    public GoldController goldController;
    @FXML
    private Label fxLokaStig;



    public void initialize(){

        fxLokaStig.setText(HighScore.getHigsScore() + "");

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


}
