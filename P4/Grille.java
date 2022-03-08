package proj.P4;


import proj.CoupInvalideException;

public class Grille {

    private char[][] laGrille;
    private int largeur,hauteur;;
    private int dernier_pion_largeur =-1;
    private int dernier_pion_hauteur =-1;


    public Grille(int largeur,int hauteur){
        this.largeur=largeur;
        this.hauteur=hauteur;

        laGrille=new char[hauteur][largeur];
        for(int i=0;i<hauteur;i++){
            for(int j=0; j<largeur; j++){
                laGrille[i][j]='.';
            }
        }
    }

    public String horizontale(){
        String leRetour="";
        for(int i=0;i<largeur;i++){
            leRetour+=laGrille[dernier_pion_hauteur][i];
        }
        return leRetour;
    }
    public String verticale(){
        String leRetour="";
        for(int i=0;i<hauteur;i++){
            leRetour+=laGrille[i][dernier_pion_largeur];
        }
        return leRetour;
    }

    public String diagonale_decroissante(){
        String leRetour="";
        for (int h = 0; h < hauteur; h++) {
            int w = dernier_pion_hauteur + dernier_pion_largeur - h;

            if (0 <= w && w < largeur) {
                leRetour+=laGrille[h][w];
            }
        }
        return leRetour;
    }

    public String diagonale_croissante(){
        String leRetour="";
        for (int h = 0; h < hauteur; h++) {
            int w = dernier_pion_hauteur - dernier_pion_largeur + h;

            if (0 <= w && w < largeur) {
                leRetour+=laGrille[h][w];
            }
        }
        return leRetour;
    }


    @Override
    public String toString() {
        String leRetour="";
        for(int i=0;i<hauteur;i++){
            for(int j=0; j<largeur; j++){
                leRetour+=laGrille[i][j];

            }
            leRetour+="\n";
        }
        return leRetour;
    }


    public void gererCoup(Coup_P4 coup) throws CoupInvalideException {
        if(coup.getColonne()>largeur-1 || coup.getColonne()<0 ){
            throw new CoupInvalideException("Coup invalide, retentez");
        }else{
            if(!placement_pion(coup.getColonne(),coup.getPion())){
                throw new CoupInvalideException("Coup invalide, colonne pleine, rententez");
            }
        }
    }

    public boolean placement_pion(int col, char pion){
        for (int h = hauteur - 1; h >= 0; h--) {
            if (laGrille[h][col] == '.') {
                laGrille[dernier_pion_hauteur = h][dernier_pion_largeur = col] = pion;
                return true;
            }
        }
        return false;
    }


    public static boolean contient(String str, String substring) {
        if(str.contains(substring)){
            return true;
        }else{
            return false;
        }
    }


    public boolean coupGagnant() {
        if (dernier_pion_largeur == -1) {
            return false;
        }

        char pion = laGrille[dernier_pion_hauteur][dernier_pion_largeur];

        String combo_gagnant = String.format("%c%c%c%c", pion, pion, pion, pion);

        return contient(horizontale(), combo_gagnant) ||
                contient(verticale(), combo_gagnant) ||
                contient(diagonale_croissante(), combo_gagnant) ||
                contient(diagonale_decroissante(), combo_gagnant);

    }
}

