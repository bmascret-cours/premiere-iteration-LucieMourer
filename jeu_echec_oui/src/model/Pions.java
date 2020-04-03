package model;

public interface Pions {
	
	/**
	 * Specifie la capture pour un pion
	 * @return true si la capture peut se faire
	 */
	public boolean capture();

	/**
	 * Specifie le mouvement Diag pour les pions
	 * @param xFinal
	 * @param yFinal
	 * @return true si deplacement degale en fontion des 
	 * algo de deplacement specifique de chaque piece
	 */
	boolean isMoveDiagOk(int xFinal,int yFinal);


}

