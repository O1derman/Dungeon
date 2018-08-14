package olderman.dungeon.map;

import java.io.Serializable;

public class RandMapData implements Serializable {
    private static final long serialVersionUID = 1L;

    public Room[][] mapRooms;
    public int downEdge;
    public int yourPositionx = 1;
    public int yourPositiony = 1;
    public int rightEdge;
    public String map = "";

}
