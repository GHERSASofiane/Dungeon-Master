package contracts;

import Errors.InvarError;
import Errors.PostError;
import Errors.PreError;
import Grille.MyGrille;
import decorators.EditMapDec;
import services.EditMapServ;
import types.Cell;

public class EditMapCont extends EditMapDec {

	public EditMapCont(EditMapServ D) { super(D); }
	
	// Invariant
	public void CheckInv() { 
		 		
				if(!( isReady() == true )) {
					throw new InvarError(" Map condition's not verified ");
				}
			
	}
		
		// Observators

		@Override
		public boolean isReachable(int h1, int w1, int h2, int w2 ) {
			// *******  Pré_Condition
			// \Pre  isReachable(h1, w1, h2, w2) requires CellNature(h1, w1) != WLL
			   //    and CellNature(h2, w2) != WLL
			if(!( ( !super.CellNature(h1, w1).equals(Cell.WLL) ) && ( !super.CellNature(h2, w2).equals(Cell.WLL) ) )) {
				throw new PreError("Can not reach or go from a wall");
			}
			// *******  Pré_CheckInv
			CheckInv();
			// *******  Capteur
			// *******  Traitement
			boolean res = super.isReachable(h1, w1, h2, w2);
			// *******  Post_Inv
			CheckInv();
			// *******  Post_Condition	
			
			return res;
		}
		
		@Override
		public boolean  isReady() {
			// *******  Pré_Condition
			// *******  Pré_CheckInv
			// *******  Capteur
			// *******  Traitement
			boolean res = super.isReady();
			// *******  Post_Inv
			// *******  Post_Condition	
			
			return res;
		}
		
		// Operators

		@Override
		public void SetNature(int h, int w, Cell c) {
			// *******  Pré_Condition
			// \Pre  SetNature(h,w,c) requires 0 ≤ w < Width and 0 ≤ h < Height
			if(!( ( (0<=w)&&(w<super.getWidth()) ) && ( (0<=h)&&(h<super.getHeight()) ) )) {
				throw new PreError("Parameters should be in [0,"+super.getHeight()+"[ And [0,"+super.getWidth());
			}
			// *******  Pré_CheckInv
			CheckInv();
			// *******  Capteur
			MyGrille grille_Pre = (MyGrille) getGrille().clone();
			// *******  Traitement
			super.SetNature(h, w, c);
			// *******  Post_Inv
			CheckInv();
			// *******  Post_Condition	
			// \Post: CellNature(SetNature(h,w,c),h,w) = c
			// \Post: forall H in [0,Height(M)-1], W in [0,Width(M)-1]  ,
			  //  W != w or H != h implies CellNature(SetNature(w,h),H,W) = CellNature(H,W)
			if(!( super.CellNature(h, w).equals(c) )) {
				throw new PostError("cell nature's should be equal to parameter c");
			}
			
			for (int H = 0; H <= (getHeight()-1); H++) {
				for (int W = 0; W <= (getWidth()-1); W++) {
					if( (W!=w) || (H!=h) ) {
						if(!( CellNature(H, W).equals(grille_Pre.getCell(H, W)) )) {
							throw new PostError("Modification of unexpected box");
						}
					}
				}	
			}
		}
		
		
	}
