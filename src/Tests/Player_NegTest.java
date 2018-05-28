package Tests;

import Content.Content;
import Content.MyContent;
import Grille.MyGrille;
import Impl.CowImpl;
import Impl.EnvironmentImpl;
import Impl.PlayerImpl;
import contracts.CowCont;
import contracts.EnvironmentCont;
import contracts.PlayerCont;
import decorators.EnvironmentDec;
import services.CowServ;
import services.EnvironmentServ;
import services.PlayerServ;
import types.Dir;
import types.TypeMob;

public class Player_NegTest {

	public static void main(String[] args) {
		
		// **************** Initial condition
				MyGrille grille = new MyGrille(1); // grille
				Content content = new MyContent(1); // Content

				EnvironmentServ envS = new EnvironmentImpl(content);
				EnvironmentDec envD = new EnvironmentCont(envS);

				CowServ cowS = new CowImpl();
				CowCont cowDec = new CowCont(cowS);
				
				PlayerServ PlayerS = new PlayerImpl();
				PlayerCont PlayerDec = new PlayerCont(PlayerS);

				// **************** treatment
				envD.init(4, 4, grille); System.out.println("init(4, 4, grille)"); // Init
				cowDec.init(envD, 0, 1, Dir.N); System.out.println("cowDec.init(envD, 0, 1, Dir.N)"); // Init
				
				PlayerDec.init(envD, 0, 0, Dir.S, 10); System.out.println("PlayerDec.init(envD, 2, 2, Dir.W, 10)"); // Init
				
				System.out.println("+++++++  Grille  +++++++++");
				AfficheContent(envD);
				System.out.println("++++++++++++++++++++++");

		
		// **************** Oracle
				System.out.println("Exception raised ");
				PlayerDec.getContent(5, 3);
	}

	public static void AfficheContent(EnvironmentServ arg) {
		TypeMob tmp = null;
		
		for (int H= 0; H <= (arg.getHeight()-1); H++) {
			for (int W = 0; W <= (arg.getWidth()-1); W++) {
				if(arg.CellContent(H, W).isPresent() ) {
					tmp = arg.CellContent(H, W).get();
				}
				System.out.print("("+H+","+W+") : "+arg.CellNature(H, W) + " : "+ tmp.name() +" | ");
				
					tmp = TypeMob.NO;
				

			}
			System.out.println();
		}
	}
}
