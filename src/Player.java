/**
 * @author: Jenny Zhen
 * @name: Player.java
 * @date: 03.27.12
 */
/**
 * $Id: Player.java,v 1.5 2012-04-18 03:38:59 jxz6853 Exp $
 * $Revision: 1.5 $
 * $Log: Player.java,v $
 * Revision 1.5  2012-04-18 03:38:59  jxz6853
 * All complete.
 *
 * Revision 1.4  2012-04-17 03:53:02  jxz6853
 * Everything fixed except for blocking.
 *
 * Revision 1.3  2012-04-15 07:15:22  jxz6853
 * Check winner works. When trying to place after an invalid, it doesn't work. Missing goodPlayer.
 *
 * Revision 1.2  2012-04-08 06:22:57  jxz6853
 * To-do: checkWinner(), goodPlayer(), fix bugs, feedback.txt, readme.txt
 *
 * Revision 1.1  2012-04-08 02:34:52  jxz6853
 * Worked on all except for Connect4.java.
 *
 */

public abstract class Player {
	/**
	 * Is used by HumanPlayer, GoodPlayer, BadPlayer, RandomPlayer to link 
	 * the classes together. 
	 */
	public Board board; //the board object representing the game
	private char playerName; //either an 'X' or an 'O'
	
	/**
	 * Constructor for Player.
	 * @param board - object representing the game
	 * @param playerName - char representing the player id
	 */
	public Player(Board board, char playerName) {
		this.board = board;
		this.playerName = playerName;
	}
	
	/**
	 * The column that the player chooses to move.
	 * @return an integer representing the column
	 */
	public abstract int chooseMove();
	
	/**
	 * Attempts to play the move that the player chose.
	 * @return true if tile was able to be placed
	 */
	public abstract boolean play();
	
	/**
	 * Returns the player id.
	 * @return char representing the player, 'X' or 'O'
	 */
	public char getPlayerName(){
		return playerName;
	}
	
	/**
	 * Prints the move that the player made.
	 * @param column - column that the move was placed.
	 * @return a string representing the move made
	 */
	public String printMove(int column) {
		return "Player drops an " + playerName + " piece into column: " +
				column + "\n";
	}
}
