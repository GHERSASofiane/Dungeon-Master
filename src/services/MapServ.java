package services;

import Grille.Grille;
import Grille.MyGrille;
import types.Cell;

public interface MapServ extends Cloneable{

	// Invariant
	
	// Observators
	 
	public int getHeight();
	 
	public int getWidth();

	public Grille getGrille();
	
	public Cell CellNature(int h, int w) ;
	
	// Constructors
	public void init(int h, int w, MyGrille g);
	
	// Operators
	public void OpenDoor(int h, int w);

	public void CloseDoor(int h, int w);

	public Object clone();
	
}
