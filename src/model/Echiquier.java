package model;

import java.util.LinkedList;
import java.util.List;


/**
 * Classe ECHIQUIER
 *__________________________________________________________________________________
 *La classe Echiquier creÌ�e ses 2 Jeu et connait le jeu courant.
 *Elle est munie d'une méthode qui vérifie si le déplacement est possible et une autre qui permet de deÌ�placer une pieÌ€ce depuis ses coordonneÌ�es initiales vers ses coordonneÌ�es finales avec prise eÌ�ventuelle.
 *La méthode isMoveOk() retourne true si le deÌ�placement est effectif, c'est-à-dire si :
 	- La pièce concernée appartient au jeu courant.
	- La position finale est différente de la position initiale et dans les limites du damier.
	- Le déplacement est possible par rapport au type de pièce, indeÌ�pendamment des autres pieÌ€ces.
	- Le déplacement est possible par rapport aux autres pièces qui seront sur la trajectoire avec prise eÌ�ventuelle.
Pour autant, la classe Echiquier ne communique pas directement avec les Pieces...
 *__________________________________________________________________________________
 */

public class Echiquier implements BoardGames {
	
	private Jeu jeuBlanc;
	private Jeu jeuNoir;
	private Jeu jeuCourant;
	private Jeu	jeuNonCourant;
	private boolean flag_prom=false;
	private int flag_erreur=0;
	private boolean flag_capture=false;
	
	 /**
	  *   CONSTRUCTEUR :
	  */
	public Echiquier() {
		this.jeuBlanc = new Jeu(Couleur.BLANC);
		this.jeuNoir = new Jeu(Couleur.NOIR);
		this.jeuCourant = jeuBlanc;
		this.jeuNonCourant = jeuNoir;
	}
	
