package ants.ml;

import ants.*;
import ants.ai.*;

public class MoverMachineLearning extends MovementManager {
	boolean movedLastTick = false;
	AntML antML;
	
	public MoverMachineLearning(Ant inant) {
		super(inant);
		antML = (AntML)inant;
	}

	@Override
	protected boolean managedMove() {
		Integer a = antML.GetAction();
		// translate the given action into a coordinate immediately within the ant's reach(including no movement) 
		Int2D dir = Util.dirs92D[a];
		// attempt the move
		return moveTowards(ant.tile.x+dir.x, ant.tile.y+dir.y);
	}

}