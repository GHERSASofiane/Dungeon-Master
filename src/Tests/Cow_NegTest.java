package Tests;

import Content.Content;
import Content.MyContent;
import Grille.MyGrille;
import Impl.CowImpl;
import Impl.EnvironmentImpl;
import Impl.MobImpl;
import Impl.PlayerImpl;
import contracts.CowCont;
import contracts.EnvironmentCont;
import contracts.MobCont;
import contracts.PlayerCont;
import decorators.EnvironmentDec;
import services.CowServ;
import services.EnvironmentServ;
import services.MobServ;
import services.PlayerServ;
import types.Dir;
import types.TypeMob;

public class Cow_NegTest {
	public static void main(String[] args) {

		// **************** Initial condition
		MyGrille grille = new MyGrille(1); // grille
		Content content = new MyContent(1); // Content

		EnvironmentServ envS = new EnvironmentImpl(content);
		EnvironmentDec envD = new EnvironmentCont(envS);

		CowServ cowS = new CowImpl();
		CowCont cowDec = new CowCont(cowS);
		
		// **************** treatment
		envD.init(4, 4, grille); System.out.println("init(4, 4, grille)"); // Init
		cowDec.init(envD, 0, 1, Dir.N); System.out.println("cowDec.init(envD, 0, 1, Dir.N)"); // Init
		
		
		System.out.println(" We have no exception provoked ");

		
	}
	

}
