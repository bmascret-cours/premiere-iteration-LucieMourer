package model;
/**
 * 

 * Classe de la piece Tour
 */
public class Tour extends AbstractPiece implements Pieces {

    /**
     * Constructeur de la piece Tour
     * @param couleur
     * @param coord
     */
	public Tour(Couleur couleur, Coord coord) {
		super(couleur, coord);
		name="Tour";
	}


	/**
	 * Definie le isMoveOk pour la piece Tour
	 */
    public boolean isMoveOk (int xFinal, int yFinal) {
        //algo de deplacement de la tour : peut se deplacer en ligne et en
        //colonnes mais pas en diagonales
    	boolean return_isMoveOk=false;
        if (isMoveOkVerifCoordonnee(xFinal, yFinal) && (coord.x == xFinal || coord.y == yFinal))
        	return_isMoveOk = true;
      
        return return_isMoveOk;
    }

}