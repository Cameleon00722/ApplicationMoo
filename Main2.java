package com.company;

import com.company.elements.Contexte_controleur;
import com.company.elements.Ihm2;
import com.company.nim.Controleur_Nim;
import com.company.puissance4.Controleur_P4;

public class Main2 {
    public static void main(String[] args) {
        Ihm2 ihm = new Ihm2();

        boolean choix_du_jeux=false;
        int jeu=-1;
        while(!choix_du_jeux){
            String reponse= ihm.demandeJeu();

            if(reponse.equals("1")){
                choix_du_jeux=true;
                jeu=1;
                //déroulé le jeu de Nim
            }else if(reponse.equals("2")){
                choix_du_jeux=true;
                jeu=2;
                //dérouler le P4
            }
        }

        String joueAvecUneIA = "" ;
        ModeGame type = ModeGame.DUO ;

        while(!joueAvecUneIA.equalsIgnoreCase("1") && !joueAvecUneIA.equalsIgnoreCase("2")) {
            joueAvecUneIA = ihm.demandeIA() ;
            if(joueAvecUneIA.equalsIgnoreCase("1")) type = ModeGame.SOLO ;
        }


        Contexte_controleur le_jeu;
        if(jeu==1){
             le_jeu = new Contexte_controleur(new Controleur_Nim(ihm), ihm, type);
        }else{
             le_jeu = new Contexte_controleur(new Controleur_P4(ihm), ihm, type);
        }
        le_jeu.jouer();

    }

    public enum ModeGame{
        SOLO, DUO ;
    }
}
