import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class LayoutTemplate {
	protected Rectangle2D.Double[][] pitRects;
	protected int width;
	protected int height;
	public LayoutTemplate() {
		pitRects = new Rectangle2D.Double[2][6];
	}
	public abstract void paintBoard(Graphics g, Board board, int [][] pits, int [] stones);
	
	public Rectangle2D.Double[][] getPits(){
		return pitRects; 
	}
	
	public abstract String getLayoutName();
	
	public void setSize(int newWidth, int newHeight) {
		height = newHeight;
		width = newWidth;
	}
}
