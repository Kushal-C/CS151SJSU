import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * The pit class's purpose is to have all of the functionality as well as have the ability to know what position it is 
 * located on in the board. Thus when used in conjunction with the listener, we can use the location of the pit class
 * to manipulate the model. 
 * 
 * @author Kushalkumar Cuttari
 *
 */
public class Pit extends JPanel {
	int row;
	int col;
	
	/**
	 * Takes in a row and columns so its position on the board is known.
	 * @param row
	 * @param col
	 */
	public Pit(int row, int col)
	{
		this.row = row;
		this.col = col;
		super.setLayout(new GridLayout(3,0));
	}
	
	/**
	 * Returns the row this pit is located on.
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the column this pit is located on.
	 * @return
	 */
	public int getCol() {
		return col;
	}
}
