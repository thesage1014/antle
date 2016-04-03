package ants;

public interface Tickable {
	public static final int GAME=0, RENDER=1, PERFORMANCECOUNTER=2;
	public void tick(int tickType);
}