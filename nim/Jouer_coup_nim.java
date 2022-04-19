package com.company.nim;

import com.company.elements.Ihm2;
import com.company.elements.Joueur;

public class Jouer_coup_nim {


    public int[] jouer_un_coup_nim(Joueur joueur1,Ihm2 ihm, int r) {
        int[] retour = new int[2];
        String unCoup;
        boolean continuons1 = false;
        while (!continuons1) {
            unCoup = ihm.SaisieCoup(joueur1.getNom());

            if (saisieValide(unCoup, r,ihm)) {
                retour[0] = getNbTas(unCoup);
                retour[1] = getNbAllumettes(unCoup);
                continuons1 = true;

            }
        }
        return retour;
    }

    public int getNbAllumettes(String coup){
        return Integer.parseInt(String.valueOf(coup.charAt(2)));
    }
    public int getNbTas(String coup){
        return Integer.parseInt(String.valueOf(coup.charAt(0)));
    }
    public boolean saisieValide(String coup,int y, Ihm2 ihm){
        try{
            if(coup.length()!=3){

                return false;
            }
            int a=Integer.parseInt(String.valueOf(coup.charAt(0)));

            int c=Integer.parseInt(String.valueOf(coup.charAt(2)));

            if(a >=1 && c>0 && c<=y && coup.charAt(1)==' '){

                return true;
            }

        }catch(NumberFormatException e){
            ihm.afficherMsg("Format incorrect");
        }
        ihm.afficherMsg("Nombre incorrect");
        return false;
    }
}
