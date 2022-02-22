package com.company;


import java.util.Scanner;

public class Ihm {
    int tas;
    int nb_allumettes;
    String nomJoueur;

    public int RetraitTas(Joueur A){
        System.out.println(A.getNom()+ "Saisir tas : ");
        Scanner saisie_tas = new Scanner(System.in);
        if(saisie_tas.hasNextInt()){
            tas=saisie_tas.nextInt();
            return tas;
        }
        return 0;
    }
    public void afficherGagnant(Joueur joueur){
        System.out.println(joueur.getNom()+"a gagné");
    }
    public int RetraitAllumettes(Joueur A){
        System.out.println(A.getNom()+ " Saisir nb allumettes : ");
        Scanner nb_all = new Scanner(System.in);
        if(nb_all.hasNextInt()){
            nb_allumettes=nb_all.nextInt();
            return nb_allumettes;
        }
        return nb_allumettes;
    }
    public String demandePrenom(){
        System.out.println("Saisir votre nom : ");
        Scanner nomJ = new Scanner(System.in);
        if (nomJ.hasNext()){
            nomJoueur=nomJ.next() ;
            return nomJoueur;
        }
        return null;
    }
    int get_tas(){
        return tas;
    }
    int get_nb_allumettes(){
        return nb_allumettes;
    }
    int nb_tas;
    int np;

    public int Saisie_nb_tas() {
        System.out.println("Saisir le nombre de tas");
        Scanner nb = new Scanner(System.in);
        if (nb.hasNextInt()) {
            nb_tas = nb.nextInt();
        }
        return nb_tas;
    }
    int getNb_tas(){
        return nb_tas;
    }
    public int nouvellePartie(){
        System.out.println("voulez vous faire une nouvelle partie? 1 si oui et 2 si non");
        Scanner nP = new Scanner(System.in);
        int resultat = nP.nextInt();
        boolean saisieValide = false;
        /*if (nP.hasNextInt()){
        while (!saisieValide) {
            if(nP.nextInt()==1){
                saisieValide = true;
                resultat = nP.nextInt();
            }else if(nP.nextInt() == 2){
                saisieValide = true;
                resultat = nP.nextInt();
            }else{
                System.out.println("mettez 1 ou 2");
                nP.nextInt();
            }
            }
        }*/
        return resultat;

    }
    public void quiAGagner(int z,Joueur j1,Joueur j2){
        if (z>0){
            System.out.println(j1.getNom()+" a gagné le jeu");
            affichernombrepaetiegagner(j1);
        }else if (z==0){
            System.out.println("Egalité");
        }else {
            System.out.println(j2.getNom()+" a gagné le jeu");
            affichernombrepaetiegagner(j2);
        }
    }
    public void affichernombrepaetiegagner(Joueur j){
        System.out.println("Nombre de partie gagner : "+ j.getNbPartiesGagnees());
    }
}

