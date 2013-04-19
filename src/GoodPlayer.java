/**
 * @author: Jenny Zhen
 * @name: GoodPlayer.java
 * @date: 03.27.12
 */
/**
 * $Id: GoodPlayer.java,v 1.6 2012-04-18 03:38:59 jxz6853 Exp $
 * $Revision: 1.6 $
 * $Log: GoodPlayer.java,v $
 * Revision 1.6  2012-04-18 03:38:59  jxz6853
 * All complete.
 *
 * Revision 1.5  2012-04-18 00:32:44  jxz6853
 * Completed...
 *
 * Revision 1.4  2012-04-17 03:53:02  jxz6853
 * Everything fixed except for blocking.
 *
 * Revision 1.3  2012-04-15 07:15:21  jxz6853
 * Check winner works. When trying to place after an invalid, it doesn't work. Missing goodPlayer.
 *
 * Revision 1.2  2012-04-08 06:22:57  jxz6853
 * To-do: checkWinner(), goodPlayer(), fix bugs, feedback.txt, readme.txt
 *
 * Revision 1.1  2012-04-08 02:34:51  jxz6853
 * Worked on all except for Connect4.java.
 *
 */
import java.util.Random;
import java.util.ArrayList;

public class GoodPlayer extends Player {
	private static final int MAXDIRECTIONS = 5; //directions to check
	
	/**
	 * Algorithm: Chooses a move based on the best number of chained tiles 
	 * that could be made.
	 * Constructor calls the parent's constructor.
	 * @param board - board object representing the game
	 * @param playerName - char representing the player id
	 */
	public GoodPlayer(Board board, char playerName) {
		super(board, playerName);
	}
	
	/**
	 * Attempts to play the move that the player chose.
	 * Tries to get a winning or blockable move, primarily.
	 * @return true if tile was able to be placed
	 */
	public boolean play() {
		int column = 0;
		Random val = new Random();
		
		column = chooseMove();
		while(!board.isValidMove(column))
			column = val.nextInt(super.board.numCols() + 1);
		board.makeMove(column, getPlayerName());
		System.out.println(printMove(column));
		return true;
	}
	
	/**
	 * Attempts to choose a winning move, or move that blocks the opponent 
	 * from a possible winning move.
	* @return an integer representing the column to place next move
	 */
	public int chooseMove() {
		return computeMove(getMoves());
	}
	
	/**
	 * Returns the number of tiles that have the same player id in a chain in 
	 * the column by checking all directions (directly below, diagonally left, 
	 * diagonally right, left, and right).
	 * @param column column to check for chain
	 * @param player player id to check against
	 * @return number of chains in that column.
	 */
	private Integer[] hasChain(int column, char player) {
		Integer[] chainsInCol = new Integer[MAXDIRECTIONS];
		int index = 0;
		int sum = 0;
		int row = -1;
		int currRow = -1;
		int currCol = -1;
		
		//get last row for this column
		for(int i = 0; i < board.numRows(); i++) {
			if(board.getPieceAt(column, i + 1) == ' ') {
				row = i; //never changes
				break;
			}
		}
		
		//on a row
		//from the left
		if(column != 0) { //can't move left if on column 0
			currCol = column; //set
			while(board.validRange(currCol - 1, row) && 
					board.getPieceAt(currCol - 1, row) == player) {
				sum += 1;
				currCol -= 1;
			} chainsInCol[index] = sum; //add chain value to list
			index += 1; //increment index
		}
		
		//from the right
		if(column < board.numCols()) { //can't move past max column
			sum = 0; //reset
			currCol = column; //reset
			while(board.validRange(currCol + 1, row) && 
					board.getPieceAt(currCol + 1, row) == player) {
				sum += 1;
				currCol += 1;
			} chainsInCol[index] = sum; //add chain value to list
			index += 1; //increment index
		}
		
		//on a column
		if(row > 0) {
			sum = 0;
			currRow = row;
			currCol = column;
			while(board.validRange(currCol, currRow) && 
					board.getPieceAt(currCol, currRow) == player) {
				sum += 1;
				currRow -= 1;
			} chainsInCol[index] = sum; //add chain value to list
			index += 1; //increment index
		}
		
		//on a diagonal
		//from the left
		if(column > 0 && row > 0) {
			sum = 0;
			currRow = row;
			currCol = column;
			while(board.validRange(currCol, currRow) && 
					board.getPieceAt(currCol, currRow) == player) {
				sum += 1;
				currCol -= 1;
				currRow -= 1;
			} chainsInCol[index] = sum; //add chain value to list
			index += 1; //increment index
		}
		
		//from the right
		if(column < board.numCols() && row < board.numRows()) {
			sum = 0;
			currRow = row;
			currCol = column;
			while(board.validRange(currCol, currRow) && 
					board.getPieceAt(currCol, currRow) == player) {
				sum += 1;
				currCol -= 1;
				currRow += 1;
			} chainsInCol[index] = sum; //add chain value to list
			index += 1; //increment index
		}
		return chainsInCol;
	}
	
