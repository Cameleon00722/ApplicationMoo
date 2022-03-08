package proj.P4;

public class Coup_P4 {

    private int colonne;
    private char pion;

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
}
