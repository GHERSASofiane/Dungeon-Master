package Impl;

import services.EditMapServ;
import services.EnvironmentServ;
import services.MapServ;
import types.Cell;

public class EditMapImpl extends MapImpl implements EditMapServ{
	
	@Override
	public Object clone(){
	
		EditMapServ res = null;
		res = ((EditMapImpl)super.clone());
		return res;
	}
	
	public EditMapImpl() {
		super();
	}
	
	@Override
	public boolean isReachable(int h1, int w1, int h2, int w2 ) {
	 
		if( (w1==w2) && (h1==h2) )return true;
		
		if( (super.CellNature(h1, w1).equals(Cell.WLL)) || (super.CellNature(h2, w2).equals(Cell.WLL)) )return false;
		
		if(h1<h2) {
			if((0<= h1+1 && h1+1 < super.getHeight())) {
				if( (!super.CellNature(h1+1, w1).equals(Cell.WLL)))return isReachable(h1+1, w1, h2, w2);
				}
		}else if (h1>h2){
			if((0<= h1-1 && h1-1 < super.getHeight())) {
				if( (!super.CellNature(h1-1, w1).equals(Cell.WLL)))return isReachable(h1-1, w1, h2, w2);
				}
		}
		
		
		
		if(w1<w2) {
			if((0<= w1+1 && w1+1 < super.getWidth())) {
				if( (!super.CellNature(h1, w1+1).equals(Cell.WLL)))return isReachable(h1, w1+1, h2, w2);
				}
		}else if (w1>w2){
			if((0<= w1-1 && w1-1 <super.getWidth())) {
				if( (!super.CellNature(h1, w1-1).equals(Cell.WLL)))return isReachable(h1, w1-1, h2, w2);
				}
		}
		
		return false;		
	}
	
	@Override
	public boolean  isReady() {
		
		int wIn=-1, hIn=-1, wOut=-1, hOut=-1;
		boolean existsIn=false, existsOut=false;
		
		for (int H=0; H<super.getHeight(); H++)
		{
			for(int W=0; W<super.getWidth(); W++)
			{
				if(super.CellNature(H, W).equals(Cell.IN))
				{	
				if(existsIn)return false;
				existsIn=true;
				wIn=W; hIn=H;  
				}
				
				if(super.CellNature(H, W).equals(Cell.OUT))
				{	
				if(existsOut)return false;
				existsOut=true;
				wOut=W; hOut=H;  
				}

				if(super.CellNature(H, W).equals(Cell.DNC) || super.CellNature(H, W).equals(Cell.DNO))
				{
					if(0<H && H<super.getHeight()-1)
					{
						if( (!super.CellNature(H+1, W).equals(Cell.EMP))
								|| (!super.CellNature(H-1, W).equals(Cell.EMP)) ) return false;
					}else {
						return false;
					}
					
					if(0<W && W<super.getWidth()-1)
					{
						if( (!super.CellNature(H, W+1).equals(Cell.WLL))
								|| (!super.CellNature(H, W-1).equals(Cell.WLL)) ) return false;
					}else {
						return false;
					}
				}
				

				if(super.CellNature(H, W).equals(Cell.DWC) || super.CellNature(H, W).equals(Cell.DWO))
				{
					if(0<H && H<super.getHeight()-1)
					{
						if( (!super.CellNature(H+1, W).equals(Cell.WLL))
								|| (!super.CellNature(H-1, W).equals(Cell.WLL)) ) return false;
					}else {
						return false;
					}
					
					if(0<W && W<super.getWidth()-1)
					{
						if( (!super.CellNature(H, W+1).equals(Cell.EMP))
								|| (!super.CellNature(H, W-1).equals(Cell.EMP)) ) return false;
					}else {
						return false;
					}
				}
			}
		}
		
		
		if(existsIn && existsOut && isReachable(hIn, wIn, hOut, wOut) ) {
			return true;
		}else {
			return false;
		}
		
		
	}
	 
	@Override
	public void SetNature(int H, int W, Cell c) {
		
		super.getGrille().setCell(H, W, c);
		
	}
	
	
}
