package hi.verkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : XXXXXXXXXXXXXXX
 *
 *
 *****************************************************************************/
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : Klasi sem skilgreinir Grafara.
 *
 *
 *****************************************************************************/
public class Grafari extends Rectangle {
    private int stefna;
    private final double speed = 15.0;

    private final double originalX = 0;
    private final double originalY = 0;

    /**
     * Smiður fyrir Grafari.
     */
    public Grafari() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("grafari-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Fall sem sér um að færa grafarann.
     */
    public void afram() {

        double radians = Math.toRadians(stefna);
        double deltaX = speed * Math.cos(radians);
        double deltaY = speed * Math.sin(radians);
        double minX = 0;
        double minY = 0;
        double maxX = this.getParent().getLayoutBounds().getWidth();
        double maxY = this.getParent().getLayoutBounds().getHeight();
        double newTranslateX = Math.max(minX, Math.min(this.getTranslateX() + deltaX, maxX - this.getWidth()));
        double newTranslateY = Math.max(minY, Math.min(this.getTranslateY() - deltaY, maxY - this.getHeight()));

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

        gullArekstur();
    }

    /**
     * Set-er fyrir stefna.
     * @param stefna
     */
    public void setStefna(int stefna) {
        this.stefna = stefna;
    }

    /**
     * Ekki notað muna að fjarlæga.
     */
    public void resetPosition() {

        this.setTranslateX(originalX);
        this.setTranslateY(originalY);
    }

    /**
     * Fall sem sér um að fjarlæga gull og bæta við stigi þegar grafari snertir gull.
     */
    private void gullArekstur() {
        if (getParent() instanceof Leikbord) {
            Leikbord leikbord = (Leikbord) getParent();
            leikbord.arekstur();
        }
    }
}