	/**
	 * Checks to see if an open space can allow the opponent to win.
	 * @param column - column to check
	 * @param player - player char to match for
	 * @return a value representing the number of chains
	 */
	private int getLeftAndRight(int column, char player) {
		int spaceRow = -1;
		int currCol;
		int leftSum = 0;
		int rightSum = 0;
		
		//get the first space in the column, bottom up
		for(int i = 0; i < board.numRows(); i++) {
			if(board.getPieceAt(column, i) == ' ') {
				spaceRow = i;
				break;
			}
		}
		
		//check the left of the space
		if(column >= 0) {
			currCol = column; //set
			while(board.validRange(currCol - 1, spaceRow) && 
					board.getPieceAt(currCol - 1, spaceRow) == player) {
				leftSum += 1;
				currCol -= 1;
			} 
		} //check the right of the space
		if(column < board.numCols()) {
			currCol = column; //reset
			while(board.validRange(currCol + 1, spaceRow) && 
					board.getPieceAt(currCol + 1, spaceRow) == player) {
				rightSum += 1;
				currCol += 1;
			}
		}
		return (leftSum + rightSum); //return total chains
	}
	
	/**
	 * Checks to see if a move made diagonally right could form a chain.
	 * @param column - the column to search
	 * @param player - the player id to match
	 * @return the value representing the number of chains
	 */
	private int rightDiagonals(int column, char player) {
		int spaceRow = -1; //row where space is
		int currRow;
		int currCol;
		int leftSum = 0; //left side
		int rightSum = 0; //right side
		
		//find the last row where a move was made on the column
		for(int i = 0; i < board.numRows(); i++) {
			if(board.getPieceAt(column, i) == ' ') {
				spaceRow = i;
				break;
			}
		}
		
		//check the left
		if(column > 0 && spaceRow < board.numRows()) {
			currCol = column; //set
			currRow = spaceRow;
			while(board.validRange(currCol - 1, currRow + 1) && 
					board.getPieceAt(currCol - 1, currRow + 1) == player) {
				leftSum += 1;
				currCol -= 1;
				currRow += 1;
			}
		} //check the right
		if(column < board.numCols() && spaceRow > 0) {
			currCol = column; //reset
			currRow = spaceRow;
			while(board.validRange(currCol + 1, currRow - 1) && 
					board.getPieceAt(currCol + 1, currRow - 1) == player) {
				rightSum += 1;
				currCol += 1;
				currRow -= 1;
			}
		} return (leftSum + rightSum); //total chains
	}
	
