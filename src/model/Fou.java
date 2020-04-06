package model;
/**
 * 
 * Classe de la piece Fou
 */
public class Fou extends AbstractPiece implements Pieces {
	
	/**
	 * Constructeur de la piece Fou
	 * @param couleur
	 * @param coord
	 */
	public Fou(Couleur couleur, Coord coord) {
		super(couleur, coord);
        name="Fou";
	}

	/**
	 * Definie le isMoveOk pour la piece Fou
	 */
    public boolean isMoveOk (int xFinal, int yFinal) {
        //algo de déplacement du fou : peut se déplacer en diagonales seulement
    	boolean return_isMoveOk=false;
        if (isMoveOkVerifCoordonnee(xFinal, yFinal) && ( (Math.abs(coord.x - xFinal)) == (Math.abs(coord.y - yFinal)) ) )
        	return_isMoveOk = true;

        return return_isMoveOk;
    }

}