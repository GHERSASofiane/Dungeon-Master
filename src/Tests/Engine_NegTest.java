package Tests;

import Content.Content;
import Content.MyContent;
import Grille.MyGrille;
import Impl.EngineImpl;
import Impl.EnvironmentImpl;
import contracts.EngineCont;
import contracts.EnvironmentCont;
import contracts.PlayerCont;
import decorators.EngineDec;
import decorators.EnvironmentDec;
import services.EngineServ;
import services.EnvironmentServ;
import types.Command;
import types.TypeMob;

public class Engine_NegTest {

	public static void main(String[] args) {


		// **************** Initial condition
		MyGrille grille = new MyGrille(1); // grille
		Content content = new MyContent(1); // Content

		EnvironmentServ envS = new EnvironmentImpl(content);
		EnvironmentDec envD = new EnvironmentCont(envS);
		
		EngineServ engS = new EngineImpl();
		EngineDec engD = new EngineCont(engS);

		// **************** treatment
		envD.init(4, 4, grille); System.out.println("init(4, 4, grille)"); // Init
		engD.init(envD);  System.out.println("engS.init(envD)");
		
		PlayerCont PlayD = null;
		
		for (int i = 0; i < engD.getEntities().size() ; i++) {
			if(engD.getEntities().get(i).getClass().getName().equals("contracts.PlayerCont"))
			PlayD = (PlayerCont) engD.getEntities().get(i);
		}
		
		
		System.out.println("+++++++  Grille  +++++++++");
		AfficheContent(envD);
		System.out.println("++++++++++++++++++++++");	


		PlayD.setLastCom(Command.BW);  System.out.println("PlayD.setLastCom(Command.BW)");
		engD.step();  System.out.println("engS.step()");

		System.out.println("+++++++  Grille  +++++++++");
		AfficheContent(envD);
		System.out.println("++++++++++++++++++++++");	

		
		
		// **************** Oracle
		System.out.println("Exception raised ");
		
		engD.removeEntity(100);

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
