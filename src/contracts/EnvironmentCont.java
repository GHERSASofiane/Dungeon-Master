package contracts;

import java.util.Optional;

import Content.MyContent;
import Errors.PostError;
import Errors.PreError;
import Grille.MyGrille;
import decorators.EnvironmentDec;
import services.EnvironmentServ;
import types.TypeMob;

public class EnvironmentCont extends EnvironmentDec {

	public EnvironmentCont(EnvironmentServ D) {
		super(D);
	}
	
	public void CheckInv() { 
			
	}

	@Override
	public Optional<TypeMob> CellContent(int h, int w) {
		 
		// *******  Pré_Condition
		// \Pre : CellContent( h, w) requires 0<= w < super.getWidth() And 0<= h < super.getHeigth()
		if(!( ( 0 <= w && w < getWidth() ) && ( 0 <= h && h < getHeight() ) )) {  
			throw new PreError("cell's width should be in [ 0 - "+getWidth()+" [ and height [ 0 - "+getHeight()+" [");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		// *******  Traitement
		Optional<TypeMob>  res = super.CellContent(h, w);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		
		return res;
	}

	@Override
	public void SetCellContent(int h, int w, TypeMob type) {
		// *******  Pré_Condition
		// \Pre : CellContent( h, w ) requires 0<= w < super.getWidth() And 0<= h < super.getHeigth()
		if(!( ( 0 <= w && w < getWidth() ) && ( 0 <= h && h < getHeight() ) )) {  
			throw new PreError("cell's width should be in [ 0 - \"+getWidth()+\" [ and height [ 0 - \"+getHeight()+\" [");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		MyContent Content_Pre = (MyContent) getContent().clone();
		// *******  Traitement
		super.SetCellContent(h, w, type);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		// \Post: CellContent(SetCellContent(h,w,type),h,w) = type
		// \Post: forall H in [0,Height(M)-1], W in [0,Width(M)-1]  ,
		  //  W != w or H != h implies CellContent(SetCellContent(w,h),H,W) = CellContent(H,W)
//		if(!( super.CellContent(h, w).equals(type) )) {
//			throw new PostError("cell Content's should be equal to parameter type");
//		}
		
		for (int H = 0; H <= (getHeight()-1); H++) {
			for (int W = 0; W <= (getWidth()-1); W++) {
				if( (W!=w) || (H!=h) ) {
					if(!( CellContent(H, W).equals(Content_Pre.getContent(H, W)) )) {
						throw new PostError("Modification of unexpected box");
					}
				}
			}	
		}
		
	}
	

	@Override
	public void CloseDoor(int h, int w) {
		// *******  Pré_Condition
		// Pre : CloseDoor(h,w) requires CellContent(h,w) = No
	 
		if(!( CellContent(h, w).get().equals(TypeMob.NO) )) {  
			throw new PreError(" Can't close a door of a cell containing an object :(");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		MyContent Content_Pre = (MyContent) getContent().clone();
		// *******  Traitement
		super.CloseDoor(h, w);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition 
		// \Post: forall H in [0,Height(M)-1], W in [0,Width(M)-1]  ,
		  //  W != w or H != h implies CellContent(SetCellContent(w,h),H,W) = CellContent(H,W)
		
		for (int H = 0; H <= (getHeight()-1); H++) {
			for (int W = 0; W <= (getWidth()-1); W++) {
				if( (W!=w) || (H!=h) ) {
					if(!( CellContent(H, W).equals(Content_Pre.getContent(H, W)) )) {
						throw new PostError("Modification of unexpected box");
					}
				}
			}	
		}
	}

}
