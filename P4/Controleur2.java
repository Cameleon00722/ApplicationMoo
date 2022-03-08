package proj.P4;


import proj.CoupInvalideException;

public class Controleur2 {
    private final Ihm2 monIhm;

    public Controleur2(Ihm2 ihm){
        this.monIhm=ihm;
    }

    public Ihm2 getMonIhm() {
        return monIhm;
    }
    public int saisie_colonne_valide(String colonne){
        int a=-1;
        try{
            a=Integer.parseInt(colonne);

        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }
        return a;
    }
    public int saisie_nouv_partie_valide(String entree){
        int a=-1;
        try{
            a=Integer.parseInt(entree);
        }catch(NumberFormatException e){
            getMonIhm().afficherMsg("Format incorrect");
        }
        return a;
    }

    public void jouer() {
        String nomj1 = getMonIhm().demandePrenom();
        String nomj2 = getMonIhm().demandePrenom();
        Joueur_P4 joueur1 = new Joueur_P4(nomj1,'r');
        Joueur_P4 joueur2 = new Joueur_P4(nomj2,'j');

        boolean  continuer_a_jouer=true;
        int balance=0;
        int nombre_de_coup;
        boolean afficher_gagnants=true;

        while(continuer_a_jouer){
            Grille grille_P4 = new Grille(7,7);
            int i=0;
            nombre_de_coup=0;

            while(!grille_P4.coupGagnant()){
                afficher_gagnants=true;
                int b;
                boolean continuer=false;
                Coup_P4 leCoup;

                getMonIhm().afficherlaGrille(grille_P4.toString());
                while(!continuer){
                    if(i%2==0){
                        b=saisie_colonne_valide(getMonIhm().saisieColonne(joueur1.getNom()));
                        if(b!=-1){
                            leCoup= new Coup_P4(b-1,joueur1.getCouleur_pion());
                            try{
                                grille_P4.gererCoup(leCoup);
                                nombre_de_coup=nombre_de_coup+1;
                                i=i+1;
                            }catch(CoupInvalideException e){
                                getMonIhm().afficherMsg(e.toString());
                            }
                            continuer=true;
                        }
                    }else{
                        b=saisie_colonne_valide(getMonIhm().saisieColonne(joueur2.getNom()));
                        if(b!=-1){
                            leCoup= new Coup_P4(b-1,joueur2.getCouleur_pion());
                            try{
                                grille_P4.gererCoup(leCoup);
                                nombre_de_coup=nombre_de_coup+1;
                                i=i+1;
                            }catch(CoupInvalideException e){
                                getMonIhm().afficherMsg(e.toString());
                            }
                            continuer=true;
                        }
                    }
                }
                if(nombre_de_coup==(49)){
                    break;
                }
            }
            if(grille_P4.coupGagnant()){
                afficher_gagnants=true;
            }else{
                afficher_gagnants=false;
                getMonIhm().afficherMsg("Egalité, pas de gagnant pour cet partie");
            }
            if(afficher_gagnants){
                if (i % 2 == 0) {
                    getMonIhm().afficherGagnant(joueur2.getNom());
                    joueur2.gagnePartie();
                    balance = balance - 1;
                } else {
                    getMonIhm().afficherGagnant(joueur1.getNom());
                    joueur1.gagnePartie();
                    balance = balance + 1;
                }
            }
            boolean continuer_nouvelle_partie=false;

            while(!continuer_nouvelle_partie){
                int res=saisie_nouv_partie_valide(getMonIhm().nouvellePartie());
                if(res ==1){
                    continuer_nouvelle_partie=true;
                }
                else if(res==2){
                    continuer_nouvelle_partie=true;
                    continuer_a_jouer=false;
                }else{
                    getMonIhm().afficherMsg("Saisie incorrecte, saisir 1 ou 2");
                }
            }
        }
        getMonIhm().quiAGagne(balance,joueur1.getNom(),joueur2.getNom(), Integer.toString(joueur1.getNbPartiesGagnees()), Integer.toString(joueur2.getNbPartiesGagnees()));
    }
}
