package services;

import types.Cell;
import types.Command;
import types.Dir;
import types.TypeMob;

public interface PlayerServ extends EntityServ {

	/** observateur **/
	// LastCom : Command
	public Command getLastCom();
	public void setLastCom(Command c);
	
	public void init (EnvironmentServ e, int h, int w, Dir D, int hp);
	
	// Content int * int => TypeMob
	public TypeMob getContent(int x, int y);
	
	// Nature int * int => Cell 
	public Cell getNature(int x, int y);
	
	// isViewable : int * int => Boolean
	public boolean isViewable(int x, int y);
	
	@Override
	public void step();
}
