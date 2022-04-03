package com.company.puissance4;

import com.company.elements.Grille;
import com.company.elements.IJouer_un_coup;
import com.company.elements.Ihm2;

public class Contexte_jouer_coup_p4 {
    private final IJouer_un_coup strategie;

    public Contexte_jouer_coup_p4(IJouer_un_coup jouer){
        this.strategie= jouer;
    }
    public boolean jouer(Joueur_P4 j,Ihm2 ihm, Grille g){
        return strategie.jouer_le_coup(j,ihm,g);
    }
}
