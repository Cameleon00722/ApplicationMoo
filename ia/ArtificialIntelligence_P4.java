package com.company.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.company.elements.Grille;

public class ArtificialIntelligence_P4 implements ArtificialIntelligenceImpl<Grille> {
	
	public ArtificialIntelligence_P4() {
	}
	
	public Grille getBestGrid(Grille grid) {
		
		List<CoupIA> list = getListAllCoups(grid) ;
		Grille max_grid = null ;
		
		// Tri de la liste à l'aide de la lib Collections
		Collections.sort(list);
		
		
		for(CoupIA coup : list) {
			
			// On actualise la meilleure grille
			// Vu que c'est trier dans l'ordre décroissant,
			// Les premières grilles sont les meilleures
			max_grid = new Grille(grid) ;
			
			if(coup.getType() == TypeCoup.CLASSIQUE) max_grid.placement_pion(coup.getX(), 'j') ;
			else if(coup.getType() == TypeCoup.ROTATION_DROITE) max_grid.rotation_a_droite() ;
			else if(coup.getType() == TypeCoup.ROTATION_GAUCHE) max_grid.rotation_gauche() ;
			
			// et on la return si on peut gagner
			if(coup.getScore() == 7) return max_grid ;
			
			
			Grille grid_droite = new Grille(coup.getGrille()) ;
			Grille grid_gauche = new Grille(coup.getGrille()) ;
			
			// à renommer pareil "rotation_a_droite" & "rotation_a_gauche"
			grid_droite.rotation_a_droite();
			grid_gauche.rotation_gauche(); 
			
			// Si avec une rotation nous trouvons un coup gagnant
			// Alors pour passons à la prochaine solutions
			if(grid_droite.check_all_coup_gagnant('r') || grid_gauche.check_all_coup_gagnant('r'))
				continue ;
			
			return max_grid ;
			
		}

		return max_grid ;
	}
	
	private List<CoupIA> getListAllCoups(Grille grid){
		

		char[] chars = {'j', 'r'} ;
		
		List<CoupIA> list = new ArrayList<CoupIA>() ;
		
		
		Grille grille_rotation_droite = new Grille(grid) ;
		Grille grille_rotation_gauche = new Grille(grid) ;
		
		grille_rotation_droite.rotation_a_droite();
		grille_rotation_gauche.rotation_gauche();
		
		int score_grille_droite = grille_rotation_droite.check_all_coup_gagnant('j') ? 7 : 1 ;
		int score_grille_gauche = grille_rotation_droite.check_all_coup_gagnant('j') ? 7 : 1 ;
		
		list.add(new CoupIA(grille_rotation_droite, score_grille_droite, TypeCoup.ROTATION_DROITE)) ;
		list.add(new CoupIA(grille_rotation_gauche, score_grille_gauche, TypeCoup.ROTATION_GAUCHE)) ;
		
		for(char c : chars) {
			for(int x=0 ; x<grid.getLargeur() ; x++) {
				Grille grid_test = new Grille(grid) ;
				grid_test.placement_pion(x, c) ;
				
				
				
				int score = getScoreLastCoup(grid_test, c) ;
				
				// Pour définir les priorités des coups
				if(c == 'j') {
					switch (score) {
						case 4:
							score = 7 ;
							break;
						case 3: 
							score = 5;
							break ;
						case 2:
							score = 3; 
							break ;
							
							
						default:
							break;
					}
				}
				else if(c == 'r') {
					switch (score) {
						case 4:
							score = 6 ;
							break;
						case 3: 
							score = 4;
							break ;
							
						default:
							break;
					}
				}
				
				//System.out.println("Score final : "+score+", max_score : "+max_score);
				
				CoupIA coup = new CoupIA(grid_test, score, TypeCoup.CLASSIQUE, x) ;
				list.add(coup) ;
				
				//System.out.println("Score : "+score);
				
				//System.out.println("-");
				//System.out.println(">>");
				//System.out.println(grid_test);
				
				
				
				/*
				for(int x1=0 ; x1<grid.getLargeur() ; x1++)
					for(int y=0 ; y<grid.getLargeur() ; y++) 
						System.out.println("["+x1+", "+y+"] "+grid_test.getLaGrille()[x1][y]);;
				*/
				
				//System.out.println(coup.toString());
			}
			

		}
		
		
		return list ;
	}
	

	private int getScoreLastCoup(Grille grid, char c) {
		
		int y = grid.getDernier_pion_largeur(), x = grid.getDernier_pion_hauteur() ;
		
		/*
		String hor = getStringCompleted(grid.horizontale(x)) ;
		String vert = getStringCompleted(grid.verticale(y)) ;
		String diag1 = getStringCompleted(grid.diagonale_croissante(x, y)) ;
		String diag2 = getStringCompleted(grid.diagonale_decroissante(x, y)) ;
		*/
		String hor = grid.horizontale(x) ;
		String vert = grid.verticale(y) ;
		String diag1 = grid.diagonale_croissante(x, y) ;
		String diag2 = grid.diagonale_decroissante(x, y) ;
		
		//System.out.println("X = "+x+", Y = "+y);

		int score = Math.max(getScoreLigne(hor, c, y), getScoreLigne(vert, c, x)) ;
		score = Math.max(score, getScoreLigne(diag1, c, y)) ;
		score = Math.max(score, getScoreLigne(diag2, c, grid.getHauteur()-y-1)) ;
		
		//System.out.println(grid.getPion(x, y)+", "+x+", "+y+" : "+"["+hor+", "+vert+", "+diag1+", "+diag2+"]");
		
		return score ;
	}
	
	private int getScoreLigne(String str, char c, int index) {

		int compt = -1 ;
		for(int i=0 ; i<4 && index+i < str.length() ; i++) {
			if(str.charAt(i+index) == c) compt++ ;
			else break ;
		}
		
		for(int i=0 ; i<4 && index-i >= 0  ; i++) {
			try {
				if(str.charAt(index-i) == c) compt++ ;
				else break ;
			} catch(StringIndexOutOfBoundsException e) {
				// Traiter l'erreur
				//System.out.println("str="+str+", index="+index+", i="+i);
			}
			
		}
		
		//System.out.println("- "+str+" : "+str+", "+index+", "+str.charAt(index)+" = SCORE : "+compt);
		return compt ;
		
		/*
		int compt = 0, size = 3 ;
		boolean pre = false , nex = false ;
		System.out.println("-");
		System.out.println(str+" : "+index);
		System.out.println(str.charAt(size+index-1)+""+str.charAt(size+index)+""+str.charAt(size+index+1));
		
		for(int i=0 ; i<size ; i++) {
			
			char previous = str.charAt(i+size+index-1) ;
			char next = str.charAt(i+size+index+1) ;
			
			if(previous == c && !pre) compt++ ;
			else pre = true ;
			
			if(next == c && !nex) compt++ ;
			else nex = true ;
			
		}
		
		//System.out.println("Ligne : "+str+", score : "+compt+", char : "+c);
		*/
	}
	/*
	private String getStringCompleted(String s) {
		String str = "" ;
		for(int i=0 ; i<3 ; i++) str+="." ;
		str += s ;
		for(int i=0 ; i<3 ; i++) str+="." ;
		//while(str.length() < grid.getLargeur()+6) str += "." ;
		return str ;
	}
	*/
}
