import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JPanel;

public abstract class LayoutTemplate extends JPanel{
	public abstract void setBackground();
	public abstract Image getImage();
	public abstract Dimension getPreferredSize();
}
