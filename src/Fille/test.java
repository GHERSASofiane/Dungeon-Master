package Fille;

public class test {

	public static void main(String[] args) {
		
		ReadGrille r = new ReadGrille("grille.txt", "content.txt");

		
		System.out.println(r.getCont().getContent(0, 0));
		System.out.println(r.getGrille().getCell(0, 0));
		
	}

}
