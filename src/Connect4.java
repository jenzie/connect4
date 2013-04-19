/**
 * @author: Jenny Zhen
 * @name: Connect4.java
 * @date: 03.27.12
 */
/**
 * $Id: Connect4.java,v 1.19 2012-04-18 03:47:48 jxz6853 Exp $
 * $Revision: 1.19 $
 * $Log: Connect4.java,v $
 * Revision 1.19  2012-04-18 03:47:48  jxz6853
 * *** empty log message ***
 *
 * Revision 1.18  2012-04-18 03:38:59  jxz6853
 * All complete.
 *
 * Revision 1.17  2012-04-18 01:47:39  jxz6853
 * Fixing output.
 *
 * Revision 1.16  2012-04-18 01:46:43  jxz6853
 * Fixing output.
 *
 * Revision 1.15  2012-04-18 01:20:44  jxz6853
 * Fixing output.
 *
 * Revision 1.14  2012-04-18 01:19:48  jxz6853
 * Fixing output.
 *
 * Revision 1.13  2012-04-18 01:18:43  jxz6853
 * Fixing output.
 *
 * Revision 1.12  2012-04-18 01:16:05  jxz6853
 * Fixing output.
 *
 * Revision 1.11  2012-04-18 01:05:16  jxz6853
 * Fixing output.
 *
 * Revision 1.10  2012-04-18 00:55:02  jxz6853
 * Fixing output.
 *
 * Revision 1.9  2012-04-18 00:41:34  jxz6853
 * Fixing output.
 *
 * Revision 1.8  2012-04-18 00:40:48  jxz6853
 * Fixing output.
 *
 * Revision 1.7  2012-04-18 00:39:46  jxz6853
 * Fixing output.
 *
 * Revision 1.6  2012-04-18 00:32:44  jxz6853
 * Completed...
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
 * Revision 1.1  2012-04-08 02:34:52  jxz6853
 * Worked on all except for Connect4.java.
 *
 */
import java.util.Scanner;

public class Connect4 {
	/**
	 * Main file to run Connect4.
	 * Win by placing 4 in a row.
	 */
	private Board board;
	private static Scanner input;
	
	/**
	 * @param args - input to run game in the format "player player 6 7" 
	 *               where player type are two choices of human, good, bad, or 
	 *               random. The last two arguments are for the optional board 
	 *               size (maximum is 6 x 7; default is 4 x 4). 
	 */
	public static void main(String[] args) {
		if(!validArgs(args))
			return;
		new Connect4(args);
	}
	
	/**
	 * Plays the game.
	 * @param args - string input used to instantiate the game.
	 */
	public Connect4(String[] args){
		
		input = new Scanner(System.in);
		String playerX = args[0]; //player-X type
		String playerO = args[1]; //player-O type
		String winner = "";
		String winType = "";
		Player X;
		Player O;
		
		if(validArgs(args)) {
			board = initBoard(args);
			X = initPlayer(playerX, board, 'X');
			O = initPlayer(playerO, board, 'O');
			
			//System.out.println("Welcome to Connect4!\n");
			System.out.println("");
			
			while(true) {
				System.out.println(board);
				System.out.println(playerX + " player X moving...");
				if(!X.play()) {
					winner = "QX"; //if X quits, stop
					break;
				}
				
				if(checkWinner(board, 'X') != "") {
					winner = "X"; //if X wins, stop
					winType = checkWinner(board, 'X');
					break;
				} else if(isTie(board))	{
					winner = "T"; //if tie, stop
					break;
				}
				System.out.println(board);
				System.out.println(playerO + " player O moving...");
				if(!O.play()) {
					winner = "QO"; //if O quits, stop
					break;
				}
				if(checkWinner(board, 'O') != "") {
					winner = "O"; //if O wins, stops
					winType = checkWinner(board, 'O');
					break;
				} else if(isTie(board))	{
					winner = "T"; //if tie, stop
					break;
				}
			}
			if(winner == "T") {
				System.out.println(board); //output for tie
				System.out.println("Its a tie, no one wins");
			} else if(winner == "X" || winner == "O") {
				System.out.println(board); //output for win
				System.out.println(declareWinner(board, winner, winType));
			} else { //output for quit
				System.out.println(winner.charAt(1) + " quits the game\n");
				System.out.println(board);
			}
		}
	}
	
