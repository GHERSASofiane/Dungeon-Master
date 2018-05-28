package Grille;

import types.Cell;

public class MyGrille implements Grille, Cloneable{

	private Cell[][] grille;

	public MyGrille() {
		// TODO
		grille = new Cell[4][4];
	}
	
	public MyGrille(int r, int c) {
		grille = new Cell[r][c];
	}
	
	public MyGrille(int i) {
		grille = new Cell[4][4];
		
		grille[0][0] = Cell.IN;	    grille[0][1] = Cell.EMP;	grille[0][2] = Cell.EMP;	grille[0][3] = Cell.WLL;
		grille[1][0] = Cell.EMP;	grille[1][1] = Cell.WLL;	grille[1][2] = Cell.DNC;	grille[1][3] = Cell.WLL;
		grille[2][0] = Cell.EMP;	grille[2][1] = Cell.EMP;	grille[2][2] = Cell.EMP;	grille[2][3] = Cell.WLL;
		grille[3][0] = Cell.EMP;	grille[3][1] = Cell.EMP;	grille[3][2] = Cell.OUT;	grille[3][3] = Cell.WLL;
	}
	
	@Override
	public Cell getCell(int h, int w) {
		return grille[h][w];
	}

	@Override
	public void setCell(int h, int w, Cell c) {
		grille[h][w] = c;
	}

	@Override
	public Object clone() {
		MyGrille res = null;
		try {
			
			res = ((MyGrille)super.clone());
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return res;
	}
}
