package Impl;

import java.util.Vector;

import contracts.CowCont;
import contracts.PlayerCont;
import decorators.CowDec;
import decorators.PlayerDec;
import services.CowServ;
import services.EngineServ;
import services.EntityServ;
import services.EnvironmentServ;
import services.PlayerServ;
import types.Dir;
import types.TypeMob;

public class EngineImpl implements EngineServ{

	private EnvironmentServ Envi;
	private Vector<EntityServ> Entities = new Vector<>();

	@Override
	public void init(EnvironmentServ env) {
		
		this.Envi = env;
		for (int H = 0; H <= (this.Envi.getHeight()-1); H++) {
			for (int W = 0; W <= (this.Envi.getWidth()-1); W++) {
				 
				TypeMob res = this.Envi.CellContent(H, W).get();
				
				switch (res) {
				case joueur:
					PlayerServ plaS = new PlayerImpl();
					PlayerDec plaD = new PlayerCont(plaS);
					plaD.init(getEnvi(), H, W, Dir.N, 100);
					addEntity( plaD );
					break;
				case monstre:
					CowServ monstreS = new CowImpl();
					CowCont monstreD = new CowCont(monstreS);
					monstreD.init(getEnvi(), H, W, Dir.N, 3);
					addEntity( monstreD );
					break;
				case Cow:
					CowServ cowS = new CowImpl();
					CowCont cowD = new CowCont(cowS);
					cowD.init(getEnvi(), H, W, Dir.N, 3);
					addEntity( cowD );
					break;
				default:
					break;
				}
				
			}	
		}
	}
	
	@Override
	public EnvironmentServ getEnvi() {
		return this.Envi;
	}

	@Override
	public Vector<EntityServ> getEntities() {
		return this.Entities;
	}

	@Override
	public EntityServ getEntity(int rng) {
		return this.Entities.elementAt(rng);
	}

	@Override
	public void removeEntity(int rng) {
		this.Entities.remove(rng);
		
	}

	@Override
	public void addEntity(EntityServ entit) {
		this.Entities.addElement(entit);		
	}

	@Override
	public void step() {
		 
		for (int i = 0; i < getEntities().size(); i++) {
			 
			if (getEntity(i).getHp() <= 0) {
				removeEntity(i);
			} else {
				if(getEntity(i).getClass().getName().equals("contracts.PlayerCont")) {
					if( ((PlayerCont)getEntity(i)).getLastCom() != null )
						getEntity(i).step();
				}else {
					getEntity(i).step();
				}
				
			}
			
		}
	}
	
}