	/**
	 * Checks the string arguments for validity.
	 * @param args - inputted as player1 player2 #rows #cols
	 * @return true if all arguments are valid
	 */
	private static boolean validArgs(String[] args) {
		int row = 0;
		int col = 0;
		if(args.length < 2) {
			System.err.print(
				"Usage: java Connect4 player-X player-O [#rows #cols]\n" +
				"where player-X and player-O are one of: human bad good random\n" +
				"[#rows #cols] are optional, if provided their values must be\n" +
				"in the ranges: #rows from 1 to 6 and #cols from 1 to 7\n");
			return false;
		} if(args.length == 3 || args.length > 4) {
				System.err.println("Usage: java Connect4 player-X " +
						"player-O [#rows #cols]\n where player-X and " +
						"player-O are one of: human bad good random\n" +
						"[#rows #cols] are optional, if provided their " +
						"values must be\n in the ranges: #rows from 1 to 6 " +
						"and #cols from 1 to 7");
				return false;
		} else if(args.length == 4) {
			row = Integer.parseInt(args[2]);
			col = Integer.parseInt(args[3]);
			if((row < 7 && row > 0) && (col < 8 && col > 0))
				return true;
			else {
				System.err.println("Usage: java Connect4 player-X " +
						"player-O [#rows #cols]\n where player-X and " +
						"player-O are one of: human bad good random\n" +
						"[#rows #cols] are optional, if provided their " +
						"values must be\n in the ranges: #rows from 1 to 6 " +
						"and #cols from 1 to 7");
				return false;
			}
		}
		if((args[0].matches("human") || args[0].matches("random") || 
				args[0].matches("good") || args[0].matches("bad")) && 
				(args[1].matches("human") || args[1].matches("random") || 
						args[1].matches("good") || args[1].matches("bad"))) {
			return true;
		}else{
			System.err.println("Usage: java Connect4 player-X " +
					"player-O [#rows #cols]\n where player-X and " +
					"player-O are one of: human bad good random\n" +
					"[#rows #cols] are optional, if provided their " +
					"values must be\n in the ranges: #rows from 1 to 6 " +
					"and #cols from 1 to 7");
			return false;
		}
	}
	
	/**
	 * Initializes the board based on string arguments for size, otherwise 
	 * the default is a 4 by 4 game.
	 * @param args - the size of board
	 * @return a new board object
	 */
	private Board initBoard(String[] args) {
		if(args.length > 2)
			return new Board(
				Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		else
			return new Board(4, 4);
	}
	
	/**
	 * Creates a player based on string arguments.
	 * @param arg - the player types
	 * @param board - the board instance
	 * @param playerName - player id
	 * @return a player if valid argument
	 */
	private Player initPlayer(String arg, Board board, char playerName) {
		if(arg.matches("human"))
			return new HumanPlayer(board, playerName, input);
		if(arg.matches("random"))
			return new RandomPlayer(board, playerName);
		if(arg.matches("good"))
			return new GoodPlayer(board, playerName);
		if(arg.matches("bad"))
			return new BadPlayer(board, playerName);
		else
			System.err.println("Invalid player option (" + arg + ").");
		return null;
	}
	
	/**
	 * Checks to see if anyone won the game.
	 * @param board - object representing the game
	 * @param player - player id
	 * @return direction of the win if someone won
	 */
	private String checkWinner(Board board, char player) {
		int currX;
		int currY;
		int total = 0;
		for(int moveX = -1; moveX <= 1; moveX++) { //loop columns
			for(int moveY = -1; moveY <= 0; moveY++) { //loop rows
				currX = board.lastCol(); //instantiate
				currY = board.lastRow();
				
				if((moveX == 0 && moveY == 0) || (moveX == 1 && moveY == 0))
					continue;
				
				// back up in the check
				while(board.validRange(currX, currY) //check if match
						&& board.getPieceAt(currX, currY) == player) {
					currX += moveX;
					currY += moveY;
				}
				currX -= moveX;
				currY -= moveY;
				total = 0; //reset total
				
				// move forwards in the check
				while(total < 4 && board.validRange(currX, currY) //count total
						&& board.getPieceAt(currX, currY) == player) {
					total += 1;
					currX -= moveX;
					currY -= moveY;
				}
				
				//check if won and with what direction
				if(total >= 4) {
					if(moveY == 0)
						return "row ";
					if(moveX == 0)
						return "column ";
					if(moveX != 0 && moveY != 0)
						return "diagonal";
				}
			}
		} return ""; //did not win
	}
	
	/**
	 * Check to see if there is a tie by checking if the board is full.
	 * @param board - object representing the game
	 * @return true if there is a tie
	 */
	private boolean isTie(Board board) {
		return (board.actualSize() == (board.numCols() * board.numRows()));
	}
	
	/**
	 * Declare the player who won the game, type of win, and/or where.
	 * @param board - object representing the game
	 * @param winner - the player who won the game
	 * @param winType - direction: column, row, diagonal
	 * @return string representing the win
	 */
	private String declareWinner(Board board, String winner, String winType) {
		int lastRow = ((board.numRows() - 1) - board.lastRow());
		if(winType == "column ")
			return winner + " won in " + winType + board.lastCol();
		if(winType == "row ")
			return winner + " won in " + winType + lastRow;
		if(winType == "diagonal")
			return winner + " won on a diagonal";
		return "";
	}
	
}
