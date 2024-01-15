import java.util.ArrayList;
import java.util.List;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private List<Items> inventory = new ArrayList<>();

    private int maxWeight = 100;


    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        currentRoom = Room.createRooms(this);  // start game outside
        parser = new Parser();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            String output = processCommand(command);
            finished = (null == output);
            if (!finished)
            {
                System.out.println(output);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * This is a further method added by BK to
     * provide a clearer interface that can be tested:
     * Game processes a commandLine and returns output.
     * @param-commandLine - the line entered as String
     * @return output of the command
     */
    public String processInputLine(String line){
        Command command = parser.getCommand(line);
        return processCommand(command);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");

        for (String exit : currentRoom.exits.keySet()) {
            System.out.print(exit + " ");
        }

        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private String processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            return "I don't know what you mean...";
        }
        String result = null;
        String commandWord = command.getCommandWord();
        // see https://docs.oracle.com/javase/8/docs/technotes/guides/language/strings-switch.html

        switch(commandWord){
            case "help":
                result = CommandWord.getCommandWords();
                break;
            case "go":
                result = goRoom(command);
                break;
            case "quit":
                result = quit(command);
                break;
            case "look":
                result = currentRoom.getDescription();
                break;
            case "eat":
                result = printEat();
                break;
            case "take":
                result = takeItems(command);
                break;
            case "inventory":
                result = getInventory();
                break;
            case "drop":
                result = dropItems(command);
                break;
            case "dropall":
                result = dropAllItems();

        }

        return result ;
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private String quit(Command command)
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private String printHelp()
    {
    return "You are lost. You are alone. You wander"
            +"\n"
            + "around at the university."
            +"\n"
            +"\n"
            +"Your command words are:"
            +"\n"
            +"   go quit help"
            +"\n";
    }

    // implementations of user commands:
    private String printEat(){
        return "You have eaten now and are not hungry any more"
                + "\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command) {
        if (!command.hasSecondWord()) {
            return "Go where?";
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return "There is no door!";
        } else {
            currentRoom = nextRoom;
            StringBuilder result = new StringBuilder("You are " + currentRoom.getDescription() + "\n");
            result.append("Exits: ");

            for (String exit : currentRoom.exits.keySet()) {
                result.append(exit).append(" ");
            }

            return result.toString() + "\n";
        }
    }

    public void addItem(Items item) {
        inventory.add(item);
    }

    public Items getItem(String itemName) {
        for (Items item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public String getInventory() {
        StringBuilder returnString = new StringBuilder("Inventory:");
        for(Items item : inventory) {
            returnString.append(item.getName()).append(" weight: ").append(item.getWeight()).append("\n");
        }
        returnString.append("\nTotal Weight: ").append(calculateTotalWeight());
        return returnString.toString();
    }

    public int calculateTotalWeight() {
        int totalWeight = 0;
        for (Items item : inventory) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    private String takeItems(Command command) {
        if (!command.hasSecondWord()) {
            return "Take what?";
        }

        String itemName = command.getSecondWord();
        Items nextItem = currentRoom.getItem(itemName);

        if (nextItem == null) {
            return "There is no " + itemName + " in this room!";
        } else if (calculateTotalWeight() + nextItem.getWeight() > maxWeight) {
            return "You can't carry that much weight!";
        } else {
            currentRoom.removeItem(nextItem);
            inventory.add(nextItem);
            return "You have taken " + itemName + ".\n";
        }
    }

    private String dropItems(Command command) {
        if (!command.hasSecondWord()) {
            return "Drop what?";
        }

        String itemName = command.getSecondWord();
        Items nextItem = getItem(itemName);

        if (nextItem == null) {
            return "You don't have " + itemName + " in your inventory!";
        } else {
            inventory.remove(nextItem);
            return "You have dropped " + itemName + ".\n";
        }
    }

    public String dropAllItems() {
        String returnString = "You have dropped all items in your inventory.\n";
        inventory.clear();
        return returnString;
    }


}
