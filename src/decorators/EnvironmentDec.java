package decorators;

import java.util.Optional;

import Content.Content;
import services.EnvironmentServ;
import types.TypeMob;

public class EnvironmentDec extends MapDec implements EnvironmentServ {

	private EnvironmentServ delegate;
	public EnvironmentDec(EnvironmentServ D) {
		super(D);
		this.delegate = D;
	}
	
	@Override
	public Optional<TypeMob> CellContent(int h, int w) {
		return this.delegate.CellContent(h, w);
	}
	
	@Override
	public void SetCellContent(int h, int w, TypeMob type) {
		this.delegate.SetCellContent(h, w, type);
	}
	
	@Override
	public void CloseDoor(int h, int w) {
		this.delegate.CloseDoor(h, w);
	}
	
	@Override
	public Object clone(){
		return delegate.clone();
	}

	@Override
	public Content getContent() {
		return this.delegate.getContent();
	}
}
