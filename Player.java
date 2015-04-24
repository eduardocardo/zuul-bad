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
        //representa la habitacion a la que se desplaza
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
    public void take(int idItem)
    {

        //primero se comprueba si el objeto que se quiere coger existe en la habitacion
        Item item = currentRoom.getItem(idItem);
        if(item != null)
        {
            //se comprueba si se supera el limite de peso maximo y si el item se puede coger

            //no supera el limite de peso maximo y el objeto se puede coger
            if((item.getPeso() + peso) <= pesoMax && item.getCoger()) 
            {
                //se añade el item a la coleccion
                items.add(item);
                //se suma el peso del item al peso que lleva el jugador
                peso += item.getPeso();
                //se elimina de la habitacion ese item
                currentRoom.removeItem(item);
                //se indica que se ha cogido ese item
                System.out.println("Has cogido " + item.getNombre());
            }
            //el objeto se puede coger pero supera el limite de peso maximo
            else if((item.getPeso() + peso) > pesoMax && item.getCoger())
            {
                System.out.println("No puedes coger ese objeto porque superas tu limite de peso maximo");
            }
            else
            {
                System.out.println("Ese item no se puede coger");
            }

        }
        else
        {
            System.out.println("El objeto que quieres coger no existe");
        }

    }

    /**
     * Metodo que suelta en la habitacion un item que ya posee el jugador
     * @param item es el nombre del objeto que quiere soltar
     */
    public void drop(int idItem)
    {

        //primero se comprueba que el jugador tiene ese item en su inventario
        Item item = null;
        boolean encontrado = false;
        int i = 0;
        while(i < items.size() && !encontrado)
        {
            if(items.get(i).getId() == idItem)
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
            System.out.println("Has soltado " + item.getNombre());
        }
        else  //no tiene ese objeto en su inventario
        {
            System.out.println("No puedes tirar un objeto que no tienes");
        }
    }

    /**
     * Metodo que muestra informacion sobre los objetos que tiene actualmente el jugador
     */
    public void infoItems()
    {
        System.out.println("En tu inventario tienes :");
        if(items.size() > 0)
        {
            for(Item item : items)
            {
                System.out.println(item);
            }
        }
        else
        {
            System.out.println("No tienes ningun item");
        }
    }
}
