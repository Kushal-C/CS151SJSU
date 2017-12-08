import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;

//This is our view
public class Board implements ImageObserver {

    JFrame frame;

    JPanel playerOneStones;
    JPanel playerTwoStones;

    JPanel pitPanel;
    
    Game model;

   // TODO:switch from BorderLayout to gridsBagLayout
    public Board(Game model)
    {
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.model = model;
        
        playerOneStones = new JPanel();
        playerTwoStones = new JPanel();
        pitPanel = new JPanel();
        
        frame.add(playerOneStones,BorderLayout.WEST);
        frame.add(playerTwoStones, BorderLayout.EAST);
        frame.add(pitPanel,BorderLayout.CENTER);
        addEndPanels();
        addPits();
        addButtons();
        
        frame.setVisible(true);
    }

    public void addEndPanels()
    {
        playerOneStones.removeAll();
        playerTwoStones.removeAll();
        playerOneStones.setLayout(new GridLayout(0,1));
        playerTwoStones.setLayout(new GridLayout(0,1));
        

        
        playerOneStones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerTwoStones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
       
        playerOneStones.setMinimumSize(new Dimension(100,475));
        playerTwoStones.setMinimumSize(new Dimension(100,475));
   
        int[] endPits = model.getEndPits();
        for (int i = 0; i < endPits[0]; i ++) {
        	Stone stone = new Stone(model);
    	    stone.setSize(50,50);
    		playerOneStones.add(stone);
        }
        
        for (int i = 0; i < endPits[1]; i ++) {
        	Stone stone = new Stone(model);
    	    stone.setSize(50,50);
    		playerTwoStones.add(stone);
        }
		playerOneStones.validate();
		playerOneStones.repaint();
		
		playerTwoStones.validate();
		playerTwoStones.repaint();
       
    }

    public void addPits()
    {
    	pitPanel.removeAll();
        //Creates an overarching pit panel that holds 12 JPanels within it
        pitPanel.setSize(300,475);
        pitPanel.setLayout(new GridLayout(2,6,5,5));

        //Creates 12 pits
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                Pit pit = new Pit(i,j);
                pit.setSize(10,10);
                pit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               
                JPanel stonePanel = new JPanel();
                int [][] tempPits = model.getPits();
                for (int k = 0; k < tempPits[i][j]; k++) {
                	Stone stone = new Stone(model);
            	    stone.setSize(50,50);
            		stonePanel.add(stone);
                }
            	
                pit.add(stonePanel);
                
                pitPanel.add(pit);
                
                pit.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						model.playGame(pit.getRow(), pit.getCol());
		
						int[][] temp = model.getPits();
					
						for(int i = 0; i < 2; i++)
						{
							for(int j = 0; j < 6; j++)
							{
								System.out.print(temp[i][j] + " ");
							}
							System.out.println();
						}
						addPits();
						addEndPanels();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						//model.playGame(pit.getRow(), pit.getCol());
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
                	
                });

            }
        }
        //Adds pits to the center
        pitPanel.validate();
        pitPanel.repaint();
    }

    /**
     * Adds buttons to the overlying panel
     */
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
    		
    		//Listeners for the buttons
    		//Call the JOption Pane dialog again and set up new game
    		play.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					DialogWindow window = new DialogWindow();
					model = window.getModel();
					
				}
    			
    		});
    		
    		//Quits the game
    		quit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
    			
    		});
    		
    		// Calls the undo action on the game logic and reflects the changes on the board
    		undo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.undo();
				}
    			
    		});
    		
    	}
    public void addingStonesToPanel() {
    	
    }

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
