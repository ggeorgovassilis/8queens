package eightqueens.naive;

import eightqueens.AbstractBoard;

public class Board extends AbstractBoard{

	private final boolean[][] board = new boolean[8][8];
	
	public Board(){
		super();
	}
	
	@Override
	public void set(int row, int column, boolean occupied){
		board[row][column] = occupied;
	}
	
	@Override
	public boolean get(int row, int column){
		return board[row][column];
	}
	
}
