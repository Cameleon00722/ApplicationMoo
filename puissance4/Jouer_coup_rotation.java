package com.company.puissance4;

import com.company.elements.Grille;
import com.company.elements.IJouer_un_coup;
import com.company.elements.Ihm2;

public class Jouer_coup_rotation implements IJouer_un_coup{


    @Override
    public boolean jouer_le_coup(Joueur_P4 joueur, Ihm2 ihm, Grille grille) {
        boolean resultat=false;
        //On choisit si on veut jouer avec la rotation
        String rotation_maintenant=jouer_avec_rotation(joueur.getNom(),ihm);

        //On effectue la rotation si c'est demandé
        resultat=la_rotation(grille, rotation_maintenant,ihm);
        ihm.afficherlaGrille(grille.toString());

        return resultat;
    }
    public String jouer_avec_rotation(String nom,Ihm2 ihm){
        boolean testchoix = false;
        String reponse="";
        while (!testchoix) {
            String rota_ou_non = ihm.demanderjouerP4(nom);
            if (rota_ou_non.equals("1") ) {
                testchoix = true;
                reponse=rota_ou_non;
            } else if(rota_ou_non.equals("2")){
                testchoix=true;
                reponse=rota_ou_non;
            }
            else {
                ihm.afficherMsg("Saisie incorrecte, saisir oui ou non ");
            }
        }
        return reponse;
    }
    private boolean la_rotation(Grille grille_P4, String resultrot2,Ihm2 ihm) {
        boolean resultat=false;
        if (resultrot2.equals("2")) {
            boolean continuer_choix_direction=false;
            String choix_direction="";
            while(!continuer_choix_direction){
                choix_direction=ihm.demande_droite_gauche();
                if(choix_direction.equals("droite") || choix_direction.equals("gauche")){
                    continuer_choix_direction=true;
                    resultat=true;
                }
            }
            if(choix_direction.equals("droite")){
                grille_P4.rotation_a_droite();
            }else{
                grille_P4.rotation_gauche();
            }
        }
        return resultat;
    }
}
