import javax.swing.*;
import java.awt.*;

//This is our view
public class Board {

    JFrame frame;

    JPanel playerOneStones;
    JPanel playerTwoStones;

    JPanel pitPanel;

    public Board()
    {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addEndPanels();


    }

    public void addEndPanels()
    {
        playerOneStones = new JPanel();
        playerTwoStones = new JPanel();

        playerOneStones.setBackground(Color.CYAN);
        playerTwoStones.setBackground(Color.CYAN);

        frame.add(playerOneStones,BorderLayout.WEST);
        frame.add(playerTwoStones, BorderLayout.EAST);
    }

    public void addPits()
    {
        //Creates an overarching pit panel that holds 12 JPanels within it
        pitPanel = new JPanel();
        pitPanel.setLayout(new GridLayout(2,6,5,5));

        //Creates 12 pits
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                JPanel pit = new JPanel();
                pit.setBackground(Color.BLUE);
                pitPanel.add(pit);

            }
        }
        //Adds pits to the center
        frame.add(pitPanel,BorderLayout.CENTER);
    }

	
}
