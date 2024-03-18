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

public class Grafari extends Rectangle {
    private int stefna;
    private final double speed = 15.0;
    // Initial positions
    private final double originalX = 0;
    private final double originalY = 0;

    public void afram() {
        // Convert degrees to radians for Java's Math functions
        double radians = Math.toRadians(stefna);

        // Calculate movement in x and y based on direction and speed
        double deltaX = speed * Math.cos(radians);
        double deltaY = speed * Math.sin(radians);


        // Assuming these are the bounds within which Grafari must stay
        double minX = 0;
        double minY = 0;
        double maxX = this.getParent().getLayoutBounds().getWidth();
        double maxY = this.getParent().getLayoutBounds().getHeight();

        // Calculate new position
        double newTranslateX = Math.max(minX, Math.min(this.getTranslateX() + deltaX, maxX - this.getWidth()));
        double newTranslateY = Math.max(minY, Math.min(this.getTranslateY() - deltaY, maxY - this.getHeight())); // Subtract deltaY because JavaFX's Y-axis is inverted

        // Update Grafari's position to new position if within bounds
        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

        gullArekstur();

    }


    public void setStefna(int stefna) {
        this.stefna = stefna;
    }

    public void resetPosition() {
        // Reset to the original position
        this.setTranslateX(originalX);
        this.setTranslateY(originalY);
    }

    private void gullArekstur() {
        if (getParent() instanceof Leikbord) {
            Leikbord leikbord = (Leikbord) getParent();
            leikbord.arekstur();
        }
    }

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
}
