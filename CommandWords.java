import java.util.HashMap;
import java.util.Set;
import java.util.Map;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{

    private HashMap<String,Option> comandos;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        comandos = new HashMap<>();
        comandos.put("go",Option.GO);
        comandos.put("quit",Option.QUIT);
        comandos.put("help",Option.HELP);
        comandos.put("look",Option.LOOK);
        comandos.put("eat",Option.EAT);
        comandos.put("back",Option.BACK);
        comandos.put("take",Option.TAKE);
        comandos.put("drop",Option.DROP);
        comandos.put("items",Option.ITEMS);
        comandos.put("unknown",Option.UNKNOWN);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        Set<String> commands = comandos.keySet();
        for(String com:commands) {
            if(com.equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

      /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        Set<String> commands = comandos.keySet();
        String allCommands =" ";
        for(String com:commands)
        {
            allCommands += com + " ";
        }
        System.out.println(allCommands);
    }
}
