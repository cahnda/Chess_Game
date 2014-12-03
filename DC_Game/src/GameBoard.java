import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel{	
	public static final int BOARD_WIDTH = 300;
	public static final int BOARD_HEIGHT = 300;
	// Creates board every-time the code is run based on current status
	public GameBoard() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

		}
	}

	// Set the parameters when the board is created
	public void reset() {
		return;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	}
}
