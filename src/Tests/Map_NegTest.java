package Tests;

import Grille.MyGrille;
import Impl.MapImpl;
import contracts.MapCont;
import decorators.MapDec;
import services.MapServ;

public class Map_NegTest {
	public static void main(String[] args) {

		// **************** Initial condition
		MyGrille grille = new MyGrille(1); // grille

		MapServ mapS = new MapImpl();
		MapDec MapC = new MapCont(mapS);

		// **************** treatment
		MapC.init(4, 4, grille); System.out.println("init(4, 4, grille)"); // Init

		System.out.println("+++++++  Grille  +++++++++");
		AfficheGrille(MapC);
		System.out.println("++++++++++++++++++++++");
		
		
		System.out.println("After Door Opener : MapC.OpenDoor(2, 2)");
		// **************** Oracle
		System.out.println("Exception raised ");
		MapC.OpenDoor(2, 2); // open Door


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
