
/**
 * Esta clase representa un objeto que puede encontrarse en una habitacion
 * 
 * @author (Eduardo) 
 * @version (1.0)
 */
public class Item
{
    //indica el nombre del item
    private String nombre;
    //es una descripcion del objeto
    private String descripcion;
    //indica el peso del item en kg
    private float peso;
    //indica si el objeto se puede coger
    private boolean coger;
    //es el numero identificador del objeto
    private int id;
    //
    private static int currentId = 1;
    //indica si el item se puede beber o no
    private boolean sePuedeBeber;
    //cte que indica la id del item que finaliza el juego
    public static final int ID_END_GAME = 15;

    /**
     *  Constructor de la clase Item
     *  @param nombre es el nombre del objeto
     *  @param descrip es la decripcion del objeto
     *  @param peso es el peso que tiene el objeto,por defecto 1 kg
     */
    public Item(String nombre,String descrip,float peso,boolean coger,int id,boolean sePuedeBeber)
    {
        this.nombre = nombre;
        this.descripcion = descrip;
        if(peso >0)
        {
            this.peso = peso;
        }
        else //por defecto
        {
            peso = 1;
        }
        this.coger = coger;
        this.id = currentId;
        currentId++;
        this.sePuedeBeber = sePuedeBeber;
    }

    /**
     * Metodo que devuelve el nombre del item
     * @return nombre del item
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Metodo que devuelve la descripcion del item
     * @return la descripcion del item
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Metodo que devuelve el peso del item
     * return el peso del item en kg
     */
    public float getPeso()
    {
        return peso;
    }
    
    /**
     * Metodo que devuelve se puede coger o no
     * @return true si el objeto se puede coger,false si no se puede
     */
    public boolean getCoger()
    {
        return coger;
    }

    /**
     * Metodo muestra informacion sobre el item
     */
    public String toString()
    {
        return "(" + id + ")" + nombre + " " + descripcion + " cuyo peso es " + peso;
    }
    /**
     * Metodo que devuelve el id del objeto
     * @return el id del objeto
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Metodo que devuelve si un item se puede beber o no
     * @return true si se puede beber,false si no se puede
     */
    public boolean sePuedeBeber()
    {
        return sePuedeBeber;
    }
}
