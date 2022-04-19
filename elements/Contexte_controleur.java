package com.company.elements;

import com.company.Main2;
import com.company.puissance4.Joueur_P4;

public class Contexte_controleur {
    private final IControleur strategie;
    private final Ihm2 monIhm;
    private final Main2.ModeGame modeGame ;


    public Contexte_controleur(IControleur IControleur, Ihm2 monIhm, Main2.ModeGame modeGame){
        this.strategie= IControleur;
        this.monIhm = monIhm;
        this.modeGame = modeGame;
    }
    public void jouer(){

        Joueur[] les_joueurs = obtenir_les_joueurs();
        int balance=0;
        boolean  continuer_a_jouer=true;

        int parametre_plateau=strategie.get_param_plateau();



        while(continuer_a_jouer){
            Object mon_plateau = strategie.creation_plateau(parametre_plateau);
            int la_contrainte=strategie.choix_contrainte();

            int i=0;
            //remettre à jour les compteurs de parties jouées

            while(!strategie.partie_terminee(mon_plateau)){
                if(i%2==0){

                    //jouer pour joueur 1
                    mon_plateau=strategie.corps_principal(mon_plateau,les_joueurs[0], la_contrainte,parametre_plateau);

                    i=i+1;
                }else{

                    mon_plateau=strategie.corps_principal(mon_plateau, les_joueurs[1], la_contrainte,parametre_plateau);
                    i=i+1;
                }

            }
            strategie.afficher_gagnants(mon_plateau);

            if(strategie.afficher_gagnants(mon_plateau)){
                if (i % 2 == 0) {
                    monIhm.afficherGagnant(les_joueurs[1].getNom());
                    les_joueurs[1].gagnePartie();
                    balance = balance - 1;
                } else {
                    monIhm.afficherGagnant(les_joueurs[0].getNom());
                    les_joueurs[0].gagnePartie();
                    balance = balance + 1;
                }
            }else{
                monIhm.afficherMsg("Egalité : aucun gagnant pour cette partie");
            }
            strategie.maj_joueur(les_joueurs);
            continuer_a_jouer=une_nouvelle_partie();
        }
        monIhm.quiAGagne(balance,
                les_joueurs[0].getNom(),
                les_joueurs[1].getNom(),
                String.valueOf(les_joueurs[0].getNbPartiesGagnees()),
                String.valueOf(les_joueurs[1].getNbPartiesGagnees()));
    }

    public Joueur[] obtenir_les_joueurs(){
        String nomj1 = monIhm.demandePrenom();
        String nomj2 = modeGame == Main2.ModeGame.DUO ? monIhm.demandePrenom() : "IA" ;

        Joueur_P4 joueur1 = new Joueur_P4(nomj1,'r');
        Joueur_P4 joueur2 = new Joueur_P4(nomj2,'j');

        return new Joueur_P4[]{joueur1, joueur2};
    }

    public boolean une_nouvelle_partie (){
        boolean continuer_nouvelle_partie=false;
        boolean reponse=false;
        while(!continuer_nouvelle_partie){
            int res=monIhm.nouvellePartie();
            if(res ==1){
                continuer_nouvelle_partie=true;
                reponse=true;
            }
            else if(res==2){
                continuer_nouvelle_partie=true;
            }else{
                monIhm.afficherMsg("Saisie incorrecte, saisir 1 ou 2");
            }
        }
        return reponse;
    }
}
