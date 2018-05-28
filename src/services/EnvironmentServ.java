package services;

import java.util.Optional;

import Content.Content;
import types.TypeMob;

public interface EnvironmentServ extends MapServ, Cloneable{
	
	public Optional<TypeMob> CellContent(int h, int w);
	
	public void SetCellContent(int h, int w, TypeMob type);
	
	@Override
	public void CloseDoor(int h, int w);
	
	public Content getContent();
	
	@Override
	public Object clone();
}
