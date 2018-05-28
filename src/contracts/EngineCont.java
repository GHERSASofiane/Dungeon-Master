package contracts;

import java.util.Vector;

import Errors.InvarError;
import Errors.PostError;
import Errors.PreError;
import decorators.EngineDec;
import services.EngineServ;
import services.EntityServ;
import services.EnvironmentServ;

public class EngineCont extends EngineDec {

	public EngineCont(EngineServ d) {
		super(d);
	}
	

	/**   invariant **/
	public void checkInv() {
		
//	forall i in [0;size(Entities(E))-1], Entity::Envi(getEntity(E,i))=Envi(E)
//			forall i in [0;size(Entities(E))-1], Entity::Col(getEntity(E,i))=x
//			and Entity::Row(getEntity(E,i))=y
//			implies Environment::CellContent(Envi(E),x,y) = getEntity(E,i)

		EnvironmentServ Envi_AtPre = getEnvi();
		
		for (int i = 0; i < getEntities().size() ; i++) {
			if(!( getEntity(i).getEnv().equals( Envi_AtPre ) )) {
				throw new InvarError(" Environement should not be modified after calling getEntity ");
			}
			int y = getEntity(i).getCol();
			int x = getEntity(i).getRow();
			if(!( getEnvi().CellContent(x, y).equals( getEntity(i) ) )) {
				throw new InvarError(" Cells contents should not be modified ");
			}
		}
	}
	
	/**   Observator **/
	@Override
	public EnvironmentServ getEnvi() {
		EnvironmentServ res;
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		res = super.getEnvi();
		// *******  Post_Inv 
		// *******  Post_Condition

		return res;
	}

	@Override
	public Vector<EntityServ> getEntities()  {
		Vector<EntityServ> res;
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		res = super.getEntities();
		// *******  Post_Inv 
		// *******  Post_Condition

		return res;
	}

	@Override
	public EntityServ getEntity(int rng) {
		EntityServ res;
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		res = super.getEntity(rng);
		// *******  Post_Inv 
		// *******  Post_Condition
		
		return res;
	}
	
	/**   Constructor **/
	@Override
	public void init(EnvironmentServ env) {
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		super.init(env);
		// *******  Post_Inv  
		// *******  Post_Condition
		// \Post: getEnvi( init( E ) ) = E
		if(!( getEnvi().equals(env) )) {
			throw new PostError(" Environement after init should be equal to the one passed in parameter ");
		}
		
	}
	
	/**   Operator **/
	@Override
	public void removeEntity(int rng) {
		// *******  Pré_Condition
//		\pre : removeEntity(E,i) requires 0 ≤ i < size(Entities(E))
		if(!( 0<=rng && rng<getEntities().size() )) {
			throw new PreError(" parameter should be in [0,"+ getEntities().size()+"[  ");
		}
		
		// *******  Pré_CheckInv
		checkInv();
		// *******  Capteur
		int size_AtPre = getEntities().size();
		Vector<EntityServ>  Entity_AtPre = (Vector<EntityServ>) getEntities().clone();
		// *******  Traitement
		super.removeEntity(rng);
		// *******  Post_Inv 
		checkInv();
		// *******  Post_Condition
//		\post : size(Entities(removeEntity(E,i))) = size(Entities(E)) - 1
		if(!( getEntities().size() == size_AtPre-1 )) {
			throw new PostError(" vector size do not change after deletion ");
		}
//		\post : forall k in [0,i-1], getEntity(removeEntity(E,i),k)) = getEntity(E,k)
		for (int i = 0; i <= rng-1 ; i++) {
			if(!( Entity_AtPre.get(i).equals(getEntity(i)) )) {
				throw new PostError(" change in the unexpected vector  ");
			} 
		}
//		\post : forall k in [i,size(Entities(E))-2], getEntity(removeEntity(E,i),k)) = getEntity(E,k+1)
		for (int i = rng; i <= getEntities().size()-2 ; i++) {
			if(!( Entity_AtPre.get(i).equals(getEntity(i)) )) {
				throw new PostError(" change in the unexpected vector  ");
			} 
		}
		
		
	}

	@Override
	public void addEntity(EntityServ entit) {
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		int size_AtPre = getEntities().size();
		Vector<EntityServ>  Entity_AtPre = (Vector<EntityServ>) getEntities().clone();
		// *******  Traitement
		super.addEntity(entit);
		// *******  Post_Inv  
		checkInv();
		// *******  Post_Condition
//		\post : size(Entities(addEntity(E,e))) = size(Entities(E)) + 1
		if(!( getEntities().size() == size_AtPre + 1 )) {
			throw new PostError(" vector size do not change after deletion ");
		}
//		\post : forall k in [0,size(Entities(E))-1], getEntity(addEntity(E,e),k)) = getEntity(E,k)
		for (int i = 0; i <= getEntities().size()-1 ; i++) {
			if(!( Entity_AtPre.get(i).equals(getEntity(i)) )) {
				throw new PostError(" change in the unexpected vector  ");
			} 
		}
//		\post : getEntity(addEntity(E,e),size(Entities(E))) = e
		if(!( getEntity( getEntities().size() ).equals(entit) )) {
			throw new PostError(" the element has not been added in the vector  ");
		} 
		
	}

	@Override
	public void step() {
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		 super.step();
		// *******  Post_Inv  
		// *******  Post_Condition
		
	}
	

}
