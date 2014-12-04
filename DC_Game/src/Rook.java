import javax.swing.JLabel;

public class Rook extends Piece {
	public Rook(boolean color, int w, int h) {
		if (color == true) {
			myLabel = new JLabel (readImage("rook.png",w,h));
			myColor = true;
		}
		else {
			myLabel = new JLabel (readImage("brook.png",w,h));
			myColor = false;
		}
		name = "rook";
	}
}
