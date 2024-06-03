package fr.iut.SAEChess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    /*
    Fonction qui prend en paramètres un nom et un mot de passe rentrés dans des textFields.
    Cette fonction parcourt le fichier des joueurs pour savoir si le joueur est déjà inscrit ou pas.
    Si oui, elle charge ses statistiques. Si non, elle l'indique au joueur.

    Cette fonction sera appelée à l'appui du bouton approprié
     */
    public void logIn(String nom, String passwd) {
        try {
            Scanner s = new Scanner(new File("Joueurs.txt"));
            ArrayList<String> playerList = new ArrayList<String>();
            while (s.hasNext()) {
                playerList.add(s.next());
            }
            s.close();

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
}
