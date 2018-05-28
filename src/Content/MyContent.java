package Content;

import java.awt.List;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;

import Grille.MyGrille;
import types.Cell;
import types.TypeMob;

public class MyContent implements Content{
	
	private TypeMob[][] Contents;
	
	public MyContent() { // TODO 
		Contents = new TypeMob[4][4];
	}	
	
	public MyContent(int r, int c) { 
		Contents = new TypeMob[r][c];
	}
	public MyContent(int i) {
		Contents = new TypeMob[4][4];
		
		Contents[0][0] = TypeMob.joueur;	Contents[0][1] = TypeMob.monstre;	Contents[0][2] = TypeMob.projectile;	Contents[0][3] = TypeMob.NO;
		Contents[1][0] = TypeMob.NO;	Contents[1][1] = TypeMob.NO;	Contents[1][2] = TypeMob.NO;	Contents[1][3] = TypeMob.NO;
		Contents[2][0] = TypeMob.NO;	Contents[2][1] = TypeMob.NO;	Contents[2][2] = TypeMob.NO;	Contents[2][3] = TypeMob.NO;
		Contents[3][0] = TypeMob.NO;	Contents[3][1] = TypeMob.Cow;	Contents[3][2] = TypeMob.NO;	Contents[3][3] = TypeMob.NO;
		
	}
	
	@Override
	public Optional<TypeMob> getContent(int h, int w) {
	 
			return Optional.of(this.Contents[h][w]);
		
	}

	@Override
	public void setContent(int h, int w, TypeMob c) {
		this.Contents[h][w] = c;
	}
	
	@Override
	public Object clone() {
		Content res = null;
		try {
			
			res = ((Content)super.clone());
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return res;
	}

}
