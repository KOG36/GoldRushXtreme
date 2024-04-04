package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : XXXXXXXXXXXXXXX
 *
 *
 *****************************************************************************/
public class MenuController implements Initializable {
    @FXML
    public GoldController goldController;
    public ToggleGroup toggleGroup1;

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }


    public void onNyrLeikur(){
        goldController.onNyrLeikur();
    }

    public void onHaetta(){
        Alert haetta = new Alert(Alert.AlertType.CONFIRMATION);
        haetta.setTitle("Hætta leik");
        haetta.setHeaderText(null);
        haetta.setContentText("Viltu hætta?");
        haetta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK){
                System.exit(0);
            }
        });

    }

    public void onInfo(){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Upplýsingar");
        info.setHeaderText(null);
        info.setContentText("Höfundur forritsins og listamaður er Kjartan Ólafur Gunnarsson.\nForritið var forritað að mestu um miðja nótt þar sem höfundur var með alvarlega streptókokkasýkingu en vildi samt skila þessu af sér.\n Árið var 2024.");
        info.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                RadioMenuItem selectedMenuItem = (RadioMenuItem) newValue;
                int difficulty = Integer.parseInt(selectedMenuItem.getId());
                goldController.setDifficulty(difficulty);
                goldController.onNyrLeikur();

            }
        });

    }
}
