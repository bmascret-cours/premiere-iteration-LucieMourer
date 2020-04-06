package model;
/**
 * 

 * Creation de la classe Pions
 *
 */
public class Pion extends AbstractPiece implements Pions {

    /**
     * 
     * @param couleur
     * @param coord
     */
	public Pion(Couleur couleur, Coord coord) {
		super(couleur, coord);
		name="Pion";
	}

	/**
	 * Definie le isMoveOk pour la piece pion
	 * Si le pion Blanc / Noir est sur sa position initial alors deplacement de 2 en y autoriser
	 * Sinon autorisation de 1 en y autoriser
	 * Sinon false
	 */
    public boolean isMoveOk (int xFinal, int yFinal) {
    	boolean return_isMoveOk = false;
    	if(isMoveOkVerifCoordonnee(xFinal, yFinal) && xFinal == coord.x){
    		switch(Math.abs(coord.y - yFinal)){
    			case 1:
    				if((couleur == Couleur.BLANC && (coord.y - yFinal)>0 ) ||
        		    	(couleur == Couleur.NOIR && (coord.y - yFinal)<0 ))
    					return_isMoveOk=true;
    			case 2:
    				if( (couleur == Couleur.BLANC && coord.y== 6 ) ||
    		    		(couleur == Couleur.NOIR && coord.y== 1 )) 
    					return_isMoveOk=true;
    			}
    	}
    	else //test si le isMoveOk a ete fait pour la diagonale
    	{
    			return_isMoveOk=isMoveDiagOk(xFinal,yFinal);
    		}
    	return return_isMoveOk;
    }
    
    /**
     * Definie le isMoveDiagOk pour la piece pion
     * Si les coordonnes finales sont differentes et si le deplacement est de 
     * 1 case en x et 1 case en y, true
     */
    public boolean isMoveDiagOk(int xFinal,int yFinal){ //TO DO
        boolean return_isMoveDiagOk = false;
        if(coord.x != xFinal && coord.y != yFinal &&
        	Math.abs(coord.y - yFinal) ==1 && Math.abs(coord.x - xFinal) ==1)
        	return_isMoveDiagOk =true;
    	return return_isMoveDiagOk;
    }
    
    /**
     * Definie le move pour la piece pion
     */
    public boolean move(int x, int y){ //TO DO
        coord.x =x;
        coord.y=y;
    	return true;
    }


}