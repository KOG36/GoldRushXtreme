package hi.verkefni.vinnsla;

public class NafnOgStig {
    public String nafn;
    public int stig;
    public int eStig;
    public NafnOgStig(String nafn, int stig, int eStig){
        this.nafn = nafn;
        this.stig = stig;
        this.eStig = eStig;
    }
    public String getNafn(){
        return nafn;
    }
    public int getStig(){
        return stig;
    }
    @Override
    public String toString(){
        return (getNafn() + ": " + getStig());
    }
}
