package com.company.elements;

import java.util.Scanner;

@SuppressWarnings("resource")
public class Ihm2 {

    
	public String SaisieCoup(String nom){
        String leCoup="";
        System.out.println(nom+ " : à vous de jouer un coup sous " +
                "la forme 'm n' où m est le tas choisi et n le nombre" +
                " d'allumette");
        Scanner saisie_tas = new Scanner(System.in);

        if(saisie_tas.hasNextLine()){
            leCoup=saisie_tas.nextLine();
        }
        return leCoup;
    }
    public void afficherGagnant(String joueur){
        System.out.println(joueur+" a gagné");
    }



    public void afficherlesTas(String lesTas){
        System.out.println(lesTas);
    }

    public int saisie_nb_tas() {

        System.out.println("Saisir le nombre de tas souhaité :");
        Scanner nb = new Scanner(System.in);
        int resultat=0;
        if(nb.hasNextInt()){
            resultat= nb.nextInt();
            nb.nextLine();
        }
        return resultat;
    }


    public int nouvellePartie(){
        int resultat=0;
        System.out.println("voulez vous faire une nouvelle partie? 1 si oui et 2 si non");
        Scanner nP = new Scanner(System.in);
        if(nP.hasNextInt()){
            resultat=nP.nextInt();
            nP.nextLine();
        }
        return resultat;

    }



    public int saisieColonne(String nom){

        System.out.println(nom+ " : à vous de jouer");
        Scanner rep = new Scanner(System.in);
        int resultat=0;
        if(rep.hasNextInt()){
            resultat= rep.nextInt();
            rep.nextLine();
        }
        return resultat;
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

    public String demandeJeu(){

        System.out.println("Saisir jeu : saisir 1 pour le jeu de Nim, saisir 2 pour le jeu de P4");
        Scanner jeu = new Scanner(System.in);
        String resultat="";
        if(jeu.hasNextInt()){
            resultat= jeu.next();
        }
        return resultat;
    }


    public void afficherlaGrille(String laGrille){
        System.out.println(laGrille);
    }


    public void afficherMsg(String msg){
        System.out.println(msg);
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
    public String P4rotation(){
        System.out.println("voulez vous jouer avec la rotation ( oui ou non ) ? ");
        Scanner rep = new Scanner(System.in);
        String res ="" ;
        if (rep.hasNext()){
             res = rep.next();
        }
        return res ;
    }
    public String demanderjouerP4(String nom){
        System.out.println(nom+", voulez vous jouer ou faire une rotation (1 pour jouer et 2 pour retourner) ? ");
        Scanner rep = new Scanner(System.in);
        String res = "";
        if ((rep.hasNext())){
            res = rep.next();
        }
        return res;
    }
    public String demande_droite_gauche(){
        System.out.println("Quelle rotation souhaitez-vous effectuer (droite ou gauche) ? ");
        Scanner rep = new Scanner(System.in);
        String res = "";
        if ((rep.hasNext())){
            res = rep.next();
        }
        return res;
    }
    public String demandeIA(){
        System.out.println("Tapez 1 pour jouer seul ou 2 pour jouer à deux.");
        Scanner rep = new Scanner(System.in);
        String res = "";
        if ((rep.hasNext())){
            res = rep.next();
        }
        return res;
    }
    public int demandeNbmaxcoup(){
        System.out.println("Au maximun, combien souhaitez-vous retirer d'allumettes (Tapez 0 " +
                "pour les règles classiques ou sinon un chffre ) ? ");
        Scanner rep = new Scanner(System.in);
        int resultat=0;
        if(rep.hasNextInt()){
            resultat= rep.nextInt();
            rep.nextLine();
        }
        return resultat;
    }
    public void affichernombrepaetiegagner(String j){
        System.out.println("Nombre de parties gagnées : "+ j);
    }
}
