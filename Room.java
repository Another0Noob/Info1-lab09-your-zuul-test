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
        Room outside, theater, pub, lab, office, cellar;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar");

        // initialize room exits
        outside.addExit("east", theater);
        outside.addExit("south", lab);
        outside.addExit("west", pub);

        theater.addExit("west", outside);

        pub.addExit("est", outside);
        pub.addExit("down", cellar);

        cellar.addExit("up", pub);
        lab.addExit("north", outside);
        lab.addExit("east", office);

        office.addExit("west", lab);



        return outside; // start game outside
    }
}
