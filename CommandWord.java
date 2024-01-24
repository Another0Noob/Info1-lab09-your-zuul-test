/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.LinkedHashMap;
import java.util.List;

public enum CommandWord
{

    GO("go"),

    QUIT("quit"),

    HELP("help"),

    WELCOME("welcome"),

    UNKNOWN("unknown"),

    LOOK("look"),

    EAT("eat"),

    PANIC("panic"),

    TAKE("take"),

    DROP("drop"),

    INVENTORY("inventory");

    private static Map<CommandWord, BiFunction<CommandWord,String,Command>> commandFactories = new LinkedHashMap<>();

    static {
        commandFactories.put(GO, (w1,w2)-> new Go(w1,w2));
        commandFactories.put(QUIT, (w1,w2)-> new Quit(w1,w2));
        commandFactories.put(HELP, (w1,w2)-> new Help(w1,w2));
        commandFactories.put(WELCOME, (w1,w2)-> new Welcome(w1,w2));
        commandFactories.put(UNKNOWN, (w1,w2)-> new Unknown(w1,w2));
        commandFactories.put(LOOK, (w1,w2)-> new Look(w1,w2));
        commandFactories.put(EAT, (w1,w2)-> new Eat(w1,w2));
        commandFactories.put(PANIC, (w1,w2)-> new Panic(w1,w2));
        commandFactories.put(TAKE, (w1,w2)-> new Take(w1,w2));
        commandFactories.put(DROP, (w1,w2)-> new Drop(w1,w2));
        commandFactories.put(INVENTORY, (w1,w2)-> new Inventory(w1,w2));
    }

    private String word;
    private CommandWord(String word){
        this.word = word;
    }

    @Override
    public String toString(){
        return word;
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public static boolean isCommand(String aString)
    {
        CommandWord[] validCommands = CommandWord.class.getEnumConstants();
        for(CommandWord cw: validCommands) {
            if(cw.toString().equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public static String getCommandWords(){
        return "You are lost. You are alone. You wander"
                +"\n"
                + "around at the university."
                +"\n"
                +"\n"
                +"Your command words are:"
                +"\n"
                +printCommands()
                +"\n";
    }

    public static String printCommands() {
        List<String> commands = Arrays.stream(values())
                .map(CommandWord::toString)
                .toList();
        return String.join(", ", commands);
    }

    public static Command buildCommand(String firstWord, String secondWord){
        CommandWord key = forString(firstWord);
        return commandFactories.get(key).apply(key, secondWord);
    }

public static CommandWord forString(String commandWord){
        for(CommandWord cw: values()) {
            if(cw.toString().equals(commandWord))
                return cw;
        }
        return UNKNOWN;
    }

}
