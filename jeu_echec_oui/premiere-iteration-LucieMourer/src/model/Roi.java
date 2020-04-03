package model;
/**
 * 
 * @author Baptiste Blanc & Eliott Joulot
 * Classe de la piece Roi
 */
public class Roi extends AbstractPiece implements Pieces {

    /**
     * Constructeur de la piece Roi
     * @param couleur
     * @param coord
     */
	public Roi(Couleur couleur, Coord coord) {
		super(couleur, coord);
		name="Roi";
	}

	/**
	 * Definie le isMoveOk pour la piece Roi
	 */
    public boolean isMoveOk (int xFinal, int yFinal) {
        //algo de deplacement du roi : peut se deplacer n'importe ou mais
        //d'une seule case
    	boolean return_isMoveOk=false;
        if (isMoveOkVerifCoordonnee(xFinal, yFinal) && (Math.abs(coord.x - xFinal) <=1 && Math.abs(coord.y - yFinal) <=1))
        	return_isMoveOk = true;
        
       return return_isMoveOk;
    }

}