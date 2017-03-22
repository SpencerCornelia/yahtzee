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
			playerTurns();
			int score = createScore(category);
			updateScore();
			display.updateScorecard(category, playerCounter, score);
			playerCounter = determinePlayerCounter(playerCounter);
			numOfTurns --;
		}
	}
	
	private void playerTurns() {
		display.waitForPlayerToClickRoll(playerCounter);
		createDice(diceArray);
		display.displayDice(diceArray);
		display.waitForPlayerToSelectDice();
		diceRoll();
		display.waitForPlayerToSelectDice();
		diceRoll();
		category = display.waitForPlayerToSelectCategory();
		println(category);
	}
	
	private void createDice(int[] diceArray) {
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = rgen.nextInt(1, 6);
		}
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
	
	private int determinePlayerCounter(int playerCounter) {
		playerCounter ++;
		if (playerCounter == nPlayers + 1) {
			return 1;
		}
		return playerCounter;
	}
	
	private int createScore(int score) {
		if (score == 1) {
			int ones = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 1) {
					ones += 1;
				}
			}
			sumScores[playerCounter][1] += ones;
			return ones;
		}
		
		if (score == 2) {
			int twos = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 2) {
					twos += 2;
				}
			}
			sumScores[playerCounter][2] += twos;
			return twos;
		}
		
		if (score == 3) {
			int threes = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 3) {
					threes += 3;
				}
			}
			sumScores[playerCounter][3] += threes;
			return threes;
		}
		
		if (score == 4) {
			int fours = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 4) {
					fours += 4;
				}
			}
			sumScores[playerCounter][4] += fours;
			return fours;
		}
		
		if (score == 5) {
			int fives = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 5) {
					fives += 5;
				}
			}
			sumScores[playerCounter][5] += fives;
			return fives;
		}
		
		if (score == 6) {
			int sixes = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 6) {
					sixes += 6;
				}
			}
			sumScores[playerCounter][6] += sixes;
			return sixes;
		}
		
		if (score == 7) {
			int one = 0;
			int two = 0;
			int three = 0;
			int four = 0;
			int five = 0;
			int six = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 1) { one ++; }
				if (updatedDiceArray[i] == 2) { two += 2; }
				if (updatedDiceArray[i] == 3) { three += 3; }
				if (updatedDiceArray[i] == 4) { four += 4; }
				if (updatedDiceArray[i] == 5) { five += 5; }
				if (updatedDiceArray[i] == 6) { six += 6; }
			}
			if (one == 3) { 
				sumScores[playerCounter][7] += 3;
				return 3; 
			}
			if (two == 6) { 
				sumScores[playerCounter][7] += 6;
				return 6; 
			}
			if (three == 9) { 
				sumScores[playerCounter][7] += 9;
				return 9; 
			}
			if (four == 12) { 
				sumScores[playerCounter][7] += 12;
				return 12; 
			}
			if (five == 15) { 
				sumScores[playerCounter][7] += 15;
				return 15; 
			}
			if (six == 18) { 
				sumScores[playerCounter][7] += 18;
				return 18; 
			}
		}
		
		if (score == 8) {
			int one = 0;
			int two = 0;
			int three = 0;
			int four = 0;
			int five = 0;
			int six = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				if (updatedDiceArray[i] == 1) { one ++; }
				if (updatedDiceArray[i] == 2) { two += 2; }
				if (updatedDiceArray[i] == 3) { three += 3; }
				if (updatedDiceArray[i] == 4) { four += 4; }
				if (updatedDiceArray[i] == 5) { five += 5; }
				if (updatedDiceArray[i] == 6) { six += 6; }
			}
			if (one == 4) { 
				sumScores[playerCounter][8] += 4; 
				return 4; 
			}
			if (two == 8) { 
				sumScores[playerCounter][8] += 8; 
				return 8; 
			}
			if (three == 12) { 
				sumScores[playerCounter][8] += 12; 
				return 12; 
			}
			if (four == 16) { 
				sumScores[playerCounter][8] += 16;
				return 16; 
			}
			if (five == 20) { 
				sumScores[playerCounter][8] += 20;
				return 20; 
			}
			if (six == 24) { 
				sumScores[playerCounter][8] += 24;
				return 24; 
			}
		}
		
		if (score == 9) {
			int[] counts = new int[6];
			boolean hasTwo = false;
			boolean hasThree = false;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				counts[updatedDiceArray[i]]++;
			}
			for (int j = 0; j < counts.length; j++) {
				if (counts[j] == 3) {
					hasThree = true;
				}
				if (counts[j] == 2) {
					hasTwo = true;
				}
			}
			
			if (hasTwo && hasThree) {
				sumScores[playerCounter][9] += 25;
				return 25;
			} else {
				return 0;
			}
		}
		
		if (score == 10) {
			boolean hasStraight = false;
			int[] counts = new int[7];
			for (int i = 0; i < updatedDiceArray.length; i++) {
				counts[updatedDiceArray[i]]++;
			}
			
			if (counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1) {
				hasStraight = true;
			}
			
			if (counts[2] == 1 && counts[3] == 1 && counts[4] == 1 && counts[5] == 1) {
				hasStraight = true;
			} 
			
			if (counts[3] == 1 && counts[4] == 1 && counts[5] == 1 && counts[6] == 1) {
				hasStraight = true;
			}
		
			if (hasStraight) {
				sumScores[playerCounter][10] += 30;
				return 30;
			} else {
				return 0;
			}
		}
		
		if (score == 11) {
			boolean hasStraight = false;
			int[] counts = new int[7];
			for (int i = 0; i < updatedDiceArray.length; i++) {
				counts[updatedDiceArray[i]]++;
			}
			
			if (counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1) {
				hasStraight = true;
			}
			
			if (counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1 && counts[6] >= 1) {
				hasStraight = true;
			} 
		
			if (hasStraight) {
				sumScores[playerCounter][11] += 40;
				return 40;
			} else {
				return 0;
			}
		}
		
		if (score == 12) {
			if (updatedDiceArray[0] == updatedDiceArray[1]) {
				if (updatedDiceArray[1] == updatedDiceArray[2]) {
					if (updatedDiceArray[2] == updatedDiceArray[3]) {
						if (updatedDiceArray[3] == updatedDiceArray[4]) {
							if (updatedDiceArray[4] == updatedDiceArray[5]) {
								sumScores[playerCounter][12] += 50;
								return 50;
								}
							}
						}
					}
				} else {
					return 0;
				}
		} 
		
		if (score == 13) {
			int sum = 0;
			for (int i = 0; i < updatedDiceArray.length; i++) {
				sum += updatedDiceArray[i];
				sumScores[playerCounter][13] += sum;
			}
			
			return sum;
		}
		
		//return 0 to satisfy the method call
		return 0;
	}
	
	private void updateScore() {
		int upperSum = 0;
		int lowerSum = 0;
		int totalSum = 0;
		
		for (int i = 1; i <= 6; i++) {
			upperSum += sumScores[playerCounter][i];
		}
		
		if (upperSum >= 63) {
			totalSum += 35;
			int upperBonus = 35;
			display.updateScorecard(UPPER_BONUS, playerCounter, upperBonus);
		}
		
		for (int j = 7; j <= 13; j++) {
			lowerSum += sumScores[playerCounter][j];
		}
		
		for (int x = 0; x <= 13; x++) {
			totalSum += sumScores[playerCounter][x];
		}
		
		display.updateScorecard(UPPER_SCORE, playerCounter, upperSum);
		display.updateScorecard(LOWER_SCORE, playerCounter, lowerSum);
		display.updateScorecard(TOTAL, playerCounter, totalSum);
	}
		
/* Private instance variables */
	private int nPlayers;
	private int[] diceArray = new int[N_DICE];
	private int[] updatedDiceArray = new int[N_DICE];
	private int[][] sumScores = new int[nPlayers + 1][N_SCORING_CATEGORIES + 1];
	private int playerCounter = 1;
	private int category;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
