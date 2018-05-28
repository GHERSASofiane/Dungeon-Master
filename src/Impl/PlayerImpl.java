package Impl;

import Errors.PreError;
import services.EnvironmentServ;
import services.PlayerServ;
import types.Cell;
import types.Command;
import types.Dir;
import types.TypeMob;

public class PlayerImpl extends EntityImpl implements PlayerServ{
	/** observateur **/
	
	// LastCom : Command
	private Command LastCom;

	@Override
	public Command getLastCom() {
		return this.LastCom;
	}
	
	public void init (EnvironmentServ e, int h, int w, Dir D, int hp) {

		super.init(e, h, w, D, hp);
	}
	
	@Override
	public TypeMob getContent(int x, int y) {
		
		TypeMob res = TypeMob.NO;
		int r = getRow();
		int c = getCol();
		
		switch (super.getFace()) {
		case N:
			r -= x;
			c += y;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
				res = super.getEnv().CellContent(r, c).get();	
			}
			
			break;
		case S:
			r += x;
			c -= y;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellContent(r, c).get();
			}
						
			break;
		case E:
			r += y;
			c += x;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellContent(r, c).get();
			}
			break;

		default:
			r -= y;
			c -= x;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellContent(r, c).get();
			}
			break;
		}
		
		return res;
	}

	@Override
	public Cell getNature(int x, int y) {
		
		Cell res = super.getEnv().CellNature(getRow(), getCol());
		int r = getRow();
		int c = getCol();
		
		switch (super.getFace()) {
		case N:
			r -= x;
			c += y;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellNature(r, c);
			}
			break;
		case S:
			r += x;
			c -= y;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellNature(r, c);
			}
			break;
		case E:
			r += y;
			c += x;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellNature(r, c);
			}
			break;

		default:
			r -= y;
			c -= x;
			if(0<=r && r<getEnv().getHeight() && 0<=c && c<getEnv().getWidth() ) {
			res = super.getEnv().CellNature(r, c);
			}
			break;
		}
		return res;
		 
	}

	@Override
	public boolean isViewable(int x, int y) {

		try {
			
		Cell tmp, tmp1, tmp2, tmp3 ;
		
		if ( !( ((y == -1) && (x ==  3)) || ((y ==  0) && (x ==  3)) || ((y ==  1) && (x ==  3))||
			    ((y == -1) && (x ==  2)) || ((y ==  0) && (x ==  2)) || ((y ==  1) && (x ==  2))||
			    ((y == -1) && (x ==  1)) || ((y ==  0) && (x ==  1)) || ((y ==  1) && (x ==  1))
		   )){
			return false;
		}
		else{
			if ( ((y == -1) && (x ==  1)) || ((y ==  0) && (x ==  1)) || ((y ==  1) && (x ==  1)) ){
				return true;
			}
			else {

				if( ((y ==  0) && (x ==  2)) ) {
					tmp = getNature(1, 0);
					if( tmp.equals(Cell.DNC) || tmp.equals(Cell.DWC) || tmp.equals(Cell.WLL) )
						return false;
					else return true;

				}
				if(  ((y == -1) && (x ==  2)) ) {
					tmp = getNature(1, -1);
					tmp1 = getNature(1, 0);
					if( tmp.equals(Cell.DNC) || tmp.equals(Cell.DWC) || tmp.equals(Cell.WLL) 
						|| tmp1.equals(Cell.DNC) || tmp1.equals(Cell.DWC) || tmp1.equals(Cell.WLL) )
						return false;
					else return true;

				}
				if( ((y ==  1) && (x ==  2)) ) {
					tmp = getNature(1, 1);
					tmp1 = getNature(1, 0);
					if( tmp.equals(Cell.DNC) || tmp.equals(Cell.DWC) || tmp.equals(Cell.WLL) 
						|| tmp1.equals(Cell.DNC) || tmp1.equals(Cell.DWC) || tmp1.equals(Cell.WLL) )
						return false;
					else return true;

				}
				if( ((y == -1) && (x ==  3)) ) {
					tmp = getNature(1, -1);
					tmp1 = getNature(2, -1);
					tmp2 = getNature(1, 0);
					tmp3 = getNature(2, 0);
					if( tmp.equals(Cell.DNC) || tmp.equals(Cell.DWC) || tmp.equals(Cell.WLL) 
						|| tmp1.equals(Cell.DNC) || tmp1.equals(Cell.DWC) || tmp1.equals(Cell.WLL)
						|| tmp2.equals(Cell.DNC) || tmp2.equals(Cell.DWC) || tmp2.equals(Cell.WLL) 
						|| tmp3.equals(Cell.DNC) || tmp3.equals(Cell.DWC) || tmp3.equals(Cell.WLL))
						return false;
					else return true;

				}
				if( ((y ==  1) && (x ==  3)) ) {
					tmp = getNature(1, 1);
					tmp1 = getNature(2, 1);
					tmp2 = getNature(1, 0);
					tmp3 = getNature(2, 0);
					if( tmp.equals(Cell.DNC) || tmp.equals(Cell.DWC) || tmp.equals(Cell.WLL) 
						|| tmp1.equals(Cell.DNC) || tmp1.equals(Cell.DWC) || tmp1.equals(Cell.WLL)
						|| tmp2.equals(Cell.DNC) || tmp2.equals(Cell.DWC) || tmp2.equals(Cell.WLL) 
						|| tmp3.equals(Cell.DNC) || tmp3.equals(Cell.DWC) || tmp3.equals(Cell.WLL))
						return false;
					else return true;

				}
				if( ((y ==  0) && (x ==  3)) ) {
					tmp2 = getNature(1, 0);
					tmp3 = getNature(2, 0);
					if( tmp2.equals(Cell.DNC) || tmp2.equals(Cell.DWC) || tmp2.equals(Cell.WLL) 
						|| tmp3.equals(Cell.DNC) || tmp3.equals(Cell.DWC) || tmp3.equals(Cell.WLL))
						return false;
					else return true;

				}
				
				
				}
			
				
				
			}
			
		return false;

		} catch (Exception e) {
			return false;
		}
	 }
		
	@Override
	public void step() {
		
		switch (this.LastCom) {
		case FW:
			super.Forward();this.LastCom=null;
			break;
		case BW:
			super.Backward();this.LastCom=null;
			break;
		case SL:
			super.StrafeL();this.LastCom=null;
			break;
		case SR:
			super.StrafeR();this.LastCom=null;
			break;
		case TL:
			super.TurnL();this.LastCom=null;
			break;
		case TR:
			super.TurnR();this.LastCom=null;
			break;
		default:
			break;
		}
		
	}

	@Override
	public void setLastCom(Command c) {
		this.LastCom = c;
	}
}
