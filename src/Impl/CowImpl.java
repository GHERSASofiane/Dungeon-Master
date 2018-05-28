package Impl;

import java.util.Random;

import services.CowServ;
import services.EnvironmentServ;
import types.Dir;

public class CowImpl extends EntityImpl implements CowServ{

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir d, int hp) {
		super.init(e, h, w, d, hp);
	}
	
	@Override
	public void step() {
		Random rn = new Random();
		int cmd = rn.nextInt(7)+1;
		
		switch (cmd) {
		case 1:
			super.Forward();
			break;
		case 2:
			super.Backward();
			break;
		case 3:
			super.StrafeL();
			break;
		case 4:
			super.StrafeR();
			break;
		case 5:
			super.TurnL();
			break;
		default:
			super.TurnR();
			break;
		}
		
	}
	
}
