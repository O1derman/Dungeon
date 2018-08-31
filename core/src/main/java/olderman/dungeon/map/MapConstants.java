package olderman.dungeon.map;


import olderman.dungeon.Dungeon;

public class MapConstants {
    public static char wallChar;
    public static char playerChar;
    public static char bossChar;
    public static char clearRoomChar;
    public static char fullRoomChar;
    public static char whiteSpace;
    public static char uncoveredRoom;


    public MapConstants(Dungeon dungeon) {
        if (dungeon.isChangeMap()) {
            wallChar = '\uff38';
            playerChar = '\uff0a';
            bossChar = '\uff01';
            clearRoomChar = '\uff10';
            fullRoomChar = '\uff1f';
            uncoveredRoom = '\u3000';
        } else {
            wallChar = '\u26DE'; //(char) 0x25A0,■ u25A0
            playerChar = '\u263B'; //(char) 0x263B,☻ u263B
            bossChar = '\u2620'; //☠ u2620
            clearRoomChar = '\u2686';
            fullRoomChar = '\u2688';
            uncoveredRoom = ' ';
        }
        whiteSpace = ' ';
    }
}