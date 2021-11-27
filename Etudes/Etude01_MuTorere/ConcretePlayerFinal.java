/**
 * Submission for Etude 1, Mu Torere
 * Freeman Eckles (2522699)
 * Leo Venn (3430125)
 * Maaha Ahmad (3759740)
 * Valentine Kiselev (5844920)
 */

package MuTorere;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

class ConcretePlayer extends Player {
	
	private Random rng;

	//losing positions for black
	private ArrayList<Board.Piece> B1 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> B2 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.TWO }));
	private ArrayList<Board.Piece> B3 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> B4 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> B5 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> B6 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> B7 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO }));
	
	//losing positions for white
	private ArrayList<Board.Piece> W1 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.ONE }));
	private ArrayList<Board.Piece> W2 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> W3 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> W4 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.TWO }));
	private ArrayList<Board.Piece> W5 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> W6 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO }));
	private ArrayList<Board.Piece> W7 = new ArrayList<Board.Piece>(Arrays.asList(new Board.Piece[]{Board.Piece.BLANK, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.ONE, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO, Board.Piece.TWO }));
	
	//collection to iterate through losing positions
	private ArrayList<ArrayList<Board.Piece>> loseList = new ArrayList<ArrayList<Board.Piece>>();
	private ArrayList<ArrayList<Board.Piece>> loseList2 = new ArrayList<ArrayList<Board.Piece>>();
	
	public ConcretePlayer(BoardReader boardReader, Board.Piece playerID) {
		super(boardReader, playerID);
		rng = new Random();
	}
	
	/**
	* The getMove method examines possible losing positions when choosing a move to make.
	* It adds the losing positions to an array list and rotates and reflects them, in order to compare the current game 
	* state with these positions.
	* It then chooses any move that is not known to result in a losing position
	* It does this for both players so that different moves can be taken for player one/two
	*/
	public int getMove() {
		ArrayList<Board.Piece> boardState = new ArrayList<Board.Piece>(getBoardState());
		ArrayList<Integer> validMoves = new ArrayList<Integer>();
		ArrayList<Integer> validMovesDest = new ArrayList<Integer>();
		
		//add all losing positions to array list
		loseList.add(B1);
		loseList.add(B2);
		loseList.add(B3);
		loseList.add(B4);
		loseList.add(B5);
		loseList.add(B6);
		loseList.add(B7);
		
		loseList2.add(W1);
		loseList2.add(W2);
		loseList2.add(W3);
		loseList2.add(W4);
		loseList2.add(W5);
		loseList2.add(W6);
		loseList2.add(W7);
		
		//determine all valid moves in position and the destination of the moved piece
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (isValid(i,j)) {
					validMoves.add(i);
					validMovesDest.add(j);
					continue;
				}
			}
		}
		if (validMoves.isEmpty()) {
			return 0;
		}
		
		//if we are player one
		if(playerID == Board.Piece.ONE){
			int willLose = 0; //int boolean to track if we found a losing position
			Collections.shuffle(validMoves); //shuffle collection of valid moves - ideally this means avoid taking the same move many times in a row
			for(int j = 0; j < validMoves.size(); j++) {
				Collections.copy(boardState, getBoardState()); //copy the board state so that we can manipulate it
				Collections.swap(boardState, validMoves.get(j), validMovesDest.get(j)); //swap the pieces on the board as specified in valid moves
				boardState.remove(boardState.size() -1); //ignore center for simpler symmetry checking
				
				for(int k = 0; k < loseList.size(); k++){
					for(int i = 0; i < 8; i++){
						//compare possible board states to known losing positions - and avoid them whenever possible
						//use modulus to examine rotational symmetry - increment pieces positions to simulate clockwise rotation
						if(boardState.get(i % 8) == loseList.get(k).get(0) && boardState.get((i + 1) % 8) == loseList.get(k).get(1) &&
							boardState.get((i + 2) % 8) == loseList.get(k).get(2) && boardState.get((i + 3) % 8) == loseList.get(k).get(3) &&
							boardState.get((i + 4) % 8) == loseList.get(k).get(4) && boardState.get((i + 5) % 8) == loseList.get(k).get(5) &&
							boardState.get((i + 6) % 8) == loseList.get(k).get(6) && boardState.get((i + 7) % 8) == loseList.get(k).get(7)) {
							
							//if this position exists, set boolean to true...
							willLose = 1;

							//...and choose a move that does not result in the losing position - just prefer any move but the found losing move 
							if(j ==0) { //if we found a losing position in the first move, just choose the next possible move
								return validMoves.get(j + 1);
							}
							else { 	// if we have examined moves and found they do not result in a losing position, take one of those
								return validMoves.get(j - 1);
							}
							
						}

						//also check reflected positions - examining pieces anticlockwise
						else if(boardState.get(i % 8) == loseList.get(k).get(0) && boardState.get((i + 7) % 8) == loseList.get(k).get(1) && 
							boardState.get((i +6) % 8) == loseList.get(k).get(2) && boardState.get((i +5) % 8) == loseList.get(k).get(3) &&
							boardState.get((i +4) % 8) == loseList.get(k).get(4) && boardState.get((i +3) % 8) == loseList.get(k).get(5) && 
							boardState.get((i +2) % 8) == loseList.get(k).get(6) && boardState.get((i +1) % 8) == loseList.get(k).get(7)) {
						
							willLose = 1; //if a loss found, choose any other move
							if(j ==0){
								return validMoves.get(j + 1);
							}else{
								return validMoves.get(j - 1);
							}
						}
					}
				}

				//if no losing position found, just take some move
				if(willLose == 0) {
					return validMoves.get(j);
				} 
				//reset var for next loop
				willLose = 0;
			}
		}
		
		//check the corresponding board positions if we are player two
		if(playerID == Board.Piece.TWO) {
			int willLose = 0;
			Collections.shuffle(validMoves);
			for(int j = 0; j < validMoves.size(); j++){
				Collections.copy(boardState, getBoardState());
				Collections.swap(boardState, validMoves.get(j), validMovesDest.get(j));
				boardState.remove(boardState.size() -1);
				for(int k = 0; k < loseList2.size(); k++){
					for(int i = 0; i < 8; i++){
						if(boardState.get(i % 8) == loseList2.get(k).get(0) && boardState.get((i + 1) % 8) == loseList2.get(k).get(1) && 
						boardState.get((i + 2) % 8) == loseList2.get(k).get(2) && boardState.get((i + 3) % 8) == loseList2.get(k).get(3) &&
						 boardState.get((i + 4) % 8) == loseList2.get(k).get(4) && boardState.get((i + 5) % 8) == loseList2.get(k).get(5) && 
						 boardState.get((i + 6) % 8) == loseList2.get(k).get(6) && boardState.get((i + 7) % 8) == loseList2.get(k).get(7)){
		
							willLose = 1;
							if(j ==0){
								return validMoves.get(j + 1);
							}else{
							return validMoves.get(j - 1);
							}
							
						}
					
						else if(boardState.get(i % 8) == loseList2.get(k).get(0) && boardState.get((i + 7) % 8) == loseList2.get(k).get(1) && 
							boardState.get((i +6) % 8) == loseList2.get(k).get(2) && boardState.get((i +5) % 8) == loseList2.get(k).get(3) &&
						 	boardState.get((i +4) % 8) == loseList2.get(k).get(4) && boardState.get((i +3) % 8) == loseList2.get(k).get(5) &&
						 	boardState.get((i +2) % 8) == loseList2.get(k).get(6) && boardState.get((i +1) % 8) == loseList2.get(k).get(7)){
			
							willLose = 1;
							if(j ==0){
								return validMoves.get(j + 1);
							}else{
								return validMoves.get(j - 1);
							}
						} 
					}
				}
		
				if(willLose == 0) {
					return validMoves.get(j);
				}
				willLose = 0;
			}
		}
		return validMoves.get(rng.nextInt(validMoves.size()));
	}
	
	
	public ArrayList<Board.Piece> getBoardState() {
		ArrayList<Board.Piece> boardState = new ArrayList<Board.Piece>();
		for (int i = 0; i < 9; ++i) {
			boardState.add(boardReader.pieceAt(i));
			if(boardReader.pieceAt(i) == Board.Piece.ONE){
				
			}
		}
		return boardState;
	}
	
	
	boolean isValid(int moveFrom, int moveTo) {	
		
		if (boardReader.pieceAt(moveTo) != Board.Piece.BLANK) {
			return false;
		}
		
		if (boardReader.pieceAt(moveFrom) != playerID) {
			return false;
		}
		if (moveTo == 8) {
			// Move to centre, check for valid neighbour
			int prev = moveFrom - 1;
			if (prev < 0) prev = 7;
			int next = moveFrom + 1;
			if (next > 7) next = 0;
			if (boardReader.pieceAt(prev) == playerID && boardReader.pieceAt(next) == playerID) {
				return false;
			}
		} else {
			// Either move from centre to kewai...
			if (moveFrom == 8) {
				return true;
			}
			// ... or from one kewai to next, make sure they are neighbours
			int prev = moveFrom - 1;
			if (prev < 0) prev = 7;
			int next = moveFrom + 1;
			if (next > 7) next = 0;
			if (boardReader.pieceAt(prev) != Board.Piece.BLANK &&
				boardReader.pieceAt(next) != Board.Piece.BLANK) {
				return false;
			}					
		}
		return true;
	}
	
}