public class Eat extends Command
{

    public Eat(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    public String processCommand(Player player){
        return "You have eaten now and are not hungry any more" + "\n";
    }
}
