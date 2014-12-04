import javax.swing.JLabel;

public class Bishop extends Piece {
	public Bishop(boolean color, int w, int h) {
		if (color == true) {
			myLabel = new JLabel (readImage("bishop.png",w,h));
			myColor = true;
		}
		else {
			myLabel = new JLabel (readImage("bbishop.png",w,h));
			myColor = false;
		}
		name = "bishop";
	}
}
