package Tests;

import Grille.MyGrille;
import Impl.EditMapImpl;
import Impl.MapImpl;
import contracts.EditMapCont;
import decorators.EditMapDec;
import services.EditMapServ;
import services.MapServ;
import types.Cell;

public class EditMap_PosTest {

	public static void main(String[] args) {
		
		// ****************  Initial condition
				MyGrille grille = new MyGrille(1); // grille

				EditMapServ EditmapS = new EditMapImpl( );
				EditMapDec EditMapC = new EditMapCont(EditmapS);

		// ****************  treatment
				EditMapC.init(4, 4, grille); System.out.println("init(4, 4, grille)");// Init
				System.out.println("+++++++  Grille  +++++++++");
				AfficheGrille(EditMapC);
				System.out.println("++++++++++++++++++++++");
				System.out.println("isReachable(0, 0, 3, 2) : "+ EditMapC.isReachable(0, 0, 3, 2));
				EditMapC.SetNature(0, 3, Cell.EMP);

				System.out.println("+++++++  After  SetNature(0, 3, Cell.EMP) +++++++++");
				AfficheGrille(EditMapC);
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
