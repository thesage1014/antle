package ants.ai;

import ants.Ant;
import ants.AntML;
import ants.Vector2i;

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
	boolean beMoved() {
		Integer a = antML.GetAction();
		Vector2i dir = intToDirectionGrid[a];
		return moveTowards(ant.tile.x+dir.x, ant.tile.y+dir.y);
	}

}