package contracts;

import Errors.PreError;
import services.EntityServ;
import services.EnvironmentServ;
import types.Dir;

public class CowCont extends EntityCont{

	public CowCont(EntityServ d) {
		super(d);
	}
	

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir d, int hp) {
		// \pre init(E,x,y,D,h) requires 4 ≥ h ≥ 3
		if(!( 3 <= hp && hp <= 4 )) {
			throw new PreError("  3 <= hp && hp <= 4 ");
		}
		super.init(e, h, w, d, hp);
		
	}
	
	@Override
	public void step() {
		
		super.step();
		
	}

}
