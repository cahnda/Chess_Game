import java.util.HashSet;
import java.util.Set;

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
	
	@Override
	public Set<Point> getOptions(Piece[][] board_arrangement, int i, int j) {
		Set <Point> arr = new HashSet<Point>();

		//back, vertical
		for (int bk = 1; bk <= i; bk++) {
			if (i - bk >= 0) {
				Point p = new Point (i - bk,j);
				arr.add(p);
				if (!(board_arrangement [i - bk][j] == null)){
					Piece ba = board_arrangement [i - bk][j];
					if (ba.getColor() == myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}

		//back, horizontal
		for (int bk = 1; bk <= j; bk++) {
			if (j-bk >= 0) {
				Point p = new Point (i, j - bk);
				arr.add(p);
				if (!(board_arrangement [i][j - bk] == null)) {
					Piece ba = board_arrangement [i][j - bk];
					if (ba.getColor() == myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}

		//forward vertical
		for (int frw = 1; frw <= 7-i; frw++) {
			if (i + frw < 8) {
				Point p = new Point (i + frw, j);
				arr.add(p);
				if (!(board_arrangement [i + frw][j] == null)) {
					Piece ba = board_arrangement [i + frw][j];
					if (ba.getColor() == myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}
		//back right
		for (int frw = 1; frw <= 7-j; frw++) {
			if (j+frw < 8) {
				Point p = new Point (i, j + frw);
				arr.add(p);		
				if (!(board_arrangement [i][j + frw] == null)){
					Piece ba = board_arrangement [i][j + frw];
					if (ba.getColor() == myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}

		//back, left
		for (int bk = 1; bk <= i; bk++) {
			if ((i - bk >= 0) && (j-bk >= 0)) {
				Point p = new Point (i - bk, j - bk);
				arr.add(p);
				if (!(board_arrangement [i - bk][j - bk] == null)){
					Piece ba = board_arrangement [i - bk][j - bk];
					if (ba.getColor() == myColor) {
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
					Piece ba = board_arrangement [i - bk][j + bk];
					if (ba.getColor() == myColor) {
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
					Piece ba = board_arrangement [i + frw][j - frw];
					if (ba.getColor() == myColor) {
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
					Piece ba = board_arrangement [i + frw][j + frw];
					if (ba.getColor() == myColor) {
						arr.remove(p);
					}
					break;
				}
			}
		}
		return arr;
	}
}
