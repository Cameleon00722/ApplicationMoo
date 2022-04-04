package com.company.ia;

import com.company.elements.Grille;
import com.company.ia.ArtificialIntelligenceImpl_P4.TypeCoup;

public class CoupIA implements Comparable<CoupIA> {
	
	private Grille grille ;
	private int score, x ;
	private TypeCoup type ;
	
	public CoupIA(Grille grille, int score, TypeCoup type, int x) {
		this(grille, score, type) ;
		setX(x);
	}
	
	public CoupIA(Grille grille, int score, TypeCoup type) {
		setGrille(grille);
		setScore(score);
		setType(type);
	}
	
	@Override
	public String toString() {
		return "[Score = "+score+", x = "+x+"]" ;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int compareTo(CoupIA o) {
		return o.getScore() - score ;
	}

	public TypeCoup getType() {
		return type;
	}

	public void setType(TypeCoup type) {
		this.type = type;
	}
	

}
