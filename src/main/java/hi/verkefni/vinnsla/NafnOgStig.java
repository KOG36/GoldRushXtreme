package hi.verkefni.vinnsla;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson og Róbert A. Jack
 * T-póstur: kog36@hi.is og ral9@hi.is
 *
 * Lýsing : Klasi sem skilgreinir Naf og stig til þess að sýna nafn og stig í list veiw
 *
 *
 *****************************************************************************/
public class NafnOgStig {
    public String nafn;
    public int stig;
    public int eStig;

    /**
     * Smiður fyrir NafnOgStig.
     * @param nafn
     * @param stig
     * @param eStig
     */
    public NafnOgStig(String nafn, int stig, int eStig) {
        this.nafn = nafn;
        this.stig = stig;
        this.eStig = eStig;
    }

    /**
     * Get-er fyrir naf.
     * @return String - skilar nafni.
     */
    public String getNafn() {
        return nafn;
    }

    /**
     * Get-er fyrir stig.
     * @return int - Stigin
     */
    public int getStig() {
        return stig;
    }

    /**
     * toString fall fyrir NafnOgStig.
     * @return String - Skilar nafni og stigi sem strengur
     */
    @Override
    public String toString() {
        return (getNafn() + ": " + getStig());
    }
}
