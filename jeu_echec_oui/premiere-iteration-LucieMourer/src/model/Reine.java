package model;
/**
 * 
 * @author Baptiste Blanc & Eliott Joulot
 * Classe de la piece Reine
 */
public class Reine extends AbstractPiece implements Pieces {

	/**
	 * Constructeur de la piece Reine
	 * @param couleur
	 * @param coord
	 */
	public Reine(Couleur couleur, Coord coord) {
		super(couleur, coord);
		name="Reine";
	}

	/**
	 * Definie le isMoveOk pour la piece Reine
	 */
    public boolean isMoveOk (int xFinal, int yFinal) {
        //algo de deplacement de la reine : toutes directions sans limite
    	boolean return_isMoveOk=false;
    	if(isMoveOkVerifCoordonnee(xFinal, yFinal))
    		return_isMoveOk=true;
       return return_isMoveOk;

    }

}