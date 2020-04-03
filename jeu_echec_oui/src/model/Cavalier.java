package model;
/**
 * 
 * @author Baptiste Blanc & Eliott Joulot
 * Classe de la piece Cavalier
 */

public class Cavalier extends AbstractPiece implements Pieces {

    /**
     * Constructeur de la piece Cavalier
     * @param couleur
     * @param coord
     */
	public Cavalier(Couleur couleur, Coord coord) {
		super(couleur, coord);
		name="Cavalier";
	}

	/**
	 * Definie le isMoveOk pour la piece Cavalier
	 */
    public boolean isMoveOk (int xFinal, int yFinal) {
        //algo de deplacement du cavalier : 1 en x 2 en y ou 2 en x 1 en y
    	boolean return_isMoveOk=false;
        if(isMoveOkVerifCoordonnee(xFinal, yFinal) &&
        ((Math.abs(coord.x - xFinal) ==1 && Math.abs(coord.y - yFinal) ==2) ||
        (Math.abs(coord.x - xFinal) ==2 && Math.abs(coord.y - yFinal) ==1) ) )
        	return_isMoveOk=true;
     
         return return_isMoveOk;
    }



}