package decorators;

import services.CowServ;
import services.EnvironmentServ;
import types.Dir;

public class CowDec extends EntityDec implements CowServ {

	public CowDec(CowServ d) {
		super(d);
	}
	
	@Override
	public void init(EnvironmentServ e, int h, int w, Dir d, int hp) {
		super.init(e, h, w, d, hp);
	}
	
	@Override
	public void step() {
		super.step();
	}
}
