package Impl;

import services.EnvironmentServ;
import services.MobServ;
import types.Cell;
import types.Dir;
import types.TypeMob;

public class MobImpl implements MobServ {

	private EnvironmentServ Env;
	private int Col, Row;
	private Dir Face;


	@Override
	public EnvironmentServ getEnv() {
		return this.Env;
	}

	@Override
	public int getCol() {
		return this.Col;
	}

	@Override
	public int getRow() {
		return this.Row;
	}

	@Override
	public Dir getFace() {
		return this.Face;
	}

	
	@Override
	public void init(EnvironmentServ e, int h, int w, Dir d) {
		this.Env = e;
		this.Col = w;
		this.Row = h;
		this.Face = d;
	}

	// avancée
	@Override
	public void Forward() {

		TypeMob ObjNature = this.Env.CellContent(this.Row, this.Col).get();

		// Nord
		if (this.Face.equals(Dir.N)) {

			if ((0 <= this.Row - 1)) {

				if (((this.Env.CellNature(Row - 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.IN))						
						)
						&& (this.Env.CellContent(Row - 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// Sud
		if (this.Face.equals(Dir.S)) {

			if ((this.Row + 1 < this.Env.getHeight())) {

				if (((this.Env.CellNature(Row + 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.IN))	)
						&& (this.Env.CellContent(Row + 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}

			}

		}
		// Est
		if (this.Face.equals(Dir.E)) {
			 
			if ((this.Col + 1 < this.Env.getWidth())) {

				if (((this.Env.CellNature(Row, Col + 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.IN))	)
						&& (this.Env.CellContent(Row, Col + 1).get().equals(TypeMob.NO))) {
 
					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col += 1;
					 
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// West
		if (this.Face.equals(Dir.W)) {

			if ((0 <= this.Col - 1)) {

				if (((this.Env.CellNature(Row, Col - 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col - 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

	}

	// reculer
	@Override
	public void Backward() {

		TypeMob ObjNature = this.Env.CellContent(this.Row, this.Col).get();

		// Nord
		if (this.Face.equals(Dir.N)) {

			if ((this.Row + 1 < this.Env.getHeight())) {

				if (((this.Env.CellNature(Row + 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.IN)))
						&& (this.Env.CellContent(Row + 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}

			}

		}

		// Sud
		if (this.Face.equals(Dir.S)) {

			if ((0 <= this.Row - 1)) {

				if (((this.Env.CellNature(Row - 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.IN)))
						&& (this.Env.CellContent(Row - 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// Est
		if (this.Face.equals(Dir.E)) {

			if ((0 <= this.Col - 1)) {

				if (((this.Env.CellNature(Row, Col - 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col - 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// West
		if (this.Face.equals(Dir.W)) {

			if ((this.Col + 1 < this.Env.getWidth())) {

				if (((this.Env.CellNature(Row, Col + 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col + 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

	}

	// tour vers la gauche
	@Override
	public void TurnL() {

		// Nord
		if (this.Face.equals(Dir.N))
			this.Face = Dir.W;
		else
		// Sud
		if (this.Face.equals(Dir.S))
			this.Face = Dir.E;
		else
		// Est
		if (this.Face.equals(Dir.E))
			this.Face = Dir.N;
		else
		// West
		if (this.Face.equals(Dir.W))
			this.Face = Dir.S;

	}

	// tour vers la droite
	@Override
	public void TurnR() {

		// Nord
		if (this.Face.equals(Dir.N)) {
			this.Face = Dir.E;
		}else

		// Sud
		if (this.Face.equals(Dir.S)) {
			this.Face = Dir.W;
		}else

		// Est
		if (this.Face.equals(Dir.E)) {
			this.Face = Dir.S;
		}else

		// West
		if (this.Face.equals(Dir.W)) {
			this.Face = Dir.N;
		}
		
	}

	// un pas chasse vers la gauche
	@Override
	public void StrafeL() {

		TypeMob ObjNature = this.Env.CellContent(this.Row, this.Col).get();

		// Nord
		if (this.Face.equals(Dir.N)) {

			if ((0 <= this.Col - 1)) {

				if (((this.Env.CellNature(Row, Col - 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col - 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// Sud
		if (this.Face.equals(Dir.S)) {

			if ((this.Col + 1 < this.Env.getWidth())) {

				if (((this.Env.CellNature(Row, Col + 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col + 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// Est
		if (this.Face.equals(Dir.E)) {

			if ((0 <= this.Row - 1)) {

				if (((this.Env.CellNature(Row - 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.IN)))
						&& (this.Env.CellContent(Row - 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// West
		if (this.Face.equals(Dir.W)) {

			if ((this.Row + 1 < this.Env.getHeight())) {

				if (((this.Env.CellNature(Row + 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.IN)))
						&& (this.Env.CellContent(Row + 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}

			}

		}

	}

	// un pas chasse vers la droite
	@Override
	public void StrafeR() {

		TypeMob ObjNature = this.Env.CellContent(this.Row, this.Col).get();

		// Nord
		if (this.Face.equals(Dir.N)) {

			if ((this.Col + 1 < this.Env.getWidth())) {

				if (((this.Env.CellNature(Row, Col + 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col + 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col + 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// Sud
		if (this.Face.equals(Dir.S)) {

			if ((0 <= this.Col - 1)) {

				if (((this.Env.CellNature(Row, Col - 1).equals(Cell.EMP))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.DWO))
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row, Col - 1).equals(Cell.IN)))
						&& (this.Env.CellContent(Row, Col - 1).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Col -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

		// Est
		if (this.Face.equals(Dir.E)) {

			if ((this.Row + 1 < this.Env.getHeight())) {

				if (((this.Env.CellNature(Row + 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row + 1, Col).equals(Cell.IN)))
						&& (this.Env.CellContent(Row + 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row += 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}

			}

		}

		// West
		if (this.Face.equals(Dir.W)) {

			if ((0 <= this.Row - 1)) {

				if (((this.Env.CellNature(Row - 1, Col).equals(Cell.EMP))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.DNO))
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.OUT))	
						|| (this.Env.CellNature(Row - 1, Col).equals(Cell.IN)))
						&& (this.Env.CellContent(Row - 1, Col).get().equals(TypeMob.NO))) {

					this.Env.SetCellContent(Row, Col, TypeMob.NO);
					this.Row -= 1;
					this.Env.SetCellContent(Row, Col, ObjNature);

				}
			}

		}

	}

}
