import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel{	
   layeredPane = new JLayeredPane();

	public static final int BOARD_WIDTH = 300;
	public static final int BOARD_HEIGHT = 300;
	private Pawn wp1;
	private Pawn wp2;
	private Pawn wp3;
	private Pawn wp4;
	private Pawn wp5;
	private Pawn wp6;
	private Pawn wp7;
	private Pawn wp8;
	private King wk1;
	private Queen wq1;
	private Bishop wb1;
	private Bishop wb2;
	private Knight wn1;
	private Knight wn2;
	private Rook wr1;
	private Rook wr2;
	
	
	// Creates board every-time the code is run based on current status
	public GameBoard() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		/*
		setLayout(new GridLayout(8,8));
		setSize (BOARD_WIDTH,BOARD_HEIGHT);
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
		wp1 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,0);
		wp2 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,1);
		wp3 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,2);
		wp4 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,3);
		wp5 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,4);
		wp6 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,5);
		wp7 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,6);
		wp8 = new Pawn(BOARD_WIDTH,BOARD_HEIGHT,1,7);
		/*
		wr1 = new Rook(BOARD_WIDTH,BOARD_HEIGHT,0,0);
		wr2 = new Rook(BOARD_WIDTH,BOARD_HEIGHT,0,7);
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
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		wp1.draw(g);	wp2.draw(g);	wp3.draw(g);
		wp4.draw(g);	wp5.draw(g);	wp6.draw(g);
		wp7.draw(g);	wp8.draw(g);
		
		/*
		wr1.draw(g); 	wr2.draw(g);
		wb1.draw(g); 	wb2.draw(g);
		wn1.draw(g); 	wn2.draw(g);
		wq1.draw(g);
		wk1.draw(g);
		
		
		bp1.draw(g);	bp2.draw(g);	bp3.draw(g);
		bp4.draw(g);	bp5.draw(g);	bp6.draw(g);
		bp7.draw(g);	bp8.draw(g);
		br1.draw(g); 	br2.draw(g);
		bb1.draw(g); 	bb2.draw(g);
		bn1.draw(g); 	bn2.draw(g);
		bq1.draw(g);
		bk1.draw(g);
		*/
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	}
}
