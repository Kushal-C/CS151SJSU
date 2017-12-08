import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Stone extends JPanel{
	int x;
	int y; 
	Game game;
	public Stone(Game game2) {
		game = game2;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (game.getTheme().equals("Classic")) {
			g.drawOval(10, 10, 10, 10);
			g.setColor(Color.RED);
			g.fillOval(10, 10, 10, 10);
		}
		else {
			g.drawRect(10, 10, 10, 10);
			g.setColor(Color.GREEN);
			g.fillRect(10, 10, 10, 10);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(20, 20); 
	}
	
}
	