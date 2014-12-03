import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pawn extends GameObj {
	public static final String img_file = "poison.png";
	private static BufferedImage img;

	public Pawn(int boardWidth, int boardHeight, int xVal, int yVal) {
		super ((boardWidth / 8) * (yVal),
				(boardHeight / 8) * (xVal),
				boardWidth,
				boardHeight);

		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	public void draw(Graphics g) {
		g.drawImage(img, pos_x, pos_y, width, height, null);		
	}

}
