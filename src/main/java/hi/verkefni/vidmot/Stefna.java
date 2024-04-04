package hi.verkefni.vidmot;

/******************************************************************************
 * Nafn : Kjartan Ólafur Gunnarsson
 * T-póstur: kog36@hi.is
 *
 * Lýsing : XXXXXXXXXXXXXXX
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
    NA(45);

    private final int gradur;
    Stefna(int s){
        gradur = s;
    }

    public int getGradur() {
        return gradur;
    }
}
