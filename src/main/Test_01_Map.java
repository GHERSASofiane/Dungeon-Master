package main;

import Content.Content;
import Content.MyContent;
import Grille.Grille;
import Grille.MyGrille;
import Impl.EditMapImpl;
import Impl.EnvironmentImpl;
import Impl.MapImpl;
import Impl.MobImpl;
import contracts.EditMapCont;
import contracts.EnvironmentCont;
import contracts.MapCont;
import decorators.EditMapDec;
import decorators.EnvironmentDec;
import decorators.MapDec;
import services.EnvironmentServ;
import services.MapServ;
import services.MobServ;
import types.Cell;
import types.Dir;
import types.TypeMob;

public class Test_01_Map {

	public static void main(String[] args) {

		// **************** Initial condition
		MyGrille grille = new MyGrille(1); // grille
		Content content = new MyContent(1); // Content

		MapServ mapS = new MapImpl();
		MapDec MapC = new MapCont(mapS);
		
		EnvironmentServ envS = new EnvironmentImpl(content);
		EnvironmentDec envD = new EnvironmentCont(envS);
		
		envD.init(4, 4, grille);
		
		MobServ mob = new MobImpl();
		mob.init(envD,  2, 1, Dir.N);
		
		AfficheContent(envD);
		 
		System.out.println("-------------------------------------------");
		System.out.println("Apres Ouverteur de porte");
		System.out.println("-------------------------------------------");
		
		mob.Forward();
		// AfficheGrille(MapC);
	}
	
	public static void AfficheContent(EnvironmentServ arg) {
		TypeMob tmp = null;
		
		for (int i = 0; i <= (arg.getWidth() - 1); i++) {
			for (int j = 0; j <= (arg.getHeight() - 1); j++) {
				if(arg.CellContent(i, j).isPresent() ) {
					tmp = arg.CellContent(i, j).get();
				}
				System.out.print("("+i+","+j+") : "+arg.CellNature(i, j) + " : "+ tmp.name() +" | ");
				
					tmp = TypeMob.NO;
				

			}
			System.out.println();
		}
	}
	
}
