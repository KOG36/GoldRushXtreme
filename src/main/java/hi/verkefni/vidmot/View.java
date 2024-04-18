package hi.verkefni.vidmot;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson og Róbert A. Jack
 * T-póstur: kog36@hi.is og ral9@hi.is
 *
 * Lýsing : Enum klasi fyrir View.
 *
 *
 *****************************************************************************/
public enum View {
    FORSIDA("forsida-view.fxml"),
    LEIKUR("goldrush-view.fxml"),
    ENDASKJAR("endaskjar-view.fxml");



    private String nafnFXMLSkra;

    /**
     * Smiður fyrir View.
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