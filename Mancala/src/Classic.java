import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Classic extends LayoutTemplate{
	BufferedImage background;
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

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return background;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawOval(10, 10, 10, 10);
		g2.setColor(Color.RED);
		g2.fillOval(10, 10, 10, 10);
	}
	public Dimension getPreferredSize() {
		return new Dimension(20, 20); 
	}
}
