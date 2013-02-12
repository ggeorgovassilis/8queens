package queens;

import org.junit.Before;
import static junit.framework.Assert.*;

public class TestQueens {

	final int warmUpRounds = 3;
	final int rounds = 1;
	
	Solver solver;
	
	@Before
	public void setup(){
	}
	
	boolean isPieceAt(int row, int column, Position[] positions){
		for (Position p:positions)
			if (p.getRow() == row && p.getColumn() == column)
				return true;
		return false;
	}
	
	void verify(Position[] positions){
		assertEquals(8, positions.length);
		for (int i=0;i<positions.length-1;i++)
			for (int j=i+1;j<positions.length;j++){
				Position posi = positions[i];
				Position posj = positions[i];
				assertFalse(posi.getRow()==posj.getRow());
				assertFalse(posi.getColumn()==posj.getColumn());
				assertFalse(posi.getRow()==posj.getRow());
			}
	}
	
	public void test(){
		for (int i=0;i<warmUpRounds;i++)
			solver.getQueenPositions();
		
		long timestamp = -System.currentTimeMillis();
		Position[] positions = null;
		for (int i=0;i<rounds;i++){
			positions = solver.getQueenPositions();
		}
		timestamp+=System.currentTimeMillis();
		verify(positions);
		System.out.println("Solved in "+timestamp+" ms");
	}
}
