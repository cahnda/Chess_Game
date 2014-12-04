import javax.swing.JLabel;

public class King extends Piece {
	private JLabel myLabel;

	public King(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("king.png",w,h));
		else
			myLabel = new JLabel (readImage("bking.png",w,h));
	}
	
	public JLabel getLabel() {
		return myLabel;
	}
	
	@Override
	public Point[] getOptions(Piece[][] board_arrangement, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

}
