
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//This is our model class
public class Game {
	
	//Two sets of pits for each player, player 1 represented by pits [0][*] player 2 represented by pits [1][*]
	private int[][] pits = new int [2][6];
	private int[][] undoPits = new int[2][];
	private int [] endPits = new int [2];
	private int [] undoEndPits;
	private int [] undoCounter = new int[2];
	private boolean isGameOver = false;
	private ArrayList<ChangeListener> listenerL = new ArrayList<ChangeListener>();
	private int currentPlayer = 0;
	private final int MaxUndos = 3;
	private boolean undo = false;
	private boolean undoSideHelper = false;
	private int currentUndoer = 0;
	
	/**
	 * Start of the game placing stones into pits
	 * @param NumberofStones is the number of stones going to be played
	 */
	public Game(int NumberofStones){
		for(int i = 0; i<2; i++){
			endPits[i] = 0;
			undoCounter[i] = 0;
			for(int j = 0; j < 6; j++){
				pits[i][j] = NumberofStones;
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

		if(pits[whichPlayer][pit]==0){
			return;
		}

		resetAll();

		if(undoSideHelper){
			undoCounter[whichPlayer] = 0;
			undoSideHelper = false;
		}
		else if (undoCounter[whichPlayer] == 0){
			undoCounter[switchPlayer(whichPlayer)] = 0;
			currentUndoer = whichPlayer;
		}

		undo = true;
		int currentPlayer = pits[whichPlayer][pit];
		pits[whichPlayer][pit] = 0;
		while(currentPlayer > 0){
			pit = ++pit >= 6 ? 0 : pit;
			if(pit == 0){
				if(whichPlayer == currentPlayer){
					--currentPlayer;
					++endPits[whichPlayer];
				
					if(currentPlayer <= 0){
						arePitsEmpty();
						undoSideHelper = true;
						updateAll();
						return;
					}
				} 
				whichPlayer = switchPlayer(whichPlayer);
			}
			++pits[whichPlayer][pit];
			--currentPlayer;
		}
		endTurn(whichPlayer,pit);
	}

	/**
	 * Undo method which updates undo counter and checks logic
	 */
	public void undo(){
		if (getGame()||!undo||(currentPlayer == currentUndoer && !undo)||(undoCounter[currentUndoer] == MaxUndos)){
			return;
		}

		for(int i = 0; i < 2; i++){
			pits[i] = undoPits[i].clone();
		}

		undo = false;
		undoSideHelper = false;
		endPits = undoEndPits.clone();
		undoCounter[currentUndoer]++;
		currentPlayer = currentUndoer;
		updateAll();
	}

	/**
	 * Change listener list
	 * @param listener to be added
	 */
	public void addChangeListener(ChangeListener listener){
		listenerL.add(listener);
		updateAll();
	}

	/**
	 * Updates the games and moves stones to appropriate pits
	 * @param whichPlayer is the player ending turn
	 * @param pit is the pit chosen
	 */
	private void endTurn(int whichPlayer, int pit){
		if (whichPlayer == currentPlayer && pits[whichPlayer][pit] == 1){
			endPits[whichPlayer] += 1 + pits[switchPlayer(whichPlayer)][6 - pit - 1];
			pits[whichPlayer][pit] = 0;
			pits[switchPlayer(whichPlayer)][6 - pit - 1] = 0;
			undoSideHelper = true;
		}
		else{
			currentPlayer = switchPlayer(currentPlayer);
		}
		arePitsEmpty();
		updateAll();
	}

	/**
	 * Checks if all pits are empty to end game
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


	public void updateAll(){
		for(ChangeListener listener: listenerL){
			listener.stateChanged(new ChangeEvent(this));
		}
	}

	public int[][] getPits(){
		return pits.clone();
	}

	public int getNumberofUndos(){
		return MaxUndos - undoCounter[currentUndoer];
	}

	public int[] getEndPits(){
		return endPits.clone();
	}

	public int getCurrentPlayer(){
		return currentPlayer + 1;
	}

	public boolean getGame(){
		return isGameOver;
	}
}
