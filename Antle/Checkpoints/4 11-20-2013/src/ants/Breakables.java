package ants;

public class Breakables {
	  public static void breakBlock(AntsMap map, int x, int y, Entity breaker) {
		  Type blockType = Types.types[map.get(x, y)];
		  if(blockType.ID == Types.FOOD) {
			  breaker.pickupItem(Types.types[Types.FOOD].itemOnBreak);
		  }
	  }
}
