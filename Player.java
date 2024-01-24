import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Game State for the Single Player.
 *
 */
public class Player
{
    private Room currentRoom;

    private List<Items> inventory = new ArrayList<>();
    private int maxWeight = 100;

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }

}
