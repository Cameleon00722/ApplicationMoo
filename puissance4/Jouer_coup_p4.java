package com.company.puissance4;

import com.company.elements.Grille;
import com.company.elements.Ihm2;
import com.company.exceptions.CoupInvalideException;

public class Jouer_coup_p4 {

    public Grille jouer_le_coup(Joueur_P4 joueur, Ihm2 ihm, Grille grille) {
        boolean continuer=false;
        Coup_P4 leCoup;


        while(!continuer){
            int colonne_saisie = ihm.saisieColonne(joueur.getNom());

            if (colonne_saisie != -1) {
                leCoup = new Coup_P4(colonne_saisie - 1, joueur.getCouleur_pion());
                try {
                    grille.gererCoup(leCoup);

                    continuer = true;

                } catch (CoupInvalideException e) {
                    ihm.afficherMsg(e.toString());
                }

            }
        }
        return grille;


    }
}
