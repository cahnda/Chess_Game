import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class GameBoard extends JPanel{
	public static final int BOARD_WIDTH = 300;
	public static final int BOARD_HEIGHT = 300;
	private Piece [][] board_arrangement;
	private JPanel [][] squares;
	protected static ImageIcon ICON = null;
	
	// Creates board every-time the code is run based on current status
	public GameBoard() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		board_arrangement = new Piece[8][8];
		squares = new JPanel[8][8];
		setLayout(new GridLayout(8,8));
		setSize (BOARD_WIDTH,BOARD_HEIGHT);
		createBoard();
		for (JPanel[] j_list: squares) {
			for (JPanel j: j_list) {
				this.add(j);
			}
		}
	}
	
	public void createBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel();
				if ((i + j) % 2 == 0) {
					square.setBackground(Color.black);
				} else {
					square.setBackground(Color.white);
				}   
				squares[i][j] = square;
			}
		}
	}
	
	public ImageIcon readImage(String filename) {
		int width = this.getWidth() / 8;
		int height = this.getHeight() / 8;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println ("If this happens, this code will break. "
					+ "It won't, because the images are hardcoded in.");		
		}
		Image pic = img.getScaledInstance( (int) (.8 * width),(int) (.8 * height),
				Image.SCALE_SMOOTH);
		return new ImageIcon(pic);
	}
	
	public void addPieces() {
		System.out.println ("Just got called");
		//Starting with zero for consistent with CS norms
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++){
				if (i == 1) {
					board_arrangement[i][j] = new Pawn();
					ICON = readImage("poison.png");
					squares[i][j].add (new JLabel (ICON));
				} else {
					if (i == 0) {
						if (j == 0 || j == 7) {
							board_arrangement[i][j] = new Rook();
							ICON = readImage("poison.png");
							squares[i][j].add (new JLabel (ICON));
						}
						if (j == 1 || j == 6) {
							board_arrangement[i][j] = new Knight();
							ICON = readImage("poison.png");
							squares[i][j].add (new JLabel (ICON));
						}
						if (j == 2 || j == 5) {
							board_arrangement[i][j] = new Bishop();
							ICON = readImage("poison.png");
							squares[i][j].add (new JLabel (ICON));						
							}
						if (j == 3){
							board_arrangement[i][j] = new Queen();
							ICON = readImage("poison.png");
							squares[i][j].add (new JLabel (ICON));						
						}
						if (j == 4){
							board_arrangement[i][j] = new King();
							ICON = readImage("poison.png");
							squares[i][j].add (new JLabel (ICON));						
						}
					}
					else {
						board_arrangement[i][j] = null;
					}
				}
			}
		}
	}
	

	// Set the parameters when the board is created
	public void reset() {
		addPieces();
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
