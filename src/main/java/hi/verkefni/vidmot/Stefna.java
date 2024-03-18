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
    HAEGRI(360);
    private final int gradur;
    Stefna(int s){
        gradur = s;
    }

    public int getGradur() {
        return gradur;
    }
}
