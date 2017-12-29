package olderman.dungeon.map;

import java.io.Serializable;

public class RandMapData implements Serializable {
	private static final long serialVersionUID = 1L;

	public Room[][] mapRooms;
	public int leftEdge;
	public int downEdge;
	public int rightEdge;
	public int w = 1;
	// public int w1 = (width + 1) / 2; // starting w
	public int l = 1;
	public String map = "";

}
