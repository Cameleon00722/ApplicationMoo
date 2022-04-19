package com.company.nim;


import com.company.IA.ArtificialIntelligence_Nim;
import com.company.IA.Contexte_IA_Nim;
import com.company.elements.IControleur;
import com.company.elements.Ihm2;
import com.company.elements.Joueur;
import com.company.exceptions.CoupInvalideException;

public class Controleur_Nim implements IControleur {
    private final Ihm2 monIhm;


    public Controleur_Nim(Ihm2 ihm){
        this.monIhm=ihm;
    }

    public Ihm2 getMonIhm() {
        return monIhm;
    }

    public int coupmaxvalide(){
        boolean continuer_saisie_nb_max = false;
        int coupmax=0;

        while(!continuer_saisie_nb_max){

            coupmax =getMonIhm().demandeNbmaxcoup();
            if(coupmax>=0){
                continuer_saisie_nb_max=true;
            }
        }
        return coupmax;
    }

    @Override
    public boolean partie_terminee(Object o){
        Tas t = (Tas) o;
        return t.nbAllumette() <= 0;
    }

    @Override
    public Object creation_plateau(int parametre){
        Tas tableDeJeux = new Tas(parametre);
        tableDeJeux.initialiser();
        return tableDeJeux;
    }

    public Object un_tour_IA(Object plateau, int contrainte, int nb) {
        Tas tableDeJeux=(Tas) plateau;
        Contexte_IA_Nim ia= new Contexte_IA_Nim(new ArtificialIntelligence_Nim());
        // On prend la meilleure grille pouvant être
        // jouée par l'IA
        CoupNim coup_tmp=ia.retour_IA(contrainte,nb,tableDeJeux);

        int[] tab = new int[2];
        tab[0]=coup_tmp.getNumeroTas();
        tab[1]=coup_tmp.getNbAllumettes();
        getMonIhm().afficherMsg(">> L'IA a joué son coup !" + tab[0]+" "+tab[1]);


        CoupNim coupJ = new CoupNim(tab[0], tab[1]);

        try {
            tableDeJeux.gererCoup(coupJ);

        } catch (CoupInvalideException e) {
            getMonIhm().afficherMsg(e.toString());
        }

        return tableDeJeux;
    }

    @Override
    public Object corps_principal(Object plateau, Joueur joueur, int contrainte, int param_supp) {
        boolean coup_valide=false;
        int nbTas;
        int nbAllumettes;
        Tas tableDeJeux=(Tas) plateau;

        if(joueur.getNom().equals("IA")){
            return un_tour_IA(plateau,contrainte,param_supp);
        }

        while(!coup_valide){
            getMonIhm().afficherlesTas(tableDeJeux.toString());
            Jouer_coup_nim coup = new Jouer_coup_nim();
            int[] tab;
            tab=coup.jouer_un_coup_nim(joueur,monIhm,contrainte);

            nbTas=tab[0];
            nbAllumettes=tab[1];

            CoupNim coupJ = new CoupNim(nbTas, nbAllumettes);

            try {
                tableDeJeux.gererCoup(coupJ);
                coup_valide=true;
            } catch (CoupInvalideException e) {
                getMonIhm().afficherMsg(e.toString());
            }
        }
        return tableDeJeux;
    }

    @Override
    public int get_param_plateau() {
        boolean continuer = false;
        int le_retour=0;
        while (!continuer) {

            le_retour = getMonIhm().saisie_nb_tas();
            if (le_retour>0) {
                continuer = true;
            }
        }
        return le_retour;
    }

    @Override
    public boolean afficher_gagnants(Object plateau) {
        return true;
    }

    @Override
    public void maj_joueur(Joueur[] joueurs) {

    }

    @Override
    public int choix_contrainte() {
        if(coupmaxvalide()!=0) return coupmaxvalide() ;
        return 3 ;
    }

}
