import javax.swing.JLabel;

public class Queen extends Piece {
	public Queen(boolean color, int w, int h) {
		if (color == true) {
			myLabel = new JLabel (readImage("queen.png",w,h));
			myColor = true;
		}
		else {
			myLabel = new JLabel (readImage("bqueen.png",w,h));
			myColor = false;
		}
		name = "queen";
	}
}
