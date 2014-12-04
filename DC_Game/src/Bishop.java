import java.util.HashSet;
import java.util.Set;

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

	@Override
	public Set<Point> getOptions(Piece[][] board_arrangement, int i, int j) {
		Set <Point> arr = new HashSet<Point>();

		//back, left
		for (int bk = 1; bk <= i; bk++) {
			if ((i - bk >= 0) && (j-bk >= 0)) {
				Point p = new Point (i - bk, j - bk);
				arr.add(p);
				if (!(board_arrangement [i - bk][j - bk] == null)){
					if (board_arrangement [i - bk][j - bk].getColor()
							== myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}

		//back, right
		for (int bk = 1; bk <= i; bk++) {
			if ((i - bk >= 0) && (j+bk < 8)) {
				Point p = new Point (i - bk, j + bk);
				arr.add(p);
				if (!(board_arrangement [i - bk][j + bk] == null)) {
					if (board_arrangement [i - bk][j + bk].getColor()
							== myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}

		//forward left
		for (int frw = 1; frw <= 7-i; frw++) {
			if ((i + frw < 8) && (j-frw >= 0)) {
				Point p = new Point (i + frw, j - frw);
				arr.add(p);
				if (!(board_arrangement [i + frw][j - frw] == null)) {
					if (board_arrangement [i + frw][j - frw].getColor()
							== myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}
		//forward right
		for (int frw = 1; frw <= 7-i; frw++) {
			if ((i + frw < 8) && (j+frw < 8)) {
				Point p = new Point (i + frw, j + frw);
				arr.add(p);		
				if (!(board_arrangement [i + frw][j + frw] == null)){
					if (board_arrangement [i + frw][j + frw].getColor()
							== myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}
		return arr;
	}
}
