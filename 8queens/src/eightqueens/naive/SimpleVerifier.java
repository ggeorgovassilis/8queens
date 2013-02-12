package eightqueens.naive;

import eightqueens.AbstractBoard;
import eightqueens.IVerifier;

public class SimpleVerifier implements IVerifier {

	public boolean hasExactlyOneQueenOnRow(byte row, AbstractBoard abstractBoard) {
		int queens = 0;
		for (byte column = 0; column < 8; column++)
			if (abstractBoard.get(row, column)) {
				queens++;
			}
		return queens == 1;
	}

	public boolean hasExactlyOneQueenOnColumn(byte column, AbstractBoard abstractBoard) {
		int queens = 0;
		for (byte row = 0; row < 8; row++)
			if (abstractBoard.get(row, column)) {
				queens++;
			}
		return queens == 1;
	}

	public boolean isSquareOccupiedOverflowSafe(byte row, byte column, AbstractBoard abstractBoard) {
		return (column >= 0 && column < 8 && row >= 0 && row < 8 && abstractBoard.get(
				row, column));
	}

	public boolean hasQueenInAnyDiagonal(int row, int column, AbstractBoard abstractBoard) {
		for (byte radius = 1; radius < 8; radius++) {
			if (isSquareOccupiedOverflowSafe((byte)(row + radius), (byte)(column + radius),
					abstractBoard)
					|| isSquareOccupiedOverflowSafe((byte)(row - radius), (byte)(column
							+ radius), abstractBoard)
					|| isSquareOccupiedOverflowSafe((byte)(row + radius), (byte)(column
							- radius), abstractBoard)
					|| isSquareOccupiedOverflowSafe((byte)(row - radius), (byte)(column
							- radius), abstractBoard))
				return true;
		}
		return false;
	}
	
	public boolean areThere8Queens(AbstractBoard abstractBoard){
		int countQueens = 0;
		for (byte row = 0; row < 8; row++)
			for (byte column = 0; column < 8; column++)
				if (abstractBoard.get(row, column))
					countQueens++;
		return countQueens ==8;
	}

	@Override
	public boolean isValidSolution(AbstractBoard abstractBoard) {
		// 1st test: exactly 8 occupied squares

		if (!areThere8Queens(abstractBoard))
			return false;
		// 2nd test: no queen sees other queens
		for (byte row = 0; row < 8; row++)
			for (byte column = 0; column < 8; column++) {
				if (abstractBoard.get(row, column)
						&& (!hasExactlyOneQueenOnColumn(column, abstractBoard)
								|| !hasExactlyOneQueenOnRow(column, abstractBoard) || hasQueenInAnyDiagonal(
									row, column, abstractBoard)))
					return false;
			}
		return true;
	}
}
