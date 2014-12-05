import java.util.HashSet;
import java.util.Set;

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
	
	@Override
	public Set<Point> getOptions(Piece[][] board_arrangement, int i, int j) {	
		Set <Point> arr = new HashSet<Point>();
		Set <Point> remove = new HashSet<Point>();
		
		//Manually add all 8 possible points
		arr.add(new Point (i+2, j+1));
		arr.add(new Point (i+2, j-1));
		arr.add(new Point (i+1, j-2));
		arr.add(new Point (i+1, j+2));
		arr.add(new Point (i-2, j+1));
		arr.add(new Point (i-2, j-1));
		arr.add(new Point (i-1, j-2));
		arr.add(new Point (i-1, j+2));
		
		//Copy King's alogorithm for removing invalid points
		for (Point p: arr) {
			if (p.getX() > 7 || p.getX() < 0 || 
					p.getY() > 7 || p.getY() < 0) {
				remove.add(p);
			}
			else {
				if (!(board_arrangement[p.getX()][p.getY()] == null)){
					Piece ba = board_arrangement[p.getX()][p.getY()];
					if (ba.getColor() 
							== myColor) {
						remove.add(p);
					}
				}
			}
		}
		
		// Remove invalid move options
		for (Point p: remove) {
			arr.remove(p);
		}
		return arr;
	}
}