	/**
	 *  fonction isMoveOk :
	 *  Permet de verifier si une piece peut etre deplacee.
	 *  Retourne true si le deplacement est effectue, false sinon.
	 *  L'algo est detaillé et commenté
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true si le deplacement est realisable, false sinon
	 */
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean return_isMoveOk = true;
		//reinitialisation des variables
		flag_prom=false;
		
		
		//Etape 0 - Verification que la piece existe
		if(jeuCourant.isPieceHere(xInit,yInit)==true){
			//Etape 1 - Verification de la couleur de la piece selectionner
			if(getColorCurrentPlayer()==jeuCourant.getPieceColor(xInit,yInit)){
				
				//Etape 2 - Switch pour differencier le pion des autres pieces
				String typePiece = jeuCourant.getPieceType(xInit,yInit);
				switch(typePiece) {
					case "Pion":
						
						//Etape Pion 1 : verifier s'il y a une piece sur l'autre case & la couleur de la piece
						if(jeuCourant.isPieceHere(xFinal, yFinal)==true || jeuNonCourant.isPieceHere(xFinal, yFinal)==true){
							
							//Etape 1-1 : verif couleur // pour le deplacement
							if(getPieceColor(xInit,yInit)!=getPieceColor(xFinal,yFinal)){

								//Etape pion 2-1 : verif deplacement diag
								//Si xInit != xFinal => deplacement sur une case sur le coté, la verif ensuite dans la classe pion fait le reste
								if(xInit != xFinal && jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)==true){
									flag_capture=true;
									//Etape pion 3-1 : verif promotion possible
									if(jeuCourant.isPawnPromotion(xFinal, yFinal)==true){
										flag_prom=true;
										flag_erreur=1;
										
									}
								}
								else //else etape 2-1 : si deplacement diag non ok
									{
									return_isMoveOk=false;
									flag_erreur=2;
									}
							}
							else //Else de Etape 1-1 : verif couleur // pour le deplacement
							{
								return_isMoveOk=false;
								flag_erreur=3;
							}
						}
						
						else //Else etape pion 1 : verif case finale vide
						{
						
							//Etape 2-2 : verif si isMoveok
							if(jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)==true){
								if(jeuCourant.isPawnPromotion(xFinal, yFinal)==true){
									flag_prom=true;
									flag_erreur=4;
								}
							}
							else //else etape 2-2 : verif si isMoveOk
								{
								return_isMoveOk=false;
								flag_erreur=5;
								}
						}
						break;
						
					default : //On verifie les "communs" pour les autres pieces
						//Etape commun 1 : verif piece aux pos finales et couleurs
						if( jeuNonCourant.isPieceHere(xFinal, yFinal)==true  || 
								jeuCourant.isPieceHere(xFinal, yFinal)==false)
						{
							if(getPieceColor(xInit,yInit)!=getPieceColor(xFinal,yFinal))
								flag_capture=true;
							//Etape commun 2 : verif du isMoveOk
							if(jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)==true)
							{
								switch(typePiece){
								case "Tour":
									if(isLinearMoveOk(xInit,yInit,xFinal,yFinal)==false){
										return_isMoveOk=false;
										flag_erreur=6;
									}
								break;
										
								case "Fou":
									if(isDiagonalMoveOk(xInit,yInit,xFinal,yFinal)==false){
										return_isMoveOk=false;
										flag_erreur=7;
									}
								break;
										
					
								case "Reine":
									if(xFinal!=xInit && yFinal!=yInit) {
										if(isDiagonalMoveOk(xInit,yInit,xFinal,yFinal)==false){
											return_isMoveOk=false;
											flag_erreur=7;
										}
											
									}
									else {
										if(isLinearMoveOk(xInit,yInit,xFinal,yFinal)==false){
											flag_erreur=6;
											return_isMoveOk=false;
										}
											
									}
								break;
									
								case "Roi":
									//TODO Verifier qu'il ne devient pas en position d'echec
								}
								break;
							}
							else// Else Etape commun 2 : verif du isMoveOk
							{
								return_isMoveOk=false;
								flag_erreur=8;
							}
						}
						else// Else de Etape commun 1 : verif piece aux pos finales et couleurs
						{
							return_isMoveOk=false;
							flag_erreur=9;
						}
						break;
					}
				}
				else //else etape1
				{
					return_isMoveOk = false;
					flag_erreur=10;
				}
		}
		else{
			return_isMoveOk = false;
			flag_erreur=11;
		}
			
			return return_isMoveOk;
		
		
		} 
	



	/**
	 *  fonction isLinearMoveOk :
	 *  Permet de verifier si une piece peut etre deplacee sur un ligne (tour par exemple).
	 *  Retourne true si le deplacement est effectue, false sinon.
	 *  
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true s'il n'y a pas de piece intermediaire en diagonale, false sinon
	 */
	public boolean isLinearMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean return_isLinearMoveOk=true;
		int i=0;
		
		// Si le deplacement est suivant l'axe x :
		if(Math.abs(yFinal-yInit)==0) {
			//On regarde le sens du deplacement :
			if(xFinal-xInit>0) { // deplacement vers le haut
				for(i=1;i<xFinal-xInit;i++) {
					if(jeuCourant.isPieceHere(xInit+i,yInit)==true)
						return_isLinearMoveOk= false;
				}
			}
			else { // deplacement vers le bas
				for(i=1;i<xInit-xFinal;i++) {
					if(jeuCourant.isPieceHere(xInit-i,yInit)==true)
						return_isLinearMoveOk = false;
				}
			}
		}
		
		// Si le deplacement est suivant l'axe y:
		if(Math.abs(xFinal-xInit)==0) {
			//On regarde le sens du deplacement :
			if(yFinal-yInit>0) { // deplacement vers la doite
				for(i=1;i<yFinal-yInit;i++) {
					if(jeuCourant.isPieceHere(xInit,yInit+i)==true)
						return_isLinearMoveOk = false;
				}
			}
			else { // deplacement vers la gauche
				for(i=1;i<yInit-yFinal;i++) {
					if(jeuCourant.isPieceHere(xInit,yInit-i)==true)
						return_isLinearMoveOk = false;
				}
			}	
		}
		
		return return_isLinearMoveOk;
	}
	
	/**
	 *  fonction isDiagonalMoveOk :
	 *  Permet de verifier si une piece peut etre deplacee sur en diagonal (fou par exemple).
	 *  Retourne true si le deplacement est effectue, false sinon.
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true s'il n'y a pas de piece intermediaire en diagonale, false sinon
	 */
	public boolean isDiagonalMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		
		int i;
		boolean return_isDiagonalMoveOk=true;
		if(xFinal-xInit>0 && yFinal-yInit>0) {
			for(i=1;i<yFinal-yInit;i++) {
				if(jeuCourant.isPieceHere(xInit+i,yInit+i)==true)
					return_isDiagonalMoveOk= false;
			}
		}
		
		if(xFinal-xInit>0 && yFinal-yInit<0) {
			for(i=1;i<xFinal-xInit;i++) {
				if(jeuCourant.isPieceHere(xInit+i,yInit-i)==true)
					return_isDiagonalMoveOk= false;
			}
		}
		
		if(xFinal-xInit<0 && yFinal-yInit<0) {
			for(i=1;i<xInit-xFinal;i++) {
				if(jeuCourant.isPieceHere(xInit-i,yInit-i)==true)
					return_isDiagonalMoveOk = false;
			}
		}
		
		if(xFinal-xInit<0 && yFinal-yInit>0) {
			for(i=1;i<yFinal-yInit;i++) {
				if(jeuCourant.isPieceHere(xInit-i,yInit+i)==true)
					return_isDiagonalMoveOk = false;
			}
		}
		
		return return_isDiagonalMoveOk;
	}
	
	/** 
	 * fonction move :
	 *  Permet de deplacer une piece connaissant ses coordonnees initiales vers ses coordonnees finales.
	 *  l'algo verifie que le deplacement est legal, effectue ce deplacement avec l'eventuelle capture, 
	 *  rembobine si le deplacement et la capture ont mis le roi en echec
	 */
	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean return_move=false;
		boolean capture=true;
		if(this.jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal) == true)
		{
			if(flag_capture==true){
				System.out.print("\n capture ");
				flag_capture=false;
				capture=jeuNonCourant.capture(xFinal, yFinal);
			}
				
			if(this.jeuCourant.move(xInit, yInit, xFinal, yFinal)==true && capture==true) {
				return_move= true;
			}
		}
		
		return return_move;
	}
	
	/**
	 *  fonction switchJoueur :
	 *  Permet de changer le joueur courant.
	 *  La fonction est appelee dans ChessGame
	 */
	public void switchJoueur() {
		if(jeuCourant == jeuBlanc) {
			jeuCourant = jeuNoir;
			jeuNonCourant = jeuBlanc;
		}
		else {
			jeuCourant = jeuBlanc;
			jeuNonCourant = jeuNoir;
		}
	}

	/**
	 *  fonction isEnd :
	 *  Retourne true si c'est la fin du jeu
	 */
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *  fonction getPiecesIHM :
	 *  Retourne une liste de PieceIHM qui pourra etre exploitee par une IHM
	 */
	public Object getPiecesIHM() {
		List<PieceIHM> list = new LinkedList<PieceIHM>();
        list.addAll(this.jeuCourant.getPieceIHM());
        list.addAll(this.jeuNonCourant.getPieceIHM());     
        return list;
	}

	/**
	 *  fonction getMessage :
	 *  Retourne un message relatif aux deplacement, capture, etc.
	 */
	public String getMessage() {
		String message="default message";
		switch(flag_erreur){ //les erreurs correspondent aux numeros des etapes
		case 1 :
			message="Promotion du pion possible!";
			break;
		case 2 :
			message="Erreur : ce deplacement diagonal c'est pas bon pour ce type de piece !";
			break;
			
		case 3 :
			message="Erreur : ce deplacement c'est pas bon car piece de même couleur pour la capture!";
			break;
			
		case 4 :
			message="Promotion : une promotion du pion est possible (default : reine) !";
			break;
			
		case 5 :
			message="Erreur : ce deplacement c'est pas bon pour ce type de piece !";
			break;
			
		case 6 :
			message="Erreur : ce deplacement provoque une collision lineaire avec une piece !";
			break;
			
		case 7 :
			message="Erreur : ce deplacement provoque une collision diagonal avec une piece !";
			break;
			
		case 8 :
			message="Erreur : ce deplacement c'est pas bon pour ce type de piece !";
			break;
			
		case 9 :
			message="Erreur : la case final est occupée par une piece non capturable !";
			break;
			
		case 10 :
			message="Erreur : la piece saisie n'est pas le votre!";
			break;
			
		case 11 :
			message="Erreur : la piece demander n'existe pas";
			break;
			
		default :
			message="Pas d'erreur, deplacement effectuée";
			break;
		}
		
		// TODO Auto-generated method stub
		flag_erreur=0;
		return message;
	}

	/**
	 *  fonction getColorCurrentPlayer :
	 *  Retourne la couleur du jeu courant
	 */
	public Couleur getColorCurrentPlayer() {
		if(jeuCourant==jeuBlanc)
			return Couleur.BLANC;
		else
			return Couleur.NOIR;
	}
	
	@Override
	public String toString() {
		return "Echiquier\n [jeuCourant=" + jeuCourant + "\n jeuNonCourant="
				+ jeuNonCourant + "]";
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		Couleur return_getPieceColor=null;
		if(jeuCourant.isPieceHere(x,y))
			return_getPieceColor=jeuCourant.getPieceColor(x, y);
		else if(jeuNonCourant.isPieceHere(x, y))
			return_getPieceColor=jeuNonCourant.getPieceColor(x, y);
		return return_getPieceColor;
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