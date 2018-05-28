package decorators;


import Grille.Grille;
import Grille.MyGrille;
import services.MapServ;
import types.Cell;

public class MapDec implements MapServ{

	private MapServ delegate ;
	public MapDec(MapServ D) {
		this.delegate = D;
	}
	
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}
	@Override
	public int getWidth() {
		return delegate.getWidth();
	}
	@Override
	public Grille getGrille() {
		return delegate.getGrille();
	}
	
	@Override
	public Cell CellNature(int h, int w) {
		return delegate.CellNature(h, w);
	}
	@Override
	public void init(int h, int w, MyGrille g) {
		delegate.init(h, w, g);
		
	}
	@Override
	public void OpenDoor(int h, int w) {
		delegate.OpenDoor(h, w);		
	}
	@Override
	public void CloseDoor(int h, int w) {
		delegate.CloseDoor(h, w);		
	}
	
	@Override
	public Object clone(){
		return delegate.clone();
	}
	
	
}
