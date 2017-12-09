import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class LayoutTemplate extends JPanel{
	public abstract BufferedImage setBackground();
	public abstract Image getImage();
	public abstract Dimension getPreferredSize();
}
