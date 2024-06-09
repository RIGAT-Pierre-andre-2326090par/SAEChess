package fr.iut.SAEChess;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVgest {
    private List<String[]> partie;

    /**
     * charge un fichier csv dans la liste partie
     * @param file
     */
    public void loadCsv(File file) {
        partie = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextCoup;
            csvReader.readNext();
            while ((nextCoup = csvReader.readNext()) != null) {
                partie.add(nextCoup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * exporte la liste partie dans le fichier csv spécifier
     * @param file
     */
    public void exportCsv(File file) {
        try {
            FileWriter filewriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(filewriter);
            csvWriter.writeNext(new String[]{"B", "N"});
            for (String[] coup : partie) {
                csvWriter.writeNext(coup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * définit la partie a exporter
     * @param partie
     */
    public void setPartie(List<String[]> partie) {
        this.partie = partie;
    }

    /**
     * donne la partie chargé
     * @return la partie chargé
     */
    public List<String[]> getPartie() {
        return partie;
    }
}