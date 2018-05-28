package Grille;

import types.Cell;

public interface Grille extends Cloneable{

	public Cell getCell(int h, int w);
	public void setCell(int h, int w, Cell c);
	public Object clone();
}
