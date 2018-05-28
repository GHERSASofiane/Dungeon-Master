package decorators;

import java.util.Vector;

import services.EngineServ;
import services.EntityServ;
import services.EnvironmentServ;

public class EngineDec implements EngineServ {

	private EngineServ delegate;
	
	public EngineDec(EngineServ d) {
		this.delegate = d;
	}

	@Override
	public EnvironmentServ getEnvi() {
		return this.delegate.getEnvi();
	}

	@Override
	public Vector<EntityServ> getEntities() {
		return this.delegate.getEntities();
	}

	@Override
	public EntityServ getEntity(int rng) {
		return this.delegate.getEntity(rng);
	}

	@Override
	public void init(EnvironmentServ env) {
		this.delegate.init(env);
		
	}

	@Override
	public void removeEntity(int rng) {
		this.delegate.removeEntity(rng);
		
	}

	@Override
	public void addEntity(EntityServ entit) {
		this.delegate.addEntity(entit);
		
	}

	@Override
	public void step() {
		this.delegate.step();
		
	}
	
}
