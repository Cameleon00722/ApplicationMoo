package com.company.puissance4;

import com.company.elements.Joueur;

public class Joueur_P4 extends Joueur {

    private final char couleur_pion;
    private int nb_rot_joues;

    public int getNb_rot_joues() {
        return nb_rot_joues;
    }

    public void setNb_rot_joues(int nb_coup_joues) {
        this.nb_rot_joues = nb_coup_joues;
    }

    public Joueur_P4(String nom, char couleur) {
        super(nom);
        this.couleur_pion=couleur;
        nb_rot_joues=0;
    }

    public char getCouleur_pion(){return couleur_pion;}
}
