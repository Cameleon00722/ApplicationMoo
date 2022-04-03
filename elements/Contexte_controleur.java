package com.company.elements;

public class Contexte_controleur {
    private final IControleur strategie;

    public Contexte_controleur(IControleur IControleur){
        this.strategie= IControleur;
    }
    public void jouer(){
        strategie.jouer();
    }
}
