package Tests;

import java.util.Optional;

import Content.Content;
import Content.MyContent;
import Grille.MyGrille;
import Impl.EnvironmentImpl;
import Impl.MapImpl;
import contracts.EnvironmentCont;
import contracts.MapCont;
import decorators.EnvironmentDec;
import decorators.MapDec;
import services.EnvironmentServ;
import services.MapServ;
import types.TypeMob;

public class Environment_PosTest {
	public static void main(String[] args) {

		// **************** Initial condition
		MyGrille grille = new MyGrille(1); // grille
		Content content = new MyContent(1); // Content

		MapServ mapS = new MapImpl();
		MapDec MapC = new MapCont(mapS);
		
		EnvironmentServ envS = new EnvironmentImpl(content);
		EnvironmentDec envD = new EnvironmentCont(envS);

		// **************** treatment
		envD.init(4, 4, grille); System.out.println("init(4, 4, grille)"); // Init

		System.out.println("+++++++  Grille  +++++++++");
		AfficheContent(envD);
		System.out.println("++++++++++++++++++++++");
		
		
		System.out.println("After Door Opener : envD.OpenDoor(1, 2)");
		envD.OpenDoor(1, 2); // open Door

		System.out.println("+++++++  Grille  +++++++++");
		AfficheContent(envD);
		System.out.println("++++++++++++++++++++++");
		
		
		System.out.println("After Door Opener : envD.CloseDoor(1, 2)");
		envD.CloseDoor(1, 2); // Close Door

		System.out.println("+++++++  Grille  +++++++++");
		AfficheContent(envD);
		System.out.println("++++++++++++++++++++++");
		
		
		// **************** Oracle
		System.out.println("Exception raised ");

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
