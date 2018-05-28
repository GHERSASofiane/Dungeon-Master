package Tests;

import Grille.MyGrille;
import Impl.MapImpl;
import contracts.MapCont;
import decorators.MapDec;
import services.MapServ;

public class Map_PosTest {

	public static void main(String[] args) {
		
// ****************  Initial condition
		MyGrille grille = new MyGrille(1); // grille

		MapServ mapS = new MapImpl();
		MapDec MapC = new MapCont(mapS);

// ****************  treatment
		MapC.init(4, 4, grille); System.out.println("init(4, 4, grille)");// Init
		
		System.out.println("+++++++  Grille  +++++++++");
		AfficheGrille(MapC);
		System.out.println("++++++++++++++++++++++");
		
		MapC.OpenDoor(1, 2); // open Door
		System.out.println("After Door Opener : MapC.OpenDoor(1, 2)");

		System.out.println("+++++++  Grille  +++++++++");
		AfficheGrille(MapC);
		System.out.println("++++++++++++++++++++++");
		
// ****************  Oracle
		System.out.println("No exception raised ");
		
	}


	public static void AfficheGrille(MapServ arg) {
		for (int H= 0; H <= (arg.getHeight()-1); H++) {
			for (int W = 0; W <= (arg.getWidth()-1); W++) {
				System.out.print("("+H+","+W+")"+arg.CellNature(H, W) + " | ");
				
			}
			System.out.println();
		}
	}
	
	
}
