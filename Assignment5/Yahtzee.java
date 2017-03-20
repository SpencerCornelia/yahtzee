/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		int numOfTurns = N_SCORING_CATEGORIES;
		while (numOfTurns > 0) {
			String currentPlayerTurn = determinePlayerTurn(playerNames);
			display.waitForPlayerToClickRoll(playerCounter);
			createDice(diceArray);
			display.displayDice(diceArray);
			display.waitForPlayerToSelectDice();
			diceRoll();
			display.waitForPlayerToSelectDice();
			
			playerCounter++;
			numOfTurns --;
		}
	}
	
	private void createDice(int[] diceArray) {
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = rgen.nextInt(1, 6);
		}
	}
	
	private String determinePlayerTurn(String[] playerNames) {
		if (playerNames.length == 1) {
			return playerNames[0];
		}
		
		if (playerCounter == playerNames.length + 1) {
			playerCounter = 0;
			return playerNames[playerCounter];
		}
		return playerNames[playerCounter - 1];
	}
	
	private void diceRoll() {
		for (int i = 0; i < diceArray.length; i++) {
			if (display.isDieSelected(i)) {
				updatedDiceArray[i] = rgen.nextInt(1, 6);
			} else {
				updatedDiceArray[i] = diceArray[i];
			}
		}
		display.displayDice(updatedDiceArray);
	}
	
		
/* Private instance variables */
	private int nPlayers;
	private int[] diceArray = new int[N_DICE];
	private int[] updatedDiceArray = new int[N_DICE];
	private int playerCounter = 1;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
