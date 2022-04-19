package com.company.IA;


import com.company.nim.CoupNim;
import com.company.nim.Tas;

public class Contexte_IA_Nim {
    private final ArtificialIntelligenceImpl_Nim<CoupNim> strategie;

    public Contexte_IA_Nim(ArtificialIntelligenceImpl_Nim<CoupNim> jouer){
        this.strategie=jouer;
    }
    public CoupNim retour_IA(int a, int b, Tas t){
        return strategie.IA_retour(a,b,t);
    }
}
