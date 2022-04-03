package com.company.ia;

public interface ArtificialIntelligenceImpl<E> {
	
	public E getBestGrid(E grid) ;
	
	public enum TypeCoup{
		CLASSIQUE, ROTATION_GAUCHE, ROTATION_DROITE ;
	}

}
