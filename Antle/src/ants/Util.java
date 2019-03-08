package ants;

public final class Util {
	/** an array containing all combinations of 1,-1, and 0 except 0,0 */
	public static final int[] dirs8 = new int[]{  1,0,  1,1,  0,1,  -1,0, 1,-1,  -1,-1,  0,-1,  -1,1  };
	/** an array containing all combinations of 1,-1, and 0 except 0,0 */
	
	public static final Int2D[] dirs2D = new Int2D[]{new Int2D(1,0),new Int2D(1,1),new Int2D(0,1),new Int2D(-1,0),
													new Int2D(1,-1),new Int2D(-1,-1),new Int2D(0,-1),new Int2D(-1,1)};
	/** an array containing all combinations of 1,-1, and 0 starting at the top-left going right */
	public static final Int2D[] dirs92D = new Int2D[]{
			new Int2D(-1,1),new Int2D(0,1),new Int2D(1,1),
			new Int2D(-1,0),new Int2D(0,0),new Int2D(1,0),
			new Int2D(-1,-1),new Int2D(0,-1),new Int2D(1,-1)};
}
