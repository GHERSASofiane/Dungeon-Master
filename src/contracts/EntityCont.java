package contracts;

import Errors.InvarError;
import Errors.PostError;
import Errors.PreError;
import decorators.EntityDec;
import services.EntityServ;
import services.EnvironmentServ;
import types.Dir;

public class EntityCont extends EntityDec {

	public EntityCont(EntityServ d) {
		super(d);
	}
	
	public void CheckInv () {
		
		// \Inv :  Hp > 0
		if(!( getHp() >= 0 )) {
			throw new InvarError(" the number of life must be higher 0 ");
		}
		
	}
	
	@Override
	public int getHp() {
		int tmp; 
		// *******  Pré_Condition
		// *******  Pré_CheckInv
		// *******  Capteur
		// *******  Traitement
		tmp = super.getHp(); 
		// *******  Post_Inv
		// *******  Post_Condition
		
		return tmp;
	}

	@Override
	public void init(EnvironmentServ e, int h, int w, Dir D, int hp) {
		// *******  Pré_Condition
		// \Pre : init(e, h, w, d, hp ) requires  hp > 0
		if(!( 0 < hp )) {  
			throw new PreError(" number of life points should be positive ");
		}
		// *******  Pré_CheckInv
		CheckInv();
		// *******  Capteur
		// *******  Traitement
		super.init(e, h, w, D, hp);
		// *******  Post_Inv
		CheckInv();
		// *******  Post_Condition
		// \Post : getHp( init(e, h, w, d, hp )  ) == hp
		if(!( getHp() == hp )) {
			throw new PostError(" number of points life is not properly initialized ");
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
		CheckInv();
		// *******  Post_Condition
		
	}

}
