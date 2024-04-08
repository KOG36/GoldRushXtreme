package hi.verkefni.vidmot;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson og Róbert A. Jack
 * T-póstur: kog36@hi.is og ral9@hi.is
 *
 * Lýsing : Enum klasi fyrir stefnu.
 *
 *
 *****************************************************************************/
public enum Stefna {
    UPP (90),
    NIDUR(270),
    VINSTRI(180),
    HAEGRI(360),
    NW(135),
    SW(225),
    SA(315),
    NA(45),
    ENGIN(0);

    private final int gradur;

    /**
     * Smiður fyrir Stefna.
     * @param s
     */
    Stefna(int s){
        gradur = s;
    }

    /**
     * Get-er fyrir gradur.
     * @return
     */
    public int getGradur() {
        return gradur;
    }
}
