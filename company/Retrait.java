package com.company;

import java.util.Scanner;

public class Retrait {
    int tas;
    int nb_allumettes;

    public Retrait(){
        System.out.println("Saisir tas : ");
        Scanner saisie_tas = new Scanner(System.in);
        if(saisie_tas.hasNextInt()){
            tas=saisie_tas.nextInt();
        }
        System.out.println("Saisir nb allumettes : ");
        Scanner nb_all = new Scanner(System.in);
        if(nb_all.hasNextInt()){
            nb_allumettes=nb_all.nextInt();
        }
    }
    int get_tas(){
        return tas;
    }
    int get_nb_allumettes(){
        return nb_allumettes;
    }
}
