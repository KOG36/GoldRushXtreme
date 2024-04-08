package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Leikur;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;

import static hi.verkefni.vinnsla.Leikur.getGrafaraURL;

/**
 * Nafn: Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 *
 * Contrloller fyrir forsíðuna.
 *
 */
public class ForsidaController {


    private static final String[] lagaListi= {"Lag 1", "lag 2", "Lag 3",  "lag 4"};
    public Label fxEStig;
    public Button fxVinstri;
    public Label fxCurrentGrafari;
    public Button fxHaegri;
    public ImageView fxGrafaraMynd;
    @FXML
    private ComboBox<String> tonlist;
    @FXML
    private ToggleGroup toggleGroup1;
    private String[] eStig = {"Létt","Miðlungs", "Erfitt"};

    public void initialize(){
        fxCurrentGrafari.textProperty().bind(Leikur.grafaraValProperty().asString());
        toggleGroup1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                RadioButton selectedMenuItem = (RadioButton) newValue;
                int difficulty = Integer.parseInt(selectedMenuItem.getId());
                fxEStig.setText(eStig[difficulty-1]);
                Leikur.setDifficulty(difficulty);

            }
        });
        frumstillaLog();
        updateImage();
        Leikur.grafaraValProperty().addListener((obs, oldVal, newVal) -> updateImage());

    }
    public void frumstillaLog(){
        ObservableList<String> lagaNofn = FXCollections.observableArrayList(lagaListi);
        tonlist.setItems(lagaNofn);
    }

    private void updateImage() {
        int index = Leikur.grafaraValProperty().get() - 1; // Convert number to array index
        Image image = new Image(getClass().getResourceAsStream(getGrafaraURL(index))); // Load image from path
        fxGrafaraMynd.setImage(image);
    }

    /**
     * Event handler fyrir takkann á forsíðunni. Skiptir um senu og hefur leik.
     * @param event
     * @throws IOException
     */
    public void fxOnByrjaTakki(ActionEvent event) throws IOException {
         ViewSwitcher.switchTo(View.LEIKUR);
    }

    public void fxOnHaegriOrTakki(){
        Leikur.valUpp();

    }
    public void fxOnVinstriOrTakki(){
        Leikur.valNidur();
    }

}
