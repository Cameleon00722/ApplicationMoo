package com.company.elements;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.company.exceptions.CoupInvalideException;
import com.company.puissance4.Coup_P4;

public class Grille {

    private final char[][] laGrille;
    private final int largeur;
    private final int hauteur;
    private int dernier_pion_largeur =-1;
    private int dernier_pion_hauteur =-1;
    private int nb_coup_joues;

    public int getNb_coup_joues() {
        return nb_coup_joues;
    }

    public void setNb_coup_joues(int nb_coup_joues) {
        this.nb_coup_joues = nb_coup_joues;
    }

    public Grille(int largeur, int hauteur){
        this.largeur=largeur;
        this.hauteur=hauteur;
        nb_coup_joues=0;

        laGrille=new char[hauteur][largeur];
        for(int i=0;i<hauteur;i++){
            for(int j=0; j<largeur; j++){
                laGrille[i][j]='.';
            }
        }
    }
    
    public Grille(Grille grid) {
    	this.largeur = grid.largeur ;
    	this.hauteur = grid.hauteur ;
    	
    	this.laGrille = new char[largeur][hauteur] ;
    	
    	for(int x=0 ; x<largeur ; x++)
    		for(int y=0 ; y<hauteur ; y++)
    			laGrille[x][y] = grid.getPion(x, y);
    	
    	this.dernier_pion_hauteur = grid.dernier_pion_hauteur ;
    	this.dernier_pion_largeur = grid.dernier_pion_largeur ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grille grille = (Grille) o;
        return largeur == grille.largeur && hauteur == grille.hauteur && dernier_pion_largeur == grille.dernier_pion_largeur && dernier_pion_hauteur == grille.dernier_pion_hauteur && nb_coup_joues == grille.nb_coup_joues && Arrays.deepEquals(laGrille, grille.laGrille);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(largeur, hauteur, dernier_pion_largeur, dernier_pion_hauteur, nb_coup_joues);
        result = 31 * result + Arrays.deepHashCode(laGrille);
        return result;
    }

    public void rotation_a_droite(){

        List<Character> maListe1 = new ArrayList<>();
        List<Character> maListe2 = new ArrayList<>();
        List<Character> maListe3 = new ArrayList<>();
        List<Character> maListe4 = new ArrayList<>();
        List<Character> maListe5 = new ArrayList<>();
        List<Character> maListe6 = new ArrayList<>();
        List<Character> maListe7 = new ArrayList<>();

        for(int i=0;i<hauteur;i++){
            maListe1.add(laGrille[i][0]);
        }
        for(int i=0;i<hauteur;i++){
            maListe2.add(laGrille[i][1]);
        }
        for(int i=0;i<hauteur;i++){
            maListe3.add(laGrille[i][2]);
        }
        for(int i=0;i<hauteur;i++){
            maListe4.add(laGrille[i][3]);
        }
        for(int i=0;i<hauteur;i++){
            maListe5.add(laGrille[i][4]);
        }
        for(int i=0;i<hauteur;i++){
            maListe6.add(laGrille[i][5]);
        }
        for(int i=0;i<hauteur;i++){
            maListe7.add(laGrille[i][6]);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[0][largeur-1-l]=maListe1.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[1][largeur-1-l]=maListe2.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[2][largeur-1-l]=maListe3.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[3][largeur-1-l]=maListe4.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[4][largeur-l-1]=maListe5.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[5][largeur-l-1]=maListe6.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[6][largeur-1-l]=maListe7.get(l);
        }
        la_gravite();
    }

