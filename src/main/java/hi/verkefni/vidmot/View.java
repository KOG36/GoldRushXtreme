package hi.verkefni.vidmot;

/**
 * Nafn: Róber A. Jack
 * Tölvupóstur: ral9@hi.is
 *
 * Enum klasi fyri view.
 */
public enum View {
    FORSIDA("forsida-view.fxml"),
    LEIKUR("goldrush-view.fxml"),
    ENDASKJAR("endaskjar-view.fxml");



    private String nafnFXMLSkra;

    /**
     * Smiður fyrir Sida.
     * @param nafnFXMLSkra
     */
    View(String nafnFXMLSkra){
        this. nafnFXMLSkra = nafnFXMLSkra;
    }

    /**
     * Getter fyrir nafnFXMLSkra.
     * @return String - Skilar nafni fxml skránnar.
     */
    public String getNafnFXMLSkra(){
        return nafnFXMLSkra;
    }
}