package fr.iut.SAEChess;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVgest {

    /**
     * charge un fichier csv dans la liste partie
     * @param file
     */
    public List<String[]> importCsv(File file, ChessController check) {
        List<String[]> partie = new ArrayList<>();
        try (FileReader filereader = new FileReader(file); CSVReader csvReader = new CSVReader(filereader)) {
            String[] nextCoup = csvReader.readNext();
            check.setIsBotPlaying(Objects.equals(nextCoup[1], "B"));
            while ((nextCoup = csvReader.readNext()) != null) {
                partie.add(nextCoup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partie;
    }

    /**
     * exporte la liste partie dans le fichier csv spécifier
     * @param file
     * @param partie
     */
    public void exportCsv(File file, List<String[]> partie, boolean botPlaying) {
        try (FileWriter filewriter = new FileWriter(file); CSVWriter csvWriter = new CSVWriter(filewriter)) {
            csvWriter.writeNext(new String[]{"B", botPlaying ? "B" : "N"});
            for (String[] coup : partie) {
                csvWriter.writeNext(coup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
