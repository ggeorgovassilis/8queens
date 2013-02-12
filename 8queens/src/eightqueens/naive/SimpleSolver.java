package eightqueens.naive;

import java.util.ArrayList;
import java.util.List;

import eightqueens.AbstractBoard;
import eightqueens.ISolver8Q;
import eightqueens.IVerifier;

public class SimpleSolver implements ISolver8Q{

	// SimpleSolver constructs boards that are guaranteed to have always exactly 8 queens and no column overlap.
	// This simplifies (and speeds up) verifiers, so a simpler version can be used for internal verification.
	IVerifier verifier = new SimpleVerifier(){
		public boolean areThere8Queens(AbstractBoard abstractBoard) {
			return true;
		};
		
	};
	
	@Override
	public List<AbstractBoard> solve() {
		AbstractBoard board = new Board();
		byte rows[] = new byte[8];
		byte carry = 0;
		List<AbstractBoard> solutions = new ArrayList<AbstractBoard>(92);
		while (carry==0){
			for (byte column=0;column<rows.length;column++){
				board.set(rows[column], column, true);
			}
			if (verifier.isValidSolution(board)){
				Board copy = new Board();
				copy.load(board);
				solutions.add(copy);
			}
			for (byte column=0;column<rows.length;column++){
				board.set(rows[column], column, false);
			}
			
			carry = 1;
			for (byte column = 0; column < 8; column++) {
				int c = rows[column]+carry;
				carry = (byte)(c >> 3); // division by 8
				rows[column] = (byte)(c % 8);
				if (carry == 0)
					break;
			}
		}
		return solutions;
	}

}
