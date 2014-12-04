import javax.swing.JLabel;

public class King extends Piece {
	private JLabel myLabel;

	public King(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("king.png",w,h));
		else
			myLabel = new JLabel (readImage("bking.png",w,h));
	}
	
	public JLabel getLabel() {
		return myLabel;
	}

}
