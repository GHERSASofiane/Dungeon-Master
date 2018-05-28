package Impl;

import Grille.Grille;
import Grille.MyGrille;
import services.EditMapServ;
import services.MapServ;
import types.Cell;

public class MapImpl implements MapServ {

	@Override
	public Object clone(){
	
		MapServ res = null;
		try {
			
			res = ((MapImpl)super.clone());
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	// Observators
	private int Height;
	private int Width;
	private MyGrille myGrille;

	@Override
	public int getHeight() {
		return Height;
	}

	@Override
	public int getWidth() {
		return Width;
	}

	@Override
	public Grille getGrille() {
		return this.myGrille;
	}
	 
	@Override
	public Cell CellNature(int h, int w) {
		return myGrille.getCell(h, w);
	}

	// Constructors
	@Override
	public void init(int h, int w, MyGrille g) {
		this.Width = w;
		this.Height = h;
		this.myGrille = g;
	}

	// Operators

	@Override
	public void OpenDoor(int h, int w) {
		if(CellNature(h, w).equals(Cell.DWC)) myGrille.setCell(h, w, Cell.DWO);
		if(CellNature(h, w).equals(Cell.DNC)) myGrille.setCell(h, w, Cell.DNO);
	}

	@Override
	public void CloseDoor(int h, int w) {
		if(CellNature(h, w).equals(Cell.DWO)) myGrille.setCell(h, w, Cell.DWC);
		if(CellNature(h, w).equals(Cell.DNO)) myGrille.setCell(h, w, Cell.DNC);
	}


}
