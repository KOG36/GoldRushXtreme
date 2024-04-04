package hi.verkefni.vidmot;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
/**
 * Nafn: Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 *
 * Contrloller fyrir forsíðuna.
 *
 */
public class ForsidaController {


    private static final String[] lagaListi= {"Lag 1", "lag 2", "Lag 3",  "lag 4"};
    @FXML
    private ComboBox<String> tonlist;
    public void initialize(){
        frumstillaLog();
    }
    public void frumstillaLog(){
        ObservableList<String> lagaNofn = FXCollections.observableArrayList(lagaListi);
        tonlist.setItems(lagaNofn);
    }

    /**
         * Event handler fyrir takkann á forsíðunni. Skiptir um senu og hefur leik.
         * @param event
         * @throws IOException
         */
        public void fxOnByrjaTakki(ActionEvent event) throws IOException {
            ViewSwitcher.switchTo(View.LEIKUR);
        }
}
