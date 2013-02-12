package eightqueens.bitpack;

import eightqueens.AbstractBoard;

public class Board extends AbstractBoard{

	private long bitmap;
	
	public void set(int row, int column, boolean occupied){
		if (occupied)
			bitmap = BitPackSolver.set(bitmap, row, column);
		else
			bitmap = BitPackSolver.unset(bitmap, row, column);
	}
	
	public boolean get(int row, int column){
		return BitPackSolver.getBit(bitmap, row, column);
	}
	
	public Board(long bitmap){
		this.bitmap = bitmap;
	}
	
}
