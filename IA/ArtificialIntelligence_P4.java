package com.company.IA;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.company.elements.Grille;
import com.company.elements.Ihm2;


public class ArtificialIntelligence_P4 implements com.company.IA.ArtificialIntelligenceImpl_P4<Grille> {
	
	public ArtificialIntelligence_P4() {
	}
	
	public Grille IA_retour(Grille grid, int contrainte, Ihm2 ihm) {
		
		List<CoupIA> list = getListAllCoups(grid) ;
		Grille max_grid = null ;
		List<CoupIA> liste_utilisee;
		
		// Tri de la liste à l'aide de la lib Collections
		Collections.sort(list);

		List<CoupIA> list1=new ArrayList<>();
		List<CoupIA> list2=new ArrayList<>();
		List<CoupIA> list3=new ArrayList<>();
		List<CoupIA> list4=new ArrayList<>();
		List<CoupIA> list5=new ArrayList<>();
		List<CoupIA> list6=new ArrayList<>();
		List<CoupIA> list7=new ArrayList<>();

		for (CoupIA coup: list ) {
			switch(coup.getScore()){
				case 1:
					list1.add(coup);
					break;
				case 2:
					list2.add(coup);
					break;
				case 3:
					list3.add(coup);
					break;
				case 4:
					list4.add(coup);
					break;
				case 5:
					list5.add(coup);
					break;
				case 6:
					list6.add(coup);
					break;
				case 7:
					list7.add(coup);
					break;
				default:
					break;
			}
		}

		System.out.println("les coups 1 : ");
		for (CoupIA coup: list1
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");
		System.out.println("les coups 2 : ");
		for (CoupIA coup: list2
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");
		System.out.println("les coups 3 : ");
		for (CoupIA coup: list3
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");
		System.out.println("les coups 4 : ");
		for (CoupIA coup: list4
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");
		System.out.println("les coups  5: ");
		for (CoupIA coup: list5
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");
		System.out.println("les coups 6 : ");
		for (CoupIA coup: list6
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");
		System.out.println("les coups 7 : ");
		for (CoupIA coup: list7
		) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");



		if(!list7.isEmpty()){
			liste_utilisee= new ArrayList<>(list7);
		}else if(!list6.isEmpty()){
			liste_utilisee= new ArrayList<>(list6);
		}else if(!list5.isEmpty()){
			liste_utilisee= new ArrayList<>(list5);
		}else if(!list4.isEmpty()){
			liste_utilisee= new ArrayList<>(list4);
		}else if(!list3.isEmpty()){
			liste_utilisee= new ArrayList<>(list3);
		}else if(!list2.isEmpty()){
			liste_utilisee= new ArrayList<>(list2);
		}else {
			liste_utilisee= new ArrayList<>(list1);
		}

		Random rand = new Random();
		int nb_aleatoire=rand.nextInt(liste_utilisee.size());


		System.out.println("les coups : ");
		for (CoupIA coup: liste_utilisee
			 ) {
			System.out.println(coup);
		}
		System.out.println("fin des coups");

		CoupIA coup = liste_utilisee.get(nb_aleatoire);
		System.out.println("voici le nb aleatoire : "+nb_aleatoire);
		System.out.println("voici le coup choisi"+coup);
		//for(CoupIA coup : list) {

			// On actualise la meilleure grille
			// Vu que c'est trier dans l'ordre décroissant,
			// Les premières grilles sont les meilleures
			max_grid = new Grille(grid) ;
			String message="aucun";
			if(coup.getType() == TypeCoup.CLASSIQUE) {
				max_grid.placement_pion(coup.getX(), 'j') ;
				int somme=coup.getX()+1;
				message="L'IA a fait un coup classique, colonne : "+somme;
			}
			else if(coup.getType() == TypeCoup.ROTATION_DROITE && contrainte==1){
				max_grid.rotation_a_droite() ;
				message="L'IA a fait une rotation à droite";
			}
			else if(coup.getType() == TypeCoup.ROTATION_GAUCHE && contrainte==1) {
				max_grid.rotation_a_gauche() ;
				message="L'IA a fait une rotation à gauche";
			}

			// et on la return si on peut gagner
			if(coup.getScore() == 7) {
				ihm.afficherMsg(message);
				return max_grid ;
			}
			Grille grid_droite = new Grille(coup.getGrille()) ;
			Grille grid_gauche = new Grille(coup.getGrille()) ;

			grid_droite.rotation_a_droite();
			grid_gauche.rotation_a_gauche();

			// Si avec une rotation nous trouvons un coup gagnant
			// Alors pour passons à la prochaine solutions
			if(grid_droite.check_all_coup_gagnant('r') || grid_gauche.check_all_coup_gagnant('r'))
				//continue ;

			ihm.afficherMsg(message);
			return max_grid ;
		//}

		//return max_grid ;

	}
	
	private List<CoupIA> getListAllCoups(Grille grid){

		char[] chars = {'j', 'r'} ;
		
		List<CoupIA> list = new ArrayList<>() ;
		
		
		Grille grille_rotation_droite = new Grille(grid) ;
		Grille grille_rotation_gauche = new Grille(grid) ;
		
		grille_rotation_droite.rotation_a_droite();
		grille_rotation_gauche.rotation_a_gauche();
		
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
							break ;
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
				CoupIA coup = new CoupIA(grid_test, score, TypeCoup.CLASSIQUE, x) ;
				list.add(coup) ;
			}
		}
		return list ;
	}
	private int getScoreLastCoup(Grille grid, char c) {
		
		int y = grid.getDernier_pion_largeur(), x = grid.getDernier_pion_hauteur() ;

		String hor = grid.horizontale(x) ;
		String vert = grid.verticale(y) ;
		String diag1 = grid.diagonale_croissante(x, y) ;
		String diag2 = grid.diagonale_decroissante(x, y) ;


		int score = Math.max(getScoreLigne(hor, c, y), getScoreLigne(vert, c, x)) ;
		score = Math.max(score, getScoreLigne(diag1, c, y)) ;
		score = Math.max(score, getScoreLigne(diag2, c, grid.getHauteur()-y-1)) ;
		
		return score ;
	}

	private int getScoreLigne(String str, char c, int index) {

		int compt = -1 ;
		int extrem1=0,extrem2=0;
		for(int i=0 ; i<4 && index+i < str.length() ; i++) {
			//System.out.println("indice visité croissant : "+(index+i));
			if(str.charAt(i+index) == c){
				compt=compt+1 ;
			}
			else if(str.charAt(i+index) == '.')extrem1=extrem1+1;
			else break ;
		}
		
		for(int i=0 ; i<4 && index-i >= 0  ; i++) {
			//System.out.println("indice visité décroissant : "+(index-i));
			try {
				if(str.charAt(index-i) == c){
					compt=compt+1 ;
				}
				else if(str.charAt(index-i) == '.')extrem2=extrem2+1;
				else break ;
			} catch(StringIndexOutOfBoundsException e) {
				//salut;
			}
		}

		if(compt==3 && (extrem1+extrem2)<1) {
			/*System.out.println("index : "+index);
			System.out.println("str : "+str);
			System.out.println("extrem 1 : "+extrem1);
			System.out.println("extrem 2 : "+extrem2);
			System.out.println("compt : "+compt);
			System.out.println("---------");*/
			System.out.println("coup évité");
			return -1;
		}
		if(compt==2 && (extrem1+extrem2)<2) {
			/*System.out.println("index : "+index);
			System.out.println("str : "+str);
			System.out.println("extrem 1 : "+extrem1);
			System.out.println("extrem 2 : "+extrem2);
			System.out.println("compt : "+compt);
			System.out.println("---------");*/
			System.out.println("coup évité");
			return -1;
		}
		return compt ;
	}
}



