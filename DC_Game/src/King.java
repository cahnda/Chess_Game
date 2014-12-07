import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

public class King extends Piece {
	private boolean hasMoved;
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
		hasMoved = false;
	}
	
	public void setMoved(boolean val) {
		hasMoved = val;
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
		
		if (canCastleUp(board_arrangement,i,j)) {
			arr.add(new Point (i,j+2));
		}
		
		if (canCastleDown(board_arrangement,i,j)) {
			arr.add(new Point (i,j-2));
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
	
	public boolean canCastleUp (Piece[][] board_arrangement, int i, int j) {
		//CASTLING
		if (j+3 == 7) {
			if (hasMoved == false
					&& (!(board_arrangement[i][j+3] == null)) &&
					board_arrangement[i][j+3].getName().equals("rook")) {
				if (board_arrangement[i][j+1] == null &&
						board_arrangement[i][j+2] == null) {
				} 
				return true;
			}
		}
		return false;
	}
	
	public boolean canCastleDown (Piece[][] board_arrangement, int i, int j) {
		//CASTLING (only possible under very specific circumstances, detailed
		// below
		if (j-4 == 0) {
			if (hasMoved == false 
					&& (!(board_arrangement[i][j-4] == null)) &&
					board_arrangement[i][j-4].getName().equals("rook")) {
				if (board_arrangement[i][j-1] == null &&
						board_arrangement[i][j-2] == null &&
						board_arrangement[i][j-3] == null) {
				}
				return true;
			}
		}
		return false;
	}
}
