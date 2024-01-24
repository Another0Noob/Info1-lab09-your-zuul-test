public class Look extends Command
{

    public Look(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    public String processCommand(Player player){
        return currentRoom.getDescription();
    }
}
