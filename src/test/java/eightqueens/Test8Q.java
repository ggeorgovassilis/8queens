package eightqueens;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eightqueens.AbstractBoard;
import eightqueens.ISolver8Q;
import eightqueens.IVerifier;
import eightqueens.bitpack.BitPackSolver;
import eightqueens.list.ListSolver;
import eightqueens.naive.Board;
import eightqueens.naive.SimpleSolver;
import eightqueens.naive.SimpleVerifier;

public class Test8Q {
	
	ISolver8Q solver;
	IVerifier verifier;
	//100 rounds are ok for ListSolver and BitPackSolver, reduce for the slower SimpleSolver
	final int rounds = 100;
	
	@Before
	public void setup(){
		//simple solver 1224ms / round
		//bitpack solver 186ms / round
		//listsolver 114ms / round
		
		solver = new ListSolver();
//		solver = new SimpleSolver();
//		solver = new BitPackSolver();
		verifier = new SimpleVerifier();
	}
	
	@Test
	public void testVerifier(){
		AbstractBoard abstractBoard = new Board();
		abstractBoard.set((byte)0, (byte)1, true);
		abstractBoard.set((byte)1, (byte)3, true);
		abstractBoard.set((byte)2, (byte)5, true);
		abstractBoard.set((byte)3, (byte)7, true);
		abstractBoard.set((byte)4, (byte)2, true);
		abstractBoard.set((byte)5, (byte)0, true);
		abstractBoard.set((byte)6, (byte)6, true);
		abstractBoard.set((byte)7, (byte)4, true);
		assertTrue(verifier.isValidSolution(abstractBoard));
		
		abstractBoard.set((byte)3, (byte)7, false);
		abstractBoard.set((byte)4, (byte)2, false);
		assertFalse(verifier.isValidSolution(abstractBoard));
		
		abstractBoard.set((byte)4, (byte)7, true);
		abstractBoard.set((byte)3, (byte)2, true);
		assertFalse(verifier.isValidSolution(abstractBoard));
	}
	
	@Test
	public void findMissingBoards(){
		BitPackSolver bps = new BitPackSolver();
		SimpleSolver ss = new SimpleSolver();
		List<AbstractBoard> list1 = bps.solve();
		List<AbstractBoard> list2 = ss.solve();
		for (AbstractBoard b1:list2)
			if (!list1.contains(b1))
				System.out.println("Didn't find board\n"+b1);
	}

	@Test
	public void testPerformance() {
		long timestamp = -System.currentTimeMillis();
		List<AbstractBoard> solutions = null;
		for (int round=0;round<rounds;round++){
			solutions = solver.solve();
			assertEquals(92, solutions.size());
		}
		timestamp+=System.currentTimeMillis();
		System.out.print((timestamp/rounds)+" ms/round");
		for (AbstractBoard abstractBoard:solutions){
			assertTrue(verifier.isValidSolution(abstractBoard));
		}
	}

}
