package decorators;

import services.EnvironmentServ;
import services.MobServ;
import types.Dir;

public class MobDec implements MobServ {

	private MobServ delegate;
	
	public MobDec(MobServ d) {
		this.delegate = d;
	}
	

	@Override
	public EnvironmentServ getEnv() {
		return this.delegate.getEnv();
	}


	@Override
	public int getCol() {
		return this.delegate.getCol();
	}


	@Override
	public int getRow() {
		return this.delegate.getRow();
	}


	@Override
	public Dir getFace() {
		return this.delegate.getFace();
	}

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir d) {
		this.delegate.init(e, h, w, d);
		
	}

	@Override
	public void Forward() {
		this.delegate.Forward();
		
	}

	@Override
	public void Backward() {
		this.delegate.Backward();
		
	}

	@Override
	public void TurnL() {
		this.delegate.TurnL();
		
	}

	@Override
	public void TurnR() {
		this.delegate.TurnR();
		
	}

	@Override
	public void StrafeL() {
		this.delegate.StrafeL();
		
	}

	@Override
	public void StrafeR() {
		this.delegate.StrafeR();
		
	}


}
