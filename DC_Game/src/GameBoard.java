import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel{
	public static final int BOARD_WIDTH = 300;
	public static final int BOARD_HEIGHT = 300;
	private GameObj[][] board_arrangement;
	
	
	// Creates board every-time the code is run based on current status
	public GameBoard() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		board_arrangement = new GameObj[8][8];
		setLayout(new GridLayout(8,8));
		setSize (BOARD_WIDTH,BOARD_HEIGHT);
		/*
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel();
				if ((i + j) % 2 == 0) {
					square.setBackground(Color.black);
				} else {
					square.setBackground(Color.white);
				}   
				this.add(square);
			}

		}	*/
	}


	// Set the parameters when the board is created
	public void reset() {
		//Starting with zero for consistent with CS norms
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++){
				if (i == 1) {
					board_arrangement[i][j] = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,i,j);
				} else {
					board_arrangement[i][j] = null;  
					}
				}
			}
		}
	/*
		wr1 = new Rook(BOARD_WIDTH,BOARD_HEIGHT,i,j);
		wb1 = new Bishop(BOARD_WIDTH,BOARD_HEIGHT,0,2);
		wb2 = new Bishop(BOARD_WIDTH,BOARD_HEIGHT,0,5);
		wn1 = new Knight(BOARD_WIDTH,BOARD_HEIGHT,0,1);
		wn2 = new Knight(BOARD_WIDTH,BOARD_HEIGHT,0,6);
		wq1 = new Queen(BOARD_WIDTH,BOARD_HEIGHT,0,4);
		wk1 = new King(BOARD_WIDTH,BOARD_HEIGHT,0,3);

		Pawn bp1 = new Pawn();
		Pawn bp2 = new Pawn();
		Pawn bp3 = new Pawn();
		Pawn bp4 = new Pawn();
		Pawn bp5 = new Pawn();
		Pawn bp6 = new Pawn();
		Pawn bp7 = new Pawn();
		Pawn bp8 = new Pawn();
		Rook br1 = new Rook();
		Rook br2 = new Rook();
		Bishop bb1 = new Bishop();
		Bishop bb2 = new Bishop();
		Knight bn1 = new Knight();
		Knight bn2 = new Knight();
		Queen bq1 = new Queen();
		King bk1 = new King();
		*/
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (GameObj[] go_arr: board_arrangement) {
			for (GameObj go: go_arr) {
				if (!(go == null))
					go.draw(	g);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	}
}
