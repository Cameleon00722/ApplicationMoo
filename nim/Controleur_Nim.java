package com.company.nim;

import com.company.elements.IControleur;
import com.company.elements.Ihm2;
import com.company.elements.Joueur;
import com.company.exceptions.CoupInvalideException;

public class Controleur_Nim implements IControleur {
    private final Ihm2 monIhm;

    public Controleur_Nim(Ihm2 ihm){
        this.monIhm=ihm;
    }

    //méthodes pour le jeu de P4
    public Ihm2 getMonIhm() {
        return monIhm;
    }
    /*public int saisie_colonne_valide(String colonne){
        int a=-1;
        try{
            a=Integer.parseInt(colonne);

        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }
        return a;
    }*/
    public int saisie_nouv_partie_valide(String entree){
        int a=-1;
        try{
            a=Integer.parseInt(entree);
        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }
        return a;
    }
    /*public int saisie_jeu_valide(String entree){
        int a=-1;
        try{
            a=Integer.parseInt(entree);
        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }

        return a;
    }*/

    // méthodes pour le Nim

    public boolean nbTasValide(String entree){
        try{
            int a = Integer.parseInt(entree);
            if(a>0){
                return true;
            }
        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }
        return false;
    }
    public int coupmaxvalide(){
        boolean continuer_saisie_nb_max = false;
        int coupmax=0;
        String f ;

        while(!continuer_saisie_nb_max){
            try{
                f =getMonIhm().demandeNbmaxcoup();
                if (f.equals("0")){
                    coupmax = 3;
                }else {
                    coupmax = Integer.parseInt(f);

                }
            }catch(NumberFormatException e){
                getMonIhm().afficherMsg("Format incorrect");
            }
            if(coupmax>0){
                continuer_saisie_nb_max=true;
            }
        }
        return coupmax;
    }

    @Override
    public void jouer() {
        String nomj1 = getMonIhm().demandePrenom();
        String nomj2 = getMonIhm().demandePrenom();
        Joueur joueur1 = new Joueur(nomj1);
        Joueur joueur2 = new Joueur(nomj2);

        String nombreTasDansPartie="";

        int compter = 0;
        int choix = 1;
        while (choix == 1){

            //on demande le nombre d'allumettes max à retirer
            int r = coupmaxvalide();

            boolean continuer = false;
            while (!continuer) {

                nombreTasDansPartie = getMonIhm().saisie_nb_tas();
                if (nbTasValide(nombreTasDansPartie)) {
                    continuer = true;
                }
            }

            Tas tableDeJeux = new Tas(Integer.parseInt(nombreTasDansPartie));
            tableDeJeux.initialiser();


            int nbTas = 0;
            int nbAllumettes = 0;
            int nbTotalAllumetteTable = tableDeJeux.nbAllumette();

            int i = 0;
            while (nbTotalAllumetteTable > 0) {

                getMonIhm().afficherlesTas(tableDeJeux.toString());
                Contexte_jouer_nim coup = new Contexte_jouer_nim(new Jouer_coup_nim());
                int[] tab;
                if (i % 2 == 0) {
                    tab=coup.jouer(joueur1,monIhm,r);
                } else {
                    tab=coup.jouer(joueur2,monIhm,r);
                }
                nbTas=tab[0];
                nbAllumettes=tab[1];
                CoupNim coupJ = new CoupNim(nbTas, nbAllumettes);
                try {
                    tableDeJeux.gererCoup(coupJ);
                    nbTotalAllumetteTable = nbTotalAllumetteTable - nbAllumettes;
                    i += 1;
                } catch (CoupInvalideException e) {
                    System.out.println(e);
                }

            }
            if (i % 2 == 0) {
                getMonIhm().afficherGagnant(joueur2);
                joueur2.gagnePartie();
                compter = compter - 1;
            } else {
                getMonIhm().afficherGagnant(joueur1);
                joueur1.gagnePartie();
                compter = compter + 1;
            }
            boolean continuer_nouvelle_partie = false;

            while (!continuer_nouvelle_partie) {
                int res = saisie_nouv_partie_valide(getMonIhm().nouvellePartie());
                if (res == 1) {
                    continuer_nouvelle_partie = true;
                } else if (res == 2) {
                    continuer_nouvelle_partie = true;
                    //continuer_a_jouer=false;
                    choix = res;
                } else getMonIhm().afficherMsg("Saisie incorrecte, saisir 1 ou 2");
            }
            //choix = getMonIhm().nouvellePartie();
        }
        getMonIhm().quiAGagne(compter, joueur1, joueur2);
    }
}
