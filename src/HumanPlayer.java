/**
 * @author: Jenny Zhen
 * @name: HumanPlayer.java
 * @date: 03.27.12
 */
/**
 * $Id: HumanPlayer.java,v 1.6 2012-04-18 03:38:59 jxz6853 Exp $
 * $Revision: 1.6 $
 * $Log: HumanPlayer.java,v $
 * Revision 1.6  2012-04-18 03:38:59  jxz6853
 * All complete.
 *
 * Revision 1.5  2012-04-17 03:53:02  jxz6853
 * Everything fixed except for blocking.
 *
 * Revision 1.4  2012-04-15 07:15:21  jxz6853
 * Check winner works. When trying to place after an invalid, it doesn't work. Missing goodPlayer.
 *
 * Revision 1.3  2012-04-09 03:13:23  jxz6853
 * Worked on checking for a winner.
 *
 * Revision 1.2  2012-04-08 06:22:57  jxz6853
 * To-do: checkWinner(), goodPlayer(), fix bugs, feedback.txt, readme.txt
 *
 * Revision 1.1  2012-04-08 02:34:51  jxz6853
 * Worked on all except for Connect4.java.
 *
 */

import java.util.Scanner;

public class HumanPlayer extends Player {
	private Scanner input; //scans in the moves from input
	
	/**
	 * Algorithm: Chooses a move based on human input.
	 * Constructor calls the parent's constructor and sets the scanner.
	 * @param board - board object representing the game
	 * @param playerName - char representing the player id
	 * @param input - the scanner used to take in user input
	 */
	public HumanPlayer(Board board, char playerName, Scanner input) {
		super(board, playerName);
		this.input = input;
	}

	/**
	 * Chooses a move based on human input, verifies the validity, and 
	 * returns it.
	 * @return an integer representing the column to place next move
	 */
	public int chooseMove() {
		int column;
		System.out.print(
				"Player " + super.getPlayerName() 
				+ ": Enter the column to drop your piece (-1 to quit): ");
		column = input.nextInt();
		if(!super.board.isValidMove(column) && column != -1) {
			System.out.println("invalid column: " + column);
			return -2;
		}
		return column;
	}
	
	/**
	 Attempts to play the move that the player chose.
	 * @return true if tile was able to be placed
	 */
	public boolean play(){
		int col;
		do{
			col = chooseMove();
		}while(col != -1 && !board.isValidMove(col));
		
		if(col == -1){
			return false;
		}
		board.makeMove(col, getPlayerName());
		System.out.println(printMove(col));
		return true;
	}
}