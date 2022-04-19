package com.company.elements;

public interface IControleur {

    int choix_contrainte();
    boolean partie_terminee(Object o);
    Object creation_plateau(int i);
    Object corps_principal(Object plateau, Joueur joueur, int contrainte, int param_supp);
    int get_param_plateau();
    boolean afficher_gagnants(Object o);
    void maj_joueur(Joueur[] joueurs);

}
