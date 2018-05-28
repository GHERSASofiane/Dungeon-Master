package Impl;


import java.util.Optional;

import Content.Content;
import Content.MyContent;
import services.EnvironmentServ;
import types.TypeMob;

public class EnvironmentImpl extends MapImpl implements EnvironmentServ{

	private Content myContent;
	
	public EnvironmentImpl( Content con ) {
		super();
		this.myContent = con;
	}

	@Override
	public Optional<TypeMob> CellContent(int h, int w) {
		return this.myContent.getContent(h, w);
	}
	
	@Override
	public void SetCellContent(int h, int w, TypeMob type) {
		this.myContent.setContent(h, w, type);
	}
	
	@Override
	public void CloseDoor(int h, int w) {
		super.CloseDoor(h, w);
	}
	
	public Content getContent() {
		return this.myContent;
	}
	
	@Override
	public Object clone(){
	
		EnvironmentServ res = null;
		res = ((EnvironmentImpl)super.clone());
		return res;
	}
	
}
