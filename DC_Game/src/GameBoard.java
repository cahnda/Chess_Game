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
	private Piece Black_King;
	private Piece White_King;
	private boolean turn;
	public boolean check;
	private JLabel status; // Current status text (i.e. Running...)


	// Creates board every-time the code is run based on current status
	public GameBoard(JLabel status) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		board_arrangement = new Piece[8][8];
		squares = new JPanel[8][8];
		setLayout(new GridLayout(8,8));
		setSize (BOARD_WIDTH,BOARD_HEIGHT);
		myMode = click_mode.unclicked;
		turn = true;
		this.status = status;
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
							if (i == 0) {
								White_King = new King
										(true,this.getWidth(),this.getHeight());
								board_arrangement[i][j] = White_King;}
							else {
								Black_King = new King
										(false,this.getWidth(),this.getHeight());
								board_arrangement[i][j] = Black_King;
							}
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

	private void addEventListners(final JPanel square, final int i, final int j) {
		square.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Set up Functionality for First Click
				if (myMode == click_mode.unclicked) {
					if (board_arrangement[i][j] != null) {
						if (board_arrangement[i][j].getColor() == turn) {
							Set <Point> myOptions = 
									board_arrangement[i][j].getOptions
									(board_arrangement,i,j);
							for (Point p: myOptions) {
								//Exclude Kings, Can't Be Taken
								if (board_arrangement[p.getX()][p.getY()]==null 
										|| (!(board_arrangement
												[p.getX()][p.getY()].getName()
												.equals("king")))) {
									if (squares[p.getX()][p.getY()].
											getBackground() == Color.white) {
										squares[p.getX()][p.getY()].
										setBackground(Color.green);
									}
									else {
										squares[p.getX()][p.getY()].
										setBackground(Color.green.darker());
									}
								}
							}
							clickedPiece = new Point (i,j);
							myMode = click_mode.clicked;
							refreshBoard();
						}
					}
				}

				// Set up Functionality for Second Click
				else {
					if (square.getBackground().equals(Color.green) ||
							square.getBackground().equals(Color.green.darker())) {
						int x = clickedPiece.getX();
						int y = clickedPiece.getY();
						//Copy the arrangement to test if the move will lead
						//to check
						Piece[][] new_arrangement = new Piece[8][8];
						for (int c = 0; c<8;c++) {
							for (int cp =0; cp<8;cp++) {
								new_arrangement[c][cp] = board_arrangement[c][cp];
							}
						}
						new_arrangement[i][j] = new_arrangement[x][y];
						new_arrangement[x][y] = null;
												
						if (new_arrangement[i][j].getName() == "pawn" 
								&& (i==0 || i==7)) {
							new_arrangement[i][j] = new Queen(
									new_arrangement[i][j].getColor(),
									getWidth(),getHeight());
						}
												
						//Check if my king is in check
						if (!(isCheck(turn,new_arrangement))){
							Piece isKing = board_arrangement[clickedPiece.getX()]
									[clickedPiece.getY()];
							if (isKing.getName().equals("king")) {
								((King) isKing).setMoved(true);
								//if I moved two spaces
								if (j-y > 1) {
									//move the rook
									new_arrangement [i][j-1] = 
											board_arrangement[i][j+1];
									new_arrangement[i][j+1]= null;
								}
								else {
									if (j-y < -1) {
										//move the rook
										new_arrangement [i][j+1] = 
												board_arrangement[i][j-2];
										new_arrangement[i][j-2]= null;
									}
								}
							}
							
							board_arrangement = new_arrangement;

							turn = !(turn);
							check = isCheck (turn,board_arrangement);
							//Set status to account for status of board
							if (check) {
								if (isCheckMate(turn,board_arrangement)) {
									if (turn)
										status.setText
										("White is in Checkmate. Black Wins.");
									else
										status.setText
										("Black is in Checkmate. White Wins.");
								}
								else {
									if (turn)
										status.setText
										("White's Turn. You are in Check");
									else
										status.setText
										("Black's Turn. You are in Check");
								}
							}
							else {
								if (isCheckMate(turn,board_arrangement)) {
									status.setText
									("Stalemate. It's a draw.");
								}
								else {
									if (turn)
										status.setText("White's Turn");
									else
										status.setText("Black's Turn");
								}
							}
						}
						else {
							status.setText
							("Check. You can't do that.");
						}
					}
					myMode = click_mode.unclicked;
					createNewBoard();
					refreshBoard();
				}
			}
		});
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

		//Reset internal variables
		myMode = click_mode.unclicked;
		turn = true;
		status.setText("White's Turn");

		//Refresh the board
		this.revalidate();
	}

	public void checkmate() {
		if (turn) {
			status.setText("Black Wins. Press Reset to Play Again");
		}
		else {
			status.setText("White Wins. Press Reset to Play Again");
		}
	}

	public boolean isCheck (boolean color, Piece[][] arrangement) {
		Piece king;
		Point king_point = null;
		//Determine which king we are evaluating
		if (color == true) {
			king = White_King;
		}
		else {
			king = Black_King;
		}

		//Find the king's coordinates
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (arrangement[i][j] == king) {
					king_point = new Point (i,j);
				}
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {				
				Piece p = arrangement[i][j];
				if (!(p == null)) {
					for (Point po: p.getOptions(arrangement,i,j)) {
						if (po.equals(king_point)) {
							System.out.println (p.getName() + "is checking the king");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isCheckMate (boolean color, Piece[][] arrangement) {
		// Color is the color of the player who is in checkmate

		//Copy the arrangement to avoid aliasing problems since this method
		//is not supposed to change any state

		for (int i = 0; i < 8; i++) {
			for (int j=0;j<8;j++) {
				Piece p = arrangement[i][j];
				if ((!(p == null)) && p.getColor() == color) {
					Set <Point> myOptions = p.getOptions(arrangement,i,j);
					for (Point option: myOptions) {
						Piece[][] new_arrangement = new Piece[8][8];
						for (int c = 0; c<8;c++) {
							for (int cp =0; cp<8;cp++) {
								new_arrangement[c][cp] = arrangement[c][cp];
							}
						}
						new_arrangement[i][j] = null;
						new_arrangement[option.getX()][option.getY()] = p;
						if (!(isCheck (color, new_arrangement))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}



	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	}
}
