package contracts;

import java.util.Optional;

import Errors.InvarError;
import Errors.PostError;
import Errors.PreError;
import Impl.EnvironmentImpl;
import decorators.MobDec;
import services.EnvironmentServ;
import services.MobServ;
import types.Cell;
import types.Dir;
import types.TypeMob;

public class MobCont extends MobDec {

	public MobCont(MobServ d) {
		super(d);
	}

	public void CheckInv() {

		// 0 ≤ Col(M) < Environment::Width(Envi(M))
		if (!(0 <= getCol() && getCol() < getEnv().getWidth())) {
			throw new InvarError(" parameters Out of bounds ");
		}
		// 0 ≤ Row(M) < Environment::Height(Envi(M))
		if (!(0 <= getRow() && getRow() < getEnv().getHeight())) {
			throw new InvarError(" parameters Out of bounds ");
		}
		// Environment::CellNature(Envi(M),Col(M),Row(M)) not∈ { WLL, DNC, DWC}
		if (!((!getEnv().CellNature(getRow(), getCol()).equals(Cell.WLL))
				&& ((!getEnv().CellNature(getRow(), getCol()).equals(Cell.DNC)))
				&& ((!getEnv().CellNature(getRow(), getCol()).equals(Cell.DWC))))) {
			throw new InvarError(" parameters Out of bounds ");
		}

	}

	@Override
	public EnvironmentServ getEnv() {
		EnvironmentServ tmp;
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		tmp = super.getEnv();
		// ******* Post_Inv
		// ******* Post_Condition

		return tmp;
	}

	@Override
	public int getCol() {
		int tmp;
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		tmp = super.getCol();
		// ******* Post_Inv
		// ******* Post_Condition

		return tmp;
	}

	@Override
	public int getRow() {
		int tmp;
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		tmp = super.getRow();
		// ******* Post_Inv
		// ******* Post_Condition

		return tmp;
	}

