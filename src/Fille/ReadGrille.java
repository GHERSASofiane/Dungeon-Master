package Fille;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Content.Content;
import Content.MyContent;
import Grille.Grille;
import Grille.MyGrille;
import types.Cell;
import types.TypeMob;

public class ReadGrille implements IReadGrille{

	private String fileCont = "src/Fille/content.txt";
	private String fileGrill = "src/Fille/grille.txt";
	private MyGrille MyGrille ;
	private MyContent MyContent ;
	
	private int row;
	private int col;
	
	public ReadGrille() {
		RempVar();
	}
	
	public ReadGrille(String g, String c) {
		this.fileGrill = "src/Fille/"+g;
		this.fileCont = "src/Fille/"+c;
		RempVar();
	}
	
	public void RempVar() {
		
		String tmp, tmp1; String[] tmprc1;
		 File f = new File (fileCont);
		 File f1 = new File (fileGrill);
		 try { Scanner scanner = new Scanner (f);  Scanner scanner1 = new Scanner (f1); 
		 tmp = scanner.nextLine(); tmp1 = scanner1.nextLine(); 
		 String[] tmprc = tmp.split(" ");
		 this.row = Integer.parseInt(tmprc[0]);
		 this.col = Integer.parseInt(tmprc[1]);

		 MyGrille = new MyGrille(row, col);
		 MyContent = new MyContent(row, col);
		 
		 for (int i = 0; i < row; i++) {
			 tmp = scanner.nextLine(); tmprc = tmp.split(" ");
			 tmp1 = scanner1.nextLine(); tmprc1 = tmp1.split(" ");
			 for (int j = 0; j < col; j++) {
					MyContent.setContent(i, j, getTypeMob(tmprc[j]));
					MyGrille.setCell(i, j, getCell(tmprc1[j]));
					
				}	
		}
		 
		 } catch (FileNotFoundException e) { e.printStackTrace(); }
	}
	
	public TypeMob getTypeMob(String s) {
		TypeMob tmp = TypeMob.NO;
		switch (s) {
		case "NO": tmp = TypeMob.NO; break;
		case "joueur": tmp = TypeMob.joueur; break;
		case "monstre": tmp = TypeMob.monstre; break;
		case "projectile": tmp = TypeMob.projectile; break;
		case "pierre": tmp = TypeMob.pierre; break;
		case "caisse": tmp = TypeMob.caisse; break;
		case "Cow": tmp = TypeMob.Cow; break;
		
		}
		
		return tmp;
	}
	
	public Cell getCell(String s) {
		 
		Cell tmp = Cell.EMP;
		switch (s) {
		case "IN": tmp = Cell.IN; break;
		case "OUT": tmp = Cell.OUT; break;
		case "EMP": tmp = Cell.EMP; break;
		case "WLL": tmp = Cell.WLL;  break;
		case "DNO": tmp = Cell.DNO; break;
		case "DNC": tmp = Cell.DNC; break;
		case "DWO": tmp = Cell.DWO; break;
		case "DWC": tmp = Cell.DWC; break;
		
		}
		
		return tmp;
	}
	
	@Override
	public Grille getGrille() {
		return this.MyGrille;
	}

	@Override
	public Content getCont() {
		return this.MyContent;
	}

	 
	
}
