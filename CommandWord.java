public enum CommandWord
{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    LOOK("look"),
    EAT("eat"); // New command word

    private String word;

    private CommandWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public static boolean isCommand(String aString) {
        CommandWord[] validCommands = values();
        for (CommandWord cw : validCommands) {
            if (cw.toString().equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}
