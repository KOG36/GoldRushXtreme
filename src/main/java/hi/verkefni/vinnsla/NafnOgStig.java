package hi.verkefni.vinnsla;

public class NafnOgStig {
    public String nafn;
    public String stig;
    public NafnOgStig(String nafn, String stig){
        this.nafn = nafn;
        this.stig = stig;
    }
    public String getNafn(){
        return nafn;
    }
    public String getStig(){
        return stig;
    }
    @Override
    public String toString(){
        return (getNafn() + ": " + getStig());
    }
}
