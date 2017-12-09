import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Template for a classic styling of the game
 * @author Tien Nguyen
 *
 */
public class Classic extends LayoutTemplate{
	
	BufferedImage background;
	
	/**Returns the background image to be used in styling the board, used only when initially loading the background
	 * file.
	 * @return BufferedImage
	 */
	@Override
	public BufferedImage setBackground() {
		// TODO Auto-generated method stub
		try {
			background = ImageIO.read(new File("background1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return background;
	}

	/**
	 * Returns the background image to be used
	 * @return Image
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return background;
	}
	
	/**
	 * Overridden paint component which contains the styling details
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawOval(10, 10, 10, 10);
		g2.setColor(Color.RED);
		g2.fillOval(10, 10, 10, 10);
	}
	
	/**
	 * Returns a Dimension object with the parameters 20,20
	 * @return Dimension
	 */
	public Dimension getPreferredSize() {
		return new Dimension(20, 20); 
	}
}
