package hi.verkefni.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : XXXXXXXXXXXXXXX
 *
 *
 *****************************************************************************/
public class Leikur {
    private IntegerProperty stig = new SimpleIntegerProperty(this, "stig", 0);

    public int getStig() {
        return stig.get();
    }

    public IntegerProperty stigProperty() {
        return stig;
    }

    public void setStig(int stig) {
        this.stig.set(stig);
    }
}
