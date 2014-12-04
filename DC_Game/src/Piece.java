import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {	

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
		Image pic = img.getScaledInstance( (int) (.8 * width),(int) (.8 * height),
				Image.SCALE_SMOOTH);
		return new ImageIcon(pic);
	}
	
	abstract JLabel getLabel();
	public Point[] getOptions(Piece[][] board_arrangement, int i, int j) {
		Point p1 = new Point (0,0);
		Point p2 = new Point (0,1);
		Point p3 = new Point (0,2);
		Point[] arr = new Point[3];
		arr[0] = p1; arr[1] = p2; arr[2] = p3;
		return arr;
	}

}
