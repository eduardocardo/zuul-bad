import java.util.HashMap;
import java.util.Set;
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
    //representa un objeto existente en la habitacion
    private String item;
    //indica el peso de un objeto existente en la habitacion
    private float pesoItem;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description,String objeto,float peso) 
    {
        this.description = description;
        exits = new HashMap<String,Room>();
        item =objeto;
        pesoItem = peso;

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

        return exits.get(direccion);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        Set<String> nombresDirecciones = exits.keySet();
        String posiblesSalidas ="Exits: ";
        for(String salidas : nombresDirecciones)
        {
            posiblesSalidas += salidas + " ";
        }
        return posiblesSalidas;

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
        String description = getDescription() + "\n" + getInfoItem() + "\n" + getExitString();
        return description;
    }

    /**
     * Metodo que muestra informacion de los objetos existentes en la habitacion
     */
    public String getInfoItem()
    {
        String info = " ";
        if(item != null)
        {
             info = "En la habitacion ves " + item + " cuyo peso es " + pesoItem;
        }
        return info;
    }
}
