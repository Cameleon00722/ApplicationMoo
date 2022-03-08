package proj.P4;

import java.util.Objects;

public class Joueur_P4 {
    private String nom;
    private int nbPartiesGagnees;
    private char couleur_pion;


    public Joueur_P4(String nom, char couleur) {
        this.nom = nom;
        this.couleur_pion=couleur;
    }


    public String getNom() {
        return nom;
    }

    public char getCouleur_pion(){return couleur_pion;}


    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }


    public void gagnePartie() {
        nbPartiesGagnees++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur_P4 joueur = (Joueur_P4) o;
        return nbPartiesGagnees == joueur.nbPartiesGagnees && Objects.equals(nom, joueur.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, nbPartiesGagnees);
    }
}
