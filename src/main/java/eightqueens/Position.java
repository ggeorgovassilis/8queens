package eightqueens;

public class Position {

	private final byte row;
	private final byte column;
	
	public Position(byte row, byte column){
		this.row = row;
		this.column = column;
	}

	public byte getRow() {
		return row;
	}

	public byte getColumn() {
		return column;
	}
}
