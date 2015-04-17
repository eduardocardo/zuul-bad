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
        stacks = new Stack();
    }

    /**
     *Metodo que situa al jugador en una habitacion determinada
     *@param room es la habitacion en la que aparecera el jugador
     */
    public void aparecerEnLaHabitacion(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Metodo que hace que el jugador coma
     */
    public void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }
    
    /**
     * Metodo que da al jugador una informacion sobre la habitacion en la que 
     * se encuentra
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Metodo que desplaza al jugador por las habitaciones por las salidas disponibles
     * @param direccion es la direccion a la que se desplaza el jugador
     */
    
    public void goRoom(String direccion)
    {
        //objeto tipo Room que representa la habitacion a la que se desplaza
        Room nextRoom = null;
        //se guarda la habitacion a la que se desplazaria segun la direccion del parametro
        nextRoom = currentRoom.getExit(direccion);

        if (nextRoom == null) {
            
            System.out.println("There is no door!");
        }
        else {
            stacks.push(currentRoom);
            currentRoom = nextRoom;
            look();
            
        }
    }
    
     /**
     * Metodo que vuelve a la habitacion anterior
     */
    public void backRoom()
    {
        if(!stacks.empty())
        {
            currentRoom =stacks.pop();
            look();
        }
        else
        {
            System.out.println("No puedes volver a atras");
        }
    }
}
