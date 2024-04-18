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

    private static final String[] tonlist = {
            "/hi/verkefni/vidmot/CSS/music/BackupPlan.mp3",
            "/hi/verkefni/vidmot/CSS/music/CatastrophicSuccess.mp3",
            "/hi/verkefni/vidmot/CSS/music/CyborgNinja.mp3",
            "/hi/verkefni/vidmot/CSS/music/FloatingCat.mp3",
            "/hi/verkefni/vidmot/CSS/music/GameBOI1.mp3",
            "/hi/verkefni/vidmot/CSS/music/GameBOI2.mp3"
    };

    private static String validLag;
    private static IntegerProperty grafaraVal = new SimpleIntegerProperty(1);
    private static int difficulty;

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Leikur.difficulty = difficulty;
    }

    public static int getGrafaraVal() {
        return grafaraVal.get();
    }

    public static String getValidLag() {
        return validLag;
    }

    public static void setValidLag(int val) {
        Leikur.validLag = tonlist[val];
    }

    public static IntegerProperty grafaraValProperty() {
        return grafaraVal;
    }

    public static void setGrafaraVal(int grafaraVal) {
        Leikur.grafaraVal.set(grafaraVal);
    }

    public static String getGrafaraURL(int val) {
        return grafarar[val];
    }

    /**
     * Get-er fyrir Stig.
     *
     * @return int - Skilar stig sem int.
     */
    public int getStig() {
        return stig.get();
    }

    /**
     * Fall sem skilar stig sem IntegerProperty.
     *
     * @return IntegerProperty - Skilar stig sem IntegerProperty.
     */
    public IntegerProperty stigProperty() {
        return stig;
    }

    /**
     * Set-er fyrir stig.
     *
     * @param stig
     */
    public void setStig(int stig) {
        this.stig.set(stig);
    }

    /**
     * Velur næsta grafara í fylkinu og ef komið er á enda stak fylkis þá fer það hring.
     */
    public static void valUpp() {
        if (getGrafaraVal() < grafarar.length) { // Ensure index stays within the array bounds
            setGrafaraVal(getGrafaraVal() + 1);
        } else {
            setGrafaraVal(1);
        }
    }

    /**
     * Velur fyrri grafara í fylkinu og ef komið er á byrjunar stak fylkisinns fer það hring.
     */
    public static void valNidur() {
        if (getGrafaraVal() > 1) { // Ensure index stays within the array bounds
            setGrafaraVal(getGrafaraVal() - 1);
        } else {
            setGrafaraVal(grafarar.length);
        }
    }
}
