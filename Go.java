public class Go extends Command

{

    public Go(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public String processCommand(Player player) {
        Command command = this;
        Room currentRoom = player.getCurrentRoom();

        if (!command.hasSecondWord()) {
            return "Go where?";
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return "There is no door!";
        } else {
            currentRoom = nextRoom;
            StringBuilder result = new StringBuilder("You are " + player.getCurrentRoom().getDescription() + "\n");
            result.append("Exits: ");

            for (String exit : currentRoom.exits.keySet()) {
                result.append(exit).append(" ");
            }

            return result.toString() + "\n";
        }
    }
}
