package services;

import types.Dir;

public interface CowServ extends EntityServ{
	
	public void init (EnvironmentServ e, int h, int w, Dir D, int hp);

}
