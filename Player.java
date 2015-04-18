import java.util.Stack;
import java.util.ArrayList;
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
    //indica el peso actual que lleva el jugador
    private float peso;
    //indica el peso maximo que puede llevar el jugador
    private float pesoMax;
    //arrayList que almacena los items que lleva actualmente el jugador
    private ArrayList<Item> items;

    /**
     * Constructor for objects of class Player
     * @param pesoMax es el peso maximo que puede llevar el jugador
     */
    public Player(float pesoMax)
    {
        stacks = new Stack();
        items = new ArrayList<>();
        this.peso = 0;
        this.pesoMax = pesoMax; 
    }
    
    /**
     * Metodo que devuelve el peso actual que lleva el jugador
     * @return el peso en kg que lleva actualmente el jugador
     */
    public float getPeso()
    {
        return peso;
    }
    
    /**
     * Metodo que devuelve la habitacion en la que se encuentra el jugador
     * @return la habitacion en la que se encuentra el jugador
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Metodo que devuelve el peso maximo que puede llevar el jugador
     * @return el peso maximo en kg
     */
    public float getPesoMax()
    {
        return pesoMax;
    }

    /**
     *Metodo que situa al jugador en una habitacion determinada
     *@param room es la habitacion en la que aparecera el jugador
     */
    public void setCurrentRoom(Room room)
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

    /**
     * Metodo que coge un item y lo añade a la coleccion
     * @param item es el nombre del objeto que coge el jugador
     */
    public void take(String item)
    {
        String name = item.toLowerCase();
        //se comprueba si el nombre pasado por parametro coincide con algun item existente
        //en la habitacion
        Item it = currentRoom.getItem(name);
        if(it != null)  //si ese objeto existe en la habitacion
        {
            //se añade el item a la coleccion
            items.add(it);
            //se suma el peso del item al peso que lleva el jugador
            peso += it.getPeso();
            //se elimina de la habitacion ese item
            currentRoom.removeItem(it);
            //se indica que se ha cogido ese item
            System.out.println("Has cogido " + name);
        }
        else
        {
            System.out.println("El item que quieres coger no existe");
        }
    }

    /**
     * Metodo que suelta en la habitacion un item que ya posee el jugador
     * @param item es el nombre del objeto que quiere soltar
     */
    public void drop(String nameItem)
    {
        String name = nameItem.toLowerCase();
        //primero se comprueba que el jugador tiene ese item en su inventario
        Item item = null;
        boolean encontrado = false;
        int i = 0;
        while(i < items.size() && !encontrado)
        {
            if(items.get(i).getNombre().equals(name))
            {
                //si nombre pasado por parametro coincide con el nombre de algun item
                //que tiene el jugador en su inventario
                item = items.get(i);
                encontrado = true;
            }
            i++;
        }
        if(encontrado) //tiene el objeto en el inventario
        {
            //se elimina el objeto del inventario del jugador
            items.remove(item);
            //se resta el peso del objeto del peso que lleva el jugador
            peso -= item.getPeso();
            //se añade este objeto a la habitacion actual
            currentRoom.addItem(item);
            //se indica que ha soltado el item
            System.out.println("Has soltado " + name);
        }
        else  //no tiene ese objeto en su inventario
        {
            System.out.println("No puedes tirar un objeto que no tienes");
        }
    }
}
