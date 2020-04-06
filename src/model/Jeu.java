package model;

import java.util.LinkedList;
import java.util.List;

// Le constructeur de jeu fait appel à la fabrique de pièce
public class Jeu  {
	
	private Couleur couleur_jeu;
	private Coord coord;
	private String type;
	private List<Pieces> pieces= null;

	/**
	 * Constructeur de jeu
	 * Cree toutes les pieces d'une couleur passee en parametres
	 * @param couleur_jeu
	 */
	public Jeu(Couleur  couleur_jeu){
		pieces=tools.ChessPiecesFactory.newPieces(couleur_jeu);
	}

	
	/**
	 * Cherche la piece presente aux coord donnees
	 * @param x
	 * @param y
	 * @return la piece presentes aux coords donnees
	 */
	private Pieces findPiece(int x, int y){
		
		Pieces Return_findPiece=null;
		for (Pieces p : pieces){
			if (p.getX() == x &&  p.getY() == y) {
				Return_findPiece = p;
			}
		}
		return Return_findPiece;
	}
	
	
	
	public boolean capture (int xCatch, int yCatch) {
		Pieces piece_catch;
		boolean return_capture=false;
		
		piece_catch=findPiece(xCatch,yCatch);
		if(piece_catch !=null)
			return_capture = piece_catch.capture();
		return return_capture;
		}
	
	public Couleur getCouleur() {
		return couleur_jeu;}
	
	public Coord getKingCoord() {
		boolean king_find=false;
		while(king_find==false){
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(getTypePiece(i,j).equals("Roi"))
					{
						coord.x=i;
						coord.y=j;
						king_find=true;
					}
						
				}
			}
		}
		
		return coord;
	}
	
	public Couleur getPieceColor(int x, int y) {
		Pieces pieceCouleur;
		pieceCouleur=findPiece(x,y);
		return pieceCouleur.getCouleur();
		}
	
	public String getTypePiece (int x,int y) {
		Pieces pieceType;
		pieceType=findPiece(x,y);
		return pieceType.getClass().getSimpleName();}
	
	public List<PieceIHM> getPieceIHM() {
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();

		for (Pieces piece : pieces){
			boolean existe = false;
			// si le type de piece existe deja  dans la liste de PieceIHM
			// ajout des coordonnes de la piece dans la liste de Coord de ce type
			// si elle est toujours en jeu (x et y != -1)
				for ( PieceIHM pieceIHM : list){
					if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
					existe = true;
							if (piece.getX() != -1){
							pieceIHM.add(new Coord(piece.getX(), piece.getY()));
						}
					}
				}
			// sinon, crÃ©ation d'une nouvelle PieceIHM si la piÃ¨ce est toujours en jeu
				if (! existe) {
					if (piece.getX() != -1){
						newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),
						piece.getCouleur());
						newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
						list.add(newPieceIHM);
					}
				}
			}
		return list;	
	}
	
	public boolean isMoveOk (int xInit, int yInit, int xFinal, int yFinal) {
		Pieces piece_ismoveOk;
		boolean return_isMoveOk=false;
		
		piece_ismoveOk=findPiece(xInit,yInit);
		if(piece_ismoveOk !=null)
			return_isMoveOk = piece_ismoveOk.isMoveOk(xFinal,yFinal);
		if(xInit==xFinal && yInit==yFinal)
			return_isMoveOk = false;
		return return_isMoveOk;
	}
	
	public boolean isPawnPromotion (int xFinal , int yFinal) {
		boolean return_isPawnPromotion =false;
		if((couleur_jeu == Couleur.BLANC && yFinal==0) || (couleur_jeu == Couleur.NOIR && yFinal==7))
			return_isPawnPromotion= true;
		return return_isPawnPromotion;
	}
	
	public boolean isPieceHere (int x , int y) {
		boolean Return_isPiecesHere =false;
		for (Pieces p : pieces){ //On parcours l'ensemble des pieces
			if (p.getX() == x &&  p.getY() == y) { //Si coord x et y sont ok return true
				Return_isPiecesHere = true;
			}
		}
		return Return_isPiecesHere;
	}
	
	public boolean move (int xInit , int yInit, int xFinal, int yFinal) {
		Pieces piece_move;
		boolean return_move=true;
		
		piece_move=findPiece(xInit, yInit);
		if (piece_move.move(xFinal, yFinal))
			return_move = true;
		return return_move;
	}
	
	public boolean pawnPromotion(int xFinal, int yFinal,String type) {
		Pieces pionPromo, newPion;
		boolean return_pawnPromotion=false;
		if(isPawnPromotion(xFinal,yFinal)){
			pionPromo=findPiece(xFinal,yFinal);
			newPion=tools.ChessSinglePieceFactory.newPiece(couleur_jeu, type, xFinal, yFinal);
			pieces.add(newPion);
			pieces.remove(pionPromo);
		
			if(pieces.size() == 11)
				return_pawnPromotion = true;
		}
		
		return return_pawnPromotion;
	}
	
	public void setCastling() {
		// met à jour un booleen pour activer d'un roque du roi
		
	}
	
	public void setPossibleCapture() {
		//Si une capture d'une pièce de l'autre jeu est possible met à jour un boolen
		
	}
	
	public String toString() {
		return "model.jeu \n " +pieces.toString();
		}
	
	public void undoCapture() {
		 //a partir d'un historique des capture la fonction annule la derniere capture
	}
	
	public void undoMove() {
		//a partir d'un historique des capture la fonction annule le dernier mouvement

	}
	
	
	public static void main(String[] args) {
		Couleur couleur_jeu_main;
		
		couleur_jeu_main=Couleur.BLANC;
		Jeu jeuCourant= new Jeu(couleur_jeu_main);
		System.out.println(new Jeu(couleur_jeu_main));
		System.out.println(jeuCourant.getTypePiece(6, 6));
		

		couleur_jeu_main=Couleur.NOIR;
		System.out.println(new Jeu(couleur_jeu_main));
	}
	
	
}
