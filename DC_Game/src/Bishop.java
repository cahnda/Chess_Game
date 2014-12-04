import javax.swing.JLabel;

public class Bishop extends Piece {
	private JLabel myLabel;

	public Bishop(boolean color, int w, int h) {
		if (color == true)
			myLabel = new JLabel (readImage("bishop.png",w,h));
		else
			myLabel = new JLabel (readImage("bbishop.png",w,h));
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
