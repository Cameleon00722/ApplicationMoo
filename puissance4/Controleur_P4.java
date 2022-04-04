package com.company.puissance4;

import com.company.Main2.ModeGame;
import com.company.elements.Grille;
import com.company.elements.IControleur;
import com.company.elements.Ihm2;
import com.company.ia.ArtificialIntelligenceImpl_P4;
import com.company.ia.ArtificialIntelligence_P4;

public class Controleur_P4 implements IControleur {
    private final Ihm2 monIhm;
    private final ModeGame modeGame ;

    public Controleur_P4(Ihm2 ihm, ModeGame type){
        this.monIhm=ihm;
        this.modeGame = type ;
    }

    //méthodes pour le jeu de P4
    public Ihm2 getMonIhm() {
        return monIhm;
    }

    public void jouer() {

        boolean rotation_de_la_grille = false;
        String on_joue_avec_rotation = "";

        String nomj1 = getMonIhm().demandePrenom();
        String nomj2 = modeGame == ModeGame.DUO ? getMonIhm().demandePrenom() : "IA" ;
        
        Joueur_P4 joueur1 = new Joueur_P4(nomj1,'r');
        Joueur_P4 joueur2 = new Joueur_P4(nomj2,'j');

        boolean  continuer_a_jouer=true;
        int balance=0;
        int nombre_de_coup;
        boolean afficher_gagnants=true;
        
        
        while(continuer_a_jouer){

            rotation_de_la_grille=false;
            while (!rotation_de_la_grille) {
            	
            	if(modeGame == ModeGame.SOLO) {
            		getMonIhm().afficherMsg("Par défaut, vous jouez avec les rotations contre l'IA.");
            		break ;
            	}
            	
                String rot = getMonIhm().P4rotation();
                if (rot.equals("oui") || rot.equals("non")) {
                    rotation_de_la_grille = true;
                    on_joue_avec_rotation = rot;
                } else {
                    getMonIhm().afficherMsg("Saisie incorrecte, saisir oui ou non ");
                }
            }

            Grille grille_P4 = new Grille(7,7);
            ArtificialIntelligenceImpl_P4<Grille> ia = new ArtificialIntelligence_P4() ;
            
            
            int i=0;
            nombre_de_coup=0;
            int nb_rotations_effectuees_j1=0;
            int nb_rotations_effectuees_j2=0;

            while(!grille_P4.check_all_coup_gagnant()){

                getMonIhm().afficherlaGrille(grille_P4.toString());

                if (on_joue_avec_rotation.equals("oui") || modeGame == ModeGame.SOLO) {
                    boolean rotation_effectuee=false;
                    Contexte_jouer_coup_p4 coup_rot = new Contexte_jouer_coup_p4(new Jouer_coup_rotation());
                    Contexte_jouer_coup_p4 un_coup = new Contexte_jouer_coup_p4(new Jouer_coup_p4());

                    
                    if (i % 2 == 0) {
                        if(nb_rotations_effectuees_j1<4){
                            rotation_effectuee=coup_rot.jouer(joueur1,monIhm,grille_P4);
                            if(rotation_effectuee){
                                nb_rotations_effectuees_j1=nb_rotations_effectuees_j1+1;
                            }
                        }

                        if(grille_P4.check_all_coup_gagnant()){

                            break;
                        }


                        if(!rotation_effectuee){
                            if(un_coup.jouer(joueur1,monIhm,grille_P4)){
                                nombre_de_coup=nombre_de_coup+1;
                            }
                        }
                    } else {
                    	
                    	// Joueur 2 ou IA
                    	
                    	
                    	// Si c'est une IA
                    	if(modeGame == ModeGame.SOLO) {
                    		// On prend la meilleure grille pouvant être
                    		// jouée par l'IA
                    		grille_P4 = ia.IA_retour(grille_P4) ;
                    		getMonIhm().afficherMsg(">> L'IA a joué son coup !");
                    		i++ ;
                    		continue ;
                    	}
                    	
                    	if(nb_rotations_effectuees_j2<4){
                            rotation_effectuee=coup_rot.jouer(joueur2,monIhm,grille_P4);
                            if(rotation_effectuee){
                                nb_rotations_effectuees_j2=nb_rotations_effectuees_j2+1;
                            }
                        }

                        if(grille_P4.check_all_coup_gagnant()){

                            break;
                        }


                        if(!rotation_effectuee){
                            if(un_coup.jouer(joueur2,monIhm,grille_P4)){
                                nombre_de_coup=nombre_de_coup+1;
                            }
                        }
                    }
                    i = i + 1;
                } else {
                    Contexte_jouer_coup_p4 un_coup= new Contexte_jouer_coup_p4(new Jouer_coup_p4());
                    if (i % 2 == 0) {
                        if(un_coup.jouer(joueur1,monIhm,grille_P4)){
                            nombre_de_coup=nombre_de_coup+1;
                        }
                    } else {
                        if(un_coup.jouer(joueur2,monIhm,grille_P4)){
                            nombre_de_coup=nombre_de_coup+1;
                        }
                    }
                    i=i+1;
                }

                if(nombre_de_coup==(49)){
                    break;
                }
            }
            getMonIhm().afficherMsg("------");
            getMonIhm().afficherMsg("");
            getMonIhm().afficherMsg("");


            getMonIhm().afficherlaGrille(grille_P4.toString()) ;

            if(grille_P4.check_all_coup_gagnant()){
                afficher_gagnants=true;
            }else{
                afficher_gagnants=false;
                getMonIhm().afficherMsg("Egalité, pas de gagnant pour cet partie");
            }
            if(afficher_gagnants){
                if (i % 2 == 0) {
                    getMonIhm().afficherGagnant(joueur2.getNom());
                    joueur2.gagnePartie();
                    balance = balance - 1;
                } else {
                    getMonIhm().afficherGagnant(joueur1.getNom());
                    joueur1.gagnePartie();
                    balance = balance + 1;
                }
            }
            boolean continuer_nouvelle_partie=false;

            while(!continuer_nouvelle_partie){
                int res=saisie_nouv_partie_valide(getMonIhm().nouvellePartie());
                if(res ==1){
                    continuer_nouvelle_partie=true;
                }
                else if(res==2){
                    continuer_nouvelle_partie=true;
                    continuer_a_jouer=false;
                }else{
                    getMonIhm().afficherMsg("Saisie incorrecte, saisir 1 ou 2");
                }
            }
        }
        getMonIhm().quiAGagne(balance,joueur1.getNom(),joueur2.getNom(), Integer.toString(joueur1.getNbPartiesGagnees()), Integer.toString(joueur2.getNbPartiesGagnees()));

    }
    public int saisie_nouv_partie_valide(String entree){
        int a=-1;
        try{
            a=Integer.parseInt(entree);
        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }
        return a;
    }

	public ModeGame getModeGame() {
		return modeGame;
	}
}
