import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * Used to implement the strategy pattern, this is the base for the two other theme classes and includes
 * the methods, setBackground(), getImage(), and getPreferredSize()
 * 
 * @author Tien Nguyen
 *
 */
public abstract class LayoutTemplate extends JPanel{
	public abstract BufferedImage setBackground();
	public abstract Image getImage();
	public abstract Dimension getPreferredSize();
}
