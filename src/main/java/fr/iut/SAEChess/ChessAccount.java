package fr.iut.SAEChess;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChessAccount {
    float elo;                                                          // Le classement du joueur
    int wins;                                                           // Le nombre de victoires du joueur

    // Getters
    public float getElo() {
        return elo;
    }
    public int getWins() {
        return wins;
    }

    // Setters
    public void setElo(float elo) {
        this.elo = elo;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }


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

    /*
    Fonction qui prend en paramètres un nom et un mot de passe rentrés dans des textFields.
    Cette fonction parcourt le fichier des joueurs pour savoir si le joueur est déjà inscrit ou pas.
    Si oui, elle charge ses statistiques. Si non, elle l'indique au joueur.

    Cette fonction sera appelée à l'appui du bouton approprié
     */
    public void logIn(String nom, String passwd) {
        try {
            ArrayList<String> playerList = readFileToArray(new File("Joueurs.txt"));

            boolean registered = false;
            int indexPlayer = 0;
            for (int i = 0 ; i < playerList.size() ; ++i) {
                String[] playerInfo = playerList.get(i).split(",\\s*");
                if (playerInfo[0] == nom && playerInfo[1] == Integer.toString(passwd.hashCode())) {
                    registered = true;
                    indexPlayer = i;
                }
            }
            if(registered) {
                String[] playerInfo = playerList.get(indexPlayer).split(",\\s*");
                setElo(Float.parseFloat(playerInfo[2]));
                setWins(Integer.parseInt(playerInfo[3]));
            }
            else {
                /*notRegistered()*/                                 // Fonction à créer. Affiche une fenêtre qui dit "pas encore inscrit"
            }
        }
        catch (IOException ioException){

        }
    }

    /*
    Fonction qui prend en paramètres un nom et un mot de passe rentrés dans des textFields.
    Cette fonction parcourt le fichier des joueurs pour savoir si le joueur est déjà inscrit ou pas.
    Si oui, elle l'indique au joueur. Si non, elle ajoute son nom et mot de passe hashé au fichier joueur, et initialise ses statistiques à 0.

    Cette fonction sera appelée à l'appui du bouton approprié
     */
    public void register(String nom, String passwd) {
        try {
            ArrayList<String> playerList = readFileToArray(new File("Joueurs.txt"));
            System.out.println(playerList);

            boolean registered = false;
            for (int i = 0 ; i < playerList.size() ; ++i) {
                List<String> playerInfo = Arrays.asList(playerList.get(i).split(", "));
                if (playerInfo.get(0).equals(nom) && playerInfo.get(1).equals(Integer.toString(passwd.hashCode()))) {
                    registered = true;
                }
            }
            if(registered) {
                System.out.println("Déjà inscrit");
                /*alreadyRegistered();*/                                                // Fonction à créer. Affiche une fenêtre qui dit "déjà inscrit"
            }
            else {
                playerList.add(nom + ", " + passwd.hashCode() + ", 0, 0");
                System.out.println(playerList);
            }
            FileWriter fw = new FileWriter("Joueurs.txt", false);       // Ecrit sur le fichier passé en paramètre. Le "false" indique que le fichier sera réécrit de 0
            for (int i = 0 ; i < playerList.size() ; ++i) {
                fw.write(playerList.get(i));
                fw.write("\n");
            }
            fw.close();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
