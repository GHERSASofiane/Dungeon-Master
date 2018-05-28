package Impl;

import services.EntityServ;
import services.EnvironmentServ;
import types.Dir;

public class EntityImpl extends MobImpl implements EntityServ {
	
	private int Hp; 
	
	
	@Override
	public int getHp() {
		
		return this.Hp;
	}

	@Override
	public void step() {
		// Nothing will be defined by the classes son 
	}

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir D, int hp) {
		super.init(e, h, w, D);
		this.Hp = hp;	
	}

}
