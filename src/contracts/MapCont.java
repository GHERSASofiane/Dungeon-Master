package contracts;

import Grille.Grille;
import Grille.MyGrille;
import decorators.MapDec;
import services.MapServ;
import types.Cell;
import Errors.*;

public class MapCont extends MapDec{
	
	public MapCont(MapServ D) { super(D); }
//	Pas d'invariantes
	public void CheckInv() { 
		
	}

// ************************************  Observators
	
	@Override
	public int getHeight() {
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		int res = super.getHeight();
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		
		return res;
	}

	@Override
	public int getWidth() {
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		int res = super.getWidth();
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		
		return res;
	}
	
	@Override
	public Grille getGrille() {
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		Grille res = super.getGrille();
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
				
		return res;
	}
	
	@Override
	public Cell CellNature(int h, int w) {
		// *******  Pré_Condition
		// \Pre CellNature(h, w) requires 0 ≤ w < Width and 0 ≤ h < Height
		if(!( ( 0 <= w && w < getWidth() ) && ( 0 <= h && h < getHeight() ) )) {  
			throw new PreError("Parameters should be in [0,"+super.getHeight()+"[ And [0,"+super.getWidth());
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		// *******  Traitement
		Cell res = super.CellNature(h, w);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		
		return res;
	}

// ************************************   Constructors   
	
	@Override
	public void init(int h, int w, MyGrille g) {
		// *******  Pré_Condition
		// \Pre init(h, w,g) requires 0 < w and 0 < h
		if(!( 0 < w && 0 < h )) {
			throw new PreError("Parameters should be in positive");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		// *******  Traitement
		super.init(h, w, g);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		// \Post Height(init(h, w,g)) = h
		if(!( getHeight() == h )) {
			throw new PostError(" Height not properly initialized ");
		}
		// \Post Width(init(h, w,g)) = w
		if(!( getWidth() == w )) {
			throw new PostError("Width not properly initialized ");
		}
		// \Post myGrille(init(h, w,g)) = g
		if(!( g.equals(getGrille()) )) {
			throw new PostError(" grid not properly initialized ");
		}
	}

	// ************************************   Operators

	@Override
	public void OpenDoor(int h, int w) {
		// *******  Pré_Condition
		// \Pre OpenDoor(h, w) requires CellNature(h, w) ∈ { DNC, DWC }
		if(!( (CellNature(h, w).equals(Cell.DNC)) || (CellNature(h, w).equals(Cell.DWC)) )) {
			throw new PreError("Door should be closed before calling OpenDoor");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		Cell CellNature_Pre = CellNature(h, w);
		MyGrille grille_Pre = (MyGrille) getGrille().clone();
		// *******  Traitement
		super.OpenDoor(h, w);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		// \Post CellNature(h, w) = DWC => CellNature(OpenDoor(h, w),h, w) = DWO
		if( CellNature_Pre.equals(Cell.DWC) ) {
			
			if(!( CellNature(h, w).equals(Cell.DWO) )) {
				throw new PostError("West door should be opened after calling opendoor method");
			}
		
		}
		// \Post CellNature(h, w) = DNC => CellNature(OpenDoor(h, w),h, w) = DNO
		if( CellNature_Pre.equals(Cell.DNC) ) {
			
			if(!( CellNature(h, w).equals(Cell.DNO) )) {
				throw new PostError("North door should be opened after calling opendoor method");
			}

		}
		// \Post forall W ∈ [0; Width-1] forall H ∈ [0; Height-1] (W != w or H != h)
		    // => CellNature(OpenDoor(h, w),H,W) = CellNature(H,W)
		for (int H = 0; H <= (getHeight()-1); H++) {
			for (int W = 0; W <= (getWidth()-1); W++) {
				if( (W!=w) || (H!=h) ) {
					if(!( CellNature(H, W).equals(grille_Pre.getCell(H, W)) )) {
						throw new PostError(" Modification of unexpected cells after OpenDoor(h, w)");
					}
				}
			}	
		}
		
	}

	
	@Override
	public void CloseDoor(int h, int w) {
		// *******  Pré_Condition
		// \Pre CloseDoor(h, w) requires CellNature(h, w) ∈ { DNO, DWO }
		if(!( (CellNature(h, w).equals(Cell.DNO)) || (CellNature(h, w).equals(Cell.DWO)) )) {
			throw new PreError("Door should be closed before calling OpenDoor method");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		Cell CellNature_Pre = CellNature(h, w);
		MyGrille grille_Pre = (MyGrille) getGrille().clone();
		// *******  Traitement
		super.CloseDoor(h, w);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		// \Post CellNature(h, w) = DWO => CellNature(CloseDoor(h, w),h, w) = DWC
		if( CellNature_Pre.equals(Cell.DWO) ) {
			
			if(!( CellNature(h, w).equals(Cell.DWC) )) {
				throw new PostError("West door should be opened after calling opendoor method");
			}
		
		}
		// \Post CellNature(h, w) = DNO => CellNature(CloseDoor(h, w),h, w) = DNC
		if( CellNature_Pre.equals(Cell.DNO) ) {
			
			if(!( CellNature(h, w).equals(Cell.DNC) )) {
				throw new PostError("North door should be opened after calling opendoor method");
			}

		}
		// \Post forall W ∈ [0; Width-1] forall H ∈ [0; Height-1] (W != w or H != h)
		    // => CellNature(CloseDoor(h, w),H,W) = CellNature(H,W)
		for (int H = 0; H <= (getHeight()-1); H++) {
			for (int W = 0; W <= (getWidth()-1); W++) {
				if( (W!=w) || (H!=h) ) {
					if(!( CellNature(H, W).equals(grille_Pre.getCell(H, W)) )) {
						throw new PostError("Modification of unexpected cells after OpenDoor(h, w)");
					}
				}
			}	
		}
	}

}
