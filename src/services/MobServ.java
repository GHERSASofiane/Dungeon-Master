package services;

import types.Dir;
import types.TypeMob;

public interface MobServ {
	// ******  invariant
		// 0 ≤ Col(M) < Environment::Width(Envi(M))
		// 0 ≤ Row(M) < Environment::Height(Envi(M))
		// Environment::CellNature(Envi(M),Col(M),Row(M)) ∈ { / WLL, DNC, DWC}
	
	// ***** Observators
	public EnvironmentServ getEnv();
	public int getCol();
	public int getRow();
	public Dir getFace();
	
	// ***** Constructors
	public void init(EnvironmentServ e, int h, int w, Dir d);

	// ***** Operators
	
	public void Forward ();
	public void Backward();
	public void TurnL();
	public void TurnR();
	public void StrafeL();
	public void StrafeR();
	
	
}
