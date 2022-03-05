package proj;

public class Controleur {
    private Ihm A;

    public Controleur(Ihm A) {
        this.A = A;
    }

    public Ihm getIHM() {
        return A;
    }

    public int getNbAllumettes(String coup){
        return Integer.parseInt(String.valueOf(coup.charAt(2)));
    }
    public int getNbTas(String coup){
        return Integer.parseInt(String.valueOf(coup.charAt(0)));
    }
    public boolean saisieValide(String coup){
        try{

            int a=Integer.parseInt(String.valueOf(coup.charAt(0)));

            int c=Integer.parseInt(String.valueOf(coup.charAt(2)));

            if(coup.length()!=3){

                return false;
            }

            if(a >=1 && c>0 && c<4 && coup.charAt(1)==' '){

                return true;
            }

        }catch(NumberFormatException e){
            getIHM().afficherMsg("Format incorrect");
        }
        getIHM().afficherMsg("Nombre incorrect");
        return false;

    }
    public boolean nbTasValide(String entree){
        try{
            int a = Integer.parseInt(entree);
            if(a>0){
                return true;
            }
        }catch(NumberFormatException e){
            getIHM().afficherMsg("Format incorrect");
        }
        return false;
    }



    public void jouer() {

        String nomj1 = getIHM().demandePrenom();
        String nomj2 = getIHM().demandePrenom();
        Joueur joueur1 = new Joueur(nomj1);
        Joueur joueur2 = new Joueur(nomj2);

        String nombreTasDansPartie="";

        boolean continuer=false;
        while(!continuer){

            nombreTasDansPartie = getIHM().saisie_nb_tas();
            if(nbTasValide(nombreTasDansPartie)){
                continuer=true;
            }
        }


        int compter = 0;
        int choix = 1;
        while (choix == 1) {

            Tas tableDeJeux = new Tas(Integer.parseInt(nombreTasDansPartie));
            tableDeJeux.initialiser();

            String unCoup="";
            int nbTas=0;
            int nbAllumettes=0;
            int nbTotalAllumetteTable = tableDeJeux.nbAllumette();

            int i = 0;
            while (nbTotalAllumetteTable > 0) {

                getIHM().afficherlesTas(tableDeJeux.toString());

                if (i % 2 == 0) {

                    boolean continuons1 = false;
                    while(!continuons1){
                        unCoup = getIHM().SaisieCoup(joueur1.getNom());

                        if(saisieValide(unCoup)) {
                            nbTas = getNbTas(unCoup);
                            nbAllumettes = getNbAllumettes(unCoup);
                            continuons1 = true;

                        }
                    }

                } else {
                    boolean continuons2 = false;
                    while(!continuons2){
                        unCoup = getIHM().SaisieCoup(joueur2.getNom());

                        if(saisieValide(unCoup)) {
                            nbTas = getNbTas(unCoup);
                            nbAllumettes = getNbAllumettes(unCoup);
                            continuons2 = true;
                        }
                    }
                }
                CoupNim coupJ = new CoupNim(nbTas, nbAllumettes);
                try {
                    tableDeJeux.gererCoup(coupJ);
                    nbTotalAllumetteTable=nbTotalAllumetteTable-nbAllumettes;
                    i += 1;
                } catch (CoupInvalideException e) {
                    System.out.println(e);
                }

            }
            if (i % 2 == 0) {
                getIHM().afficherGagnant(joueur2);
                joueur2.gagnePartie();
                compter = compter - 1;
            } else {
                getIHM().afficherGagnant(joueur1);
                joueur1.gagnePartie();
                compter = compter + 1;
            }
            choix = getIHM().nouvellePartie();
        }
        getIHM().quiAGagne(compter, joueur1, joueur2);

    }
}





