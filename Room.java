import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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

    //arrayList que representa los items que puede haber en la habitacion
    private ArrayList<Item> itemsRoom;
    //arrayList que almacena los pnj que puede haber en la habitacion
    private ArrayList<Pnj> pnjs;

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
        itemsRoom = new ArrayList();
        pnjs = new ArrayList();
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
        String description = getDescription();
        if(itemsRoom.size() > 0)
        {
            for(Item it : itemsRoom)
            {
                description +=  "\n" + "hay un objeto" + " " + it.toString();
            }
        }
        if(pnjs.size() > 0)
        {
            for(Pnj pnj : pnjs)
            {
                description += "\n" + "en la habitacion ves " + " " + pnj.toString();
            }
        }
        description +=  "\n" + getExitString();
        return description;
    }

    /**
     * Metodo que a�ade un objeto de tipo Item a la coleccion de objetos en la habitacion
     * @param item es el objeto que se a�ade al arrayList
     */
    public void addItem(Item it)
    {
        itemsRoom.add(it);
    }
    
    /**
     * Metodo que elimina un item de la coleccion
     * @param item es el objeto a eliminar
     */
    public void removeItem(Item it)
    {
        itemsRoom.remove(it);
    }
    
    /**
     * Metodo que al pasarle por parametro el id de un item te devuelve ese item
     * @param idItem es el numero identificador del item que se busca
     * @return devuelve el item correspondiente a esa id si se encuentra en la habitacion,si no esta devuelve null
     */
    public Item getItem(int idItem)
    {
        Item it = null;
        //se considera que no se ha encontrado el item
        boolean encontrado = false;
        int i = 0;
        
        while(i <itemsRoom.size() && !encontrado)
        {
            //si el nombre pasado por parametro coincide con alguno de los existentes en la habitacion
            if(itemsRoom.get(i).getId() == idItem)
            {
                //se guarda ese objeto
                it = itemsRoom.get(i);
                encontrado = true;
            }
            i++;
        }
        return it;
    }
    
    /**
     * Metodo que a�ade un objeto tipo Pnj a la habitacion
     * @param pnj es el pnj que se a�ade a la habitacion
     */
    public void addPnj(Pnj pnj)
    {
        pnjs.add(pnj);
    }
    
    /**
     * Metodo que elimina un pnj de la habitacion
     * @param pnj es el pnj que se elimina de la habitacion
     */
    public void removePnj(Pnj pnj)
    {
        pnjs.remove(pnj);
    }
    
    /**
     * Metodo que al pasarle el id de un pnj te devuelve es pnj
     * @param id es el numero identificativo del pnj
     * @return si esa id se encuentra en la habitacion devuelve el pnj,si no devuelve null
     */
    public Pnj getPnj(int id)
    {
        Pnj pnj = null;
        int i = 0;
        //se considera que no se ha encotrado el pnj
        boolean encontrado = false;
        while( i < pnjs.size() && !encontrado)
        {
            //si el id pasado por parametro coincide con alguno existente en la habitacion
            if(pnjs.get(i).getId() == id)
            {
                pnj = pnjs.get(i);
                encontrado = true;
            }
            i++;
        }
        return pnj;
    }
}
