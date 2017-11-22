package Mancala;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Game {
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

	public void addChangeListener(ChangeListener listener){
		listenerL.add(listener);
		updateAll();
	}

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

	public int switchPlayer(int whichPlayer){
		if(++whichPlayer >= 2){
			whichPlayer = 0;
		}
		return whichPlayer;
	}

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

