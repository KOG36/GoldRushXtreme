package hi.verkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson og Róbert A. Jack
 * T-póstur: kog36@hi.is og ral9@hi.is
 *
 * Lýsing : Klasi sem sér um að skipta um senu.
 *
 *
 *****************************************************************************/
public class ViewSwitcher {
    private static Map<View, Parent> cache = new HashMap<>();
    private static Scene scene;

    /**
     * Setur senuna.
     * @param scene
     */
    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    /**
     *  Tekur inn View og setur rótina af því viðmótstré sem rótin af senunni.
     * @param view
     * @throws IOException
     */
    public static void switchTo(View view) throws IOException {
        if(scene == null){
            System.out.println("Engin sena sett");
            return;
        }
        try {
            Parent root = FXMLLoader.load(ViewSwitcher.class.getResource(view.getNafnFXMLSkra()));
            scene.setRoot(root);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}