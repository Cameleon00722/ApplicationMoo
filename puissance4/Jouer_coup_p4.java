package com.company.puissance4;

import com.company.elements.Grille;
import com.company.elements.IJouer_un_coup;
import com.company.elements.Ihm2;
import com.company.exceptions.CoupInvalideException;

public class Jouer_coup_p4 implements IJouer_un_coup{

    @Override
    public boolean jouer_le_coup(Joueur_P4 joueur, Ihm2 ihm, Grille grille) {
        boolean continuer=false;
        Coup_P4 leCoup;
        boolean resultat=false;

        while(!continuer){
            int colonne_saisie = saisie_colonne_valide(ihm.saisieColonne(joueur.getNom()), ihm);

            if (colonne_saisie != -1) {
                leCoup = new Coup_P4(colonne_saisie - 1, joueur.getCouleur_pion());
                try {
                    grille.gererCoup(leCoup);
                    resultat=true;

                } catch (CoupInvalideException e) {
                    ihm.afficherMsg(e.toString());
                }
                continuer = true;

            }
        }
        return resultat;


    }
    public int saisie_colonne_valide(String colonne, Ihm2 ihm){
        int a=-1;
        try{
            a=Integer.parseInt(colonne);

        }catch(NumberFormatException e){
            ihm.afficherMsg("Format incorrect");
        }
        return a;
    }


}
