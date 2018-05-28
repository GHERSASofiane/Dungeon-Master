package decorators;

import services.EntityServ;
import services.PlayerServ;
import types.Cell;
import types.Command;
import types.TypeMob;

public class PlayerDec extends EntityDec implements PlayerServ {

	private PlayerServ delegate;
	
	public PlayerDec(PlayerServ p) {
		super(p);
		this.delegate = p;
	}

	@Override
	public Command getLastCom() {
		return this.delegate.getLastCom();
	}

	@Override
	public TypeMob getContent(int x, int y) {
		return this.delegate.getContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {
		return this.delegate.getNature(x, y);
	}

	@Override
	public boolean isViewable(int x, int y) {
		return this.delegate.isViewable(x, y);
	}
	
	@Override
	public void step() {
		this.delegate.step();
	}

	@Override
	public void setLastCom(Command c) {
		this.delegate.setLastCom(c);
	}

}
