package ants;

import ants.ml.AntStateData;

public interface Tickable {
	public static final int GAME=0, RENDER=1, PERFORMANCECOUNTER=2, GAMEML=3;
	public void tick(int tickType);
	public AntStateData mlTick();
}