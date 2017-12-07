import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Stone extends JPanel{
	int x;
	int y; 
	
	public Stone() {
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
	