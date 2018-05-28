package contracts;

import Content.MyContent;
import Errors.PostError;
import Errors.PreError;
import Grille.MyGrille;
import decorators.PlayerDec;
import services.EnvironmentServ;
import services.PlayerServ;
import types.Cell;
import types.Command;
import types.Dir;
import types.TypeMob;

public class PlayerCont extends PlayerDec {

	PlayerServ p;
	
	public PlayerCont(PlayerServ p) {
		super(p);
		this.p = p;
	}

	public void CheckInv() {
		// ******* Invariante
//		        Face(P) = N implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)-u,Row(P)-v)
//				Face(P) = N implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)-u,Row(P)-v)
//				Face(P) = S implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)+u,Row(P)+v)
//				Face(P) = S implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)+u,Row(P)+v)
//				Face(P) = E implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)-v,Row(P)+u)
//				Face(P) = E implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)-v,Row(P)+u)
//				Face(P) = W implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)+v,Row(P)-u)
//				Face(P) = W implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)+v,Row(P)-u)
//		for (int H = 0; H <= 3; H++) {
//		for (int W = -1; W <= 1; W++) {
//			if( (0<=getRow()+H) && (getRow()+H < getEnv().getHeight() ) && (0<=getCol()-(-W)) && (getCol()-(-W) < getEnv().getWidth()) ) {
//				System.out.println(p.getContent(H, W)+" "+getEnv().CellContent(getRow()+H, getCol()-(-W) ).get() );
//			}
//		}	
//	}
		
//				forall u,v in [-1,0] × [-1,0], not Viewable(P,u,v)
		if(!(  !p.isViewable(-1, -1) &&  !p.isViewable(-1, 0) &&  !p.isViewable(-1, 1) &&
				 !p.isViewable(0, -1) &&  !p.isViewable(0, 0) &&  !p.isViewable(0, 1)  )) {  
			throw new PreError(" error on the boxes he does not have to see ");
		}

//		Viewable(P,2,-1) => Nature(P,1,-1) !∈ {  WLL, DWC, DNC }
		if( p.isViewable(2, -1) ) {
			if(!(  !p.getNature(1, -1).equals(Cell.WLL) && !p.getNature(1, -1).equals(Cell.DWC) 
					&& !p.getNature(1, -1).equals(Cell.DNC) )) {  
				throw new PreError(" error Viewable(P,2,-1) => Nature(P,1,-1) !∈ {  WLL, DWC, DNC } ");
			}	
		}
//		Viewable(P,3,-1) => Nature(P,2,-1) !∈ {  WLL, DWC, DNC } and Viewable(P,2,-1)
		if( p.isViewable(3, -1) ) {
			if(!(  !p.getNature(2, -1).equals(Cell.WLL) && !p.getNature(2, -1).equals(Cell.DWC) 
					&& !p.getNature(2, -1).equals(Cell.DNC) && p.isViewable(2, -1) )) {  
				throw new PreError(" error Viewable(P,3,-1) => Nature(P,2,-1) !∈ {  WLL, DWC, DNC } and Viewable(P,2,-1) ");
			}	
		}
//		Viewable(P,2,0) => Nature(P,1,0) !∈ {  WLL, DWC, DNC }
		if( p.isViewable(2, 0) ) {
			if(!(  !p.getNature(1, 0).equals(Cell.WLL) && !p.getNature(1, 0).equals(Cell.DWC) 
					&& !p.getNature(1, 0).equals(Cell.DNC) )) {  
				throw new PreError(" error Viewable(P,2,0) => Nature(P,1,0) !∈ {  WLL, DWC, DNC } ");
			}	
		}
//		Viewable(P,3,0) => Nature(P,2,0) !∈ {  WLL, DWC, DNC } and Viewable(P,2,0)
		if( p.isViewable(3, 0) ) {
			if(!(  !p.getNature(2, 0).equals(Cell.WLL) && !p.getNature(2, 0).equals(Cell.DWC) 
					&& !p.getNature(2, 0).equals(Cell.DNC) && p.isViewable(2, 0) )) {  
				throw new PreError(" error Viewable(P,3,0) => Nature(P,2,0) !∈ {  WLL, DWC, DNC } and Viewable(P,2,0) ");
			}	
		}
//		Viewable(P,2,1) => Nature(P,1,1) !∈ {  WLL, DWC, DNC }
		if( p.isViewable(2, 1) ) {
			if(!(  !p.getNature(1, 1).equals(Cell.WLL) && !p.getNature(1, 1).equals(Cell.DWC) 
					&& !p.getNature(1, 1).equals(Cell.DNC) )) {  
				throw new PreError(" error Viewable(P,2,1) => Nature(P,1,1) !∈ {  WLL, DWC, DNC } ");
			}	
		}
//		Viewable(P,3,1) => Nature(P,2,1) !∈ {  WLL, DWC, DNC } and Viewable(P,2,1)
		if( p.isViewable(3, 1) ) {
			if(!(  !p.getNature(2, 1).equals(Cell.WLL) && !p.getNature(2, 1).equals(Cell.DWC) 
					&& !p.getNature(2, 1).equals(Cell.DNC) && p.isViewable(2, 1) )) {  
				throw new PreError(" error Viewable(P,3,1) => Nature(P,2,1) !∈ {  WLL, DWC, DNC } and Viewable(P,2,1) ");
			}	
		}
		
	}
	
