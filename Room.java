import java.util.HashMap;
/**
 * 
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;

    private HashMap<String,Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String,Room>();

    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Metodo que devuelve un objeto tipo Room a partir de una cadena que representa
     * una direccion
     * @param direccion es la direccion a la que se desplaza
     * @return el objeto tipo Room asociado a esa salida o null si no hay ninguna
     */
    public Room getExit(String direccion)
    {
        Room exit = null;
        switch(direccion)
        {
            case "north":
            exit = exits.get("north");
            break;
            case "east":
            exit = exits.get("east");
            break;
            case "south":
            exit = exits.get("south");
            break;
            case "west":
            exit = exits.get("west");
            break;
            case "southEast":
            exit = exits.get("southEast");
            break;
            case "northWest":
            exit = exits.get("northWest");
            break;
        }
        return exit;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String exit ="Exits :";
        if(exits.get("north") != null)
        {
            exit += "north" + " ";
        }
        if(exits.get("east")!= null)
        {
            exit += "east" + " ";
        }
        if(exits.get("south") != null)
        {
            exit += "south" + " ";
        }
        if(exits.get("west") != null)
        {
            exit += "west" + " ";
        }
        if(exits.get("southEast") != null)
        {
            exit += "southEast" + " ";
        }
        if(exits.get("northWest") != null)
        {
            exit += "northWest";
        }
        return exit;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction,neighbor);
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String description = getDescription() + "\n" + getExitString();
        return description;
    }
}
