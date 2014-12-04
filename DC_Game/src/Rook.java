import javax.swing.JLabel;

public class Rook extends Piece {
	private JLabel myLabel;

	public Rook(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("rook.png",w,h));
		else
			myLabel = new JLabel (readImage("brook.png",w,h));
	}
	
	public JLabel getLabel() {
		return myLabel;
	}

}
