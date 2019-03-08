package ants.ml;

import ants.*;
import ants.ai.*;

public class MoverMachineLearning extends MovementManager {
	boolean movedLastTick = false;
	AntML antML;
	final Vector2i[] intToDirectionGrid = new Vector2i[]{
			new Vector2i(-1,1),new Vector2i(0,1),new Vector2i(1,1),
			new Vector2i(-1,0),new Vector2i(0,0),new Vector2i(1,0),
			new Vector2i(-1,-1),new Vector2i(0,-1),new Vector2i(1,-1)};
	
	public MoverMachineLearning(Ant inant) {
		super(inant);
		antML = (AntML)inant;
	}

	@Override
	protected boolean managedMove() {
		Integer a = antML.GetAction();
		Vector2i dir = intToDirectionGrid[a];
		return moveTowards(ant.tile.x+dir.x, ant.tile.y+dir.y);
	}

}