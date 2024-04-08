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
}
