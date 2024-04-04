package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


public class EndaskjarController {

    @FXML
    public GoldController goldController;
    @FXML
    private Label fxLokaStig;
    private int stig;


    public void initialize(){

        fxLokaStig.textProperty().bind(goldController.getLeikur().stigProperty().asString());

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

    public void setStig(int stig) {
        this.stig=stig;

    }
}
