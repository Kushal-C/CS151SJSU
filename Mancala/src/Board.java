import javax.swing.*;
import java.awt.*;

//This is our view
public class Board {

    JFrame frame;

    JPanel playerOneStones;
    JPanel playerTwoStones;

    JPanel pitPanel;

   // TODO:switch from BorderLayout to gridsBagLayout
    public Board(Game model)
    {
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addEndPanels();
        addPits();
        addButtons();
        
        frame.setVisible(true);
    }

    public void addEndPanels()
    {
        playerOneStones = new JPanel();
        playerTwoStones = new JPanel();

        playerOneStones.setBackground(Color.CYAN);
        playerTwoStones.setBackground(Color.CYAN);
        
       
        playerOneStones.setMinimumSize(new Dimension(100,475));
        playerTwoStones.setMinimumSize(new Dimension(100,475));

        frame.add(playerOneStones,BorderLayout.WEST);
        frame.add(playerTwoStones, BorderLayout.EAST);
       
    }

    public void addPits()
    {
        //Creates an overarching pit panel that holds 12 JPanels within it
        pitPanel = new JPanel();
        pitPanel.setSize(300,475);
        pitPanel.setLayout(new GridLayout(2,6,5,5));

        //Creates 12 pits
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                JPanel pit = new JPanel();
                pit.setSize(10,10);
                pit.setBackground(Color.BLUE);
                
                JPanel stonePanel = new JPanel();
            	Stone stone = new Stone();
            	stone.setSize(50,50);
            	stonePanel.add(stone);
            	stonePanel.add(stone);
            	stonePanel.add(stone);
            	
                pit.add(stonePanel);
                
                pitPanel.add(pit);

            }
        }
        //Adds pits to the center
        frame.add(pitPanel,BorderLayout.CENTER);
    }

    public void addButtons()
    {
    	
    		JPanel buttonPanel = new JPanel();
    		buttonPanel.setLayout(new BorderLayout());
    		Button play =  new Button("New Game");
    		Button quit = new Button("Quit");
    		Button undo = new Button("Undo");
    		
    		play.setMinimumSize(new Dimension(25,25));
    		quit.setMinimumSize(new Dimension(25,25));
    		undo.setMinimumSize(new Dimension(25,25));
    		
    		
    		buttonPanel.add(play,BorderLayout.WEST);
    		buttonPanel.add(quit, BorderLayout.CENTER);
    		buttonPanel.add(undo, BorderLayout.EAST);
    		
    		frame.add(buttonPanel,BorderLayout.NORTH);
    	}
    public void addingStonesToPanel() {
    	
    }
}
