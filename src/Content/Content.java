package Content;

import java.util.Optional;

import types.TypeMob;

public interface Content extends Cloneable{

	public Optional<TypeMob> getContent(int h, int w);
	public void setContent(int h, int w, TypeMob c);
	public Object clone();
	
}
