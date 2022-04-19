package com.company.puissance4;


import com.company.IA.ArtificialIntelligenceImpl_P4;
import com.company.IA.ArtificialIntelligence_P4;
import com.company.elements.Grille;
import com.company.elements.IControleur;
import com.company.elements.Ihm2;
import com.company.elements.Joueur;

public class Controleur_P4 implements IControleur {
    private final Ihm2 monIhm;


    public Controleur_P4(Ihm2 ihm){
        this.monIhm=ihm;
    }
    //méthodes pour le jeu de P4
    public Ihm2 getMonIhm() {
        return monIhm;
    }

    public int choix_contrainte(){
        int resultat=-1;
        boolean rotation_de_la_grille=false;
        while (!rotation_de_la_grille) {

            String rot = getMonIhm().P4rotation();
            if (rot.equals("oui") ) {
                rotation_de_la_grille = true;
                resultat=1;
            } else if( rot.equals("non")){
                rotation_de_la_grille = true;
                resultat=0;
            }
            else {
                getMonIhm().afficherMsg("Saisie incorrecte, saisir oui ou non ");
            }
        }
        //renvoie 1 si on fait la rotation et 0 sinon
        return resultat;

    }

    public boolean partie_terminee(Object o){
        Grille g= (Grille)o;
        if(g==null) return true;
        return g.check_all_coup_gagnant();

    }

    public Object corps_principal(Object plateau, Joueur joueur, int contrainte, int param_supp){
        Joueur_P4 joueur_ = (Joueur_P4) joueur;
        Grille grille_P4 = (Grille) plateau;
        getMonIhm().afficherlaGrille(grille_P4.toString());
        ArtificialIntelligenceImpl_P4<Grille> ia = new ArtificialIntelligence_P4() ;
        if (joueur_.getNom().equals("IA")) {
                //contrainte =1 si rotation et =0 si rien
                // On prend la meilleure grille pouvant être
                // jouée par l'IA
                grille_P4 = ia.IA_retour(grille_P4,contrainte,monIhm) ;
                getMonIhm().afficherMsg(">> L'IA a joué son coup !");
                grille_P4.setNb_coup_joues(grille_P4.getNb_coup_joues()+1);

        }
        else {
            Jouer_coup_p4 un_coup = new Jouer_coup_p4();
            if(contrainte==1){
                //le joueur joue avec rotation//
                boolean rotation_effectuee=false;
                Jouer_coup_rotation coup_rot = new Jouer_coup_rotation();

                if(joueur_.getNb_rot_joues()<4){
                   Grille temoin=coup_rot.jouer_le_coup(joueur_,monIhm,grille_P4);
                    if(temoin!=null) {
                        rotation_effectuee = true;
                        grille_P4=temoin;
                    }
                    if(rotation_effectuee){
                        joueur_.setNb_rot_joues(joueur_.getNb_rot_joues()+1);
                    }
                }
                if(!rotation_effectuee){
                    grille_P4=un_coup.jouer_le_coup(joueur_,monIhm,grille_P4);
                    grille_P4.setNb_coup_joues(grille_P4.getNb_coup_joues()+1);
                }
            }else{
                //le joueur joue sans rotation//
                grille_P4=un_coup.jouer_le_coup(joueur_,monIhm,grille_P4);
                grille_P4.setNb_coup_joues(grille_P4.getNb_coup_joues()+1);
            }
        }
        if(grille_P4.getNb_coup_joues()==(49)){
            grille_P4=null;
           return grille_P4;
        }
        return grille_P4;
    }

    @Override
    public int get_param_plateau() {
        return 7;
    }

    @Override
    public boolean afficher_gagnants(Object o) {
        Grille grille = (Grille) o;
        boolean le_retour;
        le_retour= grille.check_all_coup_gagnant();
        return le_retour;
    }

    @Override
    public void maj_joueur(Joueur[] joueurs) {
        Joueur_P4[] players = (Joueur_P4[]) joueurs;
        for(Joueur_P4 j : players){
            j.setNb_rot_joues(0);
        }
    }

    public Object creation_plateau(int i){
        return new Grille(i,i);
    }
}
