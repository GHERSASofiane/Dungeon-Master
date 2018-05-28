package Tests;

import Content.Content;
import Content.MyContent;
import Grille.MyGrille;
import Impl.EnvironmentImpl;
import Impl.MapImpl;
import Impl.MobImpl;
import contracts.EnvironmentCont;
import contracts.MapCont;
import contracts.MobCont;
import decorators.EnvironmentDec;
import decorators.MapDec;
import decorators.MobDec;
import services.EnvironmentServ;
import services.MapServ;
import services.MobServ;
import types.Dir;
import types.TypeMob;

public class Mob_NegTest {
	public static void main(String[] args) {


		// **************** Initial condition
		MyGrille grille = new MyGrille(1); // grille
		Content content = new MyContent(1); // Content
		
		EnvironmentServ envS = new EnvironmentImpl(content);
		EnvironmentDec envD = new EnvironmentCont(envS);

		MobServ mobS = new MobImpl();
		MobDec mobC = new MobCont(mobS);
		
		// **************** treatment
		envD.init(4, 4, grille); System.out.println("init(4, 4, grille)"); // Init

		System.out.println("+++++++  Grille  +++++++++");
		AfficheContent(envD);
		System.out.println("++++++++++++++++++++++");
		
		
		
		System.out.println("mobC.init(envD, 0, 0, Dir.E)");
		// **************** Oracle
		System.out.println("Exception raised ");
		mobC.init(envD, 0, 10, Dir.E); // init Mob

	
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


