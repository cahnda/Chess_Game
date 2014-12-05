import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {	
	protected boolean myColor;
	protected JLabel myLabel;
	protected String name;
	protected Point myPos;

	public ImageIcon readImage(String filename, int w, int h) {
		int width = w / 8;
		int height = h / 8;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println ("If this happens, this code will break. "
					+ "It won't, because the images are hardcoded in.");		
		}
		Image pic = img.getScaledInstance((int)(.8 * width),(int)(.8 * height),
				Image.SCALE_SMOOTH);
		return new ImageIcon(pic);
	}
	
	public JLabel getLabel() {
		return myLabel;
	}
	
	public boolean getColor() {
		return myColor;
	}
	
	public String getName() {
		return name;
	}
	
	public Point position() {
		return myPos;
	}
	
	abstract Set <Point> getOptions(Piece[][] board_arrangement, int i, int j);
}


	

