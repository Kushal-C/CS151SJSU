import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;

/**
 * A dialog to allow player to set initial stone count
 * and a board layout to be used for game play
 * @author Team Edward.
 *
 */
public class DialogWindow extends JDialog
{
	public DialogWindow() {
		popup();
	}
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
		Game model = new Game(s);
		Board b = new Board(model);
	}
}