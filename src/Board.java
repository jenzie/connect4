/**
 * @author: Jenny Zhen
 * @name: Board.java
 * @date: 03.27.12
 */
/**
 * $Id: Board.java,v 1.6 2012-04-18 03:38:59 jxz6853 Exp $
 * $Revision: 1.6 $
 * $Log: Board.java,v $
 * Revision 1.6  2012-04-18 03:38:59  jxz6853
 * All complete.
 *
 * Revision 1.5  2012-04-17 03:53:02  jxz6853
 * Everything fixed except for blocking.
 *
 * Revision 1.4  2012-04-15 07:15:22  jxz6853
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

import java.util.ArrayList;

public class Board {
	private int numRows; //number of columns
	private int numCols; //number of rows
	private int lastCol; //last column a move was made
	private int lastRow; //last row a move was made
	private int actualSize; //number of moves made
	private ArrayList<ArrayList<Character>> board; //represents the game
	
	/**
	 * Constructor that stores all the board information for both players.
	 * @param numRows - the number of rows in the board
	 * @param numCols - the number of columns in the board
	 */
	public Board(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.lastRow = -1;
		this.lastCol = -1;
		this.actualSize = 0;
		board = new ArrayList<ArrayList<Character>>();
		ArrayList<Character> list;
		for(int i = 0; i <= numCols; i++) {
			list = new ArrayList<Character>();
			board.add(list);
			for(int j = 0; j <= numRows; j++)
				list.add(' ');
		}
	}
	
	/**
	 * Returns the piece at the coordinates, either an 'X' or 'O'.
	 * @param col - the column to search
	 * @param row - the row to search
	 * @return char representing player who made the move there
	 */
	public char getPieceAt(int col, int row){
		return board.get(col).get(row);
	}
	
	/**
	 * Checks whether or not a move is valid based on the range 
	 * and whether or not that move was made by someone already.
	 * @param column - the column to search
	 * @return true if the move is valid
	 */
	public boolean isValidMove(int column) {
		if(column >= 0 && column < numCols) {
			for(int i = 0; i < numRows; i++) {
				if(board.get(column).get(i) == ' ')
					return true;
			}
		}return false;
	}
	
	/** 
	 * Makes the chosen move on the board object.
	 * @param column - the column to place move at
	 * @param playerName - the player who made the move
	 */
	public void makeMove(int column, char playerName) {
		for(int i = 0; i < numRows; i++) {
			if(board.get(column).get(i) == ' '){
				board.get(column).set(i, playerName);
				lastCol = column;
				lastRow = i;
				actualSize += 1;
				return;
			}
		}
	}
	
	/**
	 * String representation of the board.
	 * @return a string representing the game.
	 */
	public String toString() {
		String result = "";
		for(int i = numRows - 1; i >= 0; i--) {
			for(int j = 0; j < numCols; j++) {
				result += "|";
				result += board.get(j).get(i).toString();
				if(j == numCols - 1)
					result += "|";
			}result += "\n";
		}
		for(int k = 0; k < numCols; k++)
			result += "+-";
		return result +"+\n";
	}
	
	/**
	 * Checks if the coordinates are within the board.
	 * @param col - the column to check
	 * @param row - the row to check
	 * @return true if the coordinates are valid
	 */
	public boolean validRange(int col, int row){
		return (col >= 0 && col < numCols) && (row >= 0 && row < numRows);
	}
	
	/**
	 * @return the number of columns in the board
	 */
	public int numCols() {
		return numCols;
	}
	
	/**
	 * @return the number of rows in the board
	 */
	public int numRows() {
		return numRows;
	}
	
	/**
	 * @return the last column in the board
	 */
	public int lastCol() {
		return lastCol;
	}
	
	/**
	 * @return the last row in the board
	 */
	public int lastRow() {
		return lastRow;
	}
	
	/**
	 * @return the number of total moves made
	 */
	public int actualSize() {
		return actualSize;
	}
}
