package ants;

import java.util.ArrayList;
import java.util.Vector;

public final class ParamSetGlobal extends ParamSet {
	public Param mapW, mapH, displayW, displayH, tickDelay, earthDensity, stoneLayerCutoff, earthNoiseScale, renderTickDelay, 
			erosionPerStep, antBehavior, antSpawnBoxSize, foodSpawnAmount;
	public ParamSetGlobal() {
		name = "Global";
		ArrayList<Param> arrayQueue = new ArrayList<Param>();
													//  isBool, isList, isROnly,Min,    Max,        Default,    Description
		arrayQueue.add(mapW 				= new Param(false,	false,	true,	4,		1920,		256,			"Map width"));
		arrayQueue.add(mapH 				= new Param(false,	false,	true,	4,		1080,		128,			"Map height"));
		arrayQueue.add(displayW				= new Param(false,	false,	true,	4,		1920,		1024,			"Display width"));
		arrayQueue.add(displayH				= new Param(false,	false,	true,	4,		1080,		512,			"Display height"));
		arrayQueue.add(tickDelay			= new Param(false,	false,	false,	0,		200,		1000/30,		"Tick delay(ms)"));
		arrayQueue.add(renderTickDelay		= new Param(false,	false,	false,	0,		200,		1000/60,		"Render tick delay(ms)"));
		arrayQueue.add(earthDensity			= new Param(false,	false,	false,	-1,		1,			-.1,			"Earth density cutoff"));
		arrayQueue.add(stoneLayerCutoff		= new Param(false,	false,	false,	0,		1,			.2,				"Stone layer cutoff"));
		arrayQueue.add(earthNoiseScale		= new Param(false,	false,	false,	.01,	.9,			.1,				"Earth noise scale"));
//		arrayQueue.add(erosionPersistance	= new Param(false,	false,	false,	0,		.9,			.9,				"Erosion persistance"));
		arrayQueue.add(erosionPerStep		= new Param(false,	false,	false,	1,		255,		5,				"Max erosion per step"));
//		arrayQueue.add(altErosionPattern	= new Param(true,	false,	false,	0,		1,			0,				"Alternate erosion pattern"));
		arrayQueue.add(antSpawnBoxSize		= new Param(false,	false,	false,	4,		100,		90,				"Ant spawn box size"));
		arrayQueue.add(foodSpawnAmount		= new Param(false,	false,	false,	1,		100,		70,				"Food spawn amount"));
		
		params = (Param[]) arrayQueue.toArray(new Param[arrayQueue.size()]);
	}
}
