import java.util.LinkedHashMap;
import java.util.Map;

public class Room {
    public Map<String, Room> exits = new LinkedHashMap<>();
    public String description;

    public Room(String description) {
        this.description = description;
    }

    public void addExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getDescription() {
        return description;
    }

    public static Room createRooms(Game game) {
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialize room exits
        outside.addExit("north", theater);
        outside.addExit("east", lab);
        outside.addExit("south", pub);

        theater.addExit("west", outside);

        pub.addExit("north", outside);

        lab.addExit("west", outside);
        lab.addExit("south", office);

        office.addExit("north", lab);

        return outside; // start game outside
    }
}
