import javax.swing.JLabel;

public class Queen extends Piece {
	private JLabel myLabel;

	public Queen(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("queen.png",w,h));
		else
			myLabel = new JLabel (readImage("bqueen.png",w,h));
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