	@Override
	public Command getLastCom() {
		Command tmp;
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		tmp = super.getLastCom();
		// ******* Post_Inv
		// ******* Post_Condition

		return tmp;
	}

	@Override
	public void setLastCom(Command c) {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		super.setLastCom(c);
		// ******* Post_Inv
		// ******* Post_Condition
		
	}
	
	@Override
	public void init(EnvironmentServ e, int h, int w, Dir D, int hp) {
		// *******  Pré_Condition
		// \Pre : init(e, h, w, d, hp ) requires  e.CellContent(h, w).get() = joueur
		if(!( e.CellContent(h, w).get().equals(TypeMob.joueur) )) {  
			throw new PreError(" the mob must be a player ");
		}
		// ******* Pré_CheckInv
	 CheckInv();
		// ******* Capteur
		// ******* Traitement
		super.init(e, h, w, D, hp);
		// ******* Post_Inv
		 
		// ******* Post_Condition
	}
	
	@Override
	public TypeMob getContent(int x, int y) {
		TypeMob tmp;
		// ******* Pré_Condition
		// \Pre : getContent (x, y) requires -getWidth() < y < getWidth() And -getHeight() < x < getHeight()
		if(!( ( -super.getEnv().getWidth() < y && y < super.getEnv().getWidth() ) 
				&& ( -super.getEnv().getHeight() < x && x < super.getEnv().getHeight() ) )) {  
			throw new PreError("Cell should be of width ] -"+super.getEnv().getWidth()+" - "+super.getEnv().getWidth()+" [ and heigth ] -"+super.getEnv().getHeight()+" - "+super.getEnv().getHeight()+" [");
		}
		
		// ******* Pré_CheckInv
		CheckInv();
		// ******* Capteur
		MyGrille grille_Pre = (MyGrille) super.getEnv().getGrille().clone(); 
		// ******* Traitement
		tmp = super.getContent(x, y);
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post: forall H in [0, Height(M)] ,W in [0, Width(M)] ,  implies CellNature(getContent(w,h),H,W) = CellNature(H,W)
		for (int H = 0; H <= (super.getEnv().getHeight()-1); H++) {
			for (int W = 0; W <= (super.getEnv().getWidth()-1); W++) {
					if(!( super.getEnv().CellNature(H, W).equals(grille_Pre.getCell(H, W)) )) {
						throw new PostError("Modification of unexpected box");
					}
			}	
		}
		
		return tmp;
	}

	@Override
	public Cell getNature(int x, int y) {
		Cell tmp;
		// ******* Pré_Condition
		// \Pre : getContent (x, y) requires requires -getWidth() < y < getWidth() And -getHeight() < x < getHeight()
		if(!( ( -super.getEnv().getWidth() < y && y < super.getEnv().getWidth() ) 
				&& ( -super.getEnv().getHeight() < x && x < super.getEnv().getHeight() ) )) {  
			throw new PreError("Cell should be of width ] -"+super.getEnv().getWidth()+" - "+super.getEnv().getWidth()+" [ and heigth ] -"+super.getEnv().getHeight()+" - "+super.getEnv().getHeight()+" [");
		}
		// ******* Pré_CheckInv
		CheckInv();
		// ******* Capteur
		MyGrille grille_Pre = (MyGrille) super.getEnv().getGrille().clone(); 
		// ******* Traitement
		tmp = super.getNature(x, y);
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post: forall H in [0, Height(M)] ,W in [0, Width(M)] ,  implies CellNature(getContent(w,h),H,W) = CellNature(H,W)
		for (int H = 0; H <= (super.getEnv().getHeight()-1); H++) {
			for (int W = 0; W <= (super.getEnv().getWidth()-1); W++) {
					if(!( super.getEnv().CellNature(H, W).equals(grille_Pre.getCell(H, W)) )) {
						throw new PostError("Modification of unexpected box");
					}
			}	
		}
		
		return tmp;
	}

	@Override
	public boolean isViewable(int x, int y) {
		boolean tmp;
		// ******* Pré_Condition
		// \Pre : isViewable( h, w) requires 0<= w < super.getWidth() And 0<= h < super.getHeigth()
		if(!( ( -super.getEnv().getWidth() < y && y < super.getEnv().getWidth() ) 
				&& ( -super.getEnv().getHeight() < x && x < super.getEnv().getHeight() ) )) {  
			throw new PreError("Cell should be of width ] -"+super.getEnv().getWidth()+" - "+super.getEnv().getWidth()+" [ and heigth ] -"+super.getEnv().getHeight()+" - "+super.getEnv().getHeight()+" [");
		}
		// ******* Pré_CheckInv
		CheckInv();
		// ******* Capteur
		// ******* Traitement
		tmp = super.isViewable(x, y);
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition

		return tmp;
	}
}