	/**
	 * Checks to see if a move made diagonally left could form a chain.
	 * @param column - the column to search
	 * @param player - the player id to match
	 * @return the value representing the number of chains
	 */
	private int leftDiagonals(int column, char player) {
		int spaceRow = -1;
		int currRow;
		int currCol;
		int leftSum = 0; //left side
		int rightSum = 0; //right side
		
		//find the last row where a move was made on the column
		for(int i = 0; i < board.numRows(); i++) {
			if(board.getPieceAt(column, i) == ' ') {
				spaceRow = i;
				break;
			}
		}
		
		//check left
		if(column > 0 && spaceRow < board.numRows()) {
			currCol = column; //set
			currRow = spaceRow;
			while(board.validRange(currCol - 1, currRow + 1) && 
					board.getPieceAt(currCol - 1, currRow + 1) == player) {
				leftSum += 1;
				currCol -= 1;
				currRow += 1;
			}
		} //check right
		if(column < board.numCols() && spaceRow > 0) {
			currCol = column; //reset
			currRow = spaceRow;
			while(board.validRange(currCol + 1, currRow - 1) && 
					board.getPieceAt(currCol + 1, currRow - 1) == player) {
				rightSum += 1;
				currCol += 1;
				currRow -= 1;
			}
		} return (leftSum + rightSum); //total chains
	}
	
	/**
	 * Gets all the possible efficient moves that could be made along with 
	 * their weights.
	 * @return array list of moves and their values
	 */
	private ArrayList<Integer[]> getMoves() {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		Integer[] move;
		Integer[] moveL;
		Integer[] moveR;
		Integer[] sum;
		int index = 0;
		char playerID = super.getPlayerName();
		char otherID = 'X';
		
		//set the other player's id
		if(playerID == 'X')
			otherID = 'O';
		
		for(int i = 0; i < board.numCols(); i++) {
			move = new Integer[2];
			moves.add(move);
		}
		
		//checking for the chains
		for(int column = 0; column < board.numCols(); column++) {
			sum = hasChain(column, playerID); //chain to win
			if(sum[0] != null) {
				while(index < sum.length && sum[index] != null) {
					move = new Integer[2];
					move[0] = column; //add move column
					move[1] = sum[index]; //add weight
					moves.add(move); //add move to list
					index += 1;
				} index = 0;
			}
			sum = hasChain(column, otherID); //chain to block
			if(sum[0] != null) {
				while(index < sum.length && sum[index] != null) {
					move = new Integer[2];
					move[0] = column;
					move[1] = sum[index] + 1;
					moves.add(move);
					index += 1;
				} index = 0;
			}
		}
		for(int column = 0; column < board.numCols(); column++) {
			move = new Integer[2];
			move[0] = column;
			move[1] = getLeftAndRight(column, playerID); //chain to win
			moves.add(move);
		}
		for(int column = 0; column < board.numCols(); column++) {
			move = new Integer[2];
			move[0] = column;
			move[1] = getLeftAndRight(column, otherID) + 1; //chain to block
			moves.add(move);
		}
		for(int column = 0; column < board.numCols(); column++) {
			moveL = new Integer[2];
			moveL[0] = column;
			moveL[1] = leftDiagonals(column, playerID); //chain to win
			moves.add(moveL);
			moveR = new Integer[2];
			moveR[0] = column;
			moveR[1] = rightDiagonals(column, playerID); //chain to win
			moves.add(moveR);
		}
		for(int column = 0; column < board.numCols(); column++) {
			moveL = new Integer[2];
			moveL[0] = column;
			moveL[1] = leftDiagonals(column, otherID) + 1; //chain to block
			moves.add(moveL);
			moveR = new Integer[2];
			moveR[0] = column;
			moveR[1] = rightDiagonals(column, otherID) + 1; //chain to block
			moves.add(moveR);
		}
		return moves; //list of all efficient moves
	}
	
	/**
	 * Check the weight of all efficient moves and pick the best.
	 * @param moves - array list of all efficient moves
	 * @return column representing best move to make
	 */
	private int computeMove(ArrayList<Integer[]> moves) {
		int column = -1;
		int columnVal = 0;
		Integer[] currMove; //loop all and check weights
		for(int i = 0; i < moves.size(); i++) {
			currMove = moves.get(i);
			if(currMove[1] != null && (currMove[1] >= columnVal)) {
				columnVal = currMove[1];
				column = currMove[0];
			}
		} return column; //column to make move on
	}
}