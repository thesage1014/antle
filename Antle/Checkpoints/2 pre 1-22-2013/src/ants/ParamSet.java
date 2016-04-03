package ants;

import java.util.Vector;

public final class ParamSet {
	Param[] params;
	public Param mapW, mapH, displayW, displayH, tickDelay, earthDensity, earthNoiseScale, erosionPersistance, renderTickDelay, 
			altErosionPattern, erosionPerStep;
	public ParamSet() {
		params = new Param[11];
													//  isBool  isROnly,Min,    Max,        Default,    Description
		params[0] 	= (mapW 				= new Param(false,	true,	4,		1920,		256,			"Map width"));
		params[1] 	= (mapH 				= new Param(false,	true,	4,		1080,		128,			"Map height"));
		params[2] 	= (displayW				= new Param(false,	true,	4,		1920,		1024,			"Display width"));
		params[3] 	= (displayH				= new Param(false,	true,	4,		1080,		512,			"Display height"));
		params[4] 	= (tickDelay			= new Param(false,	false,	0,		1000,		15,				"Tick delay(ms)"));
		params[5] 	= (renderTickDelay		= new Param(false,	false,	0,		1000,		tickDelay.value,"Frame tick delay(ms)"));
		params[6] 	= (earthDensity			= new Param(false,	false,	-1,		1,			-.2,			"Earth density cutoff"));
		params[7] 	= (earthNoiseScale		= new Param(false,	false,	.01,	.9,			.2,				"Earth noise scale"));
		params[8] 	= (erosionPersistance	= new Param(false,	false,	0,		.9,			.9,				"Erosion persistance"));
		params[9] 	= (erosionPerStep		= new Param(false,	false,	1,		10,			10,				"Erosion per step"));
		params[10] 	= (altErosionPattern	= new Param(true,	false,	0,		1,			0,				"Alternate erosion pattern"));
	}
}
