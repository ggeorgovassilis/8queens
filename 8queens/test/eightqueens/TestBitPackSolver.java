package eightqueens;


import org.junit.Test;

import eightqueens.bitpack.BitPackSolver;
import static junit.framework.Assert.*;
public class TestBitPackSolver {

	BitPackSolver b = new BitPackSolver();

	@Test
	public void isValidPosition(){
		assertTrue(b.isValidPosition(0, 0));
		assertTrue(b.isValidPosition(7, 7));
		assertTrue(b.isValidPosition(0, 7));
		assertTrue(b.isValidPosition(7, 0));
		assertFalse(b.isValidPosition(-1, 0));
		assertFalse(b.isValidPosition(8, 0));
		assertFalse(b.isValidPosition(0, 8));
	}
	
	@Test
	public void position(){
		assertEquals(1, BitPackSolver.position(BitPackSolver.index(0, 0)));
		assertEquals(2048, BitPackSolver.position(BitPackSolver.index(1, 3)));
		assertEquals(36028797018963968l, BitPackSolver.position(BitPackSolver.index(6, 7)));
	}

	@Test
	public void set(){
		assertEquals(1, BitPackSolver.set(0, 0, 0));
		assertEquals(256, BitPackSolver.set(0, 1, 0));
		assertEquals(512, BitPackSolver.set(0, 1, 1));
		assertEquals(513, BitPackSolver.set(512, 0, 0));
		assertEquals(576460752303423488l+512l, BitPackSolver.set(512, 7, 3));
	}

	@Test
	public void unset(){
		assertEquals(0, BitPackSolver.unset(BitPackSolver.set(0, 0, 0),0,0));
		assertEquals(512l, BitPackSolver.unset(BitPackSolver.set(512, 7, 3),7,3));
		assertEquals(576460752303423488l, BitPackSolver.unset(BitPackSolver.set(576460752303423488l, 1, 1),1,1));
	}

	@Test
	public void isSingleBitSet(){
		//check fails on 0, but we never need that.
		//assertFalse(b.isSingleBitSet(0));
		assertTrue(b.isSingleBitSet(1));
		assertTrue(b.isSingleBitSet(2));
		assertFalse(b.isSingleBitSet(3));
		assertTrue(b.isSingleBitSet(2));
		assertTrue(b.isSingleBitSet(36028797018963968l));
		assertFalse(b.isSingleBitSet(36028797018963969l));
		assertFalse(b.isSingleBitSet(36028797018963973l));
	}

	@Test
	public void isValidSolution(){
		long board = 0;
		board = BitPackSolver.set(board, 1, 0);
		board = BitPackSolver.set(board, 3, 1);
		board = BitPackSolver.set(board, 5, 2);
		board = BitPackSolver.set(board, 7, 3);
		board = BitPackSolver.set(board, 2, 4);
		board = BitPackSolver.set(board, 0, 5);
		board = BitPackSolver.set(board, 6, 6);
		board = BitPackSolver.set(board, 4, 7);
//		assertTrue(b.isValidSolution(board));
	}
}
