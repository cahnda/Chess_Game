import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
public class Pawn extends Piece {

	public Pawn(boolean color, int w, int h) {
		if (color == true) {
			myLabel = new JLabel (readImage("pawn.png",w,h));
			myColor = true;
		}
		else {
			myLabel = new JLabel (readImage("bpawn.png",w,h));
			myColor = false;
		}
	}
	
	@Override
	public Set<Point> getOptions(Piece[][] board_arrangement, int i, int j) {
		Set <Point> arr = new HashSet<Point>();
		//Add points 1 and 2 to move to
		int val1; int val2;
		if (getColor() == true) {
			val1 = i + 1;
			val2  = i + 2;
		}
		else {
			val1 = i - 1;
			val2 = i - 2;
		}
		Point P1 = new Point (val1, j);
		if (board_arrangement [val1][j] == null) {
			arr.add(P1);
		}
		Point P2 = new Point (val2, j);
		if (board_arrangement [val1][j] == null) {
			arr.add(P2);
		}
		
		//Add ability to capture pieces diagonally
		int[] capVals = new int[2]; capVals[0]=j+1; capVals[1]=j-1;
		for (int cVal: capVals) {	
			if (cVal > 0 && cVal < 8 && 
					(!(board_arrangement[val1][cVal] == null)) && 
					(board_arrangement[val1][cVal].getColor() 
							!= this.getColor())) {
				arr.add(new Point (val1,cVal));
			}
		}
		
		//Make sure every point is within the board
		for (Point p: arr) {
			if (p.getX() > 8 || p.getX() < 0 ||
					p.getY() > 8 || p.getY() < 0) {
				arr.remove(p);
			}
		}
		return arr;
	}
	
}
