/**
 * @author: Jenny Zhen
 * @name: RandomPlayer.java
 * @date: 03.27.12
 */
/**
 * $Id: RandomPlayer.java,v 1.7 2012-04-18 03:38:58 jxz6853 Exp $
 * $Revision: 1.7 $
 * $Log: RandomPlayer.java,v $
 * Revision 1.7  2012-04-18 03:38:58  jxz6853
 * All complete.
 *
 * Revision 1.6  2012-04-18 00:55:01  jxz6853
 * Fixing output.
 *
 * Revision 1.5  2012-04-18 00:42:54  jxz6853
 * Fixing output.
 *
 * Revision 1.4  2012-04-18 00:32:44  jxz6853
 * Completed...
 *
 * Revision 1.3  2012-04-15 07:15:21  jxz6853
 * Check winner works. When trying to place after an invalid, it doesn't work. Missing goodPlayer.
 *
 * Revision 1.2  2012-04-08 06:22:56  jxz6853
 * To-do: checkWinner(), goodPlayer(), fix bugs, feedback.txt, readme.txt
 *
 * Revision 1.1  2012-04-08 02:34:51  jxz6853
 * Worked on all except for Connect4.java.
 *
 */

import java.util.Random;

public class RandomPlayer extends Player {
	/**
	 * Algorithm: Chooses a random column from 0 to 7.
	 * Constructor calls the parent's constructor.
	 * @param board - board object representing the game
	 * @param playerName - char representing the player id
	 */
	public RandomPlayer(Board board, char playerName) {
		super(board, playerName);
	}
	
	/**
	 * Chooses the move as a random column.
	 * @return an integer representing the column to place next move
	 */
	public int chooseMove() {
		Random val = new Random();
		return val.nextInt(super.board.numCols() + 1);
	}
	
	/**
	 Attempts to play the move that the player chose.
	 * @return true if tile was able to be placed
	 */
	public boolean play(){
		int col;
		do{
			col = chooseMove();
		}while(!board.isValidMove(col));
		board.makeMove(col, getPlayerName());
		System.out.println(printMove(col));
		return true;
	}
}
