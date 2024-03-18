package hi.verkefni.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : XXXXXXXXXXXXXXX
 *
 *
 *****************************************************************************/
public class Klukka {
    SimpleIntegerProperty timi;

    public int getTimi() {
        return timi.get();
    }

    public Klukka(int timi) {
        this.timi = new SimpleIntegerProperty(this, "timi", timi);
    }

    public SimpleIntegerProperty timiProperty() {
        return timi;
    }

    public void setTimi(int timi) {
        this.timi.set(timi);
    }

    public void tic(){
        int tempTimi = getTimi();
        setTimi(--tempTimi);
    }
}
