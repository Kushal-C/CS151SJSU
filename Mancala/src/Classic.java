import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Classic extends LayoutTemplate{
	Image background;
	@Override
	public void setBackground() {
		// TODO Auto-generated method stub
		try {
			background = ImageIO.read(new File("background1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return background;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawOval(10, 10, 10, 10);
		g.setColor(Color.RED);
		g.fillOval(10, 10, 10, 10);
	}
	public Dimension getPreferredSize() {
		return new Dimension(20, 20); 
	}
}
