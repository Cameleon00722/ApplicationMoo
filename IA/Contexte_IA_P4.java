package com.company.IA;

import com.company.elements.Grille;
import com.company.elements.Ihm2;


public class Contexte_IA_P4 {
    private final ArtificialIntelligenceImpl_P4<Grille> strategie;
    public Contexte_IA_P4(ArtificialIntelligenceImpl_P4<Grille> jouer){
        this.strategie=jouer;
    }
    public Grille retour_IA(Grille g, int a, Ihm2 ihm){
        return strategie.IA_retour(g,a,ihm);
    }
}
