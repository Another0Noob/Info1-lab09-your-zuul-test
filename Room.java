import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Room {
    public Map<String, Room> exits = new LinkedHashMap<>();
    private List<Items> items = new ArrayList<>();
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

    public void addItem(Items item) {
        items.add(item);
    }

    public void removeItem(Items item) {
        items.remove(item);
    }

    public Items getItem(String itemName) {
        for (Items item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public List<Items> getItems() {
        return items;
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

        // add items
        outside.addItem(new Items("key", "a key to open the door", 1));
        theater.addItem(new Items("book", "a book about Java", 2));
        pub.addItem(new Items("beer", "a beer to drink", 3));
        lab.addItem(new Items("computer", "a computer to work", 4));
        office.addItem(new Items("pen", "a pen to write", 1));
        cellar.addItem(new Items("wine", "a wine to drink", 3));

        return outside; // start game outside
    }
}
