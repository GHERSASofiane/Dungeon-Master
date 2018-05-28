package decorators;

import services.EntityServ;
import services.EnvironmentServ;
import types.Dir;

public class EntityDec extends MobDec implements EntityServ{

	private EntityServ delegate;
	
	public EntityDec(EntityServ d) {
		super(d);
		this.delegate = d;
	}
	
	@Override
	public int getHp() {
		return this.delegate.getHp();
	}

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir D, int hp) {
		this.delegate.init(e, h, w, D, hp);
		
	}

	@Override
	public void step() {
		this.delegate.step();
		
	}

}
