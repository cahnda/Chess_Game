import javax.swing.JLabel;

public class Pawn extends Piece {
	private JLabel myLabel;

	public Pawn(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("pawn.png",w,h));
		else
			myLabel = new JLabel (readImage("bpawn.png",w,h));
	}
	
	public JLabel getLabel() {
		return myLabel;
	}

}
