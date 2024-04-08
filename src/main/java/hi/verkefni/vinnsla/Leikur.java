package hi.verkefni.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : Vinnsluklasi fyrir leikur.
 *
 *
 *****************************************************************************/
public class Leikur {
    private IntegerProperty stig = new SimpleIntegerProperty(this, "stig", 0);
    private static final String[] grafarar = {
            "/hi/verkefni/vidmot/CSS/images/Grafari1.png",
            "/hi/verkefni/vidmot/CSS/images/Grafari2.png",
            "/hi/verkefni/vidmot/CSS/images/Grafari3.png"
    };
    private static IntegerProperty grafaraVal = new SimpleIntegerProperty(1);

    public static int getGrafaraVal() {
        return grafaraVal.get();
    }

    public static IntegerProperty grafaraValProperty() {
        return grafaraVal;
    }

    public static void setGrafaraVal(int grafaraVal) {
        Leikur.grafaraVal.set(grafaraVal);
    }

    public static String getGrafaraURL(int val){
        return grafarar[val];
    }

    /**
     * Get-er fyrir Stig.
     * @return int - Skilar stig sem int.
     */
    public int getStig() {
        return stig.get();
    }

    /**
     * Fall sem skilar stig sem IntegerProperty.
     * @return IntegerProperty - Skilar stig sem IntegerProperty.
     */
    public IntegerProperty stigProperty() {
        return stig;
    }

    /**
     * Set-er fyrir stig.
     * @param stig
     */
    public void setStig(int stig) {
        this.stig.set(stig);
    }

    public static void valUpp(){
        setGrafaraVal(getGrafaraVal() + 1);
    }

    public static void valNidur(){
        setGrafaraVal(getGrafaraVal() - 1);
    }
}
