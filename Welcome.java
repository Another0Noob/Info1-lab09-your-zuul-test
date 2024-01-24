public class Welcome extends Command
{
    public Welcome(){
        super(CommandWord.WELCOME,"");
    }

    public Welcome(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    /**
     * Print out the opening message for the player.
     */
    public String processCommand(Player player){

        String result = "";

        result += "\n";
        result += "Welcome to the World of Zuul!";
        result += "\n";
        result += "World of Zuul is a new, incredibly boring adventure game.";
        result += "\n";
        result += "Type 'help' if you need help.";
        result += "\n";
        result += "\n";
        result += player.getCurrentRoom().getDescription();
        result += "Exits: ";


        for (String exit : currentRoom.exits.keySet()) {
            result += exit + " ";
        }

        result += "\n";
        return result;


    }
}