    public void rotation_a_gauche(){

        List<Character> maListe1 = new ArrayList<>();
        List<Character> maListe2 = new ArrayList<>();
        List<Character> maListe3 = new ArrayList<>();
        List<Character> maListe4 = new ArrayList<>();
        List<Character> maListe5 = new ArrayList<>();
        List<Character> maListe6 = new ArrayList<>();
        List<Character> maListe7 = new ArrayList<>();

        for(int i=0;i<hauteur;i++){
            maListe1.add(laGrille[i][0]);
        }
        for(int i=0;i<hauteur;i++){
            maListe2.add(laGrille[i][1]);
        }
        for(int i=0;i<hauteur;i++){
            maListe3.add(laGrille[i][2]);
        }
        for(int i=0;i<hauteur;i++){
            maListe4.add(laGrille[i][3]);
        }
        for(int i=0;i<hauteur;i++){
            maListe5.add(laGrille[i][4]);
        }
        for(int i=0;i<hauteur;i++){
            maListe6.add(laGrille[i][5]);
        }
        for(int i=0;i<hauteur;i++){
            maListe7.add(laGrille[i][6]);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[0][l]=maListe7.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[1][l]=maListe6.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[2][l]=maListe5.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[3][l]=maListe4.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[4][l]=maListe3.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[5][l]=maListe2.get(l);
        }
        for(int l = 0; l<largeur; l++){
            laGrille[6][l]=maListe1.get(l);
        }
        la_gravite();

    }
    public void la_gravite(){
        for(int j=0;j<largeur;j++){
            for(int a=0;a<20;a++){
                for(int i=hauteur-1; i>-1; i--){
                    if(laGrille[i][j]=='.' && i!=0){
                        laGrille[i][j]=laGrille[(i-1)][j];
                        laGrille[i-1][j]='.';
                    }
                }
            }
        }

    }

    public String horizontale(int x){
        StringBuilder leRetour= new StringBuilder();
        for(int i=0;i<largeur;i++){
            leRetour.append(laGrille[x][i]);
        }
        return leRetour.toString();
    }
    public String verticale(int y){
        StringBuilder leRetour= new StringBuilder();
        for(int i=0;i<hauteur;i++){
            leRetour.append(laGrille[i][y]);
        }
        return leRetour.toString();
    }

    public String diagonale_decroissante(int x,int y){
        StringBuilder leRetour= new StringBuilder();
        for (int h = 0; h < hauteur; h++) {
            int w = y + x - h;

            if (0 <= w && w < largeur) {
                leRetour.append(laGrille[h][w]);
            }
        }
        return leRetour.toString();
    }

    public String diagonale_croissante(int x,int y){
        StringBuilder leRetour= new StringBuilder();
        for (int h = 0; h < hauteur; h++) {
            int w = y - x + h;

            if (0 <= w && w < largeur) {
                leRetour.append(laGrille[h][w]);
            }
        }
        return leRetour.toString();
    }


    @Override
    public String toString() {
        StringBuilder leRetour= new StringBuilder();
        leRetour.append("----".repeat(Math.max(0, largeur)));
        leRetour.append("-");
        leRetour.append("\n");

        for(int i=0;i<hauteur;i++){
            for(int j=0; j<largeur; j++){
                leRetour.append("| ");
                leRetour.append(laGrille[i][j]);
                leRetour.append(" ");
                if(j==largeur-1){
                    leRetour.append("|");
                }

            }
            leRetour.append("\n");
            leRetour.append("----".repeat(Math.max(0, largeur)));
            leRetour.append("-");
            leRetour.append("\n");
        }
        return leRetour.toString();
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
        return str.contains(substring);
    }
    
    
    
    /**
     * 
     * @param c : null si check tous les coups gagnants
     * @param c : Cherche uniquement si le joueur ayant le char C
     * a gagn?? ou non.
     * @return True s'il a gagn??. 
     */
    public boolean check_all_coup_gagnant(Character c) {
    	
    	for(int x =0; x<largeur; x++){
            for(int y =0; y<hauteur;y++){
                char coup=laGrille[x][y];
                if(coup=='.'){
                    continue;
                }
                if((c == null || coup == c) && coupGagant(x,y)){
                    return true;
                }
            }
        }
        return false;
    	
    }
    

    public boolean check_all_coup_gagnant(){
    	return check_all_coup_gagnant(null) ;
    }

    public boolean coupGagant(int x, int y) {

        if (dernier_pion_largeur == -1) {
            return false;
        }

        char pion = laGrille[x][y];

        String combo_gagnant = String.format("%c%c%c%c", pion, pion, pion, pion);

        return contient(horizontale(x), combo_gagnant) ||
                contient(verticale(y), combo_gagnant) ||
                contient(diagonale_croissante(x,y), combo_gagnant) ||
                contient(diagonale_decroissante(x,y), combo_gagnant);

    }
    
    public int getLargeur() {
    	return largeur ;
    }

    public int getHauteur() {
    	return hauteur ;
    }
    
    public char getPion(int x, int y) {
    	return laGrille[x][y] ;
    }

    public char[][] getLaGrille(){
    	return laGrille ;
    }
    
    public int getDernier_pion_largeur() {
    	return dernier_pion_largeur ;
    }
    public int getDernier_pion_hauteur(){
    	return dernier_pion_hauteur ;
    }


}

