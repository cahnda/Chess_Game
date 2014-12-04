import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;


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
	private Point clickedPiece;
	private boolean turn;


	// Creates board every-time the code is run based on current status
	public GameBoard() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		board_arrangement = new Piece[8][8];
		squares = new JPanel[8][8];
		setLayout(new GridLayout(8,8));
		setSize (BOARD_WIDTH,BOARD_HEIGHT);
		myMode = click_mode.unclicked;
		turn = true;
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
						board_arrangement[i][j] = 
						new Pawn(true,this.getWidth(),this.getHeight());
					else 
						board_arrangement[i][j] = 
						new Pawn(false,this.getWidth(),this.getHeight());
				}
				else {
					if (i == 0 || i == 7) {
						if (j == 0 || j == 7) {
							if (i == 0)
								board_arrangement[i][j] = new Rook
								(true,this.getWidth(),this.getHeight());
							else
								board_arrangement[i][j] = new Rook
								(false,this.getWidth(),this.getHeight());
						}
						if (j == 1 || j == 6) {
							if (i == 0)
								board_arrangement[i][j] = new Knight
								(true,this.getWidth(),this.getHeight());
							else 
								board_arrangement[i][j] = new Knight
								(false,this.getWidth(),this.getHeight());
						}
						if (j == 2 || j == 5) {
							if (i == 0)
								board_arrangement[i][j] = new Bishop
								(true,this.getWidth(),this.getHeight());
							else 
								board_arrangement[i][j] = new Bishop
								(false,this.getWidth(),this.getHeight());
						}
						if (j == 3){
							if (i == 0)
								board_arrangement[i][j] = new Queen
								(true,this.getWidth(),this.getHeight());
							else 
								board_arrangement[i][j] = new Queen
								(false,this.getWidth(),this.getHeight());
						}
						if (j == 4){
							if (i == 0)
								board_arrangement[i][j] = new King
								(true,this.getWidth(),this.getHeight());
							else 
								board_arrangement[i][j] = new King
								(false,this.getWidth(),this.getHeight());
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
					squares[i][j].removeAll();
					JLabel l = board_arrangement[i][j].getLabel();
					squares[i][j].add(l);
				}
			}
		}
	}
	
	//Add a listner to each square
	private void addLisners() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = squares [i][j];
				addEventListners (square, i, j);
			}
		}
	}
		
	private void addEventListners(JPanel square, int i, int j) {
		square.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Set up Functionality for First Click
				if (myMode == click_mode.unclicked) {
					if (board_arrangement[i][j] != null) {
						if (board_arrangement[i][j].getColor() == turn) {
						Set <Point> myOptions = 
								board_arrangement[i][j].getOptions
								(board_arrangement,i,j);
						for (Point p: myOptions) {
							squares[p.getX()][p.getY()].
							setBackground(Color.green);
						}
						clickedPiece = new Point (i,j);
						myMode = click_mode.clicked;
						refreshBoard();
						}
					}
				}

				// Set up Functionality for Second Click
				else {
					if (square.getBackground().equals(Color.green)) {
						System.out.println ("hit it");
						int x = clickedPiece.getX();
						System.out.println(x);
						System.out.println(i);
						int y = clickedPiece.getY();
						System.out.println(y);
						System.out.println(j);
						board_arrangement[i][j] = board_arrangement[x][y];
						board_arrangement[x][y] = null;
					}
					myMode = click_mode.unclicked;
					createNewBoard();
					refreshBoard();
					turn = !(turn);
				}
			}
		});
	}

	
	public Point findMySquare(JPanel square) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (square == squares [i][j])
					return new Point (i,j);
			}
		}
		return null;
	}

	public void refreshBoard() {
		this.removeAll();
		addPieces();
		addLisners();
		addBoard();
		this.revalidate();
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
		
		// Add listeners to the board
		addLisners();
		
		//Add the board to our JPanel
		addBoard();
		
		//Reset the ability to click pieces
		myMode = click_mode.unclicked;
		
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
