/**
 * @author: Jenny Zhen
 * @name: Player.java
 * @date: 03.27.12
 */
/**
 * $Id: BadPlayer.java,v 1.6 2012-04-18 03:38:58 jxz6853 Exp $
 * $Revision: 1.6 $
 * $Log: BadPlayer.java,v $
 * Revision 1.6  2012-04-18 03:38:58  jxz6853
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
 * Revision 1.2  2012-04-08 06:22:56  jxz6853
 * To-do: checkWinner(), goodPlayer(), fix bugs, feedback.txt, readme.txt
 *
 * Revision 1.1  2012-04-08 02:34:51  jxz6853
 * Worked on all except for Connect4.java.
 *
 */
 
public class BadPlayer extends Player {
	
	/**
	 * Algorithm: Chooses a move from leftmost column to rightmost.
	 * Constructor calls the parent's constructor.
	 * @param board - board object representing the game
	 * @param playerName - char representing the player id
	 */
	public BadPlayer(Board board, char playerName) {
		super(board, playerName);
	}
	
	/**
	 * Chooses a move from the furtherest left column that isn't full and 
	 * then moves towards the right.
	 * @return an integer representing the column to place next move
	 */
	public int chooseMove() {
		for(int i = 0; i < board.numCols(); i++) {
			for(int j = 0; j < board.numRows(); j++) {
				if(board.getPieceAt(i, j) == ' ')
					return i;
			}
		}
		return -1;
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