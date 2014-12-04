import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

public class King extends Piece {

	public King(boolean color, int w, int h) {
		if (color == true) {
			myLabel = new JLabel (readImage("king.png",w,h));
			myColor = true;
		}
		else {
			myLabel = new JLabel (readImage("bking.png",w,h));
			myColor = false;
		}
		name = "king";
	}
	
	@Override
	public Set<Point> getOptions(Piece[][] board_arrangement, int i, int j) {
		Set <Point> arr = new HashSet<Point>();
		Set <Point> remove = new HashSet<Point>();

		for (int x = i-1; x < i+2;x++) {
			for (int y = j-1; y < j+2;y++) {
				arr.add(new Point(x,y));
			}
		}
		
		for (Point p: arr) {
			if (p.getX() > 7 || p.getX() < 0 || 
					p.getY() > 7 || p.getY() < 0) {
				remove.add(p);
			}
			else {
				if (!(board_arrangement[p.getX()][p.getY()] == null)){
					Piece ba = board_arrangement[p.getX()][p.getY()];
					if (ba.getColor() 
							== myColor || ba.getName().equals("king")) {
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
