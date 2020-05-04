package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;

public class ChessGameGUI extends JFrame implements MouseListener,MouseMotionListener,Observer{
	
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	ChessGameControler chessGameControler;
	
	
	public  ChessGameGUI(String string, ChessGameControler chessGameControler, Dimension dim) {
	 //Dimension boardSize = new Dimension(600, 600);
	this.chessGameControler= chessGameControler;
	 
	//  Use a Layered Pane for this this application
	 layeredPane = new JLayeredPane();
	 getContentPane().add(layeredPane);
	 layeredPane.setPreferredSize(dim);
	 layeredPane.addMouseListener(this);
	 layeredPane.addMouseMotionListener(this);
	 
	 //Add a chess board to the Layered Pane 
	 
	  chessBoard = new JPanel();
	  layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
	  chessBoard.setLayout( new GridLayout(8, 8) );
	  chessBoard.setPreferredSize( dim );
	  chessBoard.setBounds(0, 0, dim.width, dim.height);
	 
	  for (int i = 0; i < 64; i++) {
	  JPanel square = new JPanel( new BorderLayout() );
	  chessBoard.add( square );
	 
	  int row = (i / 8) % 2;
	  if (row == 0)
	  square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
	  else
	  square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
	  
	 
	  
	 
	  
	  JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Tour", Couleur.BLANC)) );
      JPanel panel = (JPanel)chessBoard.getComponent(0);
      panel.add(piece);
      /*piece = new JLabel( new ImageIcon("./images/tourBlancS.png") );
      panel = (JPanel)chessBoard.getComponent(10);
      panel.add(piece);
      /*piece = new JLabel(new ImageIcon("./images/roiNoirS.png"));
      panel = (JPanel)chessBoard.getComponent(16);
      panel.add(piece);
      piece = new JLabel(new ImageIcon("./images/pionNoirS.png"));
      panel = (JPanel)chessBoard.getComponent(20);
      panel.add(piece);
	  */
	  
	  }
	 
	
	 
	}

	@Override
	public void update(Observable ob, Object arg) {
		// TODO Auto-generated method stub
		 List<PieceIHM> piecesIHM = (List<PieceIHM>) arg;
		//System.out.println("coucou1");
	      for (PieceIHM piece : piecesIHM) {
	    	 // System.out.println("coucou2");
	          for (Coord coord : piece.getList()) {
	        	  JLabel current = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(piece.getTypePiece(), piece.getCouleur())));
	              JPanel panel = (JPanel) chessBoard.getComponent(coord.x + coord.y*8);
	              panel.add(current);
	              //System.out.println("cica");
	          }
	      }
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (chessPiece == null) return;
		 chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		 
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		chessPiece = null;
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JPanel) 
		  return;
		 
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		  
		  
		  System.out.println(chessPiece);
		  System.out.println("pièce prise");
		  System.out.println(getPieceCoord((int)Math.ceil(chessPiece.getLocation().getX()),(int)Math.ceil(chessPiece.getLocation().getY())));



	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		 if(chessPiece == null) return;
		 
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JLabel){
		  Container parent = c.getParent();
		  parent.remove(0);
		  parent.add( chessPiece );
		  }
		  else {
		  Container parent = (Container)c;
		  parent.add( chessPiece );
		  }
		 
		  chessPiece.setVisible(true);
		  
		  System.out.println(chessPiece);
		  System.out.println(getPieceCoord((int)Math.ceil(chessPiece.getLocation().getX()),(int)Math.ceil(chessPiece.getLocation().getY())));
		  System.out.println("pièce posée");
		  //System.out.println(ChessGameControler.getPieceColor((int)Math.ceil(chessPiece.getLocation().getX()),(int)Math.ceil(chessPiece.getLocation().getY())));
		  
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private Coord getPieceCoord(int x, int y) {
        return new Coord(x/(700/8), y/(700/8));
    }
	
	  public static void main(String[] args) {
		  Dimension boardSize = new Dimension(700, 700);
		  JFrame frame = new ChessGameGUI("title", null, boardSize);
		  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		  frame.pack();
		  frame.setResizable(true);
		  frame.setLocationRelativeTo( null );
		  frame.setVisible(true);
	 }

}
