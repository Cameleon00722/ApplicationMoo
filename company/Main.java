package proj;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Controleur Jeu = new Controleur(ihm);
        Jeu.jouer();

    }
}
