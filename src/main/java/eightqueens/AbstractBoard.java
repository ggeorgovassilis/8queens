package eightqueens;

public abstract class AbstractBoard {

	public abstract void set(int row, int column, boolean occupied);
	
	public abstract boolean get(int row, int column);
	
	@Override
	public String toString() {
		String s=" 01234567\n";
		for (int row=0;row<8;row++){
			s+=row+"";
			for (int column=0;column<8;column++){
				s+=get(row,column)?"X":" ";
			}
			s+="\n";
		}
		s+="--------\n";
		return s;
	}
	
	public AbstractBoard(){
	}
	
	public void load(AbstractBoard prototype){
		for (int row=7;row>=0;row--)
		for (int col=7;col>=0;col--)
			set(row, col, prototype.get(row, col));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AbstractBoard))
			return false;
		AbstractBoard b2 = (AbstractBoard)obj;
		return toString().equals(b2.toString());
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
