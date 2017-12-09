import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;

/**
 * The controller class for the entire program. On launch it pops up a dialog which contains all the fields
 * necessary to setup a game of mancala.
 * 
 * @authors Tien Nguyen, Kushalkumar Cuttari
 *
 */
public class DialogWindow extends JDialog
{
	Game model;
	Board b;
	
	public static void main(String[] args) {
		DialogWindow window = new DialogWindow();
	}
	
	/**
	 * Calls the popup method on initialization
	 */
	public DialogWindow() {
		popup();
	}
	
	/**
	 * Shows a dialog window on the screen with all the fields necessary to start the game.
	 */
	public void popup() {
		JTextField name1 = new JTextField();
		JTextField name2 = new JTextField();
		JTextField numberOfStartingStones = new JTextField();
		JTextField theme = new JTextField();

		Object[] fields = {
				"Player 1: ", name1,
				"Player 2: ", name2,
				"Number of Starting Stones (3 or 4): ", numberOfStartingStones,
				"Enter Theme (Classic or Modern): ", theme
		};
		
		JOptionPane.showConfirmDialog(null, fields, "Game Preferences", JOptionPane.OK_CANCEL_OPTION);
		String temp = numberOfStartingStones.getText();
		int s = Integer.parseInt(temp);
		
		LayoutTemplate lt = new Classic();
		String themeTemp = theme.getText();
		
		
		String playerOneName = name1.getText();
		String playerTwoName = name1.getText();
		model = new Game(s, themeTemp, playerOneName, playerTwoName);
		b = new Board(model, lt);
	}
	
	/**
	 * Returns the Game model initialized in popup
	 * @return Game
	 */
	public Game getModel() {
		return model;
	}
	
	/**
	 * Returns the Board initialized in popup
	 * @return Board
	 */
	public Board getBoard() {
		return b;
	}
	
}