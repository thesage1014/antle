package ants;

import java.util.ArrayList;
import java.util.Vector;

public final class ParamSetGlobal extends ParamSet {
	public Param mapW, mapH, displayW, displayH, tickDelay, earthDensity, stoneLayerCutoff, earthNoiseScale, renderTickDelay, 
			erosionPerStep, antBehavior, antSpawnBoxSize, numColonies, foodSpawnAmount, scentBlurSpeed;
	public ParamSetGlobal() {
		name = "Global";
		ArrayList<Param> arrayQueue = new ArrayList<Param>();
													//  isBool, isList, isROnly,Min,    Max,        Default,    Description
		arrayQueue.add(displayW				= new Param(Param.SLIDER,	true,	4,		1920,		1024,			"Display width"));
		arrayQueue.add(displayH				= new Param(Param.SLIDER,	true,	4,		1080,		512,			"Display height"));
		arrayQueue.add(mapW 				= new Param(Param.SLIDER,	false,	16,		displayW.i(),512,			"Map width"));
		arrayQueue.add(mapH 				= new Param(Param.SLIDER,	false,	8,		displayH.i(),256,			"Map height"));
		arrayQueue.add(tickDelay			= new Param(Param.SLIDER,	false,	0,		1000,	1000/20,			"Tick delay(ms)"));
		arrayQueue.add(renderTickDelay		= new Param(Param.SLIDER,	false,	0,		200,	1000/60,			"Render tick delay(ms)"));
		arrayQueue.add(earthDensity			= new Param(Param.SLIDER,	false,	-1,		1,			-.1,			"Earth density cutoff"));
		arrayQueue.add(stoneLayerCutoff		= new Param(Param.SLIDER,	false,	-1,		1,			.3,				"Stone layer cutoff"));
		arrayQueue.add(earthNoiseScale		= new Param(Param.SLIDER,	false,	.4,		.01,		.07,			"Earth noise scale"));
		arrayQueue.add(erosionPerStep		= new Param(Param.SLIDER,	false,	1,		255,		5,				"Max erosion per step"));
		arrayQueue.add(antSpawnBoxSize		= new Param(Param.SLIDER,	false,	4,		150,		60,				"Ant spawn box size"));
		arrayQueue.add(numColonies			= new Param(Param.SLIDER,	false,	0,		8,			2,				"Number of colonies"));
		arrayQueue.add(foodSpawnAmount		= new Param(Param.SLIDER,	false,	1,		-1,			.5,				"Food spawn amount"));
		arrayQueue.add(scentBlurSpeed		= new Param(Param.SLIDER,	false,	1000,	10,			100,			"Scent blur speed"));
		
		params = (Param[]) arrayQueue.toArray(new Param[arrayQueue.size()]);
	}
}
