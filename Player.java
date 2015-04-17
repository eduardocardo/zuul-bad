import java.util.Stack;
/**
 * Esta clase representa un jugador que entra en la torre
 * 
 * @author (Eduardo) 
 * @version (1.0)
 */
public class Player
{
    //representa la habitacion en la que esta el jugador
    private Room currentRoom;
    //coleccion que almacena las habitaciones por las que ha pasado el jugador
    private Stack<Room>stacks;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        
    }

    /**
     *Metodo que situa al jugador en una habitacion determinada
     *@param room es la habitacion en la que aparecera el jugador
     */
    public void aparecerEnLaHabitacion(Room room)
    {
        currentRoom = room;
    }
    
    
}
