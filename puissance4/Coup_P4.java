package com.company.puissance4;

public class Coup_P4 {

    private final int colonne;
    private final char pion;

    public Coup_P4(int colonne, char pion) {

        this.colonne = colonne;
        this.pion = pion;
    }

    public char getPion() {
        return pion;
    }

    public int getColonne() {
        return colonne;
    }

    public String toString(){
        return "colonne :"+colonne;
    }
}
