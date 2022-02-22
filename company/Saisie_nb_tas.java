package com.company;

import java.util.Scanner;

public class Saisie_nb_tas {
    int nb_tas;

    public Saisie_nb_tas(){
        System.out.println("Saisir le nombre de tas");
        Scanner nb = new Scanner(System.in);
        if(nb.hasNextInt()){
            nb_tas=nb.nextInt();
        }
    }
    int getNb_tas(){
        return nb_tas;
    }

}
