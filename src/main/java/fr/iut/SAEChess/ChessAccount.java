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

    public void logIn(String nom, String passwd) {
        try {
            Scanner s = new Scanner(new File("Joueurs.txt"));
            ArrayList<String> list = new ArrayList<String>();
            while (s.hasNext()) {
                list.add(s.next());
            }
            s.close();


        }
        catch (IOException ioException){

        }
    }
}
