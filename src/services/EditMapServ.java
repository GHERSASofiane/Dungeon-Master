package services;

import types.Cell;

public interface EditMapServ extends MapServ, Cloneable{

	// Invariant
	// \Inv: isReachable(w1, h1, w2, h2) = exists P in Array[int,int], P[0] = (w1, h1) and P[size(P)-1] = ( w2, h2)
		//	and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s)*(u−s) + (v−t)*(v−t) = 1
		//	and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(u,v) != WLL
	
	// \Inv: isReady() = exists xi,yi,xo,yo in int ,
		//	CellNature(xi,yi) = IN and CellNature(xo,yo) = OUT
		//	and isReachable(xi,yi,xo,yo)
		//	and forall x,y in int,
		  //	 x != xi or y != yi implies CellNature(x,y) != IN
		//	and forall x,y in int,
		 //	 x != xo or y != yo implies CellNature(x,y) != OUT
		//	forall x,y in int, CellNature(x,y) ∈ { DNO, DNC} implies
		   //	CellNature(x+1,y) = CellNature(x-1,y) = EMP and
		   //	CellNature(x,y-1) = CellNature(x,y+1) = WLL
		//	forall x,y in int, CellNature(x,y) ∈ { DWO, DWC} implies
			//	CellNature(x+1,y) = CellNature(x-1,y) = WLL and
			//	CellNature(x,y-1) = CellNature(x,y+1) = EMP
	
	// Observators
	public boolean isReachable(int h1, int w1, int h2, int w2 );
	
	public boolean  isReady();
	
	// Operators
	public void SetNature(int h, int w, Cell c);
	
	@Override
	public Object clone();
	
}
