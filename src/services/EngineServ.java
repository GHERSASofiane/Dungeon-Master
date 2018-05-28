package services;

import java.util.Vector;

public interface EngineServ {

	/**   invariant **/
	
	/**   Observator **/
//	Envi: [Engine] → Environment
	public EnvironmentServ getEnvi();
	
//	Entities: [Engine] → Array[Entity]
	public Vector<EntityServ> getEntities();
	
//	getEntity: [Engine] × int → Entity
	public EntityServ getEntity(int rng);
	
	/**   Constructor **/
// init: Environment → [Engine]
	public void init(EnvironmentServ env);
	
	/**   Operator **/
	public void removeEntity(int rng);
	
	public void addEntity(EntityServ entit);
	
	public void step();
	
}
