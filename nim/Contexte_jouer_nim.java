package com.company.nim;

import com.company.elements.Ihm2;
import com.company.elements.Joueur;

public class Contexte_jouer_nim {
    private final IJouer_coup_nim strategie;

    public Contexte_jouer_nim(IJouer_coup_nim jouer){
        this.strategie= jouer;
    }
    public int[] jouer(Joueur j,Ihm2 ihm, int r){
        return strategie.jouer_un_coup_nim(j,ihm,r);
    }
}