	@Override
	public Dir getFace() {
		Dir tmp;
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		tmp = super.getFace();
		// ******* Post_Inv
		// ******* Post_Condition

		return tmp;
	}

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir d) {
		// ******* Pré_Condition
		// \pre : init(e, h, w, d) requires 0 ≤ w < Environment::Width(E)
		// and 0 ≤ h < Environment::Height(E)
		if (!(0 <= w && w < e.getWidth()) && (0 <= h && h < e.getHeight())) {
			throw new PreError(" parameters Out of bounds ");
		}
		// ******* Pré_CheckInv
		// ******* Capteur
		// ******* Traitement
		super.init(e, h, w, d);
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// Col(init(e, h, w, d)) = w
		if (!(getCol() == w)) {
			throw new PostError(" current column should be equal to the given parameter ");
		}
		// Row(init(e, h, w, d)) = h
		if (!(getRow() == h)) {
			throw new PostError(" current row should be equal to the given parameter  ");
		}
		// Face(init(e, h, w, d)) = d
		if (!(getFace().equals(d))) {
			throw new PostError(" current direction should be equal to the given parameter  ");
		}
		// Envi(init(e, h, w, d)) = e
		if (!(getEnv().equals(e))) {
			throw new PostError(" current environment should be equal to the given parameter  ");
		}

	}

	@Override
	public void Forward() {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		Dir Face_atPre = getFace();
		
		EnvironmentServ Environment_atPre = (EnvironmentServ) getEnv().clone();
		
		int Col_atPre = getCol();
		int Row_atPre = getRow();
		// ******* Traitement
		super.Forward();
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post : Environment::CellContent(Forward(M),Row(M),Col(M)) ==
					// Environment::CellContent(M,Row(M),Col(M))
		if(!( getEnv().CellContent(getRow(), getCol()).equals(Environment_atPre.CellContent(getRow(), getCol()))  )) {
			throw new PostError(" unexpected modification of cell content ");
		}
		// \Post : Face(Forward(M)) = Face(M)
		if(!( getFace().equals(Face_atPre)  )) {
			throw new PostError(" unexpected modification of mobile direction ");
		}
		// \Post : Face(M)=Dir.N
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M)-1 )) ,
		// Environment::CellContent(Forward(M),H,W) == Environment::CellContent(M,H,W) .
		
		// 0< getRow(M) < getHeight(M)-1 ==>
		// Environment::CellContent(Forward(M),Row(M)+1,Col(M)) ==
			// Environment::CellContent(M,Row(M)-1,Col(M))
		// CellContent(M, Row(M)-1, Col(M)) == No ==> Row(Forward(M)) = Row(M) - 1 .
		
		// Col(Forward(M)) = Col(M)
		
		if( Face_atPre.equals(Dir.N) ) {
			
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()-1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( (North)");
						}
					}
				}
			}
			
			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
				if(!( getEnv().CellContent(getRow()+1, getCol()).equals(Environment_atPre.CellContent(getRow()-1, getCol()))  )) {
					throw new PostError("Content of source and destination cell should be permutted after calling forward method");
				}
				if(getEnv().CellContent(Row_atPre-1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
					if(!( getRow() == Row_atPre - 1  )) {
						throw new PostError(" Cell's row not properly modified after calling forward method (North)");
					}
				}
				
			
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" Cell's column should not be modified after calling forward method (North)");
			}
			
		}
		// \Post : Face(M)=Dir.S
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M)+1 )) ,
		// Environment::CellContent(Forward(M),H,W) == Environment::CellContent(M,H,W) . 

		// 0< getRow(M) < getHeight(M)-1 ==>
		// Environment::CellContent(Forward(M),Row(M)-1,Col(M)) ==
			// Environment::CellContent(M,Row(M)+1,Col(M))
		// CellContent(M, Row(M)+1, Col(M)) == No ==> Row(Forward(M)) = Row(M) + 1 .
		
		// Col(Forward(M)) = Col(M)

		
		if( Face_atPre.equals(Dir.S) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()+1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( (South)");
						}
					}
				}
			} 

			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
			if(!( getEnv().CellContent(getRow()-1, getCol()).equals(Environment_atPre.CellContent(getRow()+1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling forward method");
			}
			
			if(getEnv().CellContent(Row_atPre+1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				if(!( getRow() == Row_atPre + 1  )) {
					throw new PostError(" Cell's row  not properly modified after calling forward method (South)");
				}
			}
			

			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" Cell's column should not be modified after calling forward method (South)");
			}
			
		}
		// \Post : Face(M)=Dir.E
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M)+1 ) or H != Row(M) ) ,
		// Environment::CellContent(Forward(M),H,W) == Environment::CellContent(M,H,W) . 

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(Forward(M),Row(M),Col(M)-1) ==
			// Environment::CellContent(M,Row(M),Col(M)+1)
		// CellContent(M, Row(M), Col(M)+1) == No ==> Col(Forward(M)) = Col(M) + 1
		
		// Row(Forward(M)) = Row(M)
		
		
		if( Face_atPre.equals(Dir.E) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()+1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError("modification of unexpected cells content :( (Est)");
						}
					}
				}
			} 
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			if(!( getEnv().CellContent(getRow(), getCol()-1).equals(Environment_atPre.CellContent(getRow(), getCol()+1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling forward method");
			}
			
			if(getEnv().CellContent(Row_atPre, Col_atPre+1).equals(Optional.of(TypeMob.NO))) {
				if(!( getCol() == Col_atPre + 1 )) {
					throw new PostError("Cell's column not properly modified (Est) after calling forward method");
				}
			}

			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError("Cell's row after calling forward method should not be modified (Est)");
			}
			
			
		}

		// \Post : Face(M)=Dir.W
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M)-1 ) or H != Row(M) ) ,
		// Environment::CellContent(Forward(M),H,W) == Environment::CellContent(M,H,W) . 

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(Forward(M),Row(M),Col(M)+1) ==
			// Environment::CellContent(M,Row(M),Col(M)-1)
		// CellContent(M, Row(M), Col(M)-1) == No ==> Col(Forward(M)) = Col(M) - 1
		
		// Row(Forward(M)) = Row(M)
		
		
		if( Face_atPre.equals(Dir.W) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()-1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( (West)");
						}
					}
				}
			} 

			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			if(!( getEnv().CellContent(getRow(), getCol()+1).equals(Environment_atPre.CellContent(getRow(), getCol()-1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling forward method");
			}
			
			if(getEnv().CellContent(Row_atPre, Col_atPre-1).equals(Optional.of(TypeMob.NO))) {
				
			if(!( getCol() == Col_atPre - 1 )) {
				throw new PostError("Cell's column not properly modified (West) after calling forward method");
			}
			}
			
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" Cell's row after calling forward method should not be modified (West) ");
			}
			
		}

	}

	
	
	@Override
	public void Backward() {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		Dir Face_atPre = getFace();
		EnvironmentServ Environment_atPre = (EnvironmentServ) getEnv().clone();
		int Col_atPre = getCol();
		int Row_atPre = getRow();
		// ******* Traitement
		super.Backward();
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		
		// \Post : Environment::CellContent(Backward(M),Row(M),Col(M)) ==
				// Environment::CellContent(M,Row(M),Col(M))
		if(!( getEnv().CellContent(getRow(), getCol()).equals(Environment_atPre.CellContent(getRow(), getCol()))  )) {
			throw new PostError(" Cell contents should not be modified ");
		}
		// \Post : Face(Backward(M)) = Face(M)
		if(!(  getFace().equals(Face_atPre) )) {
			throw new PostError(" Mobile's direction should not be modified after calling backward method  ");
		}
		// \Post : Face(M)=Dir.N
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M) + 1 )) ,
		// Environment::CellContent(Backward(M),H,W) == Environment::CellContent(M,H,W). 

		// 0< getHeight(M) < getHeight(M)-1 ==>
		// Environment::CellContent(Backward(M),Row(M)-1,Col(M)) ==
			// Environment::CellContent(M,Row(M)+1,Col(M))
		// CellContent(M, Row(M)+1, Col(M)) == No ==> Row(Backward(M)) = Row(M) + 1
		
		// Col(Backward(M)) = Col(M)

		if( Face_atPre.equals(Dir.N) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()+1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( (B_North) ");
						}
					}
				}
			}

			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
			if(!( getEnv().CellContent(getRow()-1, getCol()).equals(Environment_atPre.CellContent(getRow()+1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Backward method ");
			}
			
			if(getEnv().CellContent(Row_atPre+1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				
			if(!( getRow() == Row_atPre + 1  )) {
				throw new PostError(" Cell's row after calling Backward method not properly modified  ");
			}
			}
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" cell's column should not be modified after calling Backward method ");
			}
			
		}
		
		// \Post : Face(M)=Dir.S
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M) - 1 )) ,
		// Environment::CellContent(Backward(M),H,W) == Environment::CellContent(M,H,W). 

		// 0< getHeight(M) < getHeight(M)-1 ==>
		// Environment::CellContent(Backward(M),Row(M)+1,Col(M)) ==
			// Environment::CellContent(M,Row(M)-1,Col(M))
		// CellContent(M, Row(M)-1, Col(M)) == No ==> Row(Backward(M)) = Row(M) - 1
		
		// Col(Backward(M)) = Col(M)
		if( Face_atPre.equals(Dir.S) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()-1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError("  modification of unexpected cells content :( (B_South) ");
						}
					}
				}
			} 
			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
			
			if(!( getEnv().CellContent(getRow()+1, getCol()).equals(Environment_atPre.CellContent(getRow()-1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Backward method ");
			}

			if(getEnv().CellContent(Row_atPre-1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				
			if(!( getRow() == Row_atPre - 1  )) {
				throw new PostError(" Cell's row not properly modified after calling Backward method ");
			}
			}
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" cell's column should not be modified after calling backward method ");
			}
			
		}
		
		// \Post : Face(M)=Dir.E
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M) - 1 ) or H != Row(M) ) ,
		// Environment::CellContent(Backward(M),H,W) == Environment::CellContent(M,H,W). 

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(Backward(M),Row(M),Col(M) + 1) ==
			// Environment::CellContent(M,Row(M),Col(M) - 1)
		// CellContent(M, Row(M), Col(M)-1) == No ==> Col(Backward(M)) = Col(M) - 1
		
		// Row(Backward(M)) = Row(M)
		
		if( Face_atPre.equals(Dir.E) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()-1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError("  modification of unexpected cells content :( (B Est) ");
						}
					}
				}
			} 
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
			
			if(!( getEnv().CellContent(getRow(), getCol()+1).equals(Environment_atPre.CellContent(getRow(), getCol()-1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Backward method  ");
			}
			if(getEnv().CellContent(Row_atPre, Col_atPre-1).equals(Optional.of(TypeMob.NO))) {
				
			if(!( getCol() == Col_atPre - 1 )) {
				throw new PostError(" Cell's column not properly modified after calling Backward method ");
			}
			}
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" cell's row should not be modified after calling backward method ");
			}

			
		}
		
		// \Post : Face(M)=Dir.W
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M) + 1 ) or H != Row(M) ) ,
		// Environment::CellContent(Backward(M),H,W) == Environment::CellContent(M,H,W). 

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(Backward(M),Row(M),Col(M) - 1) ==
			// Environment::CellContent(M,Row(M),Col(M) + 1)
		// CellContent(M, Row(M), Col(M)+1) == No ==> Col(Backward(M)) = Col(M) + 1
		
		// Row(Backward(M)) = Row(M)
		
		if( Face_atPre.equals(Dir.W) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()+1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( (B West) ");
						}
					}
				}
			} 
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			
			if(!( getEnv().CellContent(getRow(), getCol()-1).equals(Environment_atPre.CellContent(getRow(), getCol()+1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Backward method ");
			}
			if(getEnv().CellContent(Row_atPre, Col_atPre+1).equals(Optional.of(TypeMob.NO))) {
				
			if(!( getCol() == Col_atPre + 1 )) {
				throw new PostError(" Cell's column not properly modified after calling Backward method ");
			}
			}
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" cell's row should not be modified after calling Backward method  ");
			}

			
		}
		
	}

	@Override
	public void StrafeL() {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		Dir Face_atPre = getFace();
		EnvironmentServ Environment_atPre = (EnvironmentServ) getEnv().clone();
		int Col_atPre = getCol();
		int Row_atPre = getRow();
		// ******* Traitement
		super.StrafeL();
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post : Face(StrafeL(M)) = Face(M)
		if(!( getFace().equals(Face_atPre) )) {
			throw new PostError(" Mobile's direction should not be modified after calling Strafel method ");
		}
		// \Post : Environment::CellContent(StrafeL(M),Row(M),Col(M)) ==
					// Environment::CellContent(M,Row(M),Col(M))
		if(!( getEnv().CellContent(getRow(), getCol()).equals(Environment_atPre.CellContent(getRow(), getCol()))  )) {
			throw new PostError(" Cell's Content should not be modified after calling Strafel method  ");
		}
		
		// \Post : Face(M)=Dir.N
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M) - 1 ) or H != Row(M) ) ,
		// Environment::CellContent(StrafeL(M),H,W) == Environment::CellContent(M,H,W) .
		
		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(StrafeL(M),Row(M),Col(M) + 1) ==
			// Environment::CellContent(M,Row(M),Col(M) - 1)
		// CellContent(M, Row(M), Col(M)-1) == No ==> Col(StrafeL(M)) = Col(M) - 1
		
		// Row(StrafeL(M)) = Row(M)
		
		if( Face_atPre.equals(Dir.N) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()-1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError("  modification of unexpected cells content :( ( North) ");
						}
					}
				}
			} 
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			if(!( getEnv().CellContent(getRow(), getCol()+1).equals(Environment_atPre.CellContent(getRow(), getCol()-1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Strafel method ");
			}
			if(getEnv().CellContent(Row_atPre, Col_atPre-1).equals(Optional.of(TypeMob.NO))) {
				if(!( getCol() == Col_atPre - 1 )) {
					throw new PostError(" Cell's column after calling Strafel method not properly modified  ");
				}
			}
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" cell's row should not be modified after calling Strafel method  ");
			}
			
			
		}
		
		// \Post : Face(M)=Dir.S
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M) + 1 ) or H != Row(M) ) ,
		// Environment::CellContent(StrafeL(M),H,W) == Environment::CellContent(M,H,W) . 

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(StrafeL(M),Row(M),Col(M) - 1) ==
			// Environment::CellContent(M,Row(M),Col(M) + 1)
		// CellContent(M, Row(M), Col(M)+1) == No ==> Col(StrafeL(M)) = Col(M) + 1
		
		// Row(StrafeL(M)) = Row(M)
		
		if( Face_atPre.equals(Dir.S) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()+1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" Modification of unexpected cells content :( ( South) ");
						}
					}
				}
			} 
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			if(!( getEnv().CellContent(getRow(), getCol()-1).equals(Environment_atPre.CellContent(getRow(), getCol()+1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Strafel method ");
			}
			if(getEnv().CellContent(Row_atPre, Col_atPre+1).equals(Optional.of(TypeMob.NO))) {
				if(!( getCol() == Col_atPre + 1 )) {
					throw new PostError("  Cell's column not properly modified after calling Strafel method ");
				}
			}
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" cell's row should not be modified after calling Strafel method ");
			}
			
			
		}
		
		// \Post : Face(M)=Dir.W
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M) + 1 )) ,
		// Environment::CellContent(StrafeL(M),H,W) == Environment::CellContent(M,H,W) . 

		// 0< getHeight(M) < getHeight(M)-1 ==>
		// Environment::CellContent(StrafeL(M),Row(M)-1,Col(M)) ==
			// Environment::CellContent(M,Row(M)+1,Col(M)) 
		// CellContent(M, Row(M)+1, Col(M)) == No ==> Row(StrafeL(M)) = Row(M) + 1
		
		// Col(StrafeL(M)) = Col(M)
		if( Face_atPre.equals(Dir.W) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()+1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( ");
						}
					}
				}
			} 

			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
			if(!( getEnv().CellContent(getRow()-1, getCol()).equals(Environment_atPre.CellContent(getRow()+1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Strafel method  ");
			}
			if(getEnv().CellContent(Row_atPre+1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				if(!( getRow() == Row_atPre + 1  )) {
					throw new PostError("  Cell's row method not properly modified after calling Strafel  ");
				}
			}
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" Cell's column should not be modified after calling Strafel method ");
			}
			
		}
		// \Post : Face(M)=Dir.E
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M) - 1 )) ,
		// Environment::CellContent(StrafeL(M),H,W) == Environment::CellContent(M,H,W). 

		// 0< getHeight(M) < getHeight(M)-1 ==>
		// Environment::CellContent(StrafeL(M),Row(M)+1,Col(M)) ==
			// Environment::CellContent(M,Row(M)-1,Col(M)) 
		// CellContent(M, Row(M)-1, Col(M)) == No ==> Row(StrafeL(M)) = Row(M) - 1
		
		// Col(StrafeL(M)) = Col(M)
		if( Face_atPre.equals(Dir.E) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()-1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError("  modification of unexpected cells content :(  ");
						}
					}
				}
			} 
			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
			if(!( getEnv().CellContent(getRow()+1, getCol()).equals(Environment_atPre.CellContent(getRow()-1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling Strafel method ");
			}
			if(getEnv().CellContent(Row_atPre-1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				if(!( getRow() == Row_atPre - 1  )) {
					throw new PostError(" Cell's row not properly modified after calling Strafel method ");
				}
			}
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" Cell's column should not be modified  after calling Strafel method ");
			}
			
		}
	}

	@Override
	public void StrafeR() {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		Dir Face_atPre = getFace();
		EnvironmentServ Environment_atPre = (EnvironmentServ) getEnv().clone();
		int Col_atPre = getCol();
		int Row_atPre = getRow();
		// ******* Traitement
		super.StrafeR();
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post : Environment::CellContent(StrafeR(M),Row(M),Col(M)) ==
			// Environment::CellContent(M,Row(M),Col(M))
		if(!( getEnv().CellContent(getRow(), getCol()).equals(Environment_atPre.CellContent(getRow(), getCol()))  )) {
			throw new PostError("Unexpected modification of cells content ");
		}
		// \Post : Face(StrafeR(M)) = Face(M)
		if(!( getFace().equals(Face_atPre) )) {
			throw new PostError(" Unexpected modfication of cell Face");
		}
		// \Post : Face(M)=Dir.N
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M) + 1 ) or H != Row(M) ) ,
		// Environment::CellContent(StrafeR(M),H,W) == Environment::CellContent(M,H,W) .

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(StrafeR(M),Row(M),Col(M) - 1) ==
			// Environment::CellContent(M,Row(M),Col(M) + 1)
		// CellContent(M, Row(M), Col(M)+1) == No ==> Col(StrafeR(M)) = Col(M) + 1
		
		// Row(StrafeR(M)) = Row(M)
		
		if( Face_atPre.equals(Dir.N) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()+1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError("  modification of unexpected cells content :(  ");
						}
					}
				}
			}
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			if(!( getEnv().CellContent(getRow(), getCol()-1).equals(Environment_atPre.CellContent(getRow(), getCol()+1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling StrafeR method ");
			}
			if(getEnv().CellContent(Row_atPre, Col_atPre+1).equals(Optional.of(TypeMob.NO))) {
				if(!( getCol() == Col_atPre + 1 )) {
					throw new PostError(" Cell's column not properly modified after calling StrafeR method ");
				}
			}
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" Cell's row should not be modified  after calling StrafeR method ");
			}
			
			
		}


		// \Post : Face(M)=Dir.S
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (( W != Col(M) and
		// W != Col(M) - 1 ) or H != Row(M) ) ,
		// Environment::CellContent(StrafeR(M),H,W) == Environment::CellContent(M,H,W) .

		// 0< getWidth(M) < getWidth(M)-1 ==>
		// Environment::CellContent(StrafeR(M),Row(M),Col(M) + 1) ==
			// Environment::CellContent(M,Row(M),Col(M) - 1)	
		// CellContent(M, Row(M), Col(M)-1) == No ==> Col(StrafeR(M)) = Col(M) - 1
		
		// Row(StrafeR(M)) = Row(M)
		
		if( Face_atPre.equals(Dir.S) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( ((W!=getCol()) && (W!=getCol()-1) ) || (H!=getRow()) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( ");
						}
					}
				}
			}
			if( 0 < getCol() && getCol() < getEnv().getWidth()-1 ) {
				
			if(!( getEnv().CellContent(getRow(), getCol()+1).equals(Environment_atPre.CellContent(getRow(), getCol()-1))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling StrafeR method ");
			}
			if(getEnv().CellContent(Row_atPre, Col_atPre-1).equals(Optional.of(TypeMob.NO))) {
				if(!( getCol() == Col_atPre - 1 )) {
					throw new PostError(" Cell's column not properly modified after calling StrafeR method ");
				}
			}
			}
			
			if(!( getRow() == Row_atPre )) {
				throw new PostError(" Cell's row should not be modified  after calling StrafeR method ");
			}
			
			
		}
		
		// \Post : Face(M)=Dir.W
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M) - 1 )) ,
		// Environment::CellContent(StrafeR(M),H,W) == Environment::CellContent(M,H,W). 

		// 0< getHeight(M) < getHeight(M)-1 ==>
		// Environment::CellContent(StrafeR(M),Row(M)+1,Col(M)) ==
			// Environment::CellContent(M,Row(M)-1,Col(M)) 
		// CellContent(M, Row(M)-1, Col(M)) == No ==> Row(StrafeR(M)) = Row(M) - 1
		
		// Col(StrafeR(M)) = Col(M)
		if( Face_atPre.equals(Dir.W) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()-1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( ");
						}
					}
				}
			} 
			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
			if(!( getEnv().CellContent(getRow()+1, getCol()).equals(Environment_atPre.CellContent(getRow()-1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling StrafeR method ");
			}
			if(getEnv().CellContent(Row_atPre-1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				if(!( getRow() == Row_atPre - 1  )) {
					throw new PostError(" Cell's row not properly modified after calling StrafeR method  ");
				}
			}
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError("  Cell's column should not be modified  after calling StrafeR method ");
			}
			
		}
		
		// \Post : Face(M)=Dir.E	
		// ==> forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] (W != Col(M) or (H
		// != Row(M) and H != Row(M) + 1 )) ,
		// Environment::CellContent(StrafeR(M),H,W) == Environment::CellContent(M,H,W) .

		// 0< getHeight(M) < getHeight(M)-1 ==>
		// Environment::CellContent(StrafeR(M),Row(M)-1,Col(M)) ==
			// Environment::CellContent(M,Row(M)+1,Col(M)) 
		// CellContent(M, Row(M)+1, Col(M)) == No ==> Row(StrafeR(M)) = Row(M) + 1
		
		// Col(StrafeR(M)) = Col(M)
		if( Face_atPre.equals(Dir.E) ) {
			for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
				for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
					
					if( (W!=getCol()) || ((H!=getRow())&&(H!=getRow()+1)) ) {
						if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
							throw new PostError(" modification of unexpected cells content :( ");
						}
					}
				}
			} 
			if( 0 < getRow() && getRow() < getEnv().getHeight()-1 ) {
				
			if(!( getEnv().CellContent(getRow()-1, getCol()).equals(Environment_atPre.CellContent(getRow()+1, getCol()))  )) {
				throw new PostError(" Content of source and destination cell should be permutted after calling StrafeR method ");
			}
			if(getEnv().CellContent(Row_atPre+1, Col_atPre).equals(Optional.of(TypeMob.NO))) {
				if(!( getRow() == Row_atPre + 1  )) {
					throw new PostError(" Cell's row not properly modified after calling StrafeR method ");
				}
			}
			}
			
			if(!( getCol() == Col_atPre  )) {
				throw new PostError(" Cell's column should not be modified  after calling StrafeR method ");
			}
			
		}
	}


	@Override
	public void TurnL() {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		Dir Face_atPre = getFace();
		EnvironmentServ Environment_atPre = (EnvironmentServ) getEnv().clone();
		int Col_atPre = getCol();
		int Row_atPre = getRow();
		// ******* Traitement
		super.TurnL();
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post :forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] ,
			// Environment::CellContent(TurnL(M),H,W) == Environment::CellContent(M,H,W).
		for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
			for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
				
					if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
						throw new PostError(" modification of unexpected cells content :( ");
					}
			}
		}
		// \Post :Row(TurnL(M)) = Row(M)
		if(!( getRow() == Row_atPre )) {
			throw new PostError("  Cell's row should not be modified  after calling TurnL method ");
		}
		// \Post :Col(TurnL(M)) = Col(M)
		if(!( getCol() == Col_atPre)) {
			throw new PostError(" Cell's column should not be modified  after calling TurnL method ");
		}
		
		// \Post : Face(M)=Dir.N  ==>  Face(TurnL(M)) = Dir.W

		if( Face_atPre.equals(Dir.N) ) {
			
			if(!( getFace().equals(Dir.W) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}
		
		// \Post : Face(M)=Dir.S  ==>  Face(TurnL(M)) = Dir.E 

		if( Face_atPre.equals(Dir.S) ) {
			System.out.println(Face_atPre +"   "+ getFace());
			if(!( getFace().equals(Dir.E) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}
		// \Post : Face(M)=Dir.E ==>  Face(TurnL(M)) = Dir.N 

		if( Face_atPre.equals(Dir.E) ) {
			
			if(!( getFace().equals(Dir.N) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}
		// \Post : Face(M)=Dir.W ==>  Face(TurnL(M)) = Dir.S 

		if( Face_atPre.equals(Dir.W) ) {
			
			if(!( getFace().equals(Dir.S) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}
	}

	@Override
	public void TurnR() {
		// ******* Pré_Condition
		// ******* Pré_CheckInv
		// ******* Capteur
		Dir Face_atPre = getFace();
		EnvironmentServ Environment_atPre = (EnvironmentServ) getEnv().clone();
		int Col_atPre = getCol();
		int Row_atPre = getRow();
		// ******* Traitement
		super.TurnR();
		// ******* Post_Inv
		CheckInv();
		// ******* Post_Condition
		// \Post :forall H ∈ [0; Height(M)-1] forall W ∈ [0; Width(M)-1] ,
			// Environment::CellContent(TurnR(M),H,W) == Environment::CellContent(M,H,W)
		for (int H = 0; H <= (getEnv().getHeight()-1); H++) {
			for (int W = 0; W <= (getEnv().getWidth()-1); W++) {
				
					if(!( getEnv().CellContent(H, W).equals(Environment_atPre.CellContent(H, W)) )) {
						throw new PostError(" modification of unexpected cells content :( ");
					}
			}
		}
		// \Post :Row(TurnR(M)) = Row(M)
		if(!( getRow() == Row_atPre )) {
			throw new PostError(" Cell's row should not be modified  after calling TurnR method ");
		}
		// \Post :Col(TurnR(M)) = Col(M)
		if(!( getCol() == Col_atPre)) {
			throw new PostError(" Cell's column should not be modified  after calling TurnR method ");
		}
		
		// \Post : Face(M)=Dir.N ==>   Face(TurnR(M)) = Dir.E 
		if( Face_atPre.equals(Dir.N) ) {
			
			if(!( getFace().equals(Dir.E) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}

		// \Post : Face(M)=Dir.S  ==>  Face(TurnR(M)) = Dir.W 
		if( Face_atPre.equals(Dir.S) ) {
			System.out.println(Face_atPre + "  "+  getFace());
			if(!( getFace().equals(Dir.W) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}

		// \Post : Face(M)=Dir.E ==>  Face(TurnR(M)) = Dir.S 
		if( Face_atPre.equals(Dir.E) ) {
			
			if(!( getFace().equals(Dir.S) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}

		// \Post : Face(M)=Dir.W ==>  Face(TurnR(M)) = Dir.N 
		if( Face_atPre.equals(Dir.W) ) {
			
			if(!( getFace().equals(Dir.N) )) {
				throw new PostError(" Mobile's direction not properly modified after calling TurnL method ");
			}
						
		}

	}

	
}
