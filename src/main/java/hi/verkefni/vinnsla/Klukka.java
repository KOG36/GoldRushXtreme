package hi.verkefni.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing :  Vinnsluklasi sem skilgreinir Klukku.
 *
 *
 *****************************************************************************/
public class Klukka {
    SimpleIntegerProperty timi;

    /**
     * Smiður fyrir Klukka.
     * @param timi
     */
    public Klukka(int timi) {
        this.timi = new SimpleIntegerProperty(this, "timi", timi);
    }

    /**
     * Get-er fyrir timi.
     * @return int - Skilar timi sem int.
     */
    public int getTimi() {
        return timi.get();
    }

    /**
     * Fall sem skilar timi sem SimpleIntegerProperty.
     * @return SimpleInegerProperty - Skilar timi sem SimpleIntegerProperty.
     */
    public SimpleIntegerProperty timiProperty() {
        return timi;
    }

    /**
     * Set-er fyrir timi.
     * @param timi
     */
    public void setTimi(int timi) {
        this.timi.set(timi);
    }

    /**
     *Fall sem minnkar tima klukku um einn.
     */
    public void tic(){
        int tempTimi = getTimi();
        setTimi(--tempTimi);
    }
}
