package ants;

public class Breakables {
	  public static void breakBlock(AntsMap map, int x, int y, Entity breaker) {
		  Type blockType = map.get(x, y).getType();
		  if(blockType == TileTypes.FOOD) {
			  breaker.pickupItem(TileTypes.FOOD.itemOnBreak);
		  }
	  }
}
