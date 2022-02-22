package com.company;

public class controleur {
    private Ihm A;

    public controleur(Ihm A) {
        this.A = A;
    }

    public Ihm getA() {
        return A;
    }

    public void jouer(controleur controleur2) throws CoupInvalideException {

        String nomj = controleur2.getA().demandePrenom();
        String nomj2 = controleur2.getA().demandePrenom();
        Joueur joueur1 = new Joueur(nomj);
        Joueur joueur2 = new Joueur(nomj2);
        int compter = 0;
        int choix = 1;
        while (choix == 1) {
            int B = controleur2.getA().Saisie_nb_tas();
            Tas S = new Tas(B);
            S.initialiser();
            int tasD;
            int Allumettes;
            int nbA = S.nbAllumette();
            int i = 0;
            while (nbA > 0) {
                if (i % 2 == 0) {
                    tasD = controleur2.getA().RetraitTas(joueur1);
                    Allumettes = controleur2.getA().RetraitAllumettes(joueur1);
                } else {
                    tasD = controleur2.getA().RetraitTas(joueur2);
                    Allumettes = controleur2.getA().RetraitAllumettes(joueur2);
                }
                CoupNim coupJ = new CoupNim(tasD, Allumettes);
                try {
                    S.gererCoup(coupJ);
                    nbA -= Allumettes;
                    i += 1;
                } catch (CoupInvalideException e) {
                    System.out.println(e);
                }

            }
            if (i % 2 == 0) {
                controleur2.getA().afficherGagnant(joueur2);
                joueur2.gagnePartie();
                compter = compter - 1;
            } else {
                controleur2.getA().afficherGagnant(joueur1);
                joueur1.gagnePartie();
                compter = compter + 1;
            }
            choix = controleur2.getA().nouvellePartie();
        }
        controleur2.getA().quiAGagner(compter, joueur1, joueur2);

    }
}

