package com.company.IA;


import com.company.elements.Ihm2;

public interface ArtificialIntelligenceImpl_P4<E> {
	
	public E IA_retour(E grid, int contrainte, Ihm2 ihm) ;
	
	public enum TypeCoup{
		CLASSIQUE, ROTATION_GAUCHE, ROTATION_DROITE ;
	}

}
