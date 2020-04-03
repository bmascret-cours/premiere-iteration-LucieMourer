package model; 


public interface Pieces {
	
	/**
	 * Enregistrement de l'etat capturee
	 * @return true si piece effectivement
	 * Si la piece est capture Positionne x et y a  -1*
	 */
	public boolean capture();

	/**
	 * Enregistrement des nouvelles coordonnees
	 * Fonction definie dans Abstract_Pieces et utilisee la premiere fois dans jeu
	 * @param xFinal 
	 * @param yFinal 
	 * @return true si deplacement effectue*/
	boolean move(int xFinal,int yFinal);
	
	/**
	 * Définie dans chacune des sous classes de Abstract_Pieces
	 * Chaque type de piece définie le comportement de ses déplacements
	 * Fonciton réutilisée la première fois dans jeu
	 * @param xFinal 
	 * @param yFinal 
	 * @return true si deplacement legale en fonction des algo de deplacement specifique de chaque piece*/
	boolean isMoveOk(int xFinal,int yFinal);

	/**
	 * @return couleur de la piece
	 */
	Couleur getCouleur();


	/**
	 * @return indice de la ligne ou est positionnee la piece
	 */
	int getY();


	/**
	 * @return indice de la colonne ou est positionnee la piece
	 */
	int getX();


}