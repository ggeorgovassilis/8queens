package eightqueens.bitpack;

import java.util.ArrayList;
import java.util.List;

import eightqueens.AbstractBoard;
import eightqueens.ISolver8Q;

public class BitPackSolver implements ISolver8Q {

	private final long[] visibilityMask = new long[64];

	public boolean isValidPosition(int row, int column) {
		return row >= 0 && column >= 0 && row < 8 && column < 8;
	}

	public BitPackSolver() {
		for (int row = 0; row < 8; row++)
			for (int column = 0; column < 8; column++) {
				long board = 0;
				for (int radius = 0; radius < 8; radius++) {
					if (isValidPosition((row + radius), (column + radius)))
						board = set(board, (row + radius), (column + radius));
					if (isValidPosition((row + radius), (column - radius)))
						board = set(board, (row + radius), (column - radius));
					if (isValidPosition((row - radius), (column + radius)))
						board = set(board, (row - radius), (column + radius));
					if (isValidPosition((row - radius), (column - radius)))
						board = set(board, (row - radius), (column - radius));
					if (isValidPosition(radius, column))
						board = set(board, radius, column);
					if (isValidPosition(row, radius))
						board = set(board, row, radius);
				}
				visibilityMask[index(row, column)] = board;
			}
	}

	public static boolean getBit(long board, int row, int column) {
		return (board & position(index(row, column))) != 0;
	}

	boolean getBit(long board, int index) {
		return (board & (1l << index)) != 0;
	}

	AbstractBoard longToBoard(long board) {
		return new Board(board);
	}

	public static int index(int row, int column) {
		return (row * 8 + column);
	}

	public static long position(int index) {
		return 1l << (long) index;
	}

	public static long set(long board, int row, int column) {
		return board | position(index(row, column));
	}

	public static long unset(long board, int row, int column) {
		return set(board, row, column) ^ position(index(row, column));
	}

	public boolean isSingleBitSet(long x) {
		// the strict check is this:
		// x!= 0 && (x & (x - 1)) == 0;
		// but we already know x!=0 because we ever only check after we've
		// determined that the field is occupied
		return (x & (x - 1)) == 0;
	}

	@Override
	public List<AbstractBoard> solve() {
		long board = 255;
		int rows[] = new int[8];
		int carry = 0;
		List<AbstractBoard> abstractBoards = new ArrayList<AbstractBoard>(92);
		// carry = 0 -> the one time where it will be 1 it means that _all_
		// columns have overflown (all queens were already on the last row)
		while (carry == 0) {

			// 1. verify if board is a valid solution
			// in an older version i'd iterate over all 64 indexes instead of
			// row[column] which turned out to take twice as long

			int i = 0;
			for (; i<8; i++) {
				int index = index(rows[i], i);
				if (((board ^ position(index)) & visibilityMask[index]) != 0)
					break;
			}
			if (i==8)
				abstractBoards.add(longToBoard(board));

			// 2. compute next combination
			carry = 1;
			for (int column = 0; column < 8; column++) {
				int c = rows[column] + carry;
				rows[column] = c % 8;
				carry = c >> 3; // division by 8. Marginally (3ms) faster than
								// the division itself.
				if (carry == 0)
					break;
			}

			// 3. place queens according to next combination
			board = 0;
			for (int column = 7; column >= 0; column--) {
				board |= position(index(rows[column], column));
			}
		}
		return abstractBoards;
	}

}
