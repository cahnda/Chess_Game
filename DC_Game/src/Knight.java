import javax.swing.JLabel;

public class Knight extends Piece {

	public Knight(boolean color, int w, int h) {
		if (color == true) {
			myLabel = new JLabel (readImage("knight.png",w,h));
			myColor = true;
		}
		else {
			myLabel = new JLabel (readImage("bknight.png",w,h));
			myColor = false;
		}
		name = "knight";
	}

}
