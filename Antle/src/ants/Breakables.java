package ants;

public class Breakables {
	  public static void breakBlock(AntsMap map, int x, int y, Entity breaker) {
		  Type blockType = map.get(x, y).getType();
		  if(blockType == Types.FOOD) {
			  breaker.pickupItem(Types.FOOD.itemOnBreak);
		  }
	  }
}
