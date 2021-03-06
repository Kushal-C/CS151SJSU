import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model Class of the Project Mancala. Controls all the logic for the entirety of the game
 * @author Ruben Tapia
 */
public class Game {
	
	//Two sets of pits for each player, player 1 represented by pits [0][*] player 2 represented by pits [1][*]
	private int[][] pits = new int [2][6];
	private int[][] undoPits = new int[2][];
	private int [] endPits = new int [2];
	private int [] undoEndPits;
	private int [] undoCounter = new int[2];
	private boolean isGameOver = false;
	
	
	private int currentPlayer = 0;
	private final int MaxUndos = 3;
	private boolean undo = false;
	private boolean getsFreeTurn = false;
	private int currentUndoer = 0;
	private int startingStones;
	private String theme;
	private String playerOneName;
	private String playerTwoName;

	/**
	 * Start of the game placing stones into pits
	 * @param NumberofStones is the number of stones going to be played
	 */
	public Game(int NumberofStones, String theme2, String playerOne, String playerTwo){
		this.theme = theme2;
		this.startingStones = NumberofStones;
		this.playerOneName = playerOne;
		this.playerTwoName = playerTwo;
		for(int i = 0; i<2; i++){
			endPits[i] = 0;
			undoCounter[i] = 0;
			for(int j = 0; j < 6; j++){
				pits[i][j] = startingStones;
			}
		}
		resetAll();
	}

	/**
	 * Game method for when a player chooses a pit to move stones
	 * @param whichPlayer is the current player choosing a pit
	 * @param pit is the pit chosen to move stones
	 */
	public void playGame(int whichPlayer, int pit){
		if(whichPlayer != currentPlayer){
			throw new IllegalArgumentException("Wrong players turn.");
		}

		//If the pit is empty, nothing occurs
		if(pits[whichPlayer][pit]==0){
			return;
		}

		resetAll();
		
		//If player getsFreeTurn, resets undos
		if(getsFreeTurn == true){
			undoCounter[whichPlayer] = 0;
			getsFreeTurn = false;
		}
		else if (undoCounter[whichPlayer] == 0){
			undoCounter[switchPlayer(whichPlayer)] = 0;
			currentUndoer = whichPlayer;
		}

		//Takes all stones out of pit selected and move them accordingly
		undo = true;
		int stonesToBeMoved = pits[whichPlayer][pit];
		pits[whichPlayer][pit] = 0;
		while(stonesToBeMoved > 0){
			//Iterates to the left if top player for counterclockwise effect
			if(whichPlayer == 0){
				//Adds to endpit if switching sides
				if(--pit < 0){
					pit = 0;
					--stonesToBeMoved;
					++endPits[whichPlayer];
					
					if(stonesToBeMoved <= 0){
						arePitsEmpty();
						getsFreeTurn = true;
						return;
					} 
					whichPlayer = switchPlayer(whichPlayer);
				}
				else
					pit = pit;
				++pits[whichPlayer][pit];
				--stonesToBeMoved;
			}
			//Iterates to the right if bottom player for counterclockwise effect
			else{
				//Adds to endpit if switching sides
				if(++pit >= 6){
					pit = 5;
					--stonesToBeMoved;
					++endPits[whichPlayer];
					
					if(stonesToBeMoved <= 0){
						arePitsEmpty();
						getsFreeTurn = true;
						return;
					} 
					whichPlayer = switchPlayer(whichPlayer);
				}
				else
					pit = pit;
				++pits[whichPlayer][pit];
				--stonesToBeMoved;
			}
			
		}
		endTurn(whichPlayer,pit);
	}

	/**
	 * Undo method which updates undo counter and checks logic
	 * Uses clone to keep track of 2D array
	 */
	public void undo(){
		if (getGame()||undo == false||(currentPlayer == currentUndoer && undo == false)||(undoCounter[currentUndoer] == MaxUndos)){
			return;
		}

		for(int i = 0; i < 2; i++){
			pits[i] = undoPits[i].clone();
		}

		undo = false;
		getsFreeTurn = false;
		endPits = undoEndPits.clone();
		undoCounter[currentUndoer]++;
		currentPlayer = currentUndoer;

	}

