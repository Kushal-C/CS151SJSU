import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * Stone is a class that is supplemented by the the theme classes, modern and classic. Stone allows for custom creation
 * of different color circles to fill in the pits.
 * 
 * @author Tien Nguyen
 *
 */

public class Stone extends JPanel{
	int x;
	int y; 
	Game game;
	
	/**
	 * The constructor takes in a game object which holds the theme off the stone, based off of the theme the stones
	 * are colored differently
	 * @param game2
	 */
	public Stone(Game game2) {
		game = game2;
	}
	
	/** 
	 * Overriden paintComponent class which paints a circle of a different color based on the theme in the game class
	 */
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
	
	/**
	 * Returns a new Dimension object with the parameters 20,20
	 */
	public Dimension getPreferredSize() {
		return new Dimension(20, 20); 
	}
	
}
	