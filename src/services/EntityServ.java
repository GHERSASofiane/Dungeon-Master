package services;
import types.*;

public interface EntityServ extends MobServ{

	//*******Observator :
		 
	public int getHp ();

	//********Constructor :

	public void init (EnvironmentServ e, int h, int w, Dir D, int hp);
	
	//********Operators :
	public void step ();				
		
	//********Observations :
}
