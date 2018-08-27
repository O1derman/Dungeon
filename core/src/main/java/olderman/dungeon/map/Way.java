package olderman.dungeon.map;

import java.io.Serializable;
import java.util.ArrayList;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Way implements Serializable {
    private static final long serialVersionUID = 1L;

    public Way(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.randMap = new RandMap(dungeon);

    }

    private final Dungeon dungeon;
    public RandMap randMap;
    public int wayChoice;
    public int previousX;
    public int previousY;
    int myWay;

    public void go() {
        dungeon.println(Style.CENTER, "Which way?");
        dungeon.println();
        dungeon.println(randMap.data.map);
        previousX = randMap.data.yourPositionx;
        previousY = randMap.data.yourPositiony;

        Room east = randMap.getNextRoom(DirectionEnum.EAST);
        Room west = randMap.getNextRoom(DirectionEnum.WEST);
        Room south = randMap.getNextRoom(DirectionEnum.SOUTH);
        Room north = randMap.getNextRoom(DirectionEnum.NORTH);

        ArrayList<String> choices = new ArrayList<>();
        if (east == null) {
        } else if (east.isBorder() || east.isRightSideBorder() || east.isStone()) {
        } else {
            choices.add(DirectionEnum.EAST.getDescription());
        }
        if (north == null) {
        } else if (north.isBorder() || north.isRightSideBorder() || north.isStone()) {

        } else {
            choices.add(DirectionEnum.NORTH.getDescription());
        }
        if (south == null) {
        } else if (south.isBorder() || south.isRightSideBorder() || south.isStone()) {
        } else {
            choices.add(DirectionEnum.SOUTH.getDescription());
        }
        if (west == null) {
        } else if (west.isBorder() || west.isRightSideBorder() || west.isStone()) {
        } else {
            choices.add(DirectionEnum.WEST.getDescription());
        }

        wayChoice = dungeon.uzivatVolba(choices.toArray(new String[choices.size()]));

        String stringChoice = choices.get(wayChoice - 1);

        if (stringChoice.equals(DirectionEnum.EAST.getDescription())) {
            myWay = 1;
            randMap.goStraight();
        } else if (stringChoice.equals(DirectionEnum.NORTH.getDescription())) {
            myWay = 2;
            randMap.goLeft();
        } else if (stringChoice.equals(DirectionEnum.SOUTH.getDescription())) {
            myWay = 3;
            randMap.goRight();
        } else if (stringChoice.equals(DirectionEnum.WEST.getDescription())) {
            myWay = 4;
            randMap.goBackwards();
        }
    }

    public void back() {
        boolean back = true;
        while (back) {

            if (myWay == 1) {
                randMap.goBackwards();
                break;
            }
            if (myWay == 2) {
                randMap.goRight();
                break;
            }
            if (myWay == 3) {
                randMap.goLeft();
                break;
            }
            if (myWay == 4) {
                randMap.goStraight();
                break;
            }
            back = false;
        }

    }
}
