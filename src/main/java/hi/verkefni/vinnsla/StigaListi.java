package hi.verkefni.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
            while ((lina = br.readLine()) != null && !lina.isEmpty()) {
                String[] partar = lina.split(",");
                if (partar.length == 3) {
                    String nafn = partar[0].trim();
                    int stig = Integer.parseInt(partar[1].trim());
                    int difficulty = Integer.parseInt(partar[2].trim());
                    NafnOgStig stak = new NafnOgStig(nafn, stig, difficulty);
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

    public ObservableList<NafnOgStig> getOllNofnOgStig(int difficulty) {
        endurnyjaLista(); // Make sure the list is up-to-date

        // Filter the list by the provided difficulty level
        FilteredList<NafnOgStig> filteredByDifficulty = new FilteredList<>(stigaListi, stak -> stak.eStig == difficulty);

        // Sort the filtered list by score in descending order
        SortedList<NafnOgStig> sortedAndFilteredStigaListi = new SortedList<>(filteredByDifficulty, Comparator.comparingInt(NafnOgStig::getStig).reversed());

        return FXCollections.observableArrayList(sortedAndFilteredStigaListi);
    }
}

