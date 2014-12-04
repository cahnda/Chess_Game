import javax.swing.JLabel;

public class Knight extends Piece {
	private JLabel myLabel;

	public Knight(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("knight.png",w,h));
		else
			myLabel = new JLabel (readImage("bknight.png",w,h));
	}
	
	public JLabel getLabel() {
		return myLabel;
	}

}
