package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Leikur;
import hi.verkefni.vinnsla.StigaListi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.Objects;

import static hi.verkefni.vinnsla.Leikur.getGrafaraURL;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson og Róbert A. Jack
 * T-póstur: kog36@hi.is og ral9@hi.is
 * <p>
 * Lýsing : Controller fyrir Forsíðu.
 *
 *****************************************************************************/
public class ForsidaController {
    private static final String[] lagaListi= {"Backup Plan", "Catastrophic Success", "Cyborg Ninja",  "Floating Cat", "Game BOI 1", "Game BOI 2"};
    public Label fxEStig;
    public Button fxVinstri;
    public Label fxCurrentGrafari;
    public Button fxHaegri;
    public ImageView fxGrafaraMynd;
    @FXML
    private ListView fxHighScoreForsida;
    @FXML
    private ComboBox<String> tonlist;
    @FXML
    private ToggleGroup toggleGroup1;
    private final String[] eStig = {"Létt","Miðlungs", "Erfitt"};
    public StigaListi forsiduStigaListi;

    public void initialize(){
        forsiduStigaListi = new StigaListi();
        fxCurrentGrafari.textProperty().bind(Leikur.grafaraValProperty().asString());
        Leikur.setDifficulty(1);
        fxHighScoreForsida.setItems(forsiduStigaListi.getOllNofnOgStig(1));
        toggleGroup1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                RadioButton selectedMenuItem = (RadioButton) newValue;
                int difficulty = Integer.parseInt(selectedMenuItem.getId());
                fxEStig.setText(eStig[difficulty-1]);
                Leikur.setDifficulty(difficulty);
                fxHighScoreForsida.setItems(forsiduStigaListi.getOllNofnOgStig(difficulty));;
            }
        });
        frumstillaLog();
        updateImage();
        Leikur.grafaraValProperty().addListener((obs, oldVal, newVal) -> updateImage());

        tonlist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = tonlist.getSelectionModel().getSelectedIndex();
                Leikur.setValidLag(selectedIndex);
            }
        }
        );

    }

    /**
     * Fallið uppfærir hvaða lög eru sýnileg í viðmótinu.
     */
    public void frumstillaLog(){
        ObservableList<String> lagaNofn = FXCollections.observableArrayList(lagaListi);
        tonlist.setItems(lagaNofn);
    }

    /**
     * Fallið uppfærir sýnilega mynd af völdum grafara út frá fylki af myndum.
     */
    private void updateImage() {
        int index = Leikur.grafaraValProperty().get() - 1;
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(getGrafaraURL(index))));
        fxGrafaraMynd.setImage(image);
    }

    /**
     * Event handler fyrir takkann á forsíðunni. Skiptir um senu og hefur leik.
     * @throws IOException
     */
    public void fxOnByrjaTakki() throws IOException {
         ViewSwitcher.switchTo(View.LEIKUR);
    }

    /**
     * Keyrir fallið "valUpp()" í Leikur klasanum.
     */
    public void fxOnHaegriOrTakki(){
        Leikur.valUpp();

    }
    /**
     * Keyrir fallið "valNidur()" í Leikur klasanum.
     */
    public void fxOnVinstriOrTakki(){
        Leikur.valNidur();
    }

}