	/**
	 * Updates the games and moves stones to appropriate pits
	 * Checks if the players last stone lands on an empty pit, if so take stones and grants free turn
	 * @param whichPlayer is the player ending turn
	 * @param pit is the pit chosen
	 */
	private void endTurn(int whichPlayer, int pit){
		//This if checks for other getFreeTurn Condidtion
		if (whichPlayer == currentPlayer && pits[whichPlayer][pit] == 1){
			endPits[whichPlayer] += 1 + pits[switchPlayer(whichPlayer)][pit];
			pits[whichPlayer][pit] = 0;
			pits[switchPlayer(whichPlayer)][pit] = 0;
			getsFreeTurn = true;
		}
		else{
			currentPlayer = switchPlayer(currentPlayer);
		}
		arePitsEmpty();
	}

	/**
	 * Checks if all pits are empty to end game by iterating thorugh the pits of each players side
	 * @return true if pits are empty false if not
	 */
	private boolean arePitsEmpty(){
		int[][] checkPits = pits.clone();
		int isEmpty;
		for(int i = 0; i < 2; i++){
			isEmpty = 0;
			for(int j = 0; j < 6; j++){
				if(checkPits[i][j] == 0){
					isEmpty++;
				}
			}
			if(isEmpty == 6){
				endGame(switchPlayer(i));
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves remaining stones to appropriate endPits
	 * @param whichPlayer is the player whose turn the game ended on
	 * Displays the name of the winner of the game
	 */
	public void endGame(int whichPlayer){
		isGameOver = true;
		for(int i = 0; i < 6; i++){
			endPits[whichPlayer] += pits[whichPlayer][i];
			pits[whichPlayer][i] = 0;
		}
		if(endPits[currentPlayer] == endPits[switchPlayer(currentPlayer)]){
			currentPlayer = -1;
			return;
		}

		if(endPits[currentPlayer] < endPits[switchPlayer(currentPlayer)]){
			currentPlayer = switchPlayer(currentPlayer);
			if(currentPlayer == 0) 
				JOptionPane.showMessageDialog(null, "The winner is " + playerOneName);
			else
				JOptionPane.showMessageDialog(null, "The winner is " + playerTwoName);
			return;
		}
	}

	/**
	 * Switches between players 
	 * @param whichPlayer player who ended turn
	 * @return other player
	 */
	public int switchPlayer(int whichPlayer){
		if(++whichPlayer >= 2){
			whichPlayer = 0;
		}
		return whichPlayer;
	}

	/**
	 * resets all pits
	 */
	private void resetAll(){
		undoEndPits = endPits.clone();
		for (int i = 0; i < 2; i++) {
			undoPits[i] = pits[i].clone();
		}
	}

	/**
	 * Returns a clone of the pits
	 * @return int[][]
	 */
	public int[][] getPits(){
		return pits.clone();
	}

	/**
	 * Returns the number of Undos left for a player
	 * @return int
	 */
	public int getNumberofUndos(){
		return MaxUndos - undoCounter[currentUndoer];
	}

	/**
	 * Returns a clone of the end pits
	 * @return int[]
	 */
	public int[] getEndPits(){
		return endPits.clone();
	}

	/**
	 * Returns which players turn it is currently.
	 * @return int
	 */
	public int getCurrentPlayer(){
		return currentPlayer + 1;
	}
	
	/**
	 * Returns whether or not the game is over
	 * @return boolean
	 */
	public boolean getGame(){
		return isGameOver;
	}
	
	/**
	 * Returns the number of stones used to start the game
	 * @return int
	 */
	public int getStartingStones() {
		return startingStones;
	}
	
	/**
	 * Returns the theme of game.
	 * @return String
	 */
	public String getTheme() {
		return theme;
	}
	
	/**
	 * Returns player one's name
	 * @return String
	 */
	public String getPlayerOneName() {
		return playerOneName;
	}
	
	/**
	 * Returns player two's name
	 * @return String
	 */
	public String getPlayerTwoName() {
		return playerTwoName;
	}
}
