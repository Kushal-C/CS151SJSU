import javax.swing.JPanel;

/**
 * The pit class's purpose is to have all of the functionality as well as have the ability to know what position it is 
 * located on in the board. Thus when used in conjunction with the listener, we can use the location of the pit class
 * to manipulate the model. 
 * @author Kushal
 *
 */
public class Pit extends JPanel {
	int row;
	int col;
	
	public Pit(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
