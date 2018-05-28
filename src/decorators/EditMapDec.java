package decorators;

import services.EditMapServ;
import types.Cell;

public class EditMapDec extends MapDec implements EditMapServ {

	private EditMapServ delegate;
	public EditMapDec(EditMapServ D) {
		super(D);
		this.delegate = D;
	}
	
	@Override
	public boolean isReachable(int h1, int w1, int h2, int w2) {
		return delegate.isReachable(h1, w1, h2, w2);
	}
	
	@Override
	public boolean isReady() {
		return delegate.isReady();
	}
	
	@Override
	public void SetNature(int h, int w, Cell c) {
		delegate.SetNature(h, w, c);
		
	}
	
	@Override
	public Object clone(){
		return delegate.clone();
	}
	
	
	
}
