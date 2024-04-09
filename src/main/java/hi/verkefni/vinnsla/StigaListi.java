package hi.verkefni.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

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
                    int stig = Integer.parseInt(partar[1].trim());
                    NafnOgStig stak = new NafnOgStig(nafn, stig, Leikur.getDifficulty());
                    stigaListi.add(stak);
                } else {
                    System.err.println("Invalid data format: " + lina);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endurnyjaLista() {
        // Clear the current list
        stigaListi.clear();

        // Repopulate the list from the file
        fillaLista("src/main/resources/hi/verkefni/vidmot/CSS/stigalisti.txt");
    }

    public ObservableList<NafnOgStig> getOllNofnOgStig() {
        endurnyjaLista();
        SortedList<NafnOgStig> sortedStigaListi = new SortedList<>(stigaListi,
                Comparator.comparingInt(NafnOgStig::getStig).reversed());

        return FXCollections.observableArrayList(sortedStigaListi);
    }
}

