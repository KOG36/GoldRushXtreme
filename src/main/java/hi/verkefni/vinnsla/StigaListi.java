package hi.verkefni.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StigaListi {
    public ObservableList<NafnOgStig> stigaListi;

    public StigaListi(){
        this.stigaListi = FXCollections.observableArrayList();
        fillaLista("src/main/resources/hi/verkefni/vidmot/CSS/stigalisti.txt");
    }
    public void fillaLista(String textaSkra) {
        try (BufferedReader br = new BufferedReader(new FileReader(textaSkra))) {
            String lina;
            while ((lina = br.readLine()) != null) {
                String[] partar = lina.split(",");
                if (partar.length == 2) {
                    String nafn = partar[0].trim();
                    String stig = partar[1].trim();
                    NafnOgStig stak = new NafnOgStig(nafn, stig);
                    stigaListi.add(stak);
                } else {
                    System.err.println("Invalid data format: " + lina);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<NafnOgStig> getOllNofnOgStig() {
        return stigaListi;
    }
}

