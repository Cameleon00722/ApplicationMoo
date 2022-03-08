package proj.P4;

import proj.Joueur;

import java.util.Scanner;

public class Ihm2 {

    public String saisieColonne(String nom){
        String leCoup="";
        System.out.println(nom+ " : à vous de jouer");
        Scanner saisie_colonne = new Scanner(System.in);

        if(saisie_colonne.hasNextLine()){
            leCoup= saisie_colonne.nextLine();
        }
        return leCoup;
    }
    public void afficherGagnant(String a){
        System.out.println(a+" a gagné");
    }

    public String demandePrenom(){
        String nomJoueur="nom par défault";
        System.out.println("Saisir votre nom : ");
        Scanner nomJ = new Scanner(System.in);
        if (nomJ.hasNext()){
            nomJoueur=nomJ.next();
        }
        return nomJoueur;
    }

    public int afficherlaGrille(String laGrille){
        System.out.println(laGrille);
        return 0;
    }


    public void afficherMsg(String msg){
        System.out.println(msg);
    }

    public String nouvellePartie(){
        System.out.println("voulez vous faire une nouvelle partie? 1 si oui et 2 si non");
        Scanner nP = new Scanner(System.in);
        String resultat="";
        if(nP.hasNextInt()){
             resultat= nP.next();
        }
        return resultat;

    }
    public void quiAGagne(int z, String j1, String j2,String nb_j1, String nb_j2){
        if (z>0){
            System.out.println(j1+" a gagné le jeu");
            affichernombrepaetiegagner(nb_j1);
        }else if (z==0){
            System.out.println("Egalité");
        }else {
            System.out.println(j2+" a gagné le jeu");
            affichernombrepaetiegagner(nb_j2);
        }
    }
    public void affichernombrepaetiegagner(String j){
        System.out.println("Nombre de parties gagnées : "+ j);
    }
}
