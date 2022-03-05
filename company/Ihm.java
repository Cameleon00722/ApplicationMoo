package  proj;


import java.util.Scanner;

public class Ihm {



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
    public void afficherGagnant(Joueur joueur){
        System.out.println(joueur.getNom()+" a gagné");
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

    public int afficherlesTas(String lesTas){
        System.out.println(lesTas);
        return 0;
    }

    public String saisie_nb_tas() {
        String nb_tas="";
        System.out.println("Saisir le nombre de tas");
        Scanner nb = new Scanner(System.in);
        if (nb.hasNext()) {
            nb_tas = nb.next();
        }
        return nb_tas;
    }
    public void afficherMsg(String msg){
        System.out.println(msg);
    }

    public int nouvellePartie(){
        System.out.println("voulez vous faire une nouvelle partie? 1 si oui et 2 si non");
        Scanner nP = new Scanner(System.in);
        int resultat = nP.nextInt();
        boolean saisieValide = false;

        return resultat;

    }
    public void quiAGagne(int z, Joueur j1, Joueur j2){
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
        System.out.println("Nombre de parties gagnées : "+ j.getNbPartiesGagnees());
    }
}

