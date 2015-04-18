
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

    /**
     *  Constructor de la clase Item
     *  @param nombre es el nombre del objeto
     *  @param descrip es la decripcion del objeto
     *  @param peso es el peso que tiene el objeto,por defecto 1 kg
     */
    public Item(String nombre,String descrip,float peso)
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
     * Metodo muestra informacion sobre el item
     */
    public String toString()
    {
        return nombre + " " + descripcion + " cuyo peso es " + peso;
    }
}
