package hi.verkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;

import java.io.IOException;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : Klasi sem skilgreinir Gull.
 *
 *
 *****************************************************************************/
public class Gull extends Circle {
    /**
     * Smiður fyrir Gull.
     */
    public Gull() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gull-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
