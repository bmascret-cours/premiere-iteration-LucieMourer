package model;
import model.Coord;
import model.Couleur;

public abstract class AbstractPiece implements Pieces {

	protected Couleur couleur;
	protected Coord coord;
	protected String name;
	
	/**
	 * Constructeur de AbstractPiece, donne les coordonnee et la couleur
	 * @param couleur
	 * @param coord
	 */
	public AbstractPiece(Couleur couleur, Coord coord){
		this.couleur=couleur;
		this.coord=coord;
	}
	
	/**
	 * Redefinition de toString pour donnee le nom et les coordonnee de la piece
	 * @return l'affichage
	 */
	public  String toString(){
		return "Name "+ name +", Coordonnee x = " + coord.x + ", y = " + coord.y + "\n";
	}

	/**
	 * Getteur de coord.x
	 * @return la coord de x
	 */
	public int getX(){
		return coord.x;
	}

	/**
	 * Getteur de coord.y
	 * @return la coord de y
	 */
	public int getY(){
		return coord.y;
	}

	/**
	 * Getteur de la couleur
	 * @return la couleur
	 */
	public Couleur getCouleur(){
		return couleur;
	}
	
	/**
	 * Change la position x et y d'une piece
	 * @return la coord de true si le deplacement a eu lieu
	 */
	public boolean move(int x, int y){
		coord.x=x;
		coord.y=y;
		return true;
	}
	
	/**
	 * Verifie que le mouvement est bon pour chaque piece (definie dans les pieces)
	 */
	public abstract boolean isMoveOk(int xFinal, int yFinal);

	/**
	 * Verifie que les positions donnee son bien dans l'echiquier
	 * @param xFinal
	 * @param yFinal
	 * @return true si les positions sont dedans
	 */
	public boolean isMoveOkVerifCoordonnee(int xFinal, int yFinal){
		boolean return_isMoveOkVerifCoordonnee=true;
		if(xFinal <0 || xFinal >7 ||yFinal <0 || yFinal >7)
			return_isMoveOkVerifCoordonnee = false;
		return return_isMoveOkVerifCoordonnee;
	}
	
	/**
	 * change les coordonne de la piece capturee en -1, -1
	 * @return true une fois le deplacement effectue
	 */
	public boolean capture() {
		coord.x=-1;
		coord.y=-1;
		return true;
	}

	public static void main (String [] arg){
		Pieces maTour= new Tour (Couleur.NOIR,new Coord(0,0));
		boolean MoveOk, MoveNotOk;
		MoveOk=maTour.isMoveOk(5,0);
		MoveNotOk=maTour.isMoveOk(2,3);
		System.out.printf("Test sur un déplcement ok : isMoveOk retourne %b",MoveOk);
		System.out.printf("\nTest sur un déplcement non ok : isMoveOk retourne %b",MoveNotOk);
	}

}