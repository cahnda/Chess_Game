import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class GameBoard extends JPanel{
	public enum click_mode {
		unclicked, clicked
	}
	
	public static final int BOARD_WIDTH = 600;
	public static final int BOARD_HEIGHT = 600;
	private Piece [][] board_arrangement;
	private JPanel [][] squares;
	protected static ImageIcon ICON = null;
	private click_mode myMode;
	


	// Creates board every-time the code is run based on current status
	public GameBoard() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		board_arrangement = new Piece[8][8];
		squares = new JPanel[8][8];
		setLayout(new GridLayout(8,8));
		setSize (BOARD_WIDTH,BOARD_HEIGHT);
		myMode = click_mode.unclicked;
	}
	
	public void createNewBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel();
				if ((i + j) % 2 == 0) {
					square.setBackground(Color.DARK_GRAY);
				} else {
					square.setBackground(Color.white);
				}   
				squares[i][j] = square;
			}
		}
	}
	
	public void addBoard() {
		for (JPanel[] j_list: squares) {
			for (JPanel j: j_list) {
				this.add(j);
			}
		}
	}
	
	
	public void newPieces() {
		//Starting with zero for consistent with CS norms
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++){
				if (i == 1 || i == 6) {
					if (i == 1)
						board_arrangement[i][j] = new Pawn(true,BOARD_WIDTH,BOARD_HEIGHT);
					else 
						board_arrangement[i][j] = new Pawn(false,BOARD_WIDTH,BOARD_HEIGHT);
				}
				else {
					if (i == 0 || i == 7) {
						if (j == 0 || j == 7) {
							if (i == 0)
								board_arrangement[i][j] = new Rook(true,BOARD_WIDTH,BOARD_HEIGHT);
							else
								board_arrangement[i][j] = new Rook(false,BOARD_WIDTH,BOARD_HEIGHT);
						}
						if (j == 1 || j == 6) {
							if (i == 0)
								board_arrangement[i][j] = new Knight(true,BOARD_WIDTH,BOARD_HEIGHT);
							else 
								board_arrangement[i][j] = new Knight(false,BOARD_WIDTH,BOARD_HEIGHT);
						}
						if (j == 2 || j == 5) {
							if (i == 0)
								board_arrangement[i][j] = new Bishop(true,BOARD_WIDTH,BOARD_HEIGHT);
							else 
								board_arrangement[i][j] = new Bishop(false,BOARD_WIDTH,BOARD_HEIGHT);
						}
						if (j == 3){
							if (i == 0)
								board_arrangement[i][j] = new Queen(true,BOARD_WIDTH,BOARD_HEIGHT);
							else 
								board_arrangement[i][j] = new Queen(false,BOARD_WIDTH,BOARD_HEIGHT);
						}
						if (j == 4){
							if (i == 0)
								board_arrangement[i][j] = new King(true,BOARD_WIDTH,BOARD_HEIGHT);
							else 
								board_arrangement[i][j] = new King(false,BOARD_WIDTH,BOARD_HEIGHT);
						}
					} 
					else {
						board_arrangement[i][j] = null;
					}
				}
			}
		}
	}
	
	public void addPieces() {
		for (int i = 0; i < board_arrangement.length; i++) {
			for (int j = 0; j < board_arrangement.length; j++) {
				if (!(board_arrangement[i][j] == null)) {
					squares[i][j].add(board_arrangement[i][j].getLabel());
				}
			}
		}
	}
	
/*
	private void addListnerUnclicked(JLabel l, int i, int j) {
		l.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				myMode = click_mode.clicked;
				Point [] myOptions = board_arrangement[i][j].getOptions(board_arrangement,i,j);
				for (Point p: myOptions) {
					squares[p.getX()][p.getY()].setBackground(Color.green);
				}
			}
		});
	}*/
	
	
	public void refreshBoard() {
		this.removeAll();
		addPieces();
		addBoard();
	}
	
	
	// Set the parameters when the board is created
	public void reset() {
		//Clear all
		this.removeAll();
		
		//Create new board
		createNewBoard();
		
		//Create the new pieces
		newPieces();
		
		//Add the pieces to our board
		addPieces();
		
		//Add the board to our JPanel
		addBoard();
		
		//Refresh the board
		this.revalidate();
	}
	
		@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*for (GameObj[] go_arr: board_arrangement) {
			for (GameObj go: go_arr) {
				if (!(go == null))
					go.draw(g);
			}
		}*/
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	}
}
