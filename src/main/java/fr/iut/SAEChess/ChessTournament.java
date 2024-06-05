package fr.iut.SAEChess;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessTournament {
    private ArrayList<String> tournamentAttenders = new ArrayList<String>();


    public ArrayList<String> readFileToArray(File file) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            list.add(line);
            line = br.readLine();
        }

        return list;
    }

    /*public void eloSort(ArrayList<String> list) {
        ArrayList<List<String>> listInfo = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; ++i) {
            listInfo.add(Arrays.asList(list.get(i).split(", ")));
        }
        for (int i = 0 ; i < listInfo.size() ; ++i) {
            int index = i;
            for (int j = i + 1 ; j < list.size() ; ++j) {
                if (Integer.parseInt(listInfo.get(j).get(2)) < Integer.parseInt(listInfo.get(index).get(2))) {
                    index = j;
                }
            }
            int smallerElo = index;
            listInfo.set(index, listInfo.get(i));
            listInfo.set(i, listInfo.get(smallerElo));
        }
        for (int i = 0 ; i < listInfo.size() ; ++i) {
            list.add(listInfo.get(i));
        }
    }*/

    public void inscription(String nom, String passwd) throws IOException {
        ArrayList<String> playerList = readFileToArray(new File("Joueurs.txt"));
        for (int i = 0 ; i < playerList.size() ; ++i) {
            List<String> playerInfo = Arrays.asList(playerList.get(i).split(", "));
            if (playerInfo.get(0).equals(nom) && playerInfo.get(1).equals(Integer.toString(passwd.hashCode()))) {
                tournamentAttenders.add(playerList.get(i));
            }
        }
        FileWriter fw = new FileWriter("Participants.txt", false);      // Ecrit sur le fichier passé en paramètre. Le "false" indique que le fichier sera réécrit de 0
        for (int i = 0 ; i < tournamentAttenders.size() ; ++i) {
            fw.write(tournamentAttenders.get(i));
            fw.write("\n");
        }
        fw.close();
    }

    public void bracketCreate(ArrayList<String> attenderList, String tournamentName) throws IOException {                   // Ajoute au fichier du tournoi les différents rounds
        // eloSort(attenderList
        int nbRounds = (int)(Math.log(attenderList.size()) / Math.log(2));
        ArrayList<String> bracket = new ArrayList<>();
        for (int i = 0 ; i < attenderList.size() ; i = i + 2) {
            bracket.add(attenderList.get(i) + " VS " + attenderList.get(i + 1));
        }
        String name = "tournoi" + tournamentName;
        FileWriter fw = new FileWriter("Tournois/" + name, false);
        for (int i = 0 ; i < bracket.size() ; ++i) {
            fw.write(bracket.get(i));
            fw.write("\n");
        }
        fw.close();
    }

    public void tournoi() {
        
    }
}
