package com.company;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        controleur controleurJeu=new controleur(ihm);
        try {
            controleurJeu.jouer(controleurJeu);
        } catch (CoupInvalideException e) {
            e.printStackTrace();
        }
    }

}
